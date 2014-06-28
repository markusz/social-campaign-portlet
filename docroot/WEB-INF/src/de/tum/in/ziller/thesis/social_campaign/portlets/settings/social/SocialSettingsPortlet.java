package de.tum.in.ziller.thesis.social_campaign.portlets.settings.social;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import de.tum.in.ziller.thesis.social_campaign.model.FacebookCredentials;
import de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.portlets.AdvancedMVCPortlet;
import de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.SocialSettingsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.SettingsUtil;

/**
 * Portlet implementation class SocialSettingsPortlet
 */
public class SocialSettingsPortlet extends AdvancedMVCPortlet {

	private Log				_log	= LogFactory.getLog(SocialSettingsPortlet.class);

	/**
	 * Updated die SocialSettings eines Users
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public void updateSettings(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		try {
			SocialSettings settings = SettingsUtil.createSocialSettingsFromRequest(req);
			try {
				SocialSettingsLocalServiceUtil.getSocialSettings(settings.getUserProfileId());
				SocialSettingsLocalServiceUtil.updateSocialSettings(settings);
			}
			catch (PortalException e) {
				SocialSettingsLocalServiceUtil.addSocialSettings(settings);
			}
		}
		catch (SystemException e) {
			_log.error(e);
		}

	}

	/**
	 * Löscht die Facebook Authentifizierungsdaten eines Nutzers
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public void deleteFacebookCredentials(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		try {

			FacebookCredentials facebookCredentials = FacebookCredentialsLocalServiceUtil.getFacebookCredentials(PortalUtil.getUserId(req));
			FacebookCredentialsLocalServiceUtil.deleteFacebookCredentials(facebookCredentials);

		}
		catch (SystemException e) {
			_log.error(e);
		}
		catch (PortalException e) {
			_log.error(e);
		}

	}

	/**
	 * Löscht die Google Authentifizierungsdaten eines Nutzers
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws PortletException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public void deleteGoogleCredentials(ActionRequest req, ActionResponse res) throws IOException, PortletException {

		try {

			GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.getGoogleCredentials(PortalUtil.getUserId(req));
			GoogleCredentialsLocalServiceUtil.deleteGoogleCredentials(googleCredentials);

		}
		catch (SystemException e) {
			_log.error(e);
		}
		catch (PortalException e) {
			_log.error(e);
		}

	}

}
