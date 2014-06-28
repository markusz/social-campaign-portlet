<%@ include file="/html/init.jsp"%>

<portlet:actionURL name="addNews" var="addNews"/>

<%List<NewsType> newsTypes = NewsTypeLocalServiceUtil.getNewsTypes(QueryUtil.ALL_POS, QueryUtil.ALL_POS); %>

<aui:form name="<portlet:namespace/><%=de.tum.in.ziller.thesis.social_campaign.util.Constants.FORM_NAME_ADD_NEWS %>" method="post" action="<%=addNews %>">
	<aui:layout>
		<aui:column>
<%-- 			<aui:model-context bean="<%= news %>" model="<%=News.class%>" /> --%>

			<aui:input name="title"/>
			<aui:input name="link" />
			
			
			<aui:select name="newsTypeId">
				<%
					for (NewsType newsType : newsTypes) {
				%>
				<aui:option value="<%=newsType.getNewsTypeId() %>">
					<%=newsType.getNewsType()%>
				</aui:option>

				<%
					}
				%>

			</aui:select>

			<aui:button-row cssClass="xyz">
				<aui:button type="submit" name="add" value="submit" />
			</aui:button-row>
		</aui:column>
	</aui:layout>
</aui:form>