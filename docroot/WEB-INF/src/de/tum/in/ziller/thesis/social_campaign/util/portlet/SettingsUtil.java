package de.tum.in.ziller.thesis.social_campaign.util.portlet;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

import de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.service.GeneralSettingsLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.SocialSettingsLocalServiceUtil;

/**
 * Utilklasse die Methoden zum Ausführen von mit Einstellungen zusammenhängenden Operationen beinhaltet 
 * @author Markus
 *
 */
public class SettingsUtil {

	/**
	 * Erzeugt ein Objekt vom Typ GeneralSetting aus einem Request
	 * @param req
	 * @return
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public static GeneralSettings createGeneralSettingsFromRequest(ActionRequest req) {
		GeneralSettings settings = GeneralSettingsLocalServiceUtil.createGeneralSettings(PortalUtil.getUserId(req));
		settings.setInofficialEventsSearchRadius(ParamUtil.getInteger(req, "inofficialEventsSearchRadius"));
		settings.setDisplayName(ParamUtil.getString(req, "displayName"));
		settings.setHomebase(ParamUtil.getString(req, "homebase"));
		return settings;
	}

	/**
	 * Erzeugt ein Objekt vom Typ SocialSetting aus einem Request
	 * @param req
	 * @return
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public static SocialSettings createSocialSettingsFromRequest(ActionRequest req) {
		SocialSettings settings = SocialSettingsLocalServiceUtil.createSocialSettings(PortalUtil.getUserId(req));
		settings.setAutoPublishOnFacebook(ParamUtil.getBoolean(req, "autoPublishOnFacebook"));
		settings.setAutoPublishOnGoogle(ParamUtil.getBoolean(req, "autoPublishOnGoogle"));
		settings.setStandardCalendarId(ParamUtil.getString(req, "standardCalendarId"));
		return settings;
	}

	/**
	 * Liefert die SocialSettings für einen Nutzer zurück bzw. legt ein solches mit Standardwerten an, falls so ein Eintrag noch nicht existiert
	 * @param userId
	 * @return
	 * @throws SystemException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public static SocialSettings getExistingOrCreateNewSocialSettingsForUser(long userId) throws SystemException {

		try {
			SocialSettings socialSettings = SocialSettingsLocalServiceUtil.getSocialSettings(userId);
			return socialSettings;
		}
		catch (PortalException e) {
			SocialSettings createdSocialSettings = SocialSettingsLocalServiceUtil.createSocialSettings(userId);
			createdSocialSettings.setAutoPublishOnFacebook(false);
			createdSocialSettings.setAutoPublishOnGoogle(false);

			return SocialSettingsLocalServiceUtil.addSocialSettings(createdSocialSettings);

		}
	}

	/**
	 * Liefert die GeneralSettings für einen Nutzer zurück bzw. legt ein solches mit Standardwerten an, falls so ein Eintrag noch nicht existiert
	 * @param userId
	 * @return
	 * @throws SystemException
	 * @author Markus
	 * @version 
	 * @time 15.11.2011
	 */
	public static GeneralSettings getExistingOrCreateNewGeneralSettingsForUser(long userId) throws SystemException {

		try {
			GeneralSettings generalSettings = GeneralSettingsLocalServiceUtil.getGeneralSettings(userId);
			return generalSettings;
		}
		catch (PortalException e) {
			GeneralSettings createdGeneralSettings = GeneralSettingsLocalServiceUtil.createGeneralSettings(userId);
			createdGeneralSettings.setHomebase("");
			createdGeneralSettings.setInofficialEventsSearchRadius(0);
			createdGeneralSettings.setDisplayName("");

			return GeneralSettingsLocalServiceUtil.addGeneralSettings(createdGeneralSettings);

		}
	}

}
