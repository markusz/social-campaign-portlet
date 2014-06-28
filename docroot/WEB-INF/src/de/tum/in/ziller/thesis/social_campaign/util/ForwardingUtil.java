package de.tum.in.ziller.thesis.social_campaign.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.liferay.portal.kernel.log.LogFactory;

import de.tum.in.ziller.thesis.social_campaign.exceptions.ReceivingForwardingParameterFailedException;

public class ForwardingUtil {

	private static Map<String, String>	jspMap			= null;
	private static Log					_log			= org.apache.commons.logging.LogFactory.getLog(ForwardingUtil.class);

	/**
	 * Sucht die zu der übergebenen Konstante gehörende JSP
	 * @param todo
	 * @return
	 * @author Markus
	 * @version 
	 * @time 13.11.2011
	 */
	public static String getProperJSP(String todo) {
		if (jspMap == null) {
			initJspMap();
		}

		if (jspMap.containsKey(todo)) {
			_log.info("forwarding to: " + jspMap.get(todo));
			return jspMap.get(todo);
		}
		
		return "";
	}

	private static void initJspMap() {
		jspMap = new HashMap<String, String>();
		jspMap.put(Constants.CREATE_OFFICIAL_EVENT, "/html/portlets/events/official/create-event.jsp");
		jspMap.put(Constants.CREATE_INOFFICIAL_EVENT, "/html/portlets/events/inofficial/create-event.jsp");
		jspMap.put(Constants.CREATE_NEWS, "/html/portlets/news_discussion/news/create-news.jsp");
		jspMap.put(Constants.SHOW_REQUIRED_ASSISTENCE_FOR_EVENT, "/html/portlets/support/assist/show-required-assistence.jsp");
		jspMap.put(Constants.SHOW_INOFFICIAL_EVENT, "/html/portlets/events/inofficial/show-event.jsp");
		jspMap.put(Constants.SHOW_OFFICIAL_EVENT, "/html/portlets/events/official/show-event.jsp");
		jspMap.put(Constants.DONATION_THANK_YOU, "/html/portlets/support/donate/donation-thank-you.jsp");
	}
}
