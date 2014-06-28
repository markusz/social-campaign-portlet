package de.tum.in.ziller.thesis.social_campaign.api.facebook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.api.google.GoogleCalendarEventCreationWorker;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalServiceUtil;

/**
 * Klasse, die einen Thread implementiert, der aus einem Objekt vom Typ Event ein Event auf Facebook erstellt, und dessen Id in die Datenbank schreibt.
 * @author Markus
 *
 */
public class FacebookEventCreationWorker extends Thread {

	private Log		_log	= LogFactory.getLog(FacebookEventCreationWorker.class);
	private Event	event;

	public FacebookEventCreationWorker(Event event) {
		this.event = event;
	}

	@Override
	public void run() {
		
		FacebookClient fbClient = null;
		
		if (event.isIsOfficial()) {
			fbClient = new DefaultFacebookClient(OAuth.appAccessToken);
		} else {
			try {
				String accessToken = FacebookCredentialsLocalServiceUtil.getFacebookCredentials(event.getCreatorId()).getAccessToken();
				fbClient = new DefaultFacebookClient(accessToken);
			}
			catch (PortalException e) {
			}
			catch (SystemException e) {
			}
		}
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String start = df2.format(event.getStartingTime());
		String end = df2.format(event.getEndingTime());

		try {
			String postURL  = (event.isIsOfficial() ? ""+OAuth.facebookClientId : "me").concat("/events");
			FacebookType publishEventResponse = fbClient.publish(postURL, FacebookType.class, Parameter.with("name", event.getTitle()), Parameter.with("start_time", start), Parameter.with("end_time", end), Parameter.with("description", event.getDescription()), Parameter.with("location", event.getLocationAsString()),
					Parameter.with("privacy", "OPEN"));
			event.setFacebookEventId(publishEventResponse.getId());
			EventLocalServiceUtil.updateEvent(event);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

}
