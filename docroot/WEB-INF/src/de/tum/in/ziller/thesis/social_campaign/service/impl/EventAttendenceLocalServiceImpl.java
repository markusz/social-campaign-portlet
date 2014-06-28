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


import de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.service.base.EventAttendenceLocalServiceBaseImpl;

/**
 * The implementation of the Event Attendence local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil} to access the Event Attendence local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Markus Ziller
 * @see de.tum.in.ziller.thesis.social_campaign.service.base.EventAttendenceLocalServiceBaseImpl
 * @see de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalServiceUtil
 */
public class EventAttendenceLocalServiceImpl
	extends EventAttendenceLocalServiceBaseImpl {
	
	public  List<EventAttendence> getAllEventAttendencesForEvent(long eventId) throws NoSuchEventAttendenceException, SystemException {
		return eventAttendencePersistence.findByeventId(eventId);
	}
	
}

