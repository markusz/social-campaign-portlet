package de.tum.in.ziller.thesis.social_campaign.util.portlet;

import java.util.Date;

import javax.portlet.ActionRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;

import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.util.Constants;

/**
 * Stellt Methoden bereit welche das automatische generieren von Unterforen zu
 * Veranstaltungen ermöglichen
 * 
 * @author Markus
 * 
 */
public class MessageBoardEventUtil {

	private static Log	_log	= LogFactory.getLog(MessageBoardEventUtil.class);

	/**
	 * Erstellt ein Unterforum für das übergebene Event
	 * @param event
	 * @throws SystemException
	 * @throws PortalException
	 * @author Markus
	 * @version 
	 * @time 07.11.2011
	 */
	public static void createSubboardForEvent(Event event) throws SystemException, PortalException {

		try {

			MBCategory category = MBCategoryLocalServiceUtil.createMBCategory(CounterLocalServiceUtil.increment());

			User user = UserLocalServiceUtil.getUser(event.getCreatorId());
			

			category.setParentCategoryId(event.getIsOfficial() ? Constants.OFFICIAL_EVENTS_BOARD_ID : Constants.INOFFICIAL_EVENTS_BOARD_ID);
			category.setCreateDate(new Date());
			category.setName(event.getTitle());
			category.setUserName(user.getFullName());
			category.setUserId(user.getUserId());
			category.setCompanyId(user.getCompanyId());
			category.setGroupId(Constants.BOARD_GROUP_ID);
			category.setModifiedDate(new Date());
			category.setThreadCount(0);
			category.setDescription(event.getDescription());
			category.setMessageCount(0);
			category.setLastPostDate(new Date());

			MBCategoryLocalServiceUtil.addMBCategory(category);

		}
		catch (Exception e) {
			_log.error(e);
		}

	}

}
