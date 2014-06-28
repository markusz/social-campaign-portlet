package de.tum.in.ziller.thesis.social_campaign.api.facebook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchRequest.BatchRequestBuilder;

import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalServiceUtil;

/**
 * Klasse die einen Thream implementiert, welcher die Teilnahme an einer Veranstaltung im System auf Facebook abbildet.
 * @author Markus
 *
 */
public class FacebookEventParticipationWorker extends Thread {
	
	private Log		_log	= LogFactory.getLog(FacebookEventParticipationWorker.class);
	
	private EventAttendence eventAttendence;
	private String action;
	
	public FacebookEventParticipationWorker(EventAttendence eventAttendence, String action){
		this.eventAttendence = eventAttendence;
		this.action = action;
	}

	@Override
	public void run() {
		
		try {
			String userAccessToken = FacebookCredentialsLocalServiceUtil.getFacebookCredentials(eventAttendence.getUserProfileId()).getAccessToken();
			String facebookEventId = EventLocalServiceUtil.getEvent(eventAttendence.getEventId()).getFacebookEventId();
			
			FacebookClient fbClient = new DefaultFacebookClient(userAccessToken);
			
			BatchRequest eventBatch = new BatchRequestBuilder(facebookEventId+"/"+action).method("POST").build();
			fbClient.executeBatch(eventBatch);
		}
		catch (PortalException e) {
			_log.error(e);
		}
		catch (SystemException e) {
			_log.error(e);
		}
		
		
		
		
	}
	
}
