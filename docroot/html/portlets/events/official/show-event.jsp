<%@ include file="/html/init.jsp"%>
<%@ include file="/html/portlets/events/social-plugins-fragment.jsp"%>

<portlet:actionURL var="deregister" windowState="normal"
	name="unsubscribe" />

<portlet:actionURL var="register" windowState="normal" name="register" />



<style type="text/css">
#p2 {
	padding-left: 30px;
	padding-top: 3px;
	font-size: 110%;
}

#p1 {
	padding-right: 10px;
	padding-top: 3px;
	font-size: 110%;
}

#title1 {
	padding-right: 10px;
	padding-top: 10px;
	font-size: 130%;
}

#option { /* padding-left: 10px; */
	padding-top: 5px;
	font-size: 80%;
}

#title2 {
	padding-left: 30px;
	padding-top: 10px;
	font-size: 130%;
}

#p3 {
	padding-left: 300px;
	background-color: #FFFF99;
	font-size: 120%;
}
</style>

<%
	DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

	Long userId = PortalUtil.getUserId(request);
	Long eventId = ParamUtil.getLong(request, Constants.EVENT_ID);

	PortletURL editEvent = renderResponse.createRenderURL();
	editEvent.setParameter(Constants.TODO, Constants.CREATE_OFFICIAL_EVENT);

	PortletURL showDetails = renderResponse.createRenderURL();
	showDetails.setParameter(Constants.TODO, Constants.SHOW_OFFICIAL_EVENT);

	PortletURL joinEvent = renderResponse.createActionURL();
	joinEvent.setParameter("javax.portlet.action", "joinEvent");

	PortletURL deleteEvent = renderResponse.createActionURL();
	deleteEvent.setParameter("javax.portlet.action", "deleteEvent");

	PortletURL unsubscribeFromEvent = renderResponse.createActionURL();
	unsubscribeFromEvent.setParameter("javax.portlet.action", "unsubscribeFromEvent");

	try {

		Event event = EventLocalServiceUtil.getEvent(eventId);
%>

<aui:panel>
	<%
		if (PermissionsUtil.isElector(request)) {
					if (EventUtil.userJoinsEvent(userId, event.getEventId())) {
						unsubscribeFromEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));
	%>
	<span><liferay-ui:message key="events-user-attends-event" />(<a
		href="<%=unsubscribeFromEvent.toString()%>"><liferay-ui:message
				key="events-unsubscribe-from-event" /> </a>)</span>
	<br>
	<%
		} else if (!PermissionsUtil.isEventCreator(userId, event.getEventId())) {
						joinEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));
	%>
	<a href="<%=joinEvent.toString()%>"> <liferay-ui:message
			key="events-join-event" /> </a>
	<br>
	<%
		}
				}
	
		if (PermissionsUtil.isEventCreator(userId, event.getEventId()) || PermissionsUtil.isCampaignLeader(request)) {
	
		deleteEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));
	%>
	<a href="<%=deleteEvent.toString()%>"> <liferay-ui:message
			key="events-delete-event" /> </a>
	<br>
	<%
		}
	%>
</aui:panel>

<table border="0">
	<tr>
		<td style="padding: 10px 70px 50px 20px; vertical-align:top">
			<table border="0">
				<tr>
					<td id="title1"><b><liferay-ui:message key="events-title" />
					</b>
						<p>
					</td>
					<td id="title2"><%=event.getTitle()%><%if(event.getFacebookEventId() != null && !event.getFacebookEventId().equalsIgnoreCase("")){ %>
			<a
				href='https://www.facebook.com/event.php?eid=<%=event.getFacebookEventId()%>' style="margin-left:10px"><img
				width="25" height="25" src="<%=request.getContextPath() %>/images/facebook.png">
			</a><%} %>
						<p>
					</td>
				</tr>

				<tr>
					<td id="p1"><b><liferay-ui:message
								key="events-description" /> </b></td>
					<td id="p2"><%=event.getDescription()%></td>
				</tr>
				<tr>
					<td id="p1"><b><liferay-ui:message key="events-location" />
					</b>
					</td>
					<td id="p2"><%=event.getLocationAsString()%></td>
				</tr>
				<tr>
					<td id="p1"><b><liferay-ui:message key="events-start" />
					</b></td>
					<td id="p2"><%=df.format(event.getStartingTime())%></td>
				</tr>
				<tr>
					<td id="p1"><b><liferay-ui:message key="events-end" /> </b></td>
					<td id="p2"><%=df.format(event.getEndingTime())%></td>
				</tr>
				<tr>
					<td id="p1" style="vertical-align: top;"><b><liferay-ui:message
								key="events-creator" /> </b></td>

					<%
						User eventCreator = UserLocalServiceUtil.getUser(event.getCreatorId());
					%>
					<td id="p2"><img style="height: 60px;"
						src="/image/user_portrait?img_id=<%=eventCreator.getPortraitId()%>" />


						<div><%=eventCreator.getFullName()%>
							<%
								if (PermissionsUtil.isCampaignLeader(request)) {
							%><br>
							<liferay-ui:message key="events-member-of-campaign-leader" />
							<%
								}
							%>
							<a href="mailto:<%=eventCreator.getEmailAddress()%>"><span
								style="font-size: 90%;">(<liferay-ui:message
										key="contact" />)</span> </a>
						</div></td>
				</tr>
				<%
					List<EventAttendence> attendences = new ArrayList<EventAttendence>();

						try {
							attendences = EventAttendenceLocalServiceUtil.getAllEventAttendencesForEvent(eventId);
							if (attendences.size() > 0) {
				%>
				<tr>
					<td id="p1"><b><liferay-ui:message
								key="events-attending-users" /> (<%=attendences.size()%>) </b>
					</td>

					<td id="p2">
						<%
							for (EventAttendence attendence : attendences) {
											User user = UserLocalServiceUtil.getUser(attendence.getUserProfileId());
						%> <img style="height: 30px;"
						onclick="location.href='mailto:<%=user.getEmailAddress()%>'"
						title="<%=user.getFullName()%>" <%=user.getUserId()%>
						src="/image/user_portrait?img_id=<%=user.getPortraitId()%>"
						alt="<%=user.getLastName()%>" /> <%
 	}
 %>
					</td>
				</tr>
				<%
					}
						}
						catch (Exception e) {
						}
				%>
			</table></td>

		<td style="padding: 20px; vertical-align:top"><iframe width="425" height="200" frameborder="0"
				scrolling="no" marginheight="0" marginwidth="0"
				src="http://maps.google.de/maps?f=q&amp;source=s_q&amp;hl=de&amp;geocode=&amp;q=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;aq=t&amp;sll=&amp;sspn=&amp;vpsrc=0&amp;ie=UTF8&amp;hq=&amp;hnear=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;t=m&amp;z=14&amp;ll=<%out.println((event.getLocationLatitude() != 0 || event.getLocationLatitude() != 0) ? URLEncoder.encode(event.getLocationLatitude() + "," + event.getLocationLongitude()) : "");%>&amp;output=embed"></iframe>
			<br /> <small><a
				href="http://maps.google.de/maps?f=q&amp;source=embed&amp;hl=de&amp;geocode=&amp;q=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;aq=t&amp;sll=&amp;sspn=&amp;vpsrc=0&amp;ie=UTF8&amp;hq=&amp;hnear=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;t=m&amp;z=14&amp;ll=<%out.println((event.getLocationLatitude() != 0 || event.getLocationLatitude() != 0) ? URLEncoder.encode(event.getLocationLatitude() + "," + event.getLocationLongitude()) : "");%>"
				style="color: #0000FF; text-align: left">Größere Kartenansicht</a> </small>
		</td>
	</tr>
</table>


<%
	}
	catch (Exception e) {
%>
<liferay-ui:message key="events-error-displaying-event" />
<%
	}
%>

<div class="fb-like"
	data-href="<%=PortalUtil.getCurrentCompleteURL(request)%>"
	data-send="true" data-width="450" data-show-faces="true"
	data-action="recommend"></div>
<br>
<div class="fb-comments"
	data-href="<%=PortalUtil.getCurrentCompleteURL(request)%>"
	data-num-posts="2" data-width="500"></div>
