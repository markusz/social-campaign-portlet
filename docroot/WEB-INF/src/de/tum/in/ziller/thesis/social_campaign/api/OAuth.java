package de.tum.in.ziller.thesis.social_campaign.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.model.BaseModel;

import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorReceivingAccessTokenException;

/**
 * Stellt Methodensignaturen und Konstanten bereit, die zur Erlangung eines OAuth-Token benötigt werden.
 * @author Markus
 *
 */
public interface OAuth {
	
	Log _log = LogFactory.getLog(OAuth.class);
	
	public static final com.google.api.client.http.javanet.NetHttpTransport	TRANSPORT		= new com.google.api.client.http.javanet.NetHttpTransport();
	public static final com.google.api.client.json.jackson.JacksonFactory	JSON_FACTORY	= new com.google.api.client.json.jackson.JacksonFactory();
	// Access Token um im Namen der Anwednungen Aktionen durchzuführen
	public static String appAccessToken = "213686908686905|Ik5UTuMh7xADIGXedq3HAnhP1ss";
	
	// Die Id des Kalenders in welchen offizielle Veranstaltungen geschrieben werden sollen.
	public static String googleOfficialEventsCalendar = "3698fssdp6hf3lkr2672sov1eo%40group.calendar.google.com";
	
	//Die gewünschten Befugnisse für die jeweiligen APIs
	public static String[] facebookRequiredPermissions = new String[]{"publish_stream","manage_pages","rsvp_event","offline_access", "create_event"};
	public static String[] googleRequiredPermissions = new String[]{"https://www.google.com/calendar/feeds","https://www.google.com/calendar/feeds/default/allcalendars/full","https://www.google.com/calendar/feeds/default/owncalendars/full","https://www.google.com/calendar/feeds/default/private/full"};
	
	//Anwendungsids
	public static String facebookClientId = "213686908686905";
	public static String googleClientId = "349507158036.apps.googleusercontent.com";
	
	//Anwendungsschlüssel
	public static String facebookClientSecret = "aed9eacd772618f439bccb1929ded5fa";
	public static String googleClientSecret = "a1oG_QNzxhUQz2Y7nwl7Qwv1";
	
	//Startpunkte für die OAuth-Authentifizierung
	public static String facebookOAuthDialog = "https://www.facebook.com/dialog/oauth";
	public static String googleOAuthDialog = "https://accounts.google.com/o/oauth2/auth";
	
	//URL für die OAuth-Callback Aufrufe
	public static String googleRedirectURI = "http://virtfortiss11.informatik.tu-muenchen.de/web/social-campaign/googlecallbackhandler?p_p_id=googleoauthportlet_WAR_socialcampaignportlet";
	public static String facebookRedirectURI = "http://virtfortiss11.informatik.tu-muenchen.de/web/social-campaign/facebookcallbackhandler?p_p_id=facebookoauthportlet_WAR_socialcampaignportlet";
	
	//Endpoints um Autoriszation Token in Access Token zu tauschen
	public static String facebookAccessTokenEndpoint = "https://graph.facebook.com/oauth/access_token";
	public static String googleAccessTokenEndpoint = "https://accounts.google.com/o/oauth2/token";
	
	
	/**
	 * Method aquireAccessToken.
	 * @param authorizationCode String
	 * @param userId Long
	 * @return BaseModel<?>
	 * @throws ErrorReceivingAccessTokenException
	 */
	public BaseModel<?> aquireAccessToken(String authorizationCode, Long userId) throws ErrorReceivingAccessTokenException;

}
