<%@page
	import="de.tum.in.ziller.thesis.social_campaign.api.google.CalendarManager"%>
<%@ include file="/html/init.jsp"%>
<%@ include file="/html/portlets/events/social-plugins-fragment.jsp"%>

<portlet:actionURL name="updateSettings" var="updateSettings" />
<portlet:actionURL name="deleteFacebookCredentials"
	var="deleteFacebookCredentials" />
<portlet:actionURL name="deleteGoogleCredentials"
	var="deleteGoogleCredentials" />

<%
	Long userId = PortalUtil.getUserId(request);
	List<String[]> calendars = CalendarManager.getCalendarsForUser(userId);
	SocialSettings settings = SocialSettingsLocalServiceUtil.createSocialSettings(userId);
	String standardId = settings.getStandardCalendarId();
	String standardName = "";
	
	

	try {
		settings = SocialSettingsLocalServiceUtil.getSocialSettings(userId);
		standardId = settings.getStandardCalendarId();
	}
	catch (NoSuchSocialSettingsException e) {
	}
	
for (String[] cal : calendars) {
		
		if(standardId.equalsIgnoreCase(cal[0])){
			standardName = cal[1];
		}
		
		}

	
%>

<aui:form
	name="<portlet:namespace/><%=de.tum.in.ziller.thesis.social_campaign.util.Constants.FORM_NAME_GENERAL_SETTINGS %>"
	method="post" action="<%=updateSettings%>">
	<aui:layout>
		<aui:panel label="Allgemeine Einstellungen" collapsible="true">
			<aui:model-context bean="<%= settings %>"
				model="<%=SocialSettings.class%>" />

	<%if(PermissionsUtil.isElector(request)){ %>
			<aui:input name="autoPublishOnFacebook"
				label="settings-autopublish-on-facebook"
				helpMessage="settings-autopublish-on-facebook-infotext" />
			<aui:input name="autoPublishOnGoogle"
				label="settings-autopublish-on-google"
				helpMessage="settings-autopublish-on-google-infotext" />
				<%} %>

<%


if(settings != null && calendars.size() > 0){ 

%>


<br><liferay-ui:message key="settings-standard-calendar" /> <%if(settings.getStandardCalendarId() == null || settings.getStandardCalendarId().length() == 0){%>
(<liferay-ui:message key="settings-no-standard-calendar-yet" />)
<%} %><br>
			<select name="standardCalendarId">
				<%
					for (String[] cal : calendars) {
				%>
				<option value="<%=cal[0]%>"
					<%if(standardId.equalsIgnoreCase(cal[0])){ %>
					selected="selected"
					<%} %>
					>
					<%=cal[1]%>
				</option>

				<%
					}
				%>

			</select>
			<%}
%>

		</aui:panel>
<%if(PermissionsUtil.isElector(request)){ %>
		<aui:panel label="Facebook Einstellungen" collapsible="true">
			<aui:model-context bean="<%= settings %>"
				model="<%=SocialSettings.class%>" />



			<%
				User user = PortalUtil.getUser(request);
							if (user.getFacebookId() > 0) {
								com.restfb.types.User facebookUserAccount = new DefaultFacebookClient().fetchObject("" + user.getFacebookId(), com.restfb.types.User.class);
			%>
			<table>
				<tr>
					<td>
					 <%
 	}
 				try {
 					FacebookCredentials facebookCredentials = FacebookCredentialsLocalServiceUtil.getFacebookCredentials(userId);
 %>
						<div>
						<liferay-ui:message key="settings-granted-facebook-rights" />
						</div>

						<div>
							<a href="<%=deleteFacebookCredentials.toString()%>"><liferay-ui:message
									key="settings-delete-facebook-credentials" /> </a><img alt=""
								onmouseover="Liferay.Portal.ToolTip.show(this, '<liferay-ui:message key="settings-delete-facebook-credentials-infotext" />');"
								src="/html/themes/classic/images/portlet/help.png">
						</div> <%
 	}

 				catch (NoSuchFacebookCredentialsException e) {
 %>
						<div>
							<a
								href="<%=OAuth.facebookOAuthDialog%>?client_id=<%=OAuth.facebookClientId%>&redirect_uri=<%=URLEncoder.encode(OAuth.facebookRedirectURI, "UTF-8")%>&scope=<%for (int i = 0; i < OAuth.facebookRequiredPermissions.length; i++) {
								out.print(OAuth.facebookRequiredPermissions[i] + " ");
							}%>"><liferay-ui:message
									key="settings-init-facebook-linking" /> </a><img alt=""
								onmouseover="Liferay.Portal.ToolTip.show(this, '<liferay-ui:message key="settings-init-facebook-linking-infotext" />');"
								src="/html/themes/classic/images/portlet/help.png">
						</div> <%
 	}
 %>
					</td>

				</tr>
			</table>


		</aui:panel>
		<%} %>

		<aui:panel label="Google Einstellungen" collapsible="true">
			<aui:model-context bean="<%= settings %>"
				model="<%=SocialSettings.class%>" />

			<%
				try {
								GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.getGoogleCredentials(userId);
			%>


			<div>
				<liferay-ui:message key="settings-granted-google-rights" />
			</div>

			<div>

				<a href="<%=deleteGoogleCredentials.toString()%>"><liferay-ui:message
						key="settings-delete-google-credentials" /> </a><img alt=""
					onmouseover="Liferay.Portal.ToolTip.show(this, '<liferay-ui:message key="settings-delete-google-credentials-infotext" />');"
					src="/html/themes/classic/images/portlet/help.png">
			</div>




			<%
				}

							catch (NoSuchGoogleCredentialsException e) {
			%>

			<div>
				<a
					href="<%=OAuth.googleOAuthDialog%>?client_id=<%=OAuth.googleClientId%>&redirect_uri=<%=URLEncoder.encode(OAuth.googleRedirectURI, "UTF-8")%>&scope=<%for (int i = 0; i < OAuth.googleRequiredPermissions.length; i++) {
								out.print(URLEncoder.encode(OAuth.googleRequiredPermissions[i], "UTF-8") + " ");
							}%>&response_type=code"><liferay-ui:message
						key="settings-init-google-linking" /> </a><img alt=""
					onmouseover="Liferay.Portal.ToolTip.show(this, '<liferay-ui:message key="settings-init-google-linking-infotext" />');"
					src="/html/themes/classic/images/portlet/help.png">
			</div>
			<%
				}
			%>

		</aui:panel>
		<aui:button-row>
			<aui:button type="submit" name="save" value="submit" />
		</aui:button-row>
	</aui:layout>
</aui:form>