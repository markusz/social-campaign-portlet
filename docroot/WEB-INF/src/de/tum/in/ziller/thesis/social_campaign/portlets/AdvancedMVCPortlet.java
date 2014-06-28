package de.tum.in.ziller.thesis.social_campaign.portlets;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import de.tum.in.ziller.thesis.social_campaign.util.Constants;
import de.tum.in.ziller.thesis.social_campaign.util.ForwardingUtil;

/**
 * This class is placed between the actual portlet-classes and the
 * MVCPortlet-class. Its main purpose is to abstract tasks like forwarding to
 * the proper jsp and therefore minimize redundent code in the portlet-classes
 * 
 * @author Markus
 * 
 */
public class AdvancedMVCPortlet extends MVCPortlet {

	protected int			internalPortletId;
	public static String	fallbackJsp	= "";
	public static Log		_log		= LogFactory.getLog(AdvancedMVCPortlet.class);

	@Override
	public void doDispatch(RenderRequest req, RenderResponse res) throws IOException, PortletException {

		String todo = ParamUtil.getString(req, Constants.TODO, "");
		if(ForwardingUtil.getProperJSP(todo) != null && ForwardingUtil.getProperJSP(todo) != ""){
		include(ForwardingUtil.getProperJSP(todo), req, res);}
		else{
		doView(req, res);}

	}

}
