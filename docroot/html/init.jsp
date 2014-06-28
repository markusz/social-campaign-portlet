<!-- Taglibs -->
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet.tld" prefix="portlet" %>
<%@ taglib uri="/WEB-INF/tld/liferay-security.tld" prefix="liferay-security" %>
<%@ taglib uri="/WEB-INF/tld/liferay-theme.tld" prefix="liferay-theme" %>
<%@ taglib uri="/WEB-INF/tld/liferay-ui.tld" prefix="liferay-ui" %>
<%@ taglib uri="/WEB-INF/tld/liferay-util.tld" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<!-- Service-related imports -->
<%@page import="de.tum.in.ziller.thesis.social_campaign.util.Constants"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.util.PermissionsUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.util.portlet.EventUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.Category"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.Donation"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.Event"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.EventAttendence"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.FacebookAccount"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.News"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.NewsType"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.GoogleAccount"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.FacebookCredentials"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.SocialSettings"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.AssistenceRole"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.impl.GeneralSettingsImpl"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.impl.EventImpl"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.AssistenceRoleLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.CategoryLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.DonationLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.NewsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.NewsTypeLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.GoogleAccountLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.FacebookAccountLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.GeneralSettingsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.SocialSettingsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleAccountException"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookAccountException"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.NoSuchSocialSettingsException"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.NoSuchGeneralSettingsException"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.api.OAuth"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.api.facebook.OAuthFacebookImpl"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.api.google.OAuthGoogleImpl"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.api.facebook.FacebookGetUserIdWorker"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.exceptions.ErrorReceivingAccessTokenException"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookCredentialsException"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleCredentialsException"%>
<!-- Liferay imports -->
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.liferay.portal.model.ListTypeConstants"%>
<%@page import="com.liferay.portal.model.User"%>
<%@page import="com.liferay.portal.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.service.persistence.RoleUtil"%>
<%@page import="com.liferay.portal.service.ServiceContextFactory"%>
<%@page import="com.liferay.portal.service.ServiceContext"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portlet.messageboards.service.MBMessageServiceUtil"%>
<%@page import="com.liferay.portal.service.UserLocalServiceUtil"%>


<!-- Java imports -->
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URI"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="javax.portlet.PortletURL"%>

<!-- Libraries -->
<%@page import="com.restfb.DefaultFacebookClient"%>




<portlet:defineObjects />