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

package de.tum.in.ziller.thesis.social_campaign.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import de.tum.in.ziller.thesis.social_campaign.NoSuchEventException;
import de.tum.in.ziller.thesis.social_campaign.model.Event;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the Event service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link EventUtil} to access the Event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see EventPersistence
 * @see EventUtil
 * @generated
 */
public class EventPersistenceImpl extends BasePersistenceImpl<Event>
	implements EventPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = EventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_CREATOR = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCreator",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CREATOR = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCreator", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_TITLE = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByTitle",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_TITLE = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByTitle", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_TITLELIKE = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByTitleLike",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_TITLELIKE = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByTitleLike", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_EVENTID = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByEventId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTID = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByEventId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ISOFFICIAL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByisOfficial",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ISOFFICIAL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByisOfficial", new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_STARTINGDATELATERTHENANDISOFFICIAL =
		new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByStartingDateLaterThenAndIsOfficial",
			new String[] {
				Boolean.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_STARTINGDATELATERTHENANDISOFFICIAL =
		new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByStartingDateLaterThenAndIsOfficial",
			new String[] { Boolean.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the Event in the entity cache if it is enabled.
	 *
	 * @param event the Event to cache
	 */
	public void cacheResult(Event event) {
		EntityCacheUtil.putResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey(), event);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTID,
			new Object[] { new Long(event.getEventId()) }, event);
	}

	/**
	 * Caches the Events in the entity cache if it is enabled.
	 *
	 * @param events the Events to cache
	 */
	public void cacheResult(List<Event> events) {
		for (Event event : events) {
			if (EntityCacheUtil.getResult(EventModelImpl.ENTITY_CACHE_ENABLED,
						EventImpl.class, event.getPrimaryKey(), this) == null) {
				cacheResult(event);
			}
		}
	}

	/**
	 * Clears the cache for all Events.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(EventImpl.class.getName());
		EntityCacheUtil.clearCache(EventImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the Event.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Event event) {
		EntityCacheUtil.removeResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EVENTID,
			new Object[] { new Long(event.getEventId()) });
	}

	/**
	 * Creates a new Event with the primary key. Does not add the Event to the database.
	 *
	 * @param eventId the primary key for the new Event
	 * @return the new Event
	 */
	public Event create(long eventId) {
		Event event = new EventImpl();

		event.setNew(true);
		event.setPrimaryKey(eventId);

		return event;
	}

	/**
	 * Removes the Event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the Event to remove
	 * @return the Event that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the Event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the Event to remove
	 * @return the Event that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event remove(long eventId)
		throws NoSuchEventException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Event event = (Event)session.get(EventImpl.class, new Long(eventId));

			if (event == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + eventId);
				}

				throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					eventId);
			}

			return remove(event);
		}
		catch (NoSuchEventException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event removeImpl(Event event) throws SystemException {
		event = toUnwrappedModel(event);

		Session session = null;

		try {
			session = openSession();

			if (event.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(EventImpl.class,
						event.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(event);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EventModelImpl eventModelImpl = (EventModelImpl)event;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EVENTID,
			new Object[] { new Long(eventModelImpl.getOriginalEventId()) });

		EntityCacheUtil.removeResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey());

		return event;
	}

	public Event updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.Event event, boolean merge)
		throws SystemException {
		event = toUnwrappedModel(event);

		boolean isNew = event.isNew();

		EventModelImpl eventModelImpl = (EventModelImpl)event;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, event, merge);

			event.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey(), event);

		if (!isNew &&
				(event.getEventId() != eventModelImpl.getOriginalEventId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EVENTID,
				new Object[] { new Long(eventModelImpl.getOriginalEventId()) });
		}

		if (isNew ||
				(event.getEventId() != eventModelImpl.getOriginalEventId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTID,
				new Object[] { new Long(event.getEventId()) }, event);
		}

		return event;
	}

	protected Event toUnwrappedModel(Event event) {
		if (event instanceof EventImpl) {
			return event;
		}

		EventImpl eventImpl = new EventImpl();

		eventImpl.setNew(event.isNew());
		eventImpl.setPrimaryKey(event.getPrimaryKey());

		eventImpl.setEventId(event.getEventId());
		eventImpl.setFacebookEventId(event.getFacebookEventId());
		eventImpl.setCorrespondingGoogleEventId(event.getCorrespondingGoogleEventId());
		eventImpl.setCorrespondingGoogleEventCalendarId(event.getCorrespondingGoogleEventCalendarId());
		eventImpl.setTitle(event.getTitle());
		eventImpl.setDescription(event.getDescription());
		eventImpl.setStartingTime(event.getStartingTime());
		eventImpl.setEndingTime(event.getEndingTime());
		eventImpl.setLocationAsString(event.getLocationAsString());
		eventImpl.setLocationLatitude(event.getLocationLatitude());
		eventImpl.setLocationLongitude(event.getLocationLongitude());
		eventImpl.setRegisteredCount(event.getRegisteredCount());
		eventImpl.setCreatorId(event.getCreatorId());
		eventImpl.setCategoryId(event.getCategoryId());
		eventImpl.setIsOfficial(event.isIsOfficial());

		return eventImpl;
	}

	/**
	 * Finds the Event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the Event to find
	 * @return the Event
	 * @throws com.liferay.portal.NoSuchModelException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the Event with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchEventException} if it could not be found.
	 *
	 * @param eventId the primary key of the Event to find
	 * @return the Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByPrimaryKey(long eventId)
		throws NoSuchEventException, SystemException {
		Event event = fetchByPrimaryKey(eventId);

		if (event == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + eventId);
			}

			throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventId);
		}

		return event;
	}

	/**
	 * Finds the Event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the Event to find
	 * @return the Event, or <code>null</code> if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the Event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the Event to find
	 * @return the Event, or <code>null</code> if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event fetchByPrimaryKey(long eventId) throws SystemException {
		Event event = (Event)EntityCacheUtil.getResult(EventModelImpl.ENTITY_CACHE_ENABLED,
				EventImpl.class, eventId, this);

		if (event == null) {
			Session session = null;

			try {
				session = openSession();

				event = (Event)session.get(EventImpl.class, new Long(eventId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (event != null) {
					cacheResult(event);
				}

				closeSession(session);
			}
		}

		return event;
	}

	/**
	 * Finds all the Events where creatorId = &#63;.
	 *
	 * @param creatorId the creator id to search with
	 * @return the matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByCreator(long creatorId) throws SystemException {
		return findByCreator(creatorId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the Events where creatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param creatorId the creator id to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @return the range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByCreator(long creatorId, int start, int end)
		throws SystemException {
		return findByCreator(creatorId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Events where creatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param creatorId the creator id to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByCreator(long creatorId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				creatorId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Event> list = (List<Event>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CREATOR,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_CREATOR_CREATORID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(EventModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(creatorId);

				list = (List<Event>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Event>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CREATOR,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event in the ordered set where creatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param creatorId the creator id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByCreator_First(long creatorId,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		List<Event> list = findByCreator(creatorId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("creatorId=");
			msg.append(creatorId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event in the ordered set where creatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param creatorId the creator id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByCreator_Last(long creatorId,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		int count = countByCreator(creatorId);

		List<Event> list = findByCreator(creatorId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("creatorId=");
			msg.append(creatorId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Events before and after the current Event in the ordered set where creatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the primary key of the current Event
	 * @param creatorId the creator id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event[] findByCreator_PrevAndNext(long eventId, long creatorId,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByCreator_PrevAndNext(session, event, creatorId,
					orderByComparator, true);

			array[1] = event;

			array[2] = getByCreator_PrevAndNext(session, event, creatorId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByCreator_PrevAndNext(Session session, Event event,
		long creatorId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_CREATOR_CREATORID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(creatorId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the Events where title = &#63;.
	 *
	 * @param title the title to search with
	 * @return the matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByTitle(String title) throws SystemException {
		return findByTitle(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Events where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @return the range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByTitle(String title, int start, int end)
		throws SystemException {
		return findByTitle(title, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Events where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByTitle(String title, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				title,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Event> list = (List<Event>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TITLE,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENT_WHERE);

				if (title == null) {
					query.append(_FINDER_COLUMN_TITLE_TITLE_1);
				}
				else {
					if (title.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_TITLE_TITLE_3);
					}
					else {
						query.append(_FINDER_COLUMN_TITLE_TITLE_2);
					}
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(EventModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (title != null) {
					qPos.add(title);
				}

				list = (List<Event>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Event>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TITLE,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event in the ordered set where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByTitle_First(String title,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		List<Event> list = findByTitle(title, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("title=");
			msg.append(title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event in the ordered set where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByTitle_Last(String title,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		int count = countByTitle(title);

		List<Event> list = findByTitle(title, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("title=");
			msg.append(title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Events before and after the current Event in the ordered set where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the primary key of the current Event
	 * @param title the title to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event[] findByTitle_PrevAndNext(long eventId, String title,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByTitle_PrevAndNext(session, event, title,
					orderByComparator, true);

			array[1] = event;

			array[2] = getByTitle_PrevAndNext(session, event, title,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByTitle_PrevAndNext(Session session, Event event,
		String title, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLE_TITLE_1);
		}
		else {
			if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLE_TITLE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TITLE_TITLE_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (title != null) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the Events where title LIKE &#63;.
	 *
	 * @param title the title to search with
	 * @return the matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByTitleLike(String title) throws SystemException {
		return findByTitleLike(title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Events where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @return the range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByTitleLike(String title, int start, int end)
		throws SystemException {
		return findByTitleLike(title, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Events where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByTitleLike(String title, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				title,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Event> list = (List<Event>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TITLELIKE,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENT_WHERE);

				if (title == null) {
					query.append(_FINDER_COLUMN_TITLELIKE_TITLE_1);
				}
				else {
					if (title.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_TITLELIKE_TITLE_3);
					}
					else {
						query.append(_FINDER_COLUMN_TITLELIKE_TITLE_2);
					}
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(EventModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (title != null) {
					qPos.add(title);
				}

				list = (List<Event>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Event>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TITLELIKE,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event in the ordered set where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByTitleLike_First(String title,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		List<Event> list = findByTitleLike(title, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("title=");
			msg.append(title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event in the ordered set where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param title the title to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByTitleLike_Last(String title,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		int count = countByTitleLike(title);

		List<Event> list = findByTitleLike(title, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("title=");
			msg.append(title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Events before and after the current Event in the ordered set where title LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the primary key of the current Event
	 * @param title the title to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event[] findByTitleLike_PrevAndNext(long eventId, String title,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByTitleLike_PrevAndNext(session, event, title,
					orderByComparator, true);

			array[1] = event;

			array[2] = getByTitleLike_PrevAndNext(session, event, title,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByTitleLike_PrevAndNext(Session session, Event event,
		String title, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		if (title == null) {
			query.append(_FINDER_COLUMN_TITLELIKE_TITLE_1);
		}
		else {
			if (title.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TITLELIKE_TITLE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TITLELIKE_TITLE_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (title != null) {
			qPos.add(title);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the Event where eventId = &#63; or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchEventException} if it could not be found.
	 *
	 * @param eventId the event id to search with
	 * @return the matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByEventId(long eventId)
		throws NoSuchEventException, SystemException {
		Event event = fetchByEventId(eventId);

		if (event == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEventException(msg.toString());
		}

		return event;
	}

	/**
	 * Finds the Event where eventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param eventId the event id to search with
	 * @return the matching Event, or <code>null</code> if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event fetchByEventId(long eventId) throws SystemException {
		return fetchByEventId(eventId, true);
	}

	/**
	 * Finds the Event where eventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param eventId the event id to search with
	 * @return the matching Event, or <code>null</code> if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event fetchByEventId(long eventId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { eventId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_EVENTID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

				query.append(EventModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				List<Event> list = q.list();

				result = list;

				Event event = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTID,
						finderArgs, list);
				}
				else {
					event = list.get(0);

					cacheResult(event);

					if ((event.getEventId() != eventId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTID,
							finderArgs, event);
					}
				}

				return event;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EVENTID,
						finderArgs, new ArrayList<Event>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Event)result;
			}
		}
	}

	/**
	 * Finds all the Events where isOfficial = &#63;.
	 *
	 * @param isOfficial the is official to search with
	 * @return the matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByisOfficial(boolean isOfficial)
		throws SystemException {
		return findByisOfficial(isOfficial, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Events where isOfficial = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @return the range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByisOfficial(boolean isOfficial, int start, int end)
		throws SystemException {
		return findByisOfficial(isOfficial, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Events where isOfficial = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByisOfficial(boolean isOfficial, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				isOfficial,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Event> list = (List<Event>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ISOFFICIAL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_ISOFFICIAL_ISOFFICIAL_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(EventModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(isOfficial);

				list = (List<Event>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Event>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ISOFFICIAL,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event in the ordered set where isOfficial = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByisOfficial_First(boolean isOfficial,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		List<Event> list = findByisOfficial(isOfficial, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("isOfficial=");
			msg.append(isOfficial);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event in the ordered set where isOfficial = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByisOfficial_Last(boolean isOfficial,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		int count = countByisOfficial(isOfficial);

		List<Event> list = findByisOfficial(isOfficial, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("isOfficial=");
			msg.append(isOfficial);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Events before and after the current Event in the ordered set where isOfficial = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the primary key of the current Event
	 * @param isOfficial the is official to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event[] findByisOfficial_PrevAndNext(long eventId,
		boolean isOfficial, OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByisOfficial_PrevAndNext(session, event, isOfficial,
					orderByComparator, true);

			array[1] = event;

			array[2] = getByisOfficial_PrevAndNext(session, event, isOfficial,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByisOfficial_PrevAndNext(Session session, Event event,
		boolean isOfficial, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_ISOFFICIAL_ISOFFICIAL_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(isOfficial);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the Events where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @return the matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByStartingDateLaterThenAndIsOfficial(
		boolean isOfficial, Date startingTime) throws SystemException {
		return findByStartingDateLaterThenAndIsOfficial(isOfficial,
			startingTime, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Events where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @return the range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByStartingDateLaterThenAndIsOfficial(
		boolean isOfficial, Date startingTime, int start, int end)
		throws SystemException {
		return findByStartingDateLaterThenAndIsOfficial(isOfficial,
			startingTime, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Events where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findByStartingDateLaterThenAndIsOfficial(
		boolean isOfficial, Date startingTime, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				isOfficial, startingTime,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Event> list = (List<Event>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_STARTINGDATELATERTHENANDISOFFICIAL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(4 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(4);
				}

				query.append(_SQL_SELECT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_ISOFFICIAL_2);

				if (startingTime == null) {
					query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_1);
				}
				else {
					query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_2);
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(EventModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(isOfficial);

				if (startingTime != null) {
					qPos.add(CalendarUtil.getTimestamp(startingTime));
				}

				list = (List<Event>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Event>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_STARTINGDATELATERTHENANDISOFFICIAL,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event in the ordered set where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByStartingDateLaterThenAndIsOfficial_First(
		boolean isOfficial, Date startingTime,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		List<Event> list = findByStartingDateLaterThenAndIsOfficial(isOfficial,
				startingTime, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("isOfficial=");
			msg.append(isOfficial);

			msg.append(", startingTime=");
			msg.append(startingTime);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event in the ordered set where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a matching Event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event findByStartingDateLaterThenAndIsOfficial_Last(
		boolean isOfficial, Date startingTime,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		int count = countByStartingDateLaterThenAndIsOfficial(isOfficial,
				startingTime);

		List<Event> list = findByStartingDateLaterThenAndIsOfficial(isOfficial,
				startingTime, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("isOfficial=");
			msg.append(isOfficial);

			msg.append(", startingTime=");
			msg.append(startingTime);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Events before and after the current Event in the ordered set where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the primary key of the current Event
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventException if a Event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event[] findByStartingDateLaterThenAndIsOfficial_PrevAndNext(
		long eventId, boolean isOfficial, Date startingTime,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByStartingDateLaterThenAndIsOfficial_PrevAndNext(session,
					event, isOfficial, startingTime, orderByComparator, true);

			array[1] = event;

			array[2] = getByStartingDateLaterThenAndIsOfficial_PrevAndNext(session,
					event, isOfficial, startingTime, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByStartingDateLaterThenAndIsOfficial_PrevAndNext(
		Session session, Event event, boolean isOfficial, Date startingTime,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_ISOFFICIAL_2);

		if (startingTime == null) {
			query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_1);
		}
		else {
			query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(isOfficial);

		if (startingTime != null) {
			qPos.add(CalendarUtil.getTimestamp(startingTime));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the Events.
	 *
	 * @return the Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @return the range of Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the Events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of Events to return
	 * @param end the upper bound of the range of Events to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of Events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Event> list = (List<Event>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_EVENT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_EVENT.concat(EventModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Event>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the Events where creatorId = &#63; from the database.
	 *
	 * @param creatorId the creator id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCreator(long creatorId) throws SystemException {
		for (Event event : findByCreator(creatorId)) {
			remove(event);
		}
	}

	/**
	 * Removes all the Events where title = &#63; from the database.
	 *
	 * @param title the title to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTitle(String title) throws SystemException {
		for (Event event : findByTitle(title)) {
			remove(event);
		}
	}

	/**
	 * Removes all the Events where title LIKE &#63; from the database.
	 *
	 * @param title the title to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTitleLike(String title) throws SystemException {
		for (Event event : findByTitleLike(title)) {
			remove(event);
		}
	}

	/**
	 * Removes the Event where eventId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventId(long eventId)
		throws NoSuchEventException, SystemException {
		Event event = findByEventId(eventId);

		remove(event);
	}

	/**
	 * Removes all the Events where isOfficial = &#63; from the database.
	 *
	 * @param isOfficial the is official to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByisOfficial(boolean isOfficial)
		throws SystemException {
		for (Event event : findByisOfficial(isOfficial)) {
			remove(event);
		}
	}

	/**
	 * Removes all the Events where isOfficial = &#63; and startingTime &ge; &#63; from the database.
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStartingDateLaterThenAndIsOfficial(boolean isOfficial,
		Date startingTime) throws SystemException {
		for (Event event : findByStartingDateLaterThenAndIsOfficial(
				isOfficial, startingTime)) {
			remove(event);
		}
	}

	/**
	 * Removes all the Events from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Event event : findAll()) {
			remove(event);
		}
	}

	/**
	 * Counts all the Events where creatorId = &#63;.
	 *
	 * @param creatorId the creator id to search with
	 * @return the number of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCreator(long creatorId) throws SystemException {
		Object[] finderArgs = new Object[] { creatorId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CREATOR,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_CREATOR_CREATORID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(creatorId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CREATOR,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Events where title = &#63;.
	 *
	 * @param title the title to search with
	 * @return the number of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTitle(String title) throws SystemException {
		Object[] finderArgs = new Object[] { title };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TITLE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENT_WHERE);

				if (title == null) {
					query.append(_FINDER_COLUMN_TITLE_TITLE_1);
				}
				else {
					if (title.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_TITLE_TITLE_3);
					}
					else {
						query.append(_FINDER_COLUMN_TITLE_TITLE_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (title != null) {
					qPos.add(title);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TITLE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Events where title LIKE &#63;.
	 *
	 * @param title the title to search with
	 * @return the number of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTitleLike(String title) throws SystemException {
		Object[] finderArgs = new Object[] { title };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TITLELIKE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENT_WHERE);

				if (title == null) {
					query.append(_FINDER_COLUMN_TITLELIKE_TITLE_1);
				}
				else {
					if (title.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_TITLELIKE_TITLE_3);
					}
					else {
						query.append(_FINDER_COLUMN_TITLELIKE_TITLE_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (title != null) {
					qPos.add(title);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TITLELIKE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Events where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the number of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventId(long eventId) throws SystemException {
		Object[] finderArgs = new Object[] { eventId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Events where isOfficial = &#63;.
	 *
	 * @param isOfficial the is official to search with
	 * @return the number of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByisOfficial(boolean isOfficial) throws SystemException {
		Object[] finderArgs = new Object[] { isOfficial };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ISOFFICIAL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_ISOFFICIAL_ISOFFICIAL_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(isOfficial);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ISOFFICIAL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Events where isOfficial = &#63; and startingTime &ge; &#63;.
	 *
	 * @param isOfficial the is official to search with
	 * @param startingTime the starting time to search with
	 * @return the number of matching Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStartingDateLaterThenAndIsOfficial(boolean isOfficial,
		Date startingTime) throws SystemException {
		Object[] finderArgs = new Object[] { isOfficial, startingTime };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_STARTINGDATELATERTHENANDISOFFICIAL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_EVENT_WHERE);

				query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_ISOFFICIAL_2);

				if (startingTime == null) {
					query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_1);
				}
				else {
					query.append(_FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_2);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(isOfficial);

				if (startingTime != null) {
					qPos.add(CalendarUtil.getTimestamp(startingTime));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_STARTINGDATELATERTHENANDISOFFICIAL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Events.
	 *
	 * @return the number of Events
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENT);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the Event persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.Event")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Event>> listenersList = new ArrayList<ModelListener<Event>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Event>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = AssistenceRequestPersistence.class)
	protected AssistenceRequestPersistence assistenceRequestPersistence;
	@BeanReference(type = EventAssistencePersistence.class)
	protected EventAssistencePersistence eventAssistencePersistence;
	@BeanReference(type = AssistenceRolePersistence.class)
	protected AssistenceRolePersistence assistenceRolePersistence;
	@BeanReference(type = AnonymAssistencePersistence.class)
	protected AnonymAssistencePersistence anonymAssistencePersistence;
	@BeanReference(type = CategoryPersistence.class)
	protected CategoryPersistence categoryPersistence;
	@BeanReference(type = EventCreatorPersistence.class)
	protected EventCreatorPersistence eventCreatorPersistence;
	@BeanReference(type = EventAttendencePersistence.class)
	protected EventAttendencePersistence eventAttendencePersistence;
	@BeanReference(type = FacebookCredentialsPersistence.class)
	protected FacebookCredentialsPersistence facebookCredentialsPersistence;
	@BeanReference(type = GoogleCredentialsPersistence.class)
	protected GoogleCredentialsPersistence googleCredentialsPersistence;
	@BeanReference(type = DonationPersistence.class)
	protected DonationPersistence donationPersistence;
	@BeanReference(type = NewsPersistence.class)
	protected NewsPersistence newsPersistence;
	@BeanReference(type = NewsTypePersistence.class)
	protected NewsTypePersistence newsTypePersistence;
	@BeanReference(type = GeneralSettingsPersistence.class)
	protected GeneralSettingsPersistence generalSettingsPersistence;
	@BeanReference(type = SocialSettingsPersistence.class)
	protected SocialSettingsPersistence socialSettingsPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_EVENT = "SELECT event FROM Event event";
	private static final String _SQL_SELECT_EVENT_WHERE = "SELECT event FROM Event event WHERE ";
	private static final String _SQL_COUNT_EVENT = "SELECT COUNT(event) FROM Event event";
	private static final String _SQL_COUNT_EVENT_WHERE = "SELECT COUNT(event) FROM Event event WHERE ";
	private static final String _FINDER_COLUMN_CREATOR_CREATORID_2 = "event.creatorId = ?";
	private static final String _FINDER_COLUMN_TITLE_TITLE_1 = "event.title IS NULL";
	private static final String _FINDER_COLUMN_TITLE_TITLE_2 = "lower(event.title) = lower(?)";
	private static final String _FINDER_COLUMN_TITLE_TITLE_3 = "(event.title IS NULL OR lower(event.title) = lower(?))";
	private static final String _FINDER_COLUMN_TITLELIKE_TITLE_1 = "event.title LIKE NULL";
	private static final String _FINDER_COLUMN_TITLELIKE_TITLE_2 = "lower(event.title) LIKE lower(?)";
	private static final String _FINDER_COLUMN_TITLELIKE_TITLE_3 = "(event.title IS NULL OR lower(event.title) LIKE lower(?))";
	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 = "event.eventId = ?";
	private static final String _FINDER_COLUMN_ISOFFICIAL_ISOFFICIAL_2 = "event.isOfficial = ?";
	private static final String _FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_ISOFFICIAL_2 =
		"event.isOfficial = ? AND ";
	private static final String _FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_1 =
		"event.startingTime >= NULL";
	private static final String _FINDER_COLUMN_STARTINGDATELATERTHENANDISOFFICIAL_STARTINGTIME_2 =
		"event.startingTime >= ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "event.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Event exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Event exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(EventPersistenceImpl.class);
}