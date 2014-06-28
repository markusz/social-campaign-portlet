package de.tum.in.ziller.thesis.social_campaign.api.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorReceivingAccessTokenException;
import de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials;
import de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalServiceUtil;

public class OAuthGoogleImpl implements OAuth {

	public BaseModel<?> aquireAccessTokenWithGoogleDataAPI(String authorizationCode, Long userId) throws ErrorReceivingAccessTokenException {
		// Exchange for an access and refresh token
		try {
			GoogleAuthorizationCodeGrant authRequest = new GoogleAuthorizationCodeGrant(TRANSPORT, JSON_FACTORY, OAuth.googleClientId, OAuth.googleClientSecret, authorizationCode, OAuth.googleRedirectURI);
			authRequest.useBasicAuthorization = false;

			AccessTokenResponse authResponse = authRequest.execute();
			String accessToken = authResponse.accessToken;
			String refreshToken = authResponse.refreshToken;
			Long expires = authResponse.expiresIn;

			if (accessToken != null && refreshToken != null && expires != null) {
				GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.createGoogleCredentials(userId);
				googleCredentials.setAccessToken(accessToken);
				googleCredentials.setRefreshToken(refreshToken);
				googleCredentials.setTokenExpirationDate(new Date(System.currentTimeMillis() + expires * 1000));
				return googleCredentials;
			} else {
				throw new ErrorReceivingAccessTokenException("accessToken = " + accessToken + ", expires = " + expires);
			}

		}
		catch (IOException e) {
		}
		return null;
	}

	@Override
	public BaseModel<?> aquireAccessToken(String authorizationCode, Long userId) throws ErrorReceivingAccessTokenException {

		try {

			URL url = new URI("https", "accounts.google.com", "/o/oauth2/token", "client_id=" + googleClientId + "&redirect_uri=" + googleRedirectURI + "&client_secret=" + googleClientSecret + "&code=" + authorizationCode, null).toURL();

			String data = URLEncoder.encode("client_id", "UTF-8") + "=" + googleClientId;
			data += "&" + URLEncoder.encode("redirect_uri", "UTF-8") + "=" + googleRedirectURI;
			data += "&" + URLEncoder.encode("client_secret", "UTF-8") + "=" + googleClientSecret;
			data += "&" + URLEncoder.encode("code", "UTF-8") + "=" + authorizationCode;
			data += "&" + URLEncoder.encode("grant_type", "UTF-8") + "=authorization_code";

			// Send data

			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			System.out.println(data);
			wr.write(data);
			wr.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JSONObject json = (JSONObject) JSONValue.parseWithException(reader);

			String accessToken = (String) json.get("access_token");
			String refreshToken = (String) json.get("refresh_token");
			Long expires = (Long) json.get("expires_in");

			if (accessToken != null && refreshToken != null && expires != null) {
				GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.createGoogleCredentials(userId);
				googleCredentials.setAccessToken(accessToken);
				googleCredentials.setRefreshToken(refreshToken);
				googleCredentials.setTokenExpirationDate(new Date(System.currentTimeMillis() + expires * 1000));
				return googleCredentials;
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
		catch (UnsupportedEncodingException e) {
			throw new ErrorReceivingAccessTokenException(e.getMessage());
		}
		catch (IOException e) {
			throw new ErrorReceivingAccessTokenException(e.getMessage());
		}
		catch (ParseException e) {
			throw new ErrorReceivingAccessTokenException(e.getMessage());
		}

	}

	public static CustomGoogleAccessProtectedResource getGoogleCredentialsAndRefreshTokenIfRequired(long userId) throws ErrorReceivingAccessTokenException {

		try {
			GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.getGoogleCredentials(userId);
			CustomGoogleAccessProtectedResource accessProtectedResource = new CustomGoogleAccessProtectedResource(googleCredentials.getAccessToken(), TRANSPORT, JSON_FACTORY, OAuth.googleClientId, OAuth.googleClientSecret, googleCredentials.getRefreshToken(), userId);

			if (!(googleCredentials.getTokenExpirationDate()).after(new Date())) {
				if (accessProtectedResource.refreshToken()) {
					_log.info("refreshed accessToken for userId "+googleCredentials.getUserId());
					return accessProtectedResource;
				} else {
					throw new ErrorReceivingAccessTokenException("AccessToken couldn't be refreshed");
				}
			}
			return accessProtectedResource;

		}
		catch (PortalException e) {
			throw new ErrorReceivingAccessTokenException("No google credentials found");
		}
		catch (SystemException e) {
			throw new ErrorReceivingAccessTokenException("Error querying database");
		}
		catch (IOException e) {
			throw new ErrorReceivingAccessTokenException("I/O Error while getting accesstoken");
		}

	}

}
