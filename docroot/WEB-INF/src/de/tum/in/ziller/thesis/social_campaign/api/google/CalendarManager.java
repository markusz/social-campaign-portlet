package de.tum.in.ziller.thesis.social_campaign.api.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorReceivingAccessTokenException;

/**
 * Managed die Kommunikation des Systems mit Google Calendar
 * @author Markus
 *
 */
public class CalendarManager {
	
	static Log _log = LogFactory.getLog(CalendarManager.class);

	/**
	 * Holt alle Google-Kalender eines Nutzer des Systems
	 * @param userId Die Id des Nutzers im System
	 * @return Eine Liste von zweielementigen Arrays, mit dem Schlüssel der Kalenders an erster und dem Namen an zweiter Stelle
	 * @author Markus
	 * @version 
	 * @time 07.11.2011
	 */
	@SuppressWarnings("deprecation")
	public static List<String[]> getCalendarsForUser(Long userId) {
	
		ArrayList<String[]> calendar = new ArrayList<String[]>();
		HttpRequestFactory rf = null;
		HttpResponse response = null;
		HttpRequest request = null;
		BufferedReader output = null;
		GenericUrl getCalendarEndpoint = null;
		try {
			GoogleAccessProtectedResource accessProtectedResource = OAuthGoogleImpl.getGoogleCredentialsAndRefreshTokenIfRequired(userId);
			rf = OAuth.TRANSPORT.createRequestFactory(accessProtectedResource);
	
			getCalendarEndpoint = new GenericUrl("https://www.google.com/calendar/feeds/default/allcalendars/full?alt=jsonc");
	
			request = rf.buildGetRequest(getCalendarEndpoint);
			request.headers.contentType = "application/json";
			request.setConnectTimeout(5000);
			
			response = request.execute();
			
			output = new BufferedReader(new InputStreamReader(response.getContent()));
	
			org.json.simple.JSONObject obj = (org.json.simple.JSONObject) org.json.simple.JSONValue.parse(output);
			org.json.simple.JSONObject datas = (org.json.simple.JSONObject) obj.get("data");
			org.json.simple.JSONArray items = (org.json.simple.JSONArray) datas.get("items");
	
			for (Object object : items) {
				org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) object;
				
				String[] splitted = (((String) jsonObj.get("id"))).split("/");
				calendar.add(new String[] { splitted[splitted.length-1], ((String) jsonObj.get("title")) });
			}
			
			return calendar;
		}
	
		catch (HttpResponseException e) {
	
			if (e.getResponse().getStatusCode() == 302) {
				
				getCalendarEndpoint = new GenericUrl(e.getResponse().getHeaders().getLocation());
				
				try {
					request.setUrl(getCalendarEndpoint);
					response = request.execute();
					
					
	
					output = new BufferedReader(new InputStreamReader(response.getContent()));
	
					JSONObject obj = (JSONObject) org.json.simple.JSONValue.parse(output);
					JSONObject datas = (JSONObject) obj.get("data");
					JSONArray items = (JSONArray) datas.get("items");
	
					for (Object object : items) {
						org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) object;
						calendar.add(new String[] { ((String) jsonObj.get("id")), ((String) jsonObj.get("title")) });
					}
					return calendar;
				}
				catch (HttpResponseException e2) {
	
					if (e2.getResponse().getStatusCode() == 302) {
					
						getCalendarEndpoint = new GenericUrl(e2.getResponse().getHeaders().getLocation());
						try {
							request.setUrl(getCalendarEndpoint);
							response = request.execute();
	
							output = new BufferedReader(new InputStreamReader(response.getContent()));
	
							JSONObject obj = (JSONObject) org.json.simple.JSONValue.parse(output);
							JSONObject datas = (JSONObject) obj.get("data");
							JSONArray items = (JSONArray) datas.get("items");
	
							for (Object object : items) {
								org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) object;
								calendar.add(new String[] { ((String) jsonObj.get("id")), ((String) jsonObj.get("title")) });
							}
						}
						catch(HttpResponseException e3){
							_log.info("Couldn't get calendars after three attempts");
							_log.error(e3);
						}
						catch (IOException e1) {
							_log.error(e1);
						}
						
						return calendar;
	
					}
				}
				catch (IOException e1) {
					_log.error(e);
				}
				
				return calendar;
	
			}
		}
		catch (IOException e) {
			_log.error(e);
		}
		catch (ErrorReceivingAccessTokenException e) {
			_log.error(e);
			return calendar;
		}
		return calendar;
	
	}

}
