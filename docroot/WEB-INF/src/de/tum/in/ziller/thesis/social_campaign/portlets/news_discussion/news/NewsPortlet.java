package de.tum.in.ziller.thesis.social_campaign.portlets.news_discussion.news;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.WindowState;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;

import de.tum.in.ziller.thesis.social_campaign.api.facebook.FacebookEventDeletionWorker;
import de.tum.in.ziller.thesis.social_campaign.api.google.GoogleCalendarEventDeletionWorker;
import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorCreatingNewsFromRequestDataException;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.model.News;
import de.tum.in.ziller.thesis.social_campaign.portlets.AdvancedMVCPortlet;
import de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.NewsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.util.Constants;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.NewsDiscussionUtil;

/**
 * Portlet implementation class NewsPortlet
 */
public class NewsPortlet extends AdvancedMVCPortlet {

/**
	 * Fügt eine News hinzu
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
public void addNews(ActionRequest req, ActionResponse res) throws IOException, PortletException {
		
		try {
			ParamUtil.print(req);
			
			News event = NewsDiscussionUtil.createNewsFromRequest(req);
			
			NewsLocalServiceUtil.updateNews(event);
		}
		catch (ErrorCreatingNewsFromRequestDataException e) {
			e.printStackTrace();
		}
		catch (SystemException e) {
			e.printStackTrace();
		}
		
	}

public void deleteNews(ActionRequest req, ActionResponse res) throws IOException, PortletException {
	
	Long newsId = ParamUtil.getLong(req, Constants.NEWS_ID);

	try {
		NewsLocalServiceUtil.deleteNews(newsId);
	}
	catch (PortalException e) {
		_log.error(e);
	}
	catch (SystemException e) {
		_log.error(e);
	}
	finally {
		res.setWindowState(WindowState.NORMAL);
	}
	
}


}
