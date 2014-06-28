package de.tum.in.ziller.thesis.social_campaign.api.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.SettingsUtil;

public class GoogleCalendarEventDeletionWorker extends Thread {

	Event			event;
	EventAttendence	eventAttendence;
	Log _log = LogFactory.getLog(GoogleCalendarEventDeletionWorker.class);

	public GoogleCalendarEventDeletionWorker(EventAttendence eventAttendence) {
		this.eventAttendence = eventAttendence;
	}
	
	public GoogleCalendarEventDeletionWorker(Event event) {
		this.event = event;
	}

	@Override
	public void run() {
		HttpRequestFactory rf = null;
		HttpRequest request = null;
		String selfLink = null;
		String etag = null;
		String eventId = null;
		String calendarId = null;
		SocialSettings settings = null;
		try {
			
			Long userId = 0L;

			if (eventAttendence == null && event != null) {
				userId = event.getCreatorId();
				eventId = event.getCorrespondingGoogleEventId();
				calendarId = event.getCorrespondingGoogleEventCalendarId();
				
			}

			if (eventAttendence != null && event == null) {
				userId = eventAttendence.getUserProfileId();
				eventId = eventAttendence.getCorrespondingGoogleEvent();
				calendarId = eventAttendence.getCorrespondingGoogleCalendarId();
			}

			settings = SettingsUtil.getExistingOrCreateNewSocialSettingsForUser(userId);
			String calendar = settings.getStandardCalendarId();

			if (eventAttendence == null && event != null && event.isIsOfficial()) {
				calendar = OAuth.googleOfficialEventsCalendar;

			}
			
			if(calendarId == null || eventId == null){
				return;
			}
			

			GoogleAccessProtectedResource accessProtectedResource = OAuthGoogleImpl.getGoogleCredentialsAndRefreshTokenIfRequired(userId);
			rf = OAuth.TRANSPORT.createRequestFactory(accessProtectedResource);
			String url = "https://www.google.com/calendar/feeds/"+calendarId+"/private/full/" + eventId + "?alt=jsonc";

			GenericUrl eventEndpoint = new GenericUrl(url);

			request = rf.buildGetRequest(eventEndpoint);
			request.headers.contentType = "application/json";
			HttpResponse response = request.execute();

			BufferedReader output = new BufferedReader(new InputStreamReader(response.getContent()));

			org.json.simple.JSONObject receivedjson = (org.json.simple.JSONObject) org.json.simple.JSONValue.parse(output);
			JSONObject receivedData = (JSONObject) receivedjson.get("data");
			selfLink = (String) receivedData.get("selfLink");
			etag = (String) receivedData.get("etag");
		}
		catch (HttpResponseException e) {
			HttpResponse response = e.getResponse();
			if (response.getStatusCode() == 302) {
				try {

					request.setUrl(new GenericUrl(response.getHeaders().getLocation()));
					BufferedReader output = new BufferedReader(new InputStreamReader(request.execute().getContent()));

					JSONObject receivedjson = (JSONObject) JSONValue.parse(output);
					JSONObject receivedData = (JSONObject) receivedjson.get("data");
					selfLink = (String) receivedData.get("selfLink");
					etag = (String) receivedData.get("etag");
				}
				catch (HttpResponseException e2) {
					_log.error(e2);
				}
				catch (IOException e1) {
					_log.error(e1);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		//finally received event -> deleting

		HttpRequest deleteRequest = null;
		HttpResponse deleteResponse = null;
		try {
			deleteRequest = rf.buildDeleteRequest(new GenericUrl(selfLink));
			HttpHeaders header = deleteRequest.getHeaders();
			header.setIfMatch(etag != null ? etag : "*");
			
			deleteRequest.setHeaders(header);
			deleteResponse = deleteRequest.execute();
			
		}
		catch (HttpResponseException e) {
			if (e.getResponse().getStatusCode() == 302) {
				try {
					deleteRequest.setUrl(new GenericUrl(e.getResponse().getHeaders().getLocation()));
					HttpResponse res = deleteRequest.execute();
				}
				catch (HttpResponseException e2) {
					_log.info("Delete failed after three attempts");
				}
				catch (IOException e1) {
					_log.error(e1);
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}
}
