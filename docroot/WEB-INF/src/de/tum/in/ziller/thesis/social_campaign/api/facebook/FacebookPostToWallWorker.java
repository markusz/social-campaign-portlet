package de.tum.in.ziller.thesis.social_campaign.api.facebook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalServiceUtil;

public class FacebookPostToWallWorker extends Thread {
	
	
	private Log		_log	= LogFactory.getLog(FacebookPostToWallWorker.class);
	
	String messageToPost;
	Long userId;
	
	public FacebookPostToWallWorker(String message, Long userId){
		this.messageToPost = message;
		this.userId = userId;
	}
	
	@Override
	public void run() {
		
		
		try {
			String accessToken = FacebookCredentialsLocalServiceUtil.getFacebookCredentials(userId).getAccessToken();
			
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
			
			facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", messageToPost));
			
		}
		catch (PortalException e) {
			_log.error(e);
		}
		catch (SystemException e) {
			_log.error(e);
		}
		catch (com.restfb.exception.FacebookOAuthException e) {
			_log.error(e);
		}


	}

}
