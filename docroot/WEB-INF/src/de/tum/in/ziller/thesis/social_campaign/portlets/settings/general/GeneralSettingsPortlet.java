package de.tum.in.ziller.thesis.social_campaign.portlets.settings.general;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings;
import de.tum.in.ziller.thesis.social_campaign.portlets.AdvancedMVCPortlet;
import de.tum.in.ziller.thesis.social_campaign.service.GeneralSettingsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.GeocoderWorker;
import de.tum.in.ziller.thesis.social_campaign.util.portlet.SettingsUtil;

/**
 * Portletklass für das GeneralSettings Portlet
 */
public class GeneralSettingsPortlet extends AdvancedMVCPortlet {
	
	/**
	 * Updated die allgemeinen Einstellungen eines Nutzers
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
			
			GeneralSettings settings = SettingsUtil.createGeneralSettingsFromRequest(req);
			
			try {
				GeneralSettingsLocalServiceUtil.getGeneralSettings(settings.getUserProfileId());
				GeneralSettings changedSettings = GeneralSettingsLocalServiceUtil.updateGeneralSettings(settings);
				new GeocoderWorker(changedSettings).run();
			}
			catch (PortalException e) {
				GeneralSettings changedSettings = GeneralSettingsLocalServiceUtil.addGeneralSettings(settings);
				new GeocoderWorker(changedSettings).run();
			}
			
		}
		catch (SystemException e) {
			e.printStackTrace();
		}
		
		
	}
}
