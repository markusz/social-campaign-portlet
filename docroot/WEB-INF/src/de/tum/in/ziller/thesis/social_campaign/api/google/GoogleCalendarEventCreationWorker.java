package de.tum.in.ziller.thesis.social_campaign.api.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.SimpleTimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import de.tum.in.ziller.thesis.social_campaign.api.OAuth;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorReceivingAccessTokenException;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.SettingsUtil;

/**
 * Erzeugt ein Event im definierten Google Calendar. Konstruktoraufrufe können
 * entweder mit EventAttendence oder Event als Argument erfolgen. Erfolgt der
 * Konstruktoraufruf mit einem offiziellen Event wird dieses in den offiziellen
 * Kalender, wie er in den Konstanten definiert ist geschreiben, ansonsten in
 * den bevorzugten Kalender eines Nutzers.
 * 
 * Die Klasse ist als Thread realisiert
 * 
 * @author Markus
 * 
 */
public class GoogleCalendarEventCreationWorker extends Thread {

	EventAttendence	eventAttendence;
	Event			event;
	Log				_log	= LogFactory.getLog(GoogleCalendarEventCreationWorker.class);

	public GoogleCalendarEventCreationWorker(EventAttendence eventAttendence) {
		this.eventAttendence = eventAttendence;
	}

	public GoogleCalendarEventCreationWorker(Event event) {
		this.event = event;
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void run() {

		JSONObject data = null;
		HttpRequestFactory rf = null;
		String eventURL = "https://www.google.com/calendar/feeds/default/private/full/";
		HttpRequest request = null;
		String id = null;
		String createdSelflink = null;
		String calendarId = null;
		SocialSettings settings = null;

		try {

			Long userId = 0L;
			Long eventId = 0L;

			if (eventAttendence == null && event != null) {
				userId = event.getCreatorId();
				eventId = event.getEventId();
			}

			if (eventAttendence != null && event == null) {
				userId = eventAttendence.getUserProfileId();
				eventId = eventAttendence.getEventId();
			}

			settings = SettingsUtil.getExistingOrCreateNewSocialSettingsForUser(userId);
			String calendar = settings.getStandardCalendarId();

			if (eventAttendence == null && event != null && event.isIsOfficial()) {
				calendar = OAuth.googleOfficialEventsCalendar;

			}

			if (settings.getStandardCalendarId() != null && settings.getStandardCalendarId() != "") {
				eventURL = "https://www.google.com/calendar/feeds/" + calendar + "/private/full/";
			}

			CustomGoogleAccessProtectedResource accessProtectedResource = OAuthGoogleImpl.getGoogleCredentialsAndRefreshTokenIfRequired(userId);
			rf = OAuth.TRANSPORT.createRequestFactory(accessProtectedResource);

			data = createEventInJSONFormat(eventId);


			GenericUrl requestEndpoint = new GenericUrl(eventURL);

			request = rf.buildPostRequest(requestEndpoint, new ByteArrayContent(data.toString()));
			request.headers.contentType = "application/json";

			HttpResponse response = request.execute();

			BufferedReader output = new BufferedReader(new InputStreamReader(response.getContent()));

			JSONObject receivedjson = (JSONObject) JSONValue.parse(output);
			JSONObject receivedData = (JSONObject) receivedjson.get("data");

			id = (String) receivedData.get("id");
			createdSelflink = (String) receivedData.get("selfLink");
			calendarId = createdSelflink.split("feeds/")[1].split("/")[0];

		}
		catch (HttpResponseException e) {
			HttpResponse response = e.getResponse();
			if (response.getStatusCode() == 302) {
				try {
					// String url = eventURL.concat("?"+gsessionId);
					request.setUrl(new GenericUrl(response.getHeaders().getLocation()));

					BufferedReader output = new BufferedReader(new InputStreamReader(request.execute().getContent()));

					JSONObject receivedjson = (JSONObject) JSONValue.parse(output);
					JSONObject receivedData = (JSONObject) receivedjson.get("data");
					id = (String) receivedData.get("id");
					createdSelflink = (String) receivedData.get("selfLink");
					calendarId = createdSelflink.split("feeds/")[1].split("/")[0];
				}
				catch (HttpResponseException e2) {
					_log.info("Error creating event after three attempts");
					_log.error(e2);
				}
				catch (IOException e1) {
					_log.error(e1);
				}
			}

		}
		catch (ErrorReceivingAccessTokenException e) {
			_log.error(e);
		}
		catch (IOException e) {
			_log.error(e);
		}
		catch (SystemException e) {
			_log.error(e);
		}
		catch (PortalException e) {
			_log.error(e);
		}
		finally {
			if (eventAttendence != null && id != null && calendarId != null) {
				eventAttendence.setCorrespondingGoogleEvent(id);
				eventAttendence.setCorrespondingGoogleCalendarId(calendarId);
				try {

					EventAttendenceLocalServiceUtil.updateEventAttendence(eventAttendence);
				}
				catch (SystemException e) {
					e.printStackTrace();
				}
			}

			if (event != null && id != null && calendarId != null) {

				event.setCorrespondingGoogleEventCalendarId(calendarId);
				event.setCorrespondingGoogleEventId(id);
				try {

					EventLocalServiceUtil.updateEvent(event);
				}
				catch (SystemException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Erzeugt eine JSON-Repräsentation eines Events aus einem Java-Objekt der
	 * Klasse Event. Die erzeugte JSON-Repräsentation ist konform zur
	 * Google-API.
	 * 
	 * @return
	 * @author Markus
	 * @version
	 * @throws SystemException
	 * @throws PortalException
	 * @time 17.10.2011
	 */
	@SuppressWarnings("unchecked")
	private JSONObject createEventInJSONFormat(Long eventId) throws PortalException, SystemException {

		DateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.GERMANY);
		dataformat.setTimeZone(new SimpleTimeZone(0, "GMT"));
		JSONObject json = new JSONObject();

		Event event = EventLocalServiceUtil.getEvent(eventId);

		JSONObject onlyOccurence = new JSONObject();
		// _log.info("starttime: " +
		// dataformat.format(event.getStartingTime()));
		onlyOccurence.put("start", dataformat.format(event.getStartingTime()) + "Z");
		onlyOccurence.put("end", dataformat.format(event.getEndingTime()) + "Z");

		JSONArray when = new JSONArray();
		when.add(onlyOccurence);

		json.put("title", event.getTitle());
		json.put("details", event.getDescription());
		json.put("timeZone", "Europe/Berlin");
		json.put("transparency", "opaque");
		json.put("status", "confirmed");
		json.put("location", event.getLocationAsString());
		json.put("when", when);
		JSONObject data = new JSONObject();

		data.put("data", json);
		return data;
	}
}
