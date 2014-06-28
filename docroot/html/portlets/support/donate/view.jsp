<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@ include file="/html/init.jsp"%>

<portlet:renderURL var="thankYou">
	<portlet:param name="<%=Constants.TODO%>"
		value="<%=Constants.DONATION_THANK_YOU %>"></portlet:param>
</portlet:renderURL>

<% NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY); 
DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
PortletURL renderURL = renderResponse.createRenderURL();%>
<%-- <a href="<%=thankYou%>"><liferay-ui:message key="create" /></a> --%>
<%if(PermissionsUtil.isCampaignLeader(request)){ 

List<Donation> donations = DonationLocalServiceUtil.getDonations(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

Double sum = 0.;

for(Donation don : donations){
	sum += don.getDonationAmount();
	
}
%>


<%

String output = portletConfig.getResourceBundle(request.getLocale()).getString("donation-overall-donation-sum");
output = java.text.MessageFormat.format(output, new Object[] {""+nf.format(sum)});
%>
<%=output %>

<liferay-ui:search-container delta="10"
	iteratorURL="<%= renderURL %>">
	<liferay-ui:search-container-results>
		<%
			searchContainer.setResults(donations);
					searchContainer.setTotal(donations.size());
					pageContext.setAttribute("results", donations.subList(searchContainer.getStart(), searchContainer.getResultEnd()));
					pageContext.setAttribute("total", donations.size());
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="de.tum.in.ziller.thesis.social_campaign.model.Donation"
		keyProperty="donationId" modelVar="donation">
		
		<%
		User donator = null;
		if(donation.getDonatorId() > 0){
			donator = UserLocalServiceUtil.getUser(donation.getDonatorId());
			}%>

		<liferay-ui:search-container-column-text name="Spender">
			<% out.println(donator != null ? donator.getFullName() : "Anonym");%>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Wann">
			<%=df.format(donation.getDonationDate())%>
		</liferay-ui:search-container-column-text>


		<liferay-ui:search-container-column-text name="Betrag">
			<%=nf.format(donation.getDonationAmount())%>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>




<%}else
	if(PermissionsUtil.isElector(request)){
	%>
	<liferay-ui:message key="donation-list-of-own-donations"/>
	<%


List<Donation> donations = DonationLocalServiceUtil.getAllDonationsForUser(PortalUtil.getUserId(request)); %>


<liferay-ui:search-container delta="10" id="test123"
	iteratorURL="<%= renderURL %>">
	<liferay-ui:search-container-results>
		<%
			searchContainer.setResults(donations);
					searchContainer.setTotal(donations.size());
					pageContext.setAttribute("results", donations.subList(searchContainer.getStart(), searchContainer.getResultEnd()));
					pageContext.setAttribute("total", donations.size());
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="de.tum.in.ziller.thesis.social_campaign.model.Donation"
		keyProperty="donationId" modelVar="donation">

		<liferay-ui:search-container-column-text orderable="<%=true %>" name="Wann">
			<%=df.format(donation.getDonationDate())%>
		</liferay-ui:search-container-column-text>


		<liferay-ui:search-container-column-text orderable="<%=true %>" name="Betrag">
			<%=nf.format(donation.getDonationAmount())%>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>


	
	<%} %>
	
	<%if(PermissionsUtil.isElector(request) || !PermissionsUtil.isLoggedIn(request)){ %>
	<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
<input type="hidden" name="custom" value="<%=PortalUtil.getUserId(request) %>">
<input type="hidden" name="cmd" value="_s-xclick">
<input type="hidden" name="hosted_button_id" value="HLJUM85XU2K9Q">
<input type="image" src="https://www.sandbox.paypal.com/de_DE/DE/i/btn/btn_donateCC_LG.gif" border="0" name="submit" alt="Jetzt einfach, schnell und sicher online bezahlen - mit PayPal.">
<img alt="" border="0" src="https://www.sandbox.paypal.com/de_DE/i/scr/pixel.gif" width="1" height="1">
</form>
	<%} %>
