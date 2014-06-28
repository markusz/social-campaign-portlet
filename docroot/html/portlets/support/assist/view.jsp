<%@page import="javax.portlet.WindowState"%>
<%@ include file="/html/init.jsp"%>

<portlet:renderURL var="addEvent">
	<portlet:param name="<%=Constants.TODO%>"
		value="<%=Constants.CREATE_OFFICIAL_EVENT %>"></portlet:param>
</portlet:renderURL>

<%
	Long userId = PortalUtil.getUserId(request); 

	PortletURL renderURL = renderResponse.createRenderURL();
	PortletURL assistURL = renderResponse.createRenderURL();
	
	PortletURL showDetails = renderResponse.createRenderURL();
	showDetails.setWindowState(WindowState.MAXIMIZED);
	showDetails.setParameter(Constants.TODO, Constants.SHOW_INOFFICIAL_EVENT);
	
	assistURL.setParameter(Constants.TODO, Constants.SHOW_REQUIRED_ASSISTENCE_FOR_EVENT);
	DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

	GeneralSettings settings = null;
	try{
	settings = GeneralSettingsLocalServiceUtil.getGeneralSettings(userId);
	}
	catch(NoSuchGeneralSettingsException e){
		
	}
	catch(PortalException e){
		
	}
	catch(SystemException e){
	
	};
	
	List<Event> events = new ArrayList<Event>();
	List<Object[]> list = new ArrayList<Object[]>();
	
	if(settings == null || (settings.getHomebaseLatitude() == 0 && settings.getHomebaseLongitude() == 0)){
		list = EventLocalServiceUtil.getUpcomingEventsInRangeThatNeedAssistence(0., 0., Double.MAX_VALUE, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		events = EventLocalServiceUtil.getEvents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}else{
		double range = 200.;
		
		
		if(settings.getInofficialEventsSearchRadius() > 0){
			range = settings.getInofficialEventsSearchRadius();
			list = EventLocalServiceUtil.getUpcomingEventsInRangeThatNeedAssistence(settings.getHomebaseLatitude(), settings.getHomebaseLongitude(), range, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			events = EventLocalServiceUtil.getUpcomingInofficialEventsInRange(settings.getHomebaseLatitude(), settings.getHomebaseLongitude(), range, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
	}
	

%>

<liferay-ui:search-container delta="10" iteratorURL="<%= renderURL %>">
	<liferay-ui:search-container-results>
		<%
			searchContainer.setResults(list);
					searchContainer.setTotal(list.size());
					pageContext.setAttribute("results", list.subList(searchContainer.getStart(), searchContainer.getResultEnd()));
					pageContext.setAttribute("total", list.size());
		%>
		
		

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="java.lang.Object[]" modelVar="object">

		<%Event event = EventLocalServiceUtil.getEvent((Long) object[0]); %>

		<liferay-ui:search-container-column-text
			name="Name" title="title" orderableProperty="title">
			<%showDetails.setParameter(Constants.EVENT_ID, String.valueOf(event.getEventId())); %>
			<a href="<%=showDetails.toString()%>"><%=event.getTitle()%></a>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Wann">
			<%=df.format(event.getStartingTime()) %> - <br> <%=df.format(event.getEndingTime()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="Wo">
			<%=event.getLocationAsString()%>
			<a
				href="http://maps.google.de/maps?f=q&amp;source=embed&amp;hl=de&amp;geocode=&amp;<%out.println((event.getLocationLatitude() == 0. & event.getLocationLongitude() == 0.) ? "q=" + event.getLocationAsString() : "sll="+event.getLocationLatitude()+","+event.getLocationLongitude());%>&z=13"
				style='color: #0000FF; text-align: left'><img width="30" height="30"
				src="<%=request.getContextPath() %>/images/gmaps-logo.png">
			</a>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text
			name="Wer">
			<%
			try{
			User creator  = UserLocalServiceUtil.getUser(event.getCreatorId()); %>
			<img style="height: 30px;" src="/image/user_portrait?img_id=<%=creator.getPortraitId() %>" />
			<br>
			<%=creator.getFullName()+"  " %><%if(creator.getFacebookId() > 0){ %><a href='http://www.facebook.com/profile.php?id=<%=creator.getFacebookId()%>'><img width="12" height="12" src="<%=request.getContextPath() %>/images/facebook.png"></a><%} %>
		<% }catch(Exception e){}%>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="Kategorie">
			<%=CategoryLocalServiceUtil.getCategory(event.getCategoryId()).getCategoryName()%>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="Was wird gesucht?">
		
		<%assistURL.setParameter("eventId", String.valueOf(event.getEventId())); %>
		
			
			<% List<AssistenceRequest> assistenceRequests = AssistenceRequestLocalServiceUtil.getAssistenceRequestForEvent(event.getEventId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if(assistenceRequests.size() > 0){%>
			<a href="<%=assistURL.toString()%>"><%
				for(AssistenceRequest assistenceRequest:assistenceRequests){%>
				
				<div><%if(assistenceRequest.getQuantity() > 0) %> <%=AssistenceRoleLocalServiceUtil.getAssistenceRole(assistenceRequest.getRoleId()).getName() %></div>
			
			<%}%>
			</a>
			<%
			
			}else{ %>
				<a href="<%=UserLocalServiceUtil.getUserById(event.getCreatorId()).getEmailAddress()%>>"><liferay-ui:message key="support-contact-event-creator"></liferay-ui:message></a>
			<%} %>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>


