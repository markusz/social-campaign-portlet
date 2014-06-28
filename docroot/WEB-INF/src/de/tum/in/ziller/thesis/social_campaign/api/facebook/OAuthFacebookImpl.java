package de.tum.in.ziller.thesis.social_campaign.api.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import com.liferay.portal.model.BaseModel;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorReceivingAccessTokenException;
import de.tum.in.ziller.thesis.social_campaign.model.FacebookCredentials;
import de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalServiceUtil;

public class OAuthFacebookImpl implements OAuth {

	/**
	 * Implementiert die benötigte Methode um ein Authorization Token gegen ein AccessToken einzutauschen.
	 */
	@Override
	public BaseModel<?> aquireAccessToken(String authorizationCode, Long userId) throws ErrorReceivingAccessTokenException {

		try {
	
			URL url = new URI("https", "graph.facebook.com", "/oauth/access_token", "client_id=" + facebookClientId + "&redirect_uri=" + facebookRedirectURI + "&client_secret=" + facebookClientSecret + "&code=" + authorizationCode, null).toURL();
			
			String result = "";
			String line = "";
			String accessToken = null;
			Integer expires = null;

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((line = reader.readLine()) != null) {
				result = result.concat(line);
			}


			String[] parameters = result.split("&");
			for (String string : parameters) {
				String[] keyvalues = string.split("=");
				if (keyvalues[0].equalsIgnoreCase("access_token")) {
					accessToken = keyvalues[1];
				}

				if (keyvalues[0].equalsIgnoreCase("expires")) {
					expires = Integer.valueOf(keyvalues[1]);
				}
			}

			if (accessToken != null) {
				FacebookCredentials facebookCredentials = FacebookCredentialsLocalServiceUtil.createFacebookCredentials(userId);
				facebookCredentials.setAccessToken(accessToken);
				if (expires != null) {
					facebookCredentials.setTokenExpirationDate(new Date(System.currentTimeMillis() + expires * 1000));
				} 

				return facebookCredentials;
			} else {
				throw new ErrorReceivingAccessTokenException("accessToken = " + accessToken + ", expires = " + expires);
			}
		}
		catch (MalformedURLException e) {
			throw new ErrorReceivingAccessTokenException(e.getMessage());
		}
		catch (URISyntaxException e) {
			throw new ErrorReceivingAccessTokenException(e.getMessage());
		}
		catch (IOException e) {
			throw new ErrorReceivingAccessTokenException(e.getMessage());
		}
	}

}
