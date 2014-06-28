package de.tum.in.ziller.thesis.social_campaign.portlets.events;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.WindowState;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

import de.tum.in.ziller.thesis.social_campaign.api.facebook.FacebookEventCreationWorker;
import de.tum.in.ziller.thesis.social_campaign.api.facebook.FacebookEventDeletionWorker;
import de.tum.in.ziller.thesis.social_campaign.api.facebook.FacebookEventParticipationWorker;
import de.tum.in.ziller.thesis.social_campaign.api.google.GoogleCalendarEventCreationWorker;
import de.tum.in.ziller.thesis.social_campaign.api.google.GoogleCalendarEventDeletionWorker;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.portlets.AdvancedMVCPortlet;
import de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.SocialSettingsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAttendencePK;
import de.tum.in.ziller.thesis.social_campaign.util.Constants;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.EventUtil;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.GeocoderWorker;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.MessageBoardEventUtil;

public class EventsGenericPortlet extends AdvancedMVCPortlet {

	/**
	 * Meldet einen Nutzer von einer Veranstaltung an. Stößt außerdem die
	 * betreffenden API-Aufrufe an
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version
	 * @time 07.11.2011
	 */
	public void joinEvent(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		Long eventId = ParamUtil.getLong(req, Constants.EVENT_ID);
		Long userId = PortalUtil.getUserId(req);

		EventAttendence eventAttendence = EventAttendenceLocalServiceUtil.createEventAttendence(new EventAttendencePK(eventId, userId));
		SocialSettings socialSettings = null;

		try {

			socialSettings = SocialSettingsLocalServiceUtil.getSocialSettings(userId);
			EventAttendenceLocalServiceUtil.getEventAttendence(eventAttendence.getPrimaryKey());
			EventAttendence updatedEventAttendence = EventAttendenceLocalServiceUtil.updateEventAttendence(eventAttendence);

			if (socialSettings != null && socialSettings.getAutoPublishOnFacebook()) {
				new FacebookEventParticipationWorker(updatedEventAttendence, Constants.FACEBOOK_EVENT_ATTENDING).run();
			}
		}
		catch (Exception e) {
			try {
				EventAttendence createdEventAttendence = EventAttendenceLocalServiceUtil.addEventAttendence(eventAttendence);

				if (socialSettings != null && socialSettings.getAutoPublishOnFacebook()) {
					new FacebookEventParticipationWorker(createdEventAttendence, Constants.FACEBOOK_EVENT_ATTENDING).run();
				}
				if (socialSettings != null && socialSettings.getAutoPublishOnGoogle()) {
					new GoogleCalendarEventCreationWorker(createdEventAttendence).run();
				}
			}
			catch (SystemException e1) {

			}
		}
		finally {
			res.setWindowState(WindowState.NORMAL);
		}

	}

	/**
	 * Meldet einen Nutzer von einer Veranstaltung ab. Stößt außerdem die
	 * betreffenden API-Aufrufe an
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version
	 * @time 07.11.2011
	 */
	public void unsubscribeFromEvent(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		Long eventId = ParamUtil.getLong(req, Constants.EVENT_ID);
		Long userId = PortalUtil.getUserId(req);

		try {
			EventAttendence eventAttendence = EventAttendenceLocalServiceUtil.getEventAttendence(new EventAttendencePK(eventId, userId));
			SocialSettings socialSettings = SocialSettingsLocalServiceUtil.getSocialSettings(userId);

			EventAttendenceLocalServiceUtil.deleteEventAttendence(new EventAttendencePK(eventId, userId));
			if (socialSettings != null && socialSettings.getAutoPublishOnFacebook()) {
				new FacebookEventParticipationWorker(eventAttendence, Constants.FACEBOOK_EVENT_DECLINE).run();
			}
			if (socialSettings != null && socialSettings.getAutoPublishOnGoogle()) {
				if (eventAttendence.getCorrespondingGoogleEvent() != null && eventAttendence.getCorrespondingGoogleEvent() != "") {
					new GoogleCalendarEventDeletionWorker(eventAttendence).run();
				}
			}
		}
		catch (PortalException e) {

		}
		catch (SystemException e) {

		}
		finally {
			res.setWindowState(WindowState.NORMAL);
		}

	}

	/**
	 * Erstellt ein neues Objekt ein fügt es zur Datenbank hinzu. Stößt außerdem
	 * die betreffenden API-Aufrufe an
	 * 
	 * @param req
	 * @param res
	 * @param official
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version
	 * @time 07.11.2011
	 */
	public void addEvent(ActionRequest req, ActionResponse res, boolean official) throws IOException, PortletException {

		try {
			Map<Long, Integer> assistenceRequests = EventUtil.extractAssistenceRelatedDataFromRequest(req);
			Event createdEvent = EventLocalServiceUtil.addEvent(req, official);

			if (createdEvent != null) {

				if ((!official && ParamUtil.getBoolean(req, "autoPublishOnFacebook", false)) || official) {
					_log.info("publishing event to facebook");
					new FacebookEventCreationWorker(createdEvent).run();
				}
				if ((!official && ParamUtil.getBoolean(req, "autoPublishOnGoogle", false)) || official) {
					_log.info("publishing event to google");
					new GoogleCalendarEventCreationWorker(createdEvent).run();
				}

				MessageBoardEventUtil.createSubboardForEvent(createdEvent);
				EventUtil.addAssistenceRequestToDB(assistenceRequests, createdEvent.getEventId());
				new GeocoderWorker(createdEvent).run();
			}
		}
		catch (PortalException e) {

		}
		catch (SystemException e) {
			e.printStackTrace();
		}
		finally {
			res.setWindowState(WindowState.NORMAL);
		}

	}

	/**
	 * Löscht ein Event
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version
	 * @time 07.11.2011
	 */
	public void deleteEvent(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		Long eventId = ParamUtil.getLong(req, Constants.EVENT_ID);
		
		

		try {
			Event event = EventLocalServiceUtil.getEvent(eventId);
			List<EventAttendence> att = EventAttendenceLocalServiceUtil.getAllEventAttendencesForEvent(eventId);

			EventLocalServiceUtil.deleteEvent(eventId);

			new FacebookEventDeletionWorker(event).run();
			new GoogleCalendarEventDeletionWorker(event).run();
			
			
			for (EventAttendence eventAttendence : att) {
				new GoogleCalendarEventDeletionWorker(eventAttendence).run();
			}
			
		}
		catch (PortalException e) {
		}
		catch (SystemException e) {

		}
		finally {
			res.setWindowState(WindowState.NORMAL);
		}
	}

	/**
	 * Ändert den Status eines Events auf "offiziell"
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version
	 * @time 07.11.2011
	 */
	public void makeEventOfficial(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		Long eventId = ParamUtil.getLong(req, Constants.EVENT_ID);

		try {
			Event event = EventLocalServiceUtil.getEvent(eventId);
			event.setIsOfficial(true);
			EventLocalServiceUtil.updateEvent(event);
		}
		catch (PortalException e) {
		}
		catch (SystemException e) {

		}
		finally {
			res.setWindowState(WindowState.NORMAL);
		}
	}

}
