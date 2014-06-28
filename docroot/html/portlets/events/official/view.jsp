<%@page import="javax.portlet.WindowState"%>
<%@ include file="/html/init.jsp"%>
<%@ include file="/html/portlets/events/social-plugins-fragment.jsp" %>

<portlet:renderURL var="addEvent" windowState="maximized">
	<portlet:param name="<%=Constants.TODO%>"
		value="<%=Constants.CREATE_OFFICIAL_EVENT %>"></portlet:param>
</portlet:renderURL>







<%if(PermissionsUtil.isCampaignLeader(request)){ %>
<div><a href="<%=addEvent%>"><liferay-ui:message key="events-create-event"/></a></div>
<%} %>

<%

	Long userId = PortalUtil.getUserId(request);

	PortletURL renderURL = renderResponse.createRenderURL();
	DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
	
	PortletURL showDetails = renderResponse.createRenderURL();
	showDetails.setWindowState(WindowState.MAXIMIZED);
	showDetails.setParameter(Constants.TODO, Constants.SHOW_OFFICIAL_EVENT);
	
	PortletURL joinEvent =  renderResponse.createActionURL();
	joinEvent.setParameter("javax.portlet.action", "joinEvent");
	
	PortletURL deleteEvent =  renderResponse.createActionURL();
	deleteEvent.setParameter("javax.portlet.action", "deleteEvent");
	
	PortletURL unsubscribeFromEvent =  renderResponse.createActionURL();
	unsubscribeFromEvent.setParameter("javax.portlet.action", "unsubscribeFromEvent");
	
	//List<Event> events = EventLocalServiceUtil.getUpcomingOfficialEvents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	List<Event> events = EventLocalServiceUtil.getUpcomingOfficialEvents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	//Event event2 = EventLocalServiceUtil.getEvent(25);
	//new GoogleEventCreationWorker(event2).run();
%>


<liferay-ui:message key="events-all-upcoming-official"/>

<liferay-ui:search-container delta="10" iteratorURL="<%= renderURL %>">
	<liferay-ui:search-container-results>
		<%
			searchContainer.setResults(events);
					searchContainer.setTotal(events.size());
					pageContext.setAttribute("results", events.subList(searchContainer.getStart(), searchContainer.getResultEnd()));
					pageContext.setAttribute("total", events.size());
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="de.tum.in.ziller.thesis.social_campaign.model.Event"
		keyProperty="eventId" modelVar="event">

		<liferay-ui:search-container-column-text name="Name" title="title" orderableProperty="title">
		
			<%=event.getTitle()%>
			<%if(event.getFacebookEventId() != null && !event.getFacebookEventId().equalsIgnoreCase("")){ %>
			<a
				href='https://www.facebook.com/event.php?eid=<%=event.getFacebookEventId()%>' style="margin-left:10px"><img width="15" height="15"
				src="<%=request.getContextPath() %>/images/facebook.png">
			</a><%} %><br>
			(<%=EventAttendenceLocalServiceUtil.getAllEventAttendencesForEvent(event.getEventId()).size() %> <liferay-ui:message key="events-attendents"/>)
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Wann">
			<%=df.format(event.getStartingTime()) %> - <br> <%=df.format(event.getEndingTime()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="Wo">
			<%=event.getLocationAsString()%><br>
			<a
				href="http://maps.google.de/maps?f=q&amp;source=embed&amp;hl=de&amp;geocode=&amp;q=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;aq=t&amp;sll=&amp;sspn=&amp;vpsrc=0&amp;ie=UTF8&amp;hq=&amp;hnear=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;t=m&amp;z=14&amp;ll=<%out.println((event.getLocationLatitude() != 0 || event.getLocationLatitude() != 0) ? URLEncoder.encode(event.getLocationLatitude() + "," + event.getLocationLongitude()) : "");%>"
				style='color: #0000FF; text-align: left'><img width="25" height="25"
				src="<%=request.getContextPath() %>/images/gmaps-logo.png">
			</a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Kategorie">
			<%try{
				out.println(CategoryLocalServiceUtil.getCategory(event.getCategoryId()).getCategoryName());
				}
			catch(Exception e){
				out.println("no");
				} %>
		</liferay-ui:search-container-column-text>
		

		<liferay-ui:search-container-column-text>
			
			<%if(PermissionsUtil.isElector(request)){
				if(EventUtil.userJoinsEvent(userId, event.getEventId())){
			unsubscribeFromEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));%>
			<span><liferay-ui:message key="events-user-attends-event"/>(<a href="<%=unsubscribeFromEvent.toString()%>"><liferay-ui:message key="events-unsubscribe-from-event"/></a>)</span>
			<br>
			<%}else
				if(!PermissionsUtil.isEventCreator(userId, event.getEventId())){
				joinEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));%>
				<a href="<%=joinEvent.toString()%>"><liferay-ui:message key="events-join-event"/></a>
				<br>
			<%}
			} %>
			
			<%if(PermissionsUtil.isEventCreator(userId, event.getEventId()) || PermissionsUtil.isCampaignLeader(request)){ 
			deleteEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId())); %>
			<a href="<%=deleteEvent.toString()%>"><liferay-ui:message key="events-delete-event"/></a>
			<br>
			<%} %>
			<%showDetails.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId())); %>
			<a href="<%=showDetails.toString() %>"><liferay-ui:message key="events-show-event-details"/></a>
		</liferay-ui:search-container-column-text>
		<%if(!PermissionsUtil.isCampaignLeader(request)){ %>	
		<liferay-ui:search-container-column-text>
			<div class="fb-like" data-href="<%=showDetails.toString() %>" data-send="true" data-layout="box_count" data-width="55" data-show-faces="true" data-action="recommend"></div>
		</liferay-ui:search-container-column-text>
		<%} %>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<!-- <div class="fb-like-box" data-href="http://www.facebook.com/apps/application.php?id=213686908686905" data-width="292" data-show-faces="true" data-stream="true" data-header="true"></div> -->

