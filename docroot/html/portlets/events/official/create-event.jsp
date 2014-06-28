<%@ include file="/html/init.jsp"%>

<%
	Event event = ParamUtil.getLong(request, "eventId", -1) > -1 ? EventLocalServiceUtil.getEvent(ParamUtil.getLong(request, "eventId", -1)) : new EventImpl();
	List<Category> eventCategories = CategoryLocalServiceUtil.getCategories(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

<portlet:actionURL name="addEvent" var="addEvent">
	<%
		if (ParamUtil.getInteger(request, "eventId", -1) > -1) {
	%>
	<portlet:param name="eventId"
		value='<%=ParamUtil.getString(request, "eventId")%>'></portlet:param>
	<%
		}
	%>
</portlet:actionURL>

<aui:form name="<portlet:namespace/><%=de.tum.in.ziller.thesis.social_campaign.util.Constants.FORM_NAME_ADD_OFFICIAL_EVENT %>" method="post" action="<%=addEvent%>">
	<aui:layout>
		<aui:column>
			<aui:model-context bean="<%= event %>" model="<%=Event.class%>" />

			<%
				if (event.getEventId() > 0) {
			%>
			<aui:input name="eventId" disabled="true" />
			<aui:input name="facebookEventId" disabled="true" />
			<%
				}
			%>

			<aui:input name="title" helpMessage="event-name" />
			<aui:input name="description" helpMessage="event-description" />
			<aui:input name="locationAsString" helpMessage="events-location-as-string-infobox"/>
			<aui:input name="locationLatitude" />
			<aui:input name="locationLongitude" />
			<aui:input name="startingTime" />
			<aui:input name="endingTime" />
			<aui:select name="category">
				<%
					for (Category category : eventCategories) {
				%>
				<aui:option value="<%=category.getCategoryId() %>"
					selected="<%= category.getCategoryId()==event.getCategoryId() %>">
					<%=category.getCategoryName()%>
				</aui:option>

				<%
					}
				%>

			</aui:select>
			<%
				if (event.getEventId() > 0) {
			%>
			<iframe width="425" height="200" frameborder="0"
				scrolling="no" marginheight="0" marginwidth="0"
				src="http://maps.google.de/maps?f=q&amp;source=s_q&amp;hl=de&amp;geocode=&amp;q=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;aq=t&amp;sll=&amp;sspn=&amp;vpsrc=0&amp;ie=UTF8&amp;hq=&amp;hnear=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;t=m&amp;z=14&amp;ll=<%out.println((event.getLocationLatitude() != 0 || event.getLocationLatitude() != 0) ? URLEncoder.encode(event.getLocationLatitude() + "," + event.getLocationLongitude()) : "");%>&amp;output=embed"></iframe>
			<br /> <small><a
				href="http://maps.google.de/maps?f=q&amp;source=embed&amp;hl=de&amp;geocode=&amp;q=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;aq=t&amp;sll=&amp;sspn=&amp;vpsrc=0&amp;ie=UTF8&amp;hq=&amp;hnear=<%out.println(URLEncoder.encode(event.getLocationAsString()));%>&amp;t=m&amp;z=14&amp;ll=<%out.println((event.getLocationLatitude() != 0 || event.getLocationLatitude() != 0) ? URLEncoder.encode(event.getLocationLatitude() + "," + event.getLocationLongitude()) : "");%>"
				style="color: #0000FF; text-align: left">Größere Kartenansicht</a> </small>
			<%
				}
			%>

			<aui:button-row cssClass="xyz">
				<aui:button type="submit" name="add" value="submit" />
			</aui:button-row>
		</aui:column>
	</aui:layout>
</aui:form>
