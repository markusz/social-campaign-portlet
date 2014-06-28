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

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.portlet.ActionRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;
import de.tum.in.ziller.thesis.social_campaign.service.base.EventLocalServiceBaseImpl;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventFinderUtil;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventUtil;

/**
 * The implementation of the Event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.tum.in.ziller.thesis.social_campaign.service.EventLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil} to access the Event local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Markus Ziller
 * @see de.tum.in.ziller.thesis.social_campaign.service.base.EventLocalServiceBaseImpl
 * @see de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil
 */
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {
	
	public List<Event> getEventsByType(boolean isOfficial) throws PortalException, SystemException {

	    return EventUtil.findByisOfficial(isOfficial);
	}
	
//	public List<Event> findUpcomingEventsInRange(double startingPosLatitude, double startingPosLongitude, double radius, int start, int end) throws PortalException, SystemException{
//		//erzeugt rekursion
//		return EventFinderUtil.findUpcomingEventsInRange(startingPosLongitude, startingPosLatitude, radius, start, end);	}
	
	public List<Event> getUpcomingInofficialEventsInRange(double startingPosLatitude, double startingPosLongitude, double radius, int start, int end) throws PortalException, SystemException, RemoteException {
		return EventFinderUtil.findUpcomingInofficialEventsInRange(startingPosLongitude, startingPosLatitude, radius, start, end);	}
	
	public List<Object[]> getUpcomingEventsInRangeThatNeedAssistence(double startingPosLatitude, double startingPosLongitude, double radius, int start, int end) throws PortalException, SystemException, RemoteException {
		return EventFinderUtil.findUpcomingEventsInRangeThatNeedAssistence(startingPosLongitude, startingPosLatitude, radius, start, end);	}
	
	public List<Event> getUpcomingOfficialEvents(int start, int end) throws PortalException, SystemException, RemoteException {
		return eventPersistence.findByStartingDateLaterThenAndIsOfficial(true, new Date(), start, end);	}
	public Event addEvent(ActionRequest req,boolean isOfficial) throws SystemException, PortalException {
		
		Event event = null;
		
		if(ParamUtil.getLong(req, "eventId", -1) >= 0)
				event = EventLocalServiceUtil.getEvent(ParamUtil.getLong(req, "eventId", -1));
		else
				event = eventPersistence.create(counterLocalService.increment());

		//event.setEventId(ParamUtil.getLong(req, "eventId"));
		event.setTitle(ParamUtil.getString(req, "title"));
		event.setDescription(ParamUtil.getString(req, "description"));
		//event.setIsOfficial(ParamUtil.getBoolean(req, "isOfficial"));
		event.setLocationAsString(ParamUtil.getString(req, "locationAsString"));
		event.setLocationLatitude(ParamUtil.getDouble(req, "locationLatitude"));
		event.setLocationLongitude(ParamUtil.getDouble(req, "locationLongitude"));
		event.setCreatorId(PortalUtil.getUserId(req));
		event.setCategoryId(ParamUtil.getLong(req, "category"));
		event.setIsOfficial(isOfficial);
		Calendar cal = new GregorianCalendar(ParamUtil.getInteger(req, "startingTimeYear"), ParamUtil.getInteger(req, "startingTimeMonth"), ParamUtil.getInteger(req, "startingTimeDay"), ParamUtil.getInteger(req, "startingTimeHour"), ParamUtil.getInteger(req, "startingTimeMinute"));
		
		event.setStartingTime(cal.getTime());

		cal = new GregorianCalendar(ParamUtil.getInteger(req, "endingTimeYear"), ParamUtil.getInteger(req, "endingTimeMonth"), ParamUtil.getInteger(req, "endingTimeDay"), ParamUtil.getInteger(req, "endingTimeHour"), ParamUtil.getInteger(req, "endingTimeMinute"));
		event.setEndingTime(cal.getTime());
		eventPersistence.update(event, false);
		return event;
	}

}

