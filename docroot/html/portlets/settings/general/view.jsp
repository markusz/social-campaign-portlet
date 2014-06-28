<%@page import="de.tum.in.ziller.thesis.social_campaign.api.facebook.OAuthFacebookImpl"%>
<%@ include file="/html/init.jsp"%>

<portlet:actionURL name="updateSettings" var="updateSettings">
</portlet:actionURL>


<%

/* OAuth oauth = new OAuthFacebookImpl();

oauth.aquireAccessToken("123" );*/
GeneralSettings settings =  GeneralSettingsLocalServiceUtil.createGeneralSettings(PortalUtil.getUserId(request));

try{settings = GeneralSettingsLocalServiceUtil.getGeneralSettings(PortalUtil.getUserId(request));}

catch(NoSuchGeneralSettingsException e){
	
	}%>


<aui:form name="<portlet:namespace/><%=de.tum.in.ziller.thesis.social_campaign.util.Constants.FORM_NAME_GENERAL_SETTINGS %>" method="post" action="<%=updateSettings%>">
	<aui:layout>
		<aui:column>
			<aui:model-context bean="<%= settings %>" model="<%=GeneralSettings.class%>" />
			
			<aui:input name="inofficialEventsSearchRadius" label="settings-range" />
			<aui:input name="homebase" label="settings-homebase" />

			<aui:button-row>
				<aui:button type="submit" name="save" value="submit" />
			</aui:button-row>
		</aui:column>
	</aui:layout>
</aui:form>

