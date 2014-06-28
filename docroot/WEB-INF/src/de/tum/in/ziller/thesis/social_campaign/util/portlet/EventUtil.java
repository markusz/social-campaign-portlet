package de.tum.in.ziller.thesis.social_campaign.util.portlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;

import de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException;
import de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest;
import de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.AssistenceRequestPK;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAttendencePK;

/**
 * UtilKlasse die Methoden anbietet um Events zu verarbeiten
 * @author Markus
 *
 */
public class EventUtil {

/**
 * Nimmt ein User an einem Event teil?
 * @param userId
 * @param eventId
 * @return
 * @author Markus
 * @version 
 * @time 15.11.2011
 */
	public static boolean userJoinsEvent(long userId, long eventId) {

		try {
			EventAttendenceLocalServiceUtil.getEventAttendence(new EventAttendencePK(eventId, userId));
			return true;
		}
		catch (PortalException e1) {
			return false;
		}
		catch (SystemException e2) {
			return false;
		}

	}

	/**
	 * Extrahiert die Hilfeanfragen aus dem Request
	 * @param req
	 * @return
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public static Map<Long, Integer> extractAssistenceRelatedDataFromRequest(ActionRequest req) {

		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for (String key : req.getParameterMap().keySet()) {
			if (key.startsWith("assistencecategories_"))

			{
				Long row = Long.valueOf(key.split("_")[1]);
				Long roleId = ParamUtil.getLong(req, key);
				Integer quantity = ParamUtil.getInteger(req, "quantity_" + row);

				map.put(roleId, map.get(roleId) != null ? map.get(roleId) + quantity : 0 + quantity);
			}
		}

		return map;
	}
	
	/**
	 * Entfernt alle AssistenceRequests für ein Event
	 * @param eventId
	 * @throws SystemException
	 * @throws NoSuchAssistenceRequestException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	private static void clearAssistenceRequests(long eventId) throws SystemException, NoSuchAssistenceRequestException{
		List<AssistenceRequest> requests = AssistenceRequestLocalServiceUtil.getAssistenceRequestForEvent(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		for (AssistenceRequest assistenceRequest : requests) {
			
				AssistenceRequestLocalServiceUtil.deleteAssistenceRequest(assistenceRequest);
			
		}
		
	}

	/**
	 * Fügt die AssistenceRequests der Datenbank hinzu
	 * @param assistenceRequests
	 * @param eventId
	 * @throws NoSuchAssistenceRequestException
	 * @throws SystemException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public static void addAssistenceRequestToDB(Map<Long, Integer> assistenceRequests, long eventId) throws NoSuchAssistenceRequestException, SystemException {
		
		clearAssistenceRequests(eventId);
		
		for (Long roleId : assistenceRequests.keySet()) {
			
			AssistenceRequestPK key = new AssistenceRequestPK(eventId, roleId);
			AssistenceRequest assistenceRequest = AssistenceRequestLocalServiceUtil.createAssistenceRequest(key);
			assistenceRequest.setQuantity(assistenceRequests.get(roleId));
			
			if(assistenceRequest.getQuantity() > 0){
			AssistenceRequestLocalServiceUtil.addAssistenceRequest(assistenceRequest);
			}
			
		}

	}

}
