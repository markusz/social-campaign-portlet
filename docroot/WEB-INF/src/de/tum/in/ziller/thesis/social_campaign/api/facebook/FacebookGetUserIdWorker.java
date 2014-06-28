package de.tum.in.ziller.thesis.social_campaign.api.facebook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

/**
 * Diese Klasse ist als Thread realisiert und kümmert sich um die Verbindung zwischen Portalkonto und Facebookkonto
 * @author Markus
 *
 */
public class FacebookGetUserIdWorker extends Thread {
	
	private Log		_log	= LogFactory.getLog(FacebookGetUserIdWorker.class);
	
	private String	accessToken;
	private Long userId;

	public FacebookGetUserIdWorker(String accessToken, Long userId) {
		this.accessToken = accessToken;
		this.userId = userId;
	}

	/**
	 * Startet den Thread und ruft das dem Access Token zugeordnete Konto auf um die Id zu erhalten, welche danach im betreffenden Konto gespeichert wird.
	 */
	@Override
	public void run() {
		
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		
		Long facebookUserId = Long.parseLong(fbClient.fetchObject("me", com.restfb.types.User.class).getId());
		
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			user.setFacebookId(facebookUserId);
			UserLocalServiceUtil.updateUser(user);
		
		}
		catch (SystemException e) {
			_log.error(e);
		}
		catch (PortalException e) {
			_log.error(e);
		}
	}

}
