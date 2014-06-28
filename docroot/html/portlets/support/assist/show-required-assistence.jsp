<%@page
	import="de.tum.in.ziller.thesis.social_campaign.service.AnonymAssistenceLocalServiceUtil"%>
<%@page
	import="de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence"%>
<%@page
	import="de.tum.in.ziller.thesis.social_campaign.model.EventAssistence"%>
<%@page
	import="de.tum.in.ziller.thesis.social_campaign.service.EventAssistenceLocalServiceUtil"%>
<%@ include file="/html/init.jsp"%>
<%@ include file="/html/portlets/events/social-plugins-fragment.jsp" %>

<%
	Long eventId = ParamUtil.getLong(request, "eventId", -1);
	Long userId = PortalUtil.getUserId(request);
	List<AssistenceRequest> requests = AssistenceRequestLocalServiceUtil.getAssistenceRequestForEvent(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	Boolean userHelps = PermissionsUtil.userHelpsForEvent(eventId, userId);

	PortletURL renderURL = renderResponse.createRenderURL();
	PortletURL toogleSupport = renderResponse.createActionURL();
	toogleSupport.setParameter("eventId", String.valueOf(eventId));

	PortletURL changeRequests = renderResponse.createActionURL();
	
	toogleSupport.setParameter("javax.portlet.action", "toggleSupport");

	changeRequests.setParameter("javax.portlet.action", "changeRequests");
	changeRequests.setParameter("eventId", String.valueOf(eventId));
%>


<liferay-ui:search-container delta="10" iteratorURL="<%= renderURL %>">
	<liferay-ui:search-container-results>
		<%
			searchContainer.setResults(requests);
					searchContainer.setTotal(requests.size());
					pageContext.setAttribute("results", requests.subList(searchContainer.getStart(), searchContainer.getResultEnd()));
					pageContext.setAttribute("total", requests.size());
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest"
		modelVar="assistenceRequest">

		<%
			Long roleId = assistenceRequest.getRoleId();

					changeRequests.setParameter("roleId", String.valueOf(roleId));
		%>

		<liferay-ui:search-container-column-text name="Aufgabe">
			<%=AssistenceRoleLocalServiceUtil.getAssistenceRole(assistenceRequest.getRoleId()).getName()%>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Angemeldet">

			<%
				List<EventAssistence> assistences = EventAssistenceLocalServiceUtil.getAllAssistencesForEventAndRole(assistenceRequest.getEventId(), assistenceRequest.getRoleId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			%>

			
				<table>
					<tr>
						<td><%=assistences.size()%></td>
					</tr>

				</table>

		</liferay-ui:search-container-column-text>


		<liferay-ui:search-container-column-text name="Benötigt">
			<form method="post" action="<%=changeRequests%>">
				<table>
					<tr>
					
<td><%=assistenceRequest.getQuantity()%></td>					
						<%
							if (PermissionsUtil.isEventCreator(userId, eventId)) {
						%>
						<td><input name="<%=assistenceRequest.getRoleId()%>_amount"
							size="2" /></td>



						<td>
							<table>
								<tr>
									<td><input type="radio" name="<%=roleId%>_manipulate"
										value="1">+ 
									</td>
								</tr>
								<tr>
									<td><input type="radio" name="<%=roleId%>_manipulate"
										value="2"> - 
									</td>
								</tr>
							</table></td>
						<td><input type="submit" value=" Absenden ">
						</td>

						<%
							}
						%>

						
					</tr>

				</table>
			</form>
		</liferay-ui:search-container-column-text>



		<liferay-ui:search-container-column-text>
			<%
				toogleSupport.setParameter("roleId", String.valueOf(assistenceRequest.getRoleId()));
				if (userHelps) {
						if (PermissionsUtil.userHelpsForEventInRole(assistenceRequest.getEventId(), userId, assistenceRequest.getRoleId())) {
							%>
							<liferay-ui:message key="support-user-helps-in-this-role" />
							<a href="<%=toogleSupport.toString()%>">(<liferay-ui:message
							key="support-revoke-support" />)</a>
							<%
						} else {
							%>
							<liferay-ui:message key="support-already-helps" />
							<%
						}
				} else {
						if (PermissionsUtil.isEventCreator(userId, eventId)) {
							List<EventAssistence> assists = EventAssistenceLocalServiceUtil.getAllAssistencesForEventAndRole(eventId, roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
							if(assists.size() > 0){
								%>
								<liferay-ui:message key="support-list-of-helpers"></liferay-ui:message><br>
								<%
							}else{
								%>
								<liferay-ui:message key="support-no-helpers-so-far"></liferay-ui:message>
								<%
							}

							for (EventAssistence assist : assists) {
								User user = UserLocalServiceUtil.getUser(assist.getUserId());
								%>
								<img style="height: 25px;"
									onclick="location.href='mailto:<%=user.getEmailAddress()%>'"
									title="<%=user.getFullName()%>" <%=user.getUserId()%>
									src="/image/user_portrait?img_id=<%=user.getPortraitId()%>"
									alt="<%=user.getLastName()%>" />
								<%
							}
						} else {
								if(PermissionsUtil.isLoggedIn(request)){
									if(!PermissionsUtil.isCampaignLeader(request)){
									%>
									<a href="<%=toogleSupport.toString()%>"><liferay-ui:message
											key="support-support-event" /> </a>
									<%
									}
								}
								else{
									%>
									<liferay-ui:message key="support-not-logged-in" />
									<%
									
								}
						}

				}
			%>
		</liferay-ui:search-container-column-text>
		
		<%if(!PermissionsUtil.isCampaignLeader(request)){ %>
<%} %>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="fb-like" data-href="<%=PortalUtil.getCurrentCompleteURL(request)%>" data-send="true" data-layout="box_count" data-width="55" data-show-faces="true" data-action="recommend"></div>