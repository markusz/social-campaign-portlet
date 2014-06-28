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

import de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventAttendenceImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventAttendenceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the Event Attendence service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link EventAttendenceUtil} to access the Event Attendence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see EventAttendencePersistence
 * @see EventAttendenceUtil
 * @generated
 */
public class EventAttendencePersistenceImpl extends BasePersistenceImpl<EventAttendence>
	implements EventAttendencePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = EventAttendenceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByuserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByuserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_EVENTID = new FinderPath(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByeventId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTID = new FinderPath(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByeventId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the Event Attendence in the entity cache if it is enabled.
	 *
	 * @param eventAttendence the Event Attendence to cache
	 */
	public void cacheResult(EventAttendence eventAttendence) {
		EntityCacheUtil.putResult(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceImpl.class, eventAttendence.getPrimaryKey(),
			eventAttendence);
	}

	/**
	 * Caches the Event Attendences in the entity cache if it is enabled.
	 *
	 * @param eventAttendences the Event Attendences to cache
	 */
	public void cacheResult(List<EventAttendence> eventAttendences) {
		for (EventAttendence eventAttendence : eventAttendences) {
			if (EntityCacheUtil.getResult(
						EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
						EventAttendenceImpl.class,
						eventAttendence.getPrimaryKey(), this) == null) {
				cacheResult(eventAttendence);
			}
		}
	}

	/**
	 * Clears the cache for all Event Attendences.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(EventAttendenceImpl.class.getName());
		EntityCacheUtil.clearCache(EventAttendenceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the Event Attendence.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(EventAttendence eventAttendence) {
		EntityCacheUtil.removeResult(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceImpl.class, eventAttendence.getPrimaryKey());
	}

	/**
	 * Creates a new Event Attendence with the primary key. Does not add the Event Attendence to the database.
	 *
	 * @param eventAttendencePK the primary key for the new Event Attendence
	 * @return the new Event Attendence
	 */
	public EventAttendence create(EventAttendencePK eventAttendencePK) {
		EventAttendence eventAttendence = new EventAttendenceImpl();

		eventAttendence.setNew(true);
		eventAttendence.setPrimaryKey(eventAttendencePK);

		return eventAttendence;
	}

	/**
	 * Removes the Event Attendence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the Event Attendence to remove
	 * @return the Event Attendence that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove((EventAttendencePK)primaryKey);
	}

	/**
	 * Removes the Event Attendence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventAttendencePK the primary key of the Event Attendence to remove
	 * @return the Event Attendence that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence remove(EventAttendencePK eventAttendencePK)
		throws NoSuchEventAttendenceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			EventAttendence eventAttendence = (EventAttendence)session.get(EventAttendenceImpl.class,
					eventAttendencePK);

			if (eventAttendence == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						eventAttendencePK);
				}

				throw new NoSuchEventAttendenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					eventAttendencePK);
			}

			return remove(eventAttendence);
		}
		catch (NoSuchEventAttendenceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAttendence removeImpl(EventAttendence eventAttendence)
		throws SystemException {
		eventAttendence = toUnwrappedModel(eventAttendence);

		Session session = null;

		try {
			session = openSession();

			if (eventAttendence.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(EventAttendenceImpl.class,
						eventAttendence.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(eventAttendence);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceImpl.class, eventAttendence.getPrimaryKey());

		return eventAttendence;
	}

	public EventAttendence updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.EventAttendence eventAttendence,
		boolean merge) throws SystemException {
		eventAttendence = toUnwrappedModel(eventAttendence);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, eventAttendence, merge);

			eventAttendence.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAttendenceImpl.class, eventAttendence.getPrimaryKey(),
			eventAttendence);

		return eventAttendence;
	}

	protected EventAttendence toUnwrappedModel(EventAttendence eventAttendence) {
		if (eventAttendence instanceof EventAttendenceImpl) {
			return eventAttendence;
		}

		EventAttendenceImpl eventAttendenceImpl = new EventAttendenceImpl();

		eventAttendenceImpl.setNew(eventAttendence.isNew());
		eventAttendenceImpl.setPrimaryKey(eventAttendence.getPrimaryKey());

		eventAttendenceImpl.setEventId(eventAttendence.getEventId());
		eventAttendenceImpl.setUserProfileId(eventAttendence.getUserProfileId());
		eventAttendenceImpl.setCorrespondingGoogleEvent(eventAttendence.getCorrespondingGoogleEvent());
		eventAttendenceImpl.setCorrespondingGoogleCalendarId(eventAttendence.getCorrespondingGoogleCalendarId());

		return eventAttendenceImpl;
	}

	/**
	 * Finds the Event Attendence with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the Event Attendence to find
	 * @return the Event Attendence
	 * @throws com.liferay.portal.NoSuchModelException if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((EventAttendencePK)primaryKey);
	}

	/**
	 * Finds the Event Attendence with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException} if it could not be found.
	 *
	 * @param eventAttendencePK the primary key of the Event Attendence to find
	 * @return the Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence findByPrimaryKey(EventAttendencePK eventAttendencePK)
		throws NoSuchEventAttendenceException, SystemException {
		EventAttendence eventAttendence = fetchByPrimaryKey(eventAttendencePK);

		if (eventAttendence == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + eventAttendencePK);
			}

			throw new NoSuchEventAttendenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventAttendencePK);
		}

		return eventAttendence;
	}

	/**
	 * Finds the Event Attendence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the Event Attendence to find
	 * @return the Event Attendence, or <code>null</code> if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((EventAttendencePK)primaryKey);
	}

	/**
	 * Finds the Event Attendence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventAttendencePK the primary key of the Event Attendence to find
	 * @return the Event Attendence, or <code>null</code> if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence fetchByPrimaryKey(
		EventAttendencePK eventAttendencePK) throws SystemException {
		EventAttendence eventAttendence = (EventAttendence)EntityCacheUtil.getResult(EventAttendenceModelImpl.ENTITY_CACHE_ENABLED,
				EventAttendenceImpl.class, eventAttendencePK, this);

		if (eventAttendence == null) {
			Session session = null;

			try {
				session = openSession();

				eventAttendence = (EventAttendence)session.get(EventAttendenceImpl.class,
						eventAttendencePK);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (eventAttendence != null) {
					cacheResult(eventAttendence);
				}

				closeSession(session);
			}
		}

		return eventAttendence;
	}

	/**
	 * Finds all the Event Attendences where userProfileId = &#63;.
	 *
	 * @param userProfileId the user profile id to search with
	 * @return the matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findByuserId(long userProfileId)
		throws SystemException {
		return findByuserId(userProfileId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Event Attendences where userProfileId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userProfileId the user profile id to search with
	 * @param start the lower bound of the range of Event Attendences to return
	 * @param end the upper bound of the range of Event Attendences to return (not inclusive)
	 * @return the range of matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findByuserId(long userProfileId, int start,
		int end) throws SystemException {
		return findByuserId(userProfileId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Event Attendences where userProfileId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userProfileId the user profile id to search with
	 * @param start the lower bound of the range of Event Attendences to return
	 * @param end the upper bound of the range of Event Attendences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findByuserId(long userProfileId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userProfileId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAttendence> list = (List<EventAttendence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
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
					query = new StringBundler(2);
				}

				query.append(_SQL_SELECT_EVENTATTENDENCE_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERPROFILEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userProfileId);

				list = (List<EventAttendence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAttendence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event Attendence in the ordered set where userProfileId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userProfileId the user profile id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a matching Event Attendence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence findByuserId_First(long userProfileId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAttendenceException, SystemException {
		List<EventAttendence> list = findByuserId(userProfileId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userProfileId=");
			msg.append(userProfileId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAttendenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event Attendence in the ordered set where userProfileId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userProfileId the user profile id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a matching Event Attendence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence findByuserId_Last(long userProfileId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAttendenceException, SystemException {
		int count = countByuserId(userProfileId);

		List<EventAttendence> list = findByuserId(userProfileId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userProfileId=");
			msg.append(userProfileId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAttendenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Event Attendences before and after the current Event Attendence in the ordered set where userProfileId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAttendencePK the primary key of the current Event Attendence
	 * @param userProfileId the user profile id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence[] findByuserId_PrevAndNext(
		EventAttendencePK eventAttendencePK, long userProfileId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAttendenceException, SystemException {
		EventAttendence eventAttendence = findByPrimaryKey(eventAttendencePK);

		Session session = null;

		try {
			session = openSession();

			EventAttendence[] array = new EventAttendenceImpl[3];

			array[0] = getByuserId_PrevAndNext(session, eventAttendence,
					userProfileId, orderByComparator, true);

			array[1] = eventAttendence;

			array[2] = getByuserId_PrevAndNext(session, eventAttendence,
					userProfileId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAttendence getByuserId_PrevAndNext(Session session,
		EventAttendence eventAttendence, long userProfileId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTATTENDENCE_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERPROFILEID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userProfileId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAttendence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAttendence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the Event Attendences where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findByeventId(long eventId)
		throws SystemException {
		return findByeventId(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Event Attendences where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param start the lower bound of the range of Event Attendences to return
	 * @param end the upper bound of the range of Event Attendences to return (not inclusive)
	 * @return the range of matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findByeventId(long eventId, int start, int end)
		throws SystemException {
		return findByeventId(eventId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the Event Attendences where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param start the lower bound of the range of Event Attendences to return
	 * @param end the upper bound of the range of Event Attendences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findByeventId(long eventId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				eventId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAttendence> list = (List<EventAttendence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTID,
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
					query = new StringBundler(2);
				}

				query.append(_SQL_SELECT_EVENTATTENDENCE_WHERE);

				query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				list = (List<EventAttendence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAttendence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_EVENTID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first Event Attendence in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a matching Event Attendence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence findByeventId_First(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAttendenceException, SystemException {
		List<EventAttendence> list = findByeventId(eventId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAttendenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last Event Attendence in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a matching Event Attendence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence findByeventId_Last(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAttendenceException, SystemException {
		int count = countByeventId(eventId);

		List<EventAttendence> list = findByeventId(eventId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAttendenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the Event Attendences before and after the current Event Attendence in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAttendencePK the primary key of the current Event Attendence
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next Event Attendence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAttendenceException if a Event Attendence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAttendence[] findByeventId_PrevAndNext(
		EventAttendencePK eventAttendencePK, long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAttendenceException, SystemException {
		EventAttendence eventAttendence = findByPrimaryKey(eventAttendencePK);

		Session session = null;

		try {
			session = openSession();

			EventAttendence[] array = new EventAttendenceImpl[3];

			array[0] = getByeventId_PrevAndNext(session, eventAttendence,
					eventId, orderByComparator, true);

			array[1] = eventAttendence;

			array[2] = getByeventId_PrevAndNext(session, eventAttendence,
					eventId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAttendence getByeventId_PrevAndNext(Session session,
		EventAttendence eventAttendence, long eventId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTATTENDENCE_WHERE);

		query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(eventId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAttendence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAttendence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the Event Attendences.
	 *
	 * @return the Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Event Attendences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of Event Attendences to return
	 * @param end the upper bound of the range of Event Attendences to return (not inclusive)
	 * @return the range of Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the Event Attendences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of Event Attendences to return
	 * @param end the upper bound of the range of Event Attendences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAttendence> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAttendence> list = (List<EventAttendence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_EVENTATTENDENCE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_EVENTATTENDENCE;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<EventAttendence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<EventAttendence>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAttendence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the Event Attendences where userProfileId = &#63; from the database.
	 *
	 * @param userProfileId the user profile id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByuserId(long userProfileId) throws SystemException {
		for (EventAttendence eventAttendence : findByuserId(userProfileId)) {
			remove(eventAttendence);
		}
	}

	/**
	 * Removes all the Event Attendences where eventId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByeventId(long eventId) throws SystemException {
		for (EventAttendence eventAttendence : findByeventId(eventId)) {
			remove(eventAttendence);
		}
	}

	/**
	 * Removes all the Event Attendences from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (EventAttendence eventAttendence : findAll()) {
			remove(eventAttendence);
		}
	}

	/**
	 * Counts all the Event Attendences where userProfileId = &#63;.
	 *
	 * @param userProfileId the user profile id to search with
	 * @return the number of matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByuserId(long userProfileId) throws SystemException {
		Object[] finderArgs = new Object[] { userProfileId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENTATTENDENCE_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERPROFILEID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userProfileId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the Event Attendences where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the number of matching Event Attendences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByeventId(long eventId) throws SystemException {
		Object[] finderArgs = new Object[] { eventId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENTATTENDENCE_WHERE);

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
	 * Counts all the Event Attendences.
	 *
	 * @return the number of Event Attendences
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

				Query q = session.createQuery(_SQL_COUNT_EVENTATTENDENCE);

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
	 * Initializes the Event Attendence persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.EventAttendence")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<EventAttendence>> listenersList = new ArrayList<ModelListener<EventAttendence>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<EventAttendence>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_EVENTATTENDENCE = "SELECT eventAttendence FROM EventAttendence eventAttendence";
	private static final String _SQL_SELECT_EVENTATTENDENCE_WHERE = "SELECT eventAttendence FROM EventAttendence eventAttendence WHERE ";
	private static final String _SQL_COUNT_EVENTATTENDENCE = "SELECT COUNT(eventAttendence) FROM EventAttendence eventAttendence";
	private static final String _SQL_COUNT_EVENTATTENDENCE_WHERE = "SELECT COUNT(eventAttendence) FROM EventAttendence eventAttendence WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERPROFILEID_2 = "eventAttendence.id.userProfileId = ?";
	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 = "eventAttendence.id.eventId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventAttendence.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventAttendence exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventAttendence exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(EventAttendencePersistenceImpl.class);
}