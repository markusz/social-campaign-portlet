<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletMode"%>
<%@ include file="/html/init.jsp"%>
<%@ include file="/html/portlets/events/social-plugins-fragment.jsp" %>

<portlet:renderURL var="addEvent" windowState="maximized">
	<portlet:param name="<%=Constants.TODO%>" value="<%=Constants.CREATE_INOFFICIAL_EVENT %>"></portlet:param>
	
</portlet:renderURL>

<% %>


<%-- <aui:button-row><aui:button onClick="location.href(addEvent)"><liferay-ui:message key="events-create-event"/></aui:button></aui:button-row> --%>


 <div id="map_canvas" style="width:90%; height:50%"></div>


<%if(PermissionsUtil.isElector(request)){ %>
<a href="<%=addEvent%>"><liferay-ui:message key="events-create-event"/></a><br>
<%} %>

	
	<%
	
	Long userId = PortalUtil.getUserId(request);
	try{
		
	//out.println(OAuthGoogleImpl.getAccessTokenAndRefreshIfRequired(userId));
	}
	catch(Exception e){
		out.println("error:" +e.getMessage());
	}
	DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
	
	PortletURL renderURL = renderResponse.createRenderURL();
	
	PortletURL showDetails = renderResponse.createRenderURL();
	showDetails.setWindowState(WindowState.MAXIMIZED);
	showDetails.setParameter(Constants.TODO, Constants.SHOW_INOFFICIAL_EVENT);
	
	PortletURL deleteEvent =  renderResponse.createActionURL();
	deleteEvent.setParameter("javax.portlet.action", "deleteEvent");
	
	PortletURL joinEvent =  renderResponse.createActionURL();
	joinEvent.setParameter("javax.portlet.action", "joinEvent");
	
	PortletURL makeEventOfficial =  renderResponse.createActionURL();
	makeEventOfficial.setParameter("javax.portlet.action", "makeEventOfficial");
	
	PortletURL unsubscribeFromEvent =  renderResponse.createActionURL();
	unsubscribeFromEvent.setParameter("javax.portlet.action", "unsubscribeFromEvent");
	
	double range = Double.MAX_VALUE;
	GeneralSettings settings = null;
	
	try{
	settings = GeneralSettingsLocalServiceUtil.getGeneralSettings(userId);
	}
	catch(NoSuchGeneralSettingsException e){
		
	}
	catch(PortalException e){
		
	}
	catch(SystemException e){
	
	}
	
	List<Event> events = new ArrayList<Event>();
	
	
	
	
	if(!PermissionsUtil.isLoggedIn(request) || PermissionsUtil.isCampaignLeader(request) || settings == null || (settings.getHomebaseLatitude() == 0 && settings.getHomebaseLongitude() == 0)){ 
		if(PermissionsUtil.isElector(request)) {%>
		<liferay-ui:message key="events-no-homebase-added-display-all-inofficial"/>
		<%}
		events = EventLocalServiceUtil.getUpcomingInofficialEventsInRange(0, 0, range, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}else{
		if(settings.getInofficialEventsSearchRadius() > 0){
		
		String output = portletConfig.getResourceBundle(request.getLocale()).getString("events-homebase-added-displaying-range");
		output = java.text.MessageFormat.format(output, new Object[] {""+settings.getInofficialEventsSearchRadius(), settings.getHomebase()});
		%>
		<%=output %>
		<%-- <liferay-ui:message key="events-homebase-added-displaying-range"/> --%>
		
		<%
			range = settings.getInofficialEventsSearchRadius();
		}else{
		%>
		<liferay-ui:message key="events-homebase-added-but-no-range-setting-custom-range"/>
		<%
		}
		events = EventLocalServiceUtil.getUpcomingInofficialEventsInRange(settings.getHomebaseLatitude(), settings.getHomebaseLongitude(), range, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}
	%>

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
		className="de.tum.in.ziller.thesis.social_campaign.model.Event" keyProperty="eventId" modelVar="event" stringKey="test123">


		<liferay-ui:search-container-column-text orderable="<%=true %>"
			name="Name" title="title" orderableProperty="title">
			<%=event.getTitle() %>
			<%if(event.getFacebookEventId() != null && !event.getFacebookEventId().equalsIgnoreCase("")){ %>
			<a
				href='https://www.facebook.com/event.php?eid=<%=event.getFacebookEventId()%>' style="margin-left:10px"><img
				width="15" height="15" src="<%=request.getContextPath() %>/images/facebook.png">
			</a><%} %><br>
			(<%=EventAttendenceLocalServiceUtil.getAllEventAttendencesForEvent(event.getEventId()).size() %> <liferay-ui:message key="events-attendents"/>)
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Wann">
			<%=df.format(event.getStartingTime()) %> - <br> <%=df.format(event.getEndingTime()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Wo">
			<%=event.getLocationAsString() %><br>
			<a href="http://maps.google.de/maps?f=q&amp;source=embed&amp;hl=de&amp;geocode=&amp;q=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;aq=t&amp;sll=&amp;sspn=&amp;vpsrc=0&amp;ie=UTF8&amp;hq=&amp;hnear=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;t=m&amp;z=14&amp;ll=<%out.println((event.getLocationLatitude() != 0 || event.getLocationLatitude() != 0) ? URLEncoder.encode(event.getLocationLatitude() + "," + event.getLocationLongitude()) : "");%>"
				style='color: #0000FF; text-align: left'><img width="25" height="25" src="<%=request.getContextPath() %>/images/gmaps-logo.png"></a>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="Organisator">
			<%-- <%=UserLocalServiceUtil.getUser(event.getCreatorId()).getFullName() %> --%>
			<% User eventCreator = UserLocalServiceUtil.getUser(event.getCreatorId());
			FacebookAccount facebookAccount = null;
			try{
			facebookAccount = FacebookAccountLocalServiceUtil.getFacebookAccount(eventCreator.getUserId());}
			catch(Exception e){
			}%>
			<img style="height: 30px;" src="/image/user_portrait?img_id=<%=eventCreator.getPortraitId() %>" /><br>
			<%=eventCreator.getFullName()+"  " %><%if(eventCreator.getFacebookId() > 0){ %><a href='http://www.facebook.com/profile.php?id=<%=eventCreator.getFacebookId()%>'><img width="12" height="12" src="<%=request.getContextPath() %>/images/facebook.png"></a><%} %>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="Kategorie">
			<%try{
				out.println(CategoryLocalServiceUtil.getCategory(event.getCategoryId()).getCategoryName());
				}
			catch(Exception e){
				out.println("");
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
				joinEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));
			%>
				<a href="<%=joinEvent.toString()%>"><liferay-ui:message key="events-join-event"/></a>
			<br>
			<%}
				
			
			} %>
			
			<%if(PermissionsUtil.isEventCreator(userId, event.getEventId()) || PermissionsUtil.isCampaignLeader(request)){
			deleteEvent.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId())); %>
			<a href="<%=deleteEvent.toString()%>"><liferay-ui:message key="events-delete-event"/></a>
			<br>
			
			<% }%>
			<%showDetails.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId()));%>
		 	<a href="<%=showDetails.toString() %>"><liferay-ui:message key="events-show-event-details"/></a>
		 	<br>
		 	
		 	<%if(PermissionsUtil.isCampaignLeader(request)){
		 	makeEventOfficial.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId())); %>
		 	<a href="<%=makeEventOfficial.toString() %>"><liferay-ui:message key="events-make-event-official"/></a>
		 	<%} %>
		 	
		</liferay-ui:search-container-column-text>
		<%if(!PermissionsUtil.isCampaignLeader(request)){ %>
		<liferay-ui:search-container-column-text>
			<div class="fb-like" data-href="<%=showDetails.toString()%>" data-send="true" data-layout="box_count" data-width="55" data-show-faces="true" data-action="recommend"></div>
		</liferay-ui:search-container-column-text>
<%} %>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

