package de.tum.in.ziller.thesis.social_campaign.util;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import de.tum.in.ziller.thesis.social_campaign.service.EventAssistenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAssistencePK;

/**
 * Klasse welche verschiedene Methoden anbietet um Berechtigungen zu überprüfen
 * @author Markus
 *
 */
public class PermissionsUtil {

	private static String[]	campaignLeaderRoles	= new String[] {"Wahlkaempfer"};
	private static String[]	electorRoles		= new String[] {"Waehler"};

	public static boolean isEventCreator(Long userId, Long eventId) {
		if (userId < 1 || eventId < 1) {
			return false;
		}

		try {
			return EventLocalServiceUtil.getEvent(eventId).getCreatorId() == userId;
		}
		catch (Exception e) {
			return false;
		}

	}
	
	public static boolean isCampaignLeader(User user) {
		if (user == null) {
			return false;
		}

		try {
			return hasUserAtLeastOneRole(user, campaignLeaderRoles);
		}
		catch (Exception e) {
			return false;
		}

	}

	public static boolean isCampaignLeader(HttpServletRequest req) {
		if (!isLoggedIn(req)) {
			return false;
		}

		try {
			return hasUserAtLeastOneRole(PortalUtil.getUser(req), campaignLeaderRoles);
		}
		catch (Exception e) {
			return false;
		}

	}

	public static boolean isCampaignLeader(PortletRequest req) {
		if (!isLoggedIn(req)) {
			return false;
		}

		try {
			return hasUserAtLeastOneRole(PortalUtil.getUser(req), campaignLeaderRoles);
		}
		catch (Exception e) {
			return false;
		}

	}

	public static boolean hasUserAtLeastOneRole(User user, String[] neededRolesNames) {

		for (String roleName : neededRolesNames) {
			try {
				
				if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), user.getCompanyId(), roleName, true)) {
					return true;
				}
			}
			catch (Exception e) {
				// fail silently
			}

		}

		return false;

	}

	public static boolean isElector(HttpServletRequest req) {
		if (!isLoggedIn(req)) {
			return false;
		}

		try {
			return hasUserAtLeastOneRole(PortalUtil.getUser(req), electorRoles);
		}
		catch (Exception e) {
			return false;
		}
	}

	public static boolean isElector(PortletRequest req) {
		if (!isLoggedIn(req)) {
			return false;
		}

		try {
			return hasUserAtLeastOneRole(PortalUtil.getUser(req), electorRoles);
		}
		catch (Exception e) {
			return false;
		}
	}

	public static boolean isLoggedIn(HttpServletRequest req) {
		return PortalUtil.getUserId(req) > 0;

	}

	public static boolean isLoggedIn(PortletRequest req) {
		return PortalUtil.getUserId(req) > 0;

	}

	public static void printIfLoggedIn(HttpServletRequest req, String content, JspWriter writer) {
		if (isLoggedIn(req)) {
			try {
				writer.write(content);
			}
			catch (IOException e) {
				// fail silently
			}
		}

	}

	public static void printIfLoggedIn(HttpServletRequest req, String content, String alternative, JspWriter writer) {
		try {

			if (isLoggedIn(req)) {
				writer.write(content);
			} else {
				writer.write(alternative);
			}
		}
		catch (IOException e) {
			// fail silently
		}

	}
	
	public static boolean userHelpsForEvent(Long eventId, Long userId){
		try {
			System.out.println(eventId+" "+ userId);
			return EventAssistenceLocalServiceUtil.getEventAssistenceForEventAndUser(eventId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS).size() > 0;
			
		}
		catch (SystemException e) {
			return false;
		}
	}
	
	public static boolean userHelpsForEventInRole(Long eventId, Long userId, Long roleId){
		try {
			return EventAssistenceLocalServiceUtil.getEventAssistence(new EventAssistencePK(eventId,roleId,userId)) != null;
			
		}
		catch (SystemException e) {
			return false;
		}
		catch (PortalException e) {
			return false;
		}
	}

}
