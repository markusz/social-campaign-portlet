package de.tum.in.ziller.thesis.social_campaign.portlets.support.assist;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

import de.tum.in.ziller.thesis.social_campaign.api.facebook.FacebookPostToWallWorker;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorSettingAssistenceRequestsException;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorSettingEventAssistenceExcpetion;
import de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence;
import de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest;
import de.tum.in.ziller.thesis.social_campaign.model.EventAssistence;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.portlets.AdvancedMVCPortlet;
import de.tum.in.ziller.thesis.social_campaign.service.AnonymAssistenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.EventAssistenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.SocialSettingsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.AnonymAssistencePK;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.AssistenceRequestPK;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAssistencePK;
import de.tum.in.ziller.thesis.social_campaign.util.Constants;

/**
 * Portlet implementation class FindAssistancePortlet
 */
public class FindAssistancePortlet extends AdvancedMVCPortlet {

	public void changeRequests(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		System.out.println("increase");
		try {
			changeRequests(req);
		}
		catch (ErrorSettingAssistenceRequestsException e) {

		}
		finally {
			res.setRenderParameter(Constants.TODO, Constants.SHOW_REQUIRED_ASSISTENCE_FOR_EVENT);
			res.setRenderParameter(Constants.EVENT_ID, String.valueOf(ParamUtil.getLong(req, "eventId")));

		}

	}

	/**
	 * Ändert die bestehenden Hilfsgesuche für ein bestimmtes Event und eine bestimmte Rolle.
	 * @param req
	 * @throws ErrorSettingAssistenceRequestsException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	private static void changeRequests(ActionRequest req) throws ErrorSettingAssistenceRequestsException {

		Long eventId = ParamUtil.getLong(req, "eventId");
		Long roleId = ParamUtil.getLong(req, "roleId");

		try {
			Integer delta = ParamUtil.getInteger(req, roleId + "_amount");

			AssistenceRequest assRequest = AssistenceRequestLocalServiceUtil.getAssistenceRequest(new AssistenceRequestPK(eventId, roleId));

			Integer current = assRequest.getQuantity();

			if (ParamUtil.getInteger(req, roleId + "_manipulate") == 1) {
				assRequest.setQuantity(current + delta);
			} else {
				if (ParamUtil.getInteger(req, roleId + "_manipulate") == 2)
					assRequest.setQuantity(current - delta < 0 ? 0 : current - delta);
			}

			AssistenceRequestLocalServiceUtil.updateAssistenceRequest(assRequest);
		}
		catch (PortalException e) {
			throw new ErrorSettingAssistenceRequestsException();
		}
		catch (SystemException e) {
			throw new ErrorSettingAssistenceRequestsException();
		}

	}

	/**
	 * Methode die die Teilnahme an einem Event ändert. Ein Aufruf bei Teilnahme an einem Event sagt diese also ab und vice versa.
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public void toggleSupport(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		long eventId = ParamUtil.getLong(req, "eventId");
		long roleId = ParamUtil.getLong(req, "roleId");
		long userId = PortalUtil.getUserId(req);

		try {
			EventAssistence eventAssistence = EventAssistenceLocalServiceUtil.getEventAssistence(new EventAssistencePK(eventId, roleId, userId));
			EventAssistenceLocalServiceUtil.deleteEventAssistence(eventAssistence);
		}
		catch (PortalException e) {
			EventAssistence eventAssistence = EventAssistenceLocalServiceUtil.createEventAssistence(new EventAssistencePK(eventId, roleId, userId));
			try {
				EventAssistenceLocalServiceUtil.addEventAssistence(eventAssistence);
				SocialSettings settings = null;
				try {
					settings = SocialSettingsLocalServiceUtil.getSocialSettings(userId);
				}
				catch (PortalException e1) {
					// fail silenty
				}
				if (settings != null && settings.getAutoPublishOnFacebook()) {
					new FacebookPostToWallWorker("Ich supporte die Social Campaign. Wenn du Lust hast, schau doch auch mal unter http://virtfortiss11.informatik.tu-muenchen.de/web/social-campaign/ vorbei.", userId).run();
				}
			}
			catch (SystemException e1) {

			}
		}
		catch (SystemException e) {

		}
		finally {
			res.setRenderParameter(Constants.TODO, Constants.SHOW_REQUIRED_ASSISTENCE_FOR_EVENT);
			res.setRenderParameter(Constants.EVENT_ID, String.valueOf(ParamUtil.getLong(req, "eventId")));
		}
	}
}
