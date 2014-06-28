<%@ include file="/html/init.jsp"%>

<%PortletURL renderURL = renderResponse.createRenderURL();
			String authorizationCode = request.getParameter("code");
			OAuth facebookOAuth = new OAuthFacebookImpl();

			try {
				FacebookCredentials facebookCredentials = (FacebookCredentials) facebookOAuth.aquireAccessToken(authorizationCode, PortalUtil.getUserId(request));

				try {
					FacebookCredentialsLocalServiceUtil.getFacebookCredentials(facebookCredentials.getUserId());
					FacebookCredentialsLocalServiceUtil.updateFacebookCredentials(facebookCredentials);
					%>
					<liferay-ui:message key="handler-facebook-settings-updated"/>

				<%}
				catch (Exception e) {
					FacebookCredentialsLocalServiceUtil.addFacebookCredentials(facebookCredentials);%>
					<liferay-ui:message key="handler-facebook-settings-added"/>
				<%}
				finally{
					new FacebookGetUserIdWorker(facebookCredentials.getAccessToken(), PortalUtil.getUserId(request)).run();
				}

			}
			catch (ErrorReceivingAccessTokenException e) {
				%>
				<liferay-ui:message key="handler-facebook-settings-error"/>
				<%
			}%>
