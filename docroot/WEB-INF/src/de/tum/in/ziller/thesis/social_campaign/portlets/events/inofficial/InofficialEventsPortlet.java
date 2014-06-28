package de.tum.in.ziller.thesis.social_campaign.portlets.events.inofficial;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import de.tum.in.ziller.thesis.social_campaign.portlets.events.EventsGenericPortlet;

/**
 * Portlet implementation class InofficialEventsPortlet
 */
public class InofficialEventsPortlet extends EventsGenericPortlet {

	public void addEvent(ActionRequest req, ActionResponse res) throws IOException, PortletException {
		addEvent(req, res, false);
		
	}

}
