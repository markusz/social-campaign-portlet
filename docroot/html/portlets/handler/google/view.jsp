<%@page import="de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.api.google.OAuthGoogleImpl"%>
<%@ include file="/html/init.jsp"%>

<%PortletURL renderURL = renderResponse.createRenderURL();
			
			
			String authorizationCode = request.getParameter("code");
			
			OAuth googleOAuth = new OAuthGoogleImpl();

			try {
				GoogleCredentials googleCredentials = (GoogleCredentials) googleOAuth.aquireAccessToken(authorizationCode, PortalUtil.getUserId(request));

				try {
					GoogleCredentialsLocalServiceUtil.getGoogleCredentials(googleCredentials.getUserId());
					GoogleCredentialsLocalServiceUtil.updateGoogleCredentials(googleCredentials);
					%>
					<liferay-ui:message key="handler-google-settings-updated"/>

				<%}
				catch (Exception e) {
					GoogleCredentialsLocalServiceUtil.addGoogleCredentials(googleCredentials);%>
					<liferay-ui:message key="handler-google-settings-added"/>
				<%}
				finally{
				//	new FacebookGetUserIdWorker(googleCredentials.getAccessToken(), PortalUtil.getUserId(request)).run();
				}

			}
			catch (ErrorReceivingAccessTokenException e) {
				%>
				<liferay-ui:message key="handler-google-settings-error"/>
				<%
			}%>