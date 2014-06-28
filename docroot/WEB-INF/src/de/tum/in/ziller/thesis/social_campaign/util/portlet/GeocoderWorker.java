package de.tum.in.ziller.thesis.social_campaign.util.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.GeneralSettingsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings;

/**
 * Utilklasse, welche eine textuelle Repräsentation eines Ortes mithilfe der Geocoder API in Koordination umwandelt und diese dann in der Datenbank für den entsprechenden Eintrag speichert. Zu diesem Zweck benötigt der Konstruktor entweder ein Objekt vom Typ "Event" oder "GeneralSettings"
 * 
 * @author Markus
 * 
 */
public class GeocoderWorker extends Thread {
	Event			event;
	GeneralSettings	settings;
	Log				_log	= LogFactory.getLog(GeocoderWorker.class);

	public GeocoderWorker(Event event) {

		this.event = event;
	}

	public GeocoderWorker(GeneralSettings settings) {

		this.settings = settings;
	}

	public void run() {
		String adress = "";

		try {


			if (settings == null && event != null) {
				adress = event.getLocationAsString();
			}
			if (settings != null && event == null) {
				adress = settings.getHomebase();
			}

			URL url = new URI("http", "maps.google.com", "/maps/api/geocode/json", "address=" + adress + "&sensor=false", null).toURL();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			JSONObject json = (JSONObject) JSONValue.parseWithException(reader);
			JSONArray results = (JSONArray) json.get("results");
			JSONObject content = (JSONObject) results.get(0);
			JSONObject geometry = (JSONObject) content.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");
			Double latitude = (Double) location.get("lat");
			Double longitude = (Double) location.get("lng");

			if (settings == null && event != null) {
				event.setLocationLatitude(latitude);
				event.setLocationLongitude(longitude);

				EventLocalServiceUtil.updateEvent(event, true);
			}
			if (settings != null && event == null) {

				settings.setHomebaseLatitude(latitude);
				settings.setHomebaseLongitude(longitude);

				GeneralSettingsLocalServiceUtil.updateGeneralSettings(settings);
			}

		}
		catch (Exception e) {
			_log.info("couldn't determine lat-long values for the input: " + adress);
			_log.error(e);
		}

	}

}
