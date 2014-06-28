/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package de.tum.in.ziller.thesis.social_campaign.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;


import de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException;
import de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest;
import de.tum.in.ziller.thesis.social_campaign.service.base.AssistenceRequestLocalServiceBaseImpl;

/**
 * The implementation of the assistence request local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalServiceUtil} to access the assistence request local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Markus Ziller
 * @see de.tum.in.ziller.thesis.social_campaign.service.base.AssistenceRequestLocalServiceBaseImpl
 * @see de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalServiceUtil
 */
public class AssistenceRequestLocalServiceImpl
	extends AssistenceRequestLocalServiceBaseImpl {
	
	public  List<AssistenceRequest> getAssistenceRequestForEvent(long eventId, int start, int end) throws NoSuchAssistenceRequestException, SystemException {
		return assistenceRequestPersistence.findByEventId(eventId);
	}

	@Override
	public List<AssistenceRequest> getUpcomingUserEvents(long eventId, int start, int end) throws SystemException, NoSuchAssistenceRequestException {
		// TODO Auto-generated method stub
		return null;
	}
}