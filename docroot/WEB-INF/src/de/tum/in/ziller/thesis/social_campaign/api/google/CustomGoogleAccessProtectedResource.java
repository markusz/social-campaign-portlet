package de.tum.in.ziller.thesis.social_campaign.api.google;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleRefreshTokenGrant;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials;
import de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalServiceUtil;

/**
 * Modifizierte GoogleAccessProtectedResource welche es ermöglicht, das Ablaufdatum des Tokens auch zu speichern
 * @author Markus
 *
 */
public class CustomGoogleAccessProtectedResource extends GoogleAccessProtectedResource {

	private Long	userId;
	private Date	expiresIn;
	private Log _log = LogFactory.getLog(CustomGoogleAccessProtectedResource.class);

	public CustomGoogleAccessProtectedResource(String accessToken,
		      HttpTransport transport,
		      JsonFactory jsonFactory,
		      String clientId,
		      String clientSecret,
		      String refreshToken, Long userId) {
		super(accessToken, transport, jsonFactory, clientId, clientSecret, refreshToken);
		this.userId = userId;
	}
	
	

	

	@Override
	protected synchronized boolean executeRefreshToken() throws IOException {
		
		GoogleCredentials googleCredentials;
		try {
			googleCredentials = GoogleCredentialsLocalServiceUtil.getGoogleCredentials(userId);
		
		
		if (getRefreshToken() != null && googleCredentials.getTokenExpirationDate().before(new Date())) {
			GoogleRefreshTokenGrant request = new GoogleRefreshTokenGrant(getTransport(), getJsonFactory(), getClientId(), getClientSecret(), getRefreshToken());

			AccessTokenResponse response = request.execute();
			_log.info("refreshedToken");
			setExpirationDate(response.expiresIn);
			setAccessToken(response.accessToken);
			return true;
		}
		return false;
		
		}
		catch (PortalException e) {
			return false;
		}
		catch (SystemException e) {
			return false;
		}
		
	}

	private void setExpirationDate(Long expiresIn) {
		this.expiresIn = new Date(System.currentTimeMillis() + expiresIn * 1000);

	}

	/**
	 * called by setAccessToken()
	 */
	@Override
	protected synchronized void onAccessToken(String accessToken) {
		try {
			GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.getGoogleCredentials(this.userId);
			googleCredentials.setTokenExpirationDate(expiresIn);
			googleCredentials.setAccessToken(accessToken);
			GoogleCredentialsLocalServiceUtil.updateGoogleCredentials(googleCredentials);

		}
		catch (PortalException e) {

		}
		catch (SystemException e) {

		}
	}

}
