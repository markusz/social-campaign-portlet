<%@page import="de.tum.in.ziller.thesis.social_campaign.util.portlet.SettingsUtil"%>
<%@ include file="/html/init.jsp"%>

<%
	Long userId = PortalUtil.getUserId(request);
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

<script type="text/javascript">


function removeRow($rowNumber) {
    if (confirm('<liferay-ui:message key="events-confirm-delete" />')) {
    	jQuery($rowNumber).parent().remove();
    }

}

	

function addRow() {
	var num = parseInt((jQuery("#assistences tr:last").attr('id')));
	num++;
	//alert(num);
	var clonedSelectBox = jQuery('#assistencecategories_1').clone();
	//alert(clonedSelectBox.html());
	//clonedSelectBox.attr("id", "assistencecategories_"+num);
	//alert(clonedSelectBox.html());
	add = new String("<td onClick='addRow()'><img src='<%=request.getContextPath()%>/images/add-icon.png'/></td>");
	remove = new String("<td onclick='removeRow(this)'><img src='<%=request.getContextPath()%>/images/delete.png'/></td>");
	quantity = new String("<td><input size='5' name='quantity_"+num+"'></input></td>");
	
	ind = new String("<td></td>");


	jQuery("#assistences").append("<tr id='"+num+"'>"+remove+""+add+""+quantity+""+ind+"</tr>");
	//alert(clonedSelectBox.html());
	jQuery("#assistences tr:last td:last").append("<select name='assistencecategories_"+num+"' id='assistencecategories_"+num+"'>"+clonedSelectBox.html()+"</select>");
	
}

function addFirstRow() {
	var num = parseInt((jQuery("#assistences tr:last").attr('id')));
	num++;
	var clonedSelectBox = jQuery('#assistencecategories_1').clone();

	add = new String("<td onClick='addRow()'><img src='<%=request.getContextPath()%>/images/add-icon.png'/></td>");
	remove = new String("<td onclick='removeRow(this)'><img src='<%=request.getContextPath()%>/images/delete.png'/></td>");
	quantity = new String("<td><input size='5' name='quantity_"+num+"'></input></td>");
	
	ind = new String("<td></td>");


	jQuery("#assistences").append("<tr id='"+num+"'>"+remove+""+add+""+quantity+""+ind+"</tr>");
	//alert(clonedSelectBox.html());
	jQuery("#assistences tr:last td:last").append("<select name='assistencecategories_"+num+"' id='assistencecategories_"+num+"'>"+clonedSelectBox.html()+"</select>");
	
}


</script>

<aui:form name="<portlet:namespace/><%=de.tum.in.ziller.thesis.social_campaign.util.Constants.FORM_NAME_ADD_OFFICIAL_EVENT %>" method="post" action="<%=addEvent%>">
	<aui:layout>
		<aui:column>
			<aui:model-context bean="<%= event %>" model="<%=Event.class%>" />
			
			<aui:panel label="General Event Settings" collapsible="true">

			<%
				if (event.getEventId() > 0) {
			%>
			<aui:input name="facebookEventId" disabled="true" />
			<%
				}
			%>

			<aui:input name="title" helpMessage="event-name" />
			<aui:input name="description" helpMessage="event-description" />
			<aui:input name="locationAsString" helpMessage="events-location-as-string-infobox"/>
			<aui:input name="locationLatitude" />
			<aui:input name="locationLongitude" />
			<%Calendar cal = new GregorianCalendar();
			cal.setTime(event.getStartingTime() != null ? event.getStartingTime() : new Date());%>
			<aui:input name="startingTime" value="<%=cal %>"/>
			<%cal.setTime(event.getEndingTime() != null ? event.getEndingTime() : new Date()); %>
			<aui:input name="endingTime" value="<%=cal %>"/>
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
			</aui:panel>

			<aui:panel label="Assistence" collapsible="true">
			
			<div>
		 	<table id="assistences">
		 	
		 		<%
					if (event.getEventId() > 0) {
						List<AssistenceRequest> requests = AssistenceRequestLocalServiceUtil.getAssistenceRequestForEvent(event.getEventId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						
						int counter = 1;
						for(AssistenceRequest assistenceRequest :  requests){%>
							
						<tr id="<%=counter%>">
					
					
					<%if(counter < 2){%><td></td><%}else{%> <td onclick='removeRow(this)'><img src='<%=request.getContextPath()%>/images/delete.png'/></td><%} %> 
					<td onClick="addRow()"><img src='<%=request.getContextPath()%>/images/add-icon.png' alt='<liferay-ui:message key="events-add-row" />' /></td>
					<td><input size="5" name="quantity_<%=counter%>" id="quantity_<%=counter%>" value="<%=assistenceRequest.getQuantity() %>"></input></td>
					<td><select name="assistencecategories_<%=counter%>" id="assistencecategories_<%=counter%>">
					<%List<AssistenceRole> assistenceRoles = AssistenceRoleLocalServiceUtil.getAssistenceRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
					for(AssistenceRole assistenceRole:assistenceRoles){%>
					
						<aui:option selected="<%=assistenceRole.getRoleId() == assistenceRequest.getRoleId()%>" value="<%=assistenceRole.getRoleId() %>">
							<%=assistenceRole.getName()%>
						</aui:option>
					<%} %>
					</select></td>
				</tr>
						
						

						<%
						counter++;
						}
				%>
				
				
				
				
				<%}else{ %>
		 		
				<tr id="1">
					<td></td> 
					<td onClick="addRow()"><img src='<%=request.getContextPath()%>/images/add-icon.png' alt='<liferay-ui:message key="events-add-row" />' /></td>
					<td><input size="5" name="quantity_1" id="quantity_1"></input></td>
					<td><select name="assistencecategories_1" id="assistencecategories_1">
					<%List<AssistenceRole> assistenceRoles = AssistenceRoleLocalServiceUtil.getAssistenceRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
					for(AssistenceRole assistenceRole:assistenceRoles){%>
					
						<aui:option value="<%=assistenceRole.getRoleId() %>">
							<%=assistenceRole.getName()%>
						</aui:option>
					<%} %>
					</select></td>
				</tr>
				
				<%} %>
			</table>
			
			
			
			
			
			</div>

			</aui:panel>
			<%
			SocialSettings settings = SettingsUtil.getExistingOrCreateNewSocialSettingsForUser(userId);
		%>
			<aui:model-context bean="<%= settings %>" model="<%=SocialSettings.class%>" />
			
			<aui:panel label="events-social-settings">
			<aui:input name="autoPublishOnFacebook" label="settings-publish-on-facebook" helpMessage="settings-publish-on-facebook-infotext"/>
			<aui:input name="autoPublishOnGoogle" label="settings-publish-on-google" helpMessage="settings-publish-on-google-infotext" />
			</aui:panel>
			<aui:button-row cssClass="xyz">
				<aui:button type="submit" name="add" value="submit" />
			</aui:button-row>
			
		</aui:column>
	</aui:layout>
</aui:form>
