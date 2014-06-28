<%@ include file="/html/init.jsp"%>

<%

String output = portletConfig.getResourceBundle(request.getLocale()).getString("donation-thank-you-message");
output = java.text.MessageFormat.format(output, new Object[] {""+PortalUtil.getUser(request).getFirstName()});
%>
<%=output %>