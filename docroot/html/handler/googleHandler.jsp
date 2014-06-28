<%@page import="java.util.Date"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.api.OAuth"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<portlet:defineObjects />

<% String authorizationCode = request.getParameter("code");
String str = "";
URL u = new URL(OAuth.googleAccessTokenEndpoint);
URLConnection uc = u.openConnection();
uc.setDoOutput(true);
uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
PrintWriter pw = new PrintWriter(uc.getOutputStream());
pw.println(str);
pw.close();

BufferedReader in = new BufferedReader(
new InputStreamReader(uc.getInputStream()));
String res = in.readLine();
in.close();

String googleAccessToken = request.getParameter("access_token");
String googleRefreshToken = request.getParameter("refresh_token");
Date expirationDate = new Date(System.currentTimeMillis() + Long.valueOf(request.getParameter("expires_in")));

GoogleCredentials googleCredentials = GoogleCredentialsLocalServiceUtil.createGoogleCredentials(Long.valueOf(request.getRemoteUser()));
googleCredentials.setAccessToken(googleAccessToken);
googleCredentials.setRefreshToken(googleRefreshToken);
googleCredentials.setTokenExpirationDate(expirationDate);
GoogleCredentialsLocalServiceUtil.updateGoogleCredentials(googleCredentials);
%>