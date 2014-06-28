package de.tum.in.ziller.thesis.social_campaign.util.portlet;

import java.util.Date;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;



import de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorCreatingNewsFromRequestDataException;
import de.tum.in.ziller.thesis.social_campaign.model.News;
import de.tum.in.ziller.thesis.social_campaign.model.impl.NewsImpl;
import de.tum.in.ziller.thesis.social_campaign.service.NewsLocalServiceUtil;

public class NewsDiscussionUtil {

	/**
	 * Erzeugt eine Objekt vom Typ "News" aus den aus dem Request empfangenen Daten.
	 * @param req
	 * @return
	 * @throws ErrorCreatingNewsFromRequestDataException
	 * @author Markus
	 * @version 
	 * @time 07.11.2011
	 */
	public static News createNewsFromRequest(ActionRequest req) throws ErrorCreatingNewsFromRequestDataException {
		News news = new NewsImpl();
		
		news.setTitle(ParamUtil.getString(req, "title"));
		try {
			news.setContent(extractYoutubeId(ParamUtil.getString(req, "link")));
		}
		catch (Exception e) {
			throw new ErrorCreatingNewsFromRequestDataException("Couldn't extract video id from Link: "+ParamUtil.getString(req, "link"));
		}
		news.setLink(ParamUtil.getString(req, "link"));
		news.setPublishDate(new Date());
		news.setPublisherId(PortalUtil.getUserId(req));
		news.setNewsTypeId(ParamUtil.getLong(req, "newsTypeId"));
		
		return news;
	}
	
	/**
	 * Extrahiert die Id eines Youtube-Videos aus dessen URL.
	 * @param url
	 * @return
	 * @throws Exception
	 * @author Markus
	 * @version 
	 * @time 07.11.2011
	 */
	private static String extractYoutubeId(String url) throws Exception{
		String parameters = url.split("\\?")[1];
		String[] parametersArray = parameters.split("&");
		for (int i = 0; i < parametersArray.length; i++) {
			if (parametersArray[i].startsWith("v=")) {
				return(parametersArray[i].split("=")[1]);
			}
			if (parametersArray[i].startsWith("t=")) {
				return parametersArray[i].split("=")[1];
			}
		}
		throw new Exception();
	}
	

}
