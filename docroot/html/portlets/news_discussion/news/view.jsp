<%@ include file="/html/init.jsp"%>

<portlet:renderURL var="addNews">
	<portlet:param name="<%=Constants.TODO%>"
		value="<%=Constants.CREATE_NEWS %>"></portlet:param>
</portlet:renderURL>

<%
	if (PermissionsUtil.isCampaignLeader(request)) {
%>
<aui:layout>
<aui:panel label="Verwalten"><a href="<%=addNews%>"><liferay-ui:message key="news-create-news" /></a></aui:panel>
</aui:layout>
<%
	}
%>



<%
	DateFormat df = new SimpleDateFormat("dd.MM.yy");

	PortletURL deleteNews = renderResponse.createActionURL();
	deleteNews.setParameter("javax.portlet.action", "deleteNews");

	PortletURL renderURL = renderResponse.createRenderURL();
	List<News> newsList = NewsLocalServiceUtil.getNewses(QueryUtil.ALL_POS, 10);
%>


<aui:layout>
	<%
		for (News news : newsList) {
				deleteNews.setParameter(Constants.NEWS_ID, String.valueOf(news.getNewsId()));
	%>
<%
	String label = df.format(news.getPublishDate()) + " - " + news.getTitle();
%>
<aui:panel collapsible="true" label="<%=label%>">

<%
	if (PermissionsUtil.isCampaignLeader(request)) {
%>	
<br><a href="<%=deleteNews.toString()%>"> <liferay-ui:message
			key="news-delete-news" /> </a><br><%
 	}
 %>
			 <%
			 	if (news.getNewsTypeId() == 1) {
			 %> <div align="center"><object width="350" height="199">
				<param name="movie"
					value="http://www.youtube.com/v/<%=news.getContent()%>?version=3&amp;hl=de_DE"></param>
				<param name="allowFullScreen" value="true"></param>
				<param name="allowscriptaccess" value="always"></param>
				<embed
					src="http://www.youtube.com/v/<%=news.getContent()%>?version=3&amp;hl=de_DE"
					type="application/x-shockwave-flash" width="350" height="199"
					allowscriptaccess="always" allowfullscreen="true"></embed>
			</object></div> <%
 	}
 %>
		


	</aui:panel>
	<%
		}
	%>
	
	</aui:layout>


