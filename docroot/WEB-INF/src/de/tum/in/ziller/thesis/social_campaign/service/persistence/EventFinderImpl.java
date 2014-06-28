package de.tum.in.ziller.thesis.social_campaign.service.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import de.tum.in.ziller.thesis.social_campaign.NoSuchEventException;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalServiceUtil;

public class EventFinderImpl extends BasePersistenceImpl implements EventFinder {
	
	public List<Object[]> findUpcomingEventsInRangeThatNeedAssistence(double longitude, double latitude, double range, int start, int end) throws NoSuchEventException {
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get("findUpcomingEventsInRangeThatNeedAssistence");
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			//sqlQuery.addEntity("Event", de.tum.in.ziller.thesis.social_campaign.model.impl.EventImpl.class);

			sqlQuery.addScalar("eventId", Type.LONG);
			sqlQuery.addScalar("total", Type.INTEGER);
			sqlQuery.addScalar("distance", Type.DOUBLE);

			
			try {
				QueryPos queryPos = QueryPos.getInstance(sqlQuery);
				queryPos.add(latitude);
				queryPos.add(latitude);
				queryPos.add(longitude);
				queryPos.add(range);
				
			} catch (Exception e) {
				
			}

			@SuppressWarnings("unchecked")
			List<Object[]> eventsObjects = (List<Object[]>) QueryUtil.list(sqlQuery, getDialect(), start, end);


			return eventsObjects;

		} catch (ORMException e) {
			throw new NoSuchEventException();
		} finally {
			closeSession(session);
		}
	}
	
	public List<Event> findUpcomingInofficialEventsInRange(double longitude, double latitude, double range, int start, int end) throws NoSuchEventException {
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get("findEventsInRange");
			SQLQuery sqlQuery = session.createSQLQuery(sql);

			//sqlQuery.addEntity("Event", de.tum.in.ziller.thesis.social_campaign.model.impl.EventImpl.class);

			addScalars(sqlQuery);

			
			try {
				QueryPos queryPos = QueryPos.getInstance(sqlQuery);
				queryPos.add(latitude);
				queryPos.add(latitude);
				queryPos.add(longitude);
				queryPos.add(range);
				
			} catch (Exception e) {
				
			}

			@SuppressWarnings("unchecked")
			List<Object[]> eventsWithBalances = (List<Object[]>) QueryUtil.list(sqlQuery, getDialect(), start, end);
			List<Event> events = assembleEvents(eventsWithBalances);
			return events;

		} catch (ORMException e) {
			throw new NoSuchEventException();
		} finally {
			closeSession(session);
		}
	}
	
	private List<Event> assembleEvents(List<Object[]> categoriesWithBalances) {
		List<Event> categories = new ArrayList<Event>();

		for (Object[] eventArray : categoriesWithBalances) {

			
			categories.add(castObjectArrayToEvent(eventArray));
		}

		return categories;
	}

	private Event castObjectArrayToEvent(Object[] eventArray) {
		
		Event event = EventLocalServiceUtil.createEvent((Long) eventArray[1]);

		event.setFacebookEventId((String) eventArray[2]);
		event.setCorrespondingGoogleEventId((String) eventArray[3]);
		event.setCorrespondingGoogleEventCalendarId((String) eventArray[4]);
		event.setTitle((String) eventArray[5]);
		event.setDescription((String) eventArray[6]);
		event.setStartingTime((Date) eventArray[7]);
		event.setEndingTime((Date) eventArray[8]);
		event.setLocationAsString((String) eventArray[9]);
		event.setLocationLatitude((Double) eventArray[10]);
		event.setLocationLongitude((Double) eventArray[11]);
		event.setRegisteredCount((Integer) eventArray[12]);
		event.setCreatorId((Long) eventArray[13]);
		event.setCategoryId((Long) eventArray[14]);
		event.setIsOfficial((Boolean) eventArray[15]);
		return event;
	}
	
	private void addScalars(SQLQuery sqlQuery) {
		sqlQuery.addScalar("distance", Type.DOUBLE);
		sqlQuery.addScalar("eventId", Type.LONG);
		sqlQuery.addScalar("facebookEventId", Type.STRING);
		sqlQuery.addScalar("correspondingGoogleEventId", Type.STRING);
		sqlQuery.addScalar("correspondingGoogleEventCalendarId", Type.STRING);
		sqlQuery.addScalar("title", Type.STRING);
		sqlQuery.addScalar("description", Type.STRING);
		sqlQuery.addScalar("start", Type.TIMESTAMP);
		sqlQuery.addScalar("end", Type.TIMESTAMP);
		sqlQuery.addScalar("location", Type.STRING);
		sqlQuery.addScalar("locationLatitude", Type.DOUBLE);
		sqlQuery.addScalar("locationLongitude", Type.DOUBLE);
		sqlQuery.addScalar("registeredCount", Type.INTEGER);
		sqlQuery.addScalar("creatorId", Type.LONG);
		sqlQuery.addScalar("categoryId", Type.LONG);
		sqlQuery.addScalar("isOfficial", Type.BOOLEAN);
	}

}
