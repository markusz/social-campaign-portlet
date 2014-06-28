package de.tum.in.ziller.thesis.social_campaign.api.facebook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchRequest.BatchRequestBuilder;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.model.Event;


/**
 * Klasse die einen Thread implementiert, welcher das einem Event zugeordnete Facebook-Event löscht.
 * @author Markus
 *
 */
public class FacebookEventDeletionWorker extends Thread {

	private Log		_log	= LogFactory.getLog(FacebookEventCreationWorker.class);
	
	private Event	event;

	public FacebookEventDeletionWorker(Event event) {
		this.event = event;
	}

	@Override
	public void run() {
		
		if(event.getFacebookEventId() != null && !event.getFacebookEventId().equalsIgnoreCase("") && event.getIsOfficial()){
			FacebookClient fbClient = new DefaultFacebookClient(OAuth.appAccessToken);
			
			BatchRequest eventBatch = new BatchRequestBuilder(event.getFacebookEventId()).method("DELETE").build();
			fbClient.executeBatch(eventBatch);
			
		}


	}

}
