<%@page import="java.util.Date"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.model.Donation"%>
<%@page import="com.liferay.counter.service.CounterLocalServiceUtil"%>
<%@page import="de.tum.in.ziller.thesis.social_campaign.service.DonationLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Enumeration"%>
<%@ include file="/html/init.jsp"%>
<%
// read post from PayPal system and add 'cmd'
ParamUtil.print(request);

Enumeration en = request.getParameterNames();



String str = "cmd=_notify-validate";




while(en.hasMoreElements()){
String paramName = (String)en.nextElement();
String paramValue = request.getParameter(paramName);
str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue);
}



// post back to PayPal system to validate
// NOTE: change http: to https: in the following URL to verify using SSL (for increased security).
// using HTTPS requires either Java 1.4 or greater, or Java Secure Socket Extension (JSSE)
// and configured for older versions.
URL u = new URL("https://www.paypal.com/cgi-bin/webscr");

URLConnection uc = u.openConnection();

uc.setDoOutput(true);
uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
PrintWriter pw = new PrintWriter(uc.getOutputStream());
pw.println(str);
pw.close();

BufferedReader in = new BufferedReader(
new InputStreamReader(uc.getInputStream()));
String res = in.readLine();
in.close();

// assign posted variables to local variables
String itemName = request.getParameter("item_name");
String itemNumber = request.getParameter("item_number");
String paymentStatus = request.getParameter("payment_status");
String paymentAmount = request.getParameter("mc_gross");
String paymentCurrency = request.getParameter("mc_currency");
String txnId = request.getParameter("txn_id");
String receiverEmail = request.getParameter("receiver_email");
String payerEmail = request.getParameter("payer_email");
//String comment = request.getParameter("");
String custom = request.getParameter("custom");

long donatingUser = ParamUtil.getLong(request, "custom", -1);
float amount = Float.parseFloat(paymentAmount);

Donation donation = DonationLocalServiceUtil.createDonation(CounterLocalServiceUtil.increment());
donation.setDonatorId(donatingUser);
donation.setDonationDate(new Date());
donation.setDonationAmount(amount);

try{
DonationLocalServiceUtil.addDonation(donation);
}
catch(Exception e){

}
%>