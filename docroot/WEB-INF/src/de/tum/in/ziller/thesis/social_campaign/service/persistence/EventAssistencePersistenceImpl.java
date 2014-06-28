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

import de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException;
import de.tum.in.ziller.thesis.social_campaign.model.EventAssistence;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventAssistenceImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventAssistenceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the event assistence service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link EventAssistenceUtil} to access the event assistence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see EventAssistencePersistence
 * @see EventAssistenceUtil
 * @generated
 */
public class EventAssistencePersistenceImpl extends BasePersistenceImpl<EventAssistence>
	implements EventAssistencePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = EventAssistenceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_EVENTIDANDROLEID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByEventIdAndRoleId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTIDANDROLEID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByEventIdAndRoleId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_EVENTIDANDUSERID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByEventIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTIDANDUSERID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByEventIdAndUserId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERIDANDROLEID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserIdAndRoleId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERIDANDROLEID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserIdAndRoleId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_EVENTID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByEventId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByEventId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ROLEID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByRoleId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ROLEID = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByRoleId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the event assistence in the entity cache if it is enabled.
	 *
	 * @param eventAssistence the event assistence to cache
	 */
	public void cacheResult(EventAssistence eventAssistence) {
		EntityCacheUtil.putResult(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceImpl.class, eventAssistence.getPrimaryKey(),
			eventAssistence);
	}

	/**
	 * Caches the event assistences in the entity cache if it is enabled.
	 *
	 * @param eventAssistences the event assistences to cache
	 */
	public void cacheResult(List<EventAssistence> eventAssistences) {
		for (EventAssistence eventAssistence : eventAssistences) {
			if (EntityCacheUtil.getResult(
						EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
						EventAssistenceImpl.class,
						eventAssistence.getPrimaryKey(), this) == null) {
				cacheResult(eventAssistence);
			}
		}
	}

	/**
	 * Clears the cache for all event assistences.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(EventAssistenceImpl.class.getName());
		EntityCacheUtil.clearCache(EventAssistenceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the event assistence.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(EventAssistence eventAssistence) {
		EntityCacheUtil.removeResult(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceImpl.class, eventAssistence.getPrimaryKey());
	}

	/**
	 * Creates a new event assistence with the primary key. Does not add the event assistence to the database.
	 *
	 * @param eventAssistencePK the primary key for the new event assistence
	 * @return the new event assistence
	 */
	public EventAssistence create(EventAssistencePK eventAssistencePK) {
		EventAssistence eventAssistence = new EventAssistenceImpl();

		eventAssistence.setNew(true);
		eventAssistence.setPrimaryKey(eventAssistencePK);

		return eventAssistence;
	}

	/**
	 * Removes the event assistence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event assistence to remove
	 * @return the event assistence that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove((EventAssistencePK)primaryKey);
	}

	/**
	 * Removes the event assistence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventAssistencePK the primary key of the event assistence to remove
	 * @return the event assistence that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence remove(EventAssistencePK eventAssistencePK)
		throws NoSuchEventAssistenceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			EventAssistence eventAssistence = (EventAssistence)session.get(EventAssistenceImpl.class,
					eventAssistencePK);

			if (eventAssistence == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						eventAssistencePK);
				}

				throw new NoSuchEventAssistenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					eventAssistencePK);
			}

			return remove(eventAssistence);
		}
		catch (NoSuchEventAssistenceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAssistence removeImpl(EventAssistence eventAssistence)
		throws SystemException {
		eventAssistence = toUnwrappedModel(eventAssistence);

		Session session = null;

		try {
			session = openSession();

			if (eventAssistence.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(EventAssistenceImpl.class,
						eventAssistence.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(eventAssistence);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceImpl.class, eventAssistence.getPrimaryKey());

		return eventAssistence;
	}

	public EventAssistence updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.EventAssistence eventAssistence,
		boolean merge) throws SystemException {
		eventAssistence = toUnwrappedModel(eventAssistence);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, eventAssistence, merge);

			eventAssistence.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			EventAssistenceImpl.class, eventAssistence.getPrimaryKey(),
			eventAssistence);

		return eventAssistence;
	}

	protected EventAssistence toUnwrappedModel(EventAssistence eventAssistence) {
		if (eventAssistence instanceof EventAssistenceImpl) {
			return eventAssistence;
		}

		EventAssistenceImpl eventAssistenceImpl = new EventAssistenceImpl();

		eventAssistenceImpl.setNew(eventAssistence.isNew());
		eventAssistenceImpl.setPrimaryKey(eventAssistence.getPrimaryKey());

		eventAssistenceImpl.setEventId(eventAssistence.getEventId());
		eventAssistenceImpl.setRoleId(eventAssistence.getRoleId());
		eventAssistenceImpl.setUserId(eventAssistence.getUserId());

		return eventAssistenceImpl;
	}

	/**
	 * Finds the event assistence with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the event assistence to find
	 * @return the event assistence
	 * @throws com.liferay.portal.NoSuchModelException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((EventAssistencePK)primaryKey);
	}

	/**
	 * Finds the event assistence with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException} if it could not be found.
	 *
	 * @param eventAssistencePK the primary key of the event assistence to find
	 * @return the event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByPrimaryKey(EventAssistencePK eventAssistencePK)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = fetchByPrimaryKey(eventAssistencePK);

		if (eventAssistence == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + eventAssistencePK);
			}

			throw new NoSuchEventAssistenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventAssistencePK);
		}

		return eventAssistence;
	}

	/**
	 * Finds the event assistence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event assistence to find
	 * @return the event assistence, or <code>null</code> if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((EventAssistencePK)primaryKey);
	}

	/**
	 * Finds the event assistence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventAssistencePK the primary key of the event assistence to find
	 * @return the event assistence, or <code>null</code> if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence fetchByPrimaryKey(
		EventAssistencePK eventAssistencePK) throws SystemException {
		EventAssistence eventAssistence = (EventAssistence)EntityCacheUtil.getResult(EventAssistenceModelImpl.ENTITY_CACHE_ENABLED,
				EventAssistenceImpl.class, eventAssistencePK, this);

		if (eventAssistence == null) {
			Session session = null;

			try {
				session = openSession();

				eventAssistence = (EventAssistence)session.get(EventAssistenceImpl.class,
						eventAssistencePK);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (eventAssistence != null) {
					cacheResult(eventAssistence);
				}

				closeSession(session);
			}
		}

		return eventAssistence;
	}

	/**
	 * Finds all the event assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @return the matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventIdAndRoleId(long eventId,
		long roleId) throws SystemException {
		return findByEventIdAndRoleId(eventId, roleId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventIdAndRoleId(long eventId,
		long roleId, int start, int end) throws SystemException {
		return findByEventIdAndRoleId(eventId, roleId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventIdAndRoleId(long eventId,
		long roleId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				eventId, roleId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTIDANDROLEID,
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
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_EVENTIDANDROLEID_EVENTID_2);

				query.append(_FINDER_COLUMN_EVENTIDANDROLEID_ROLEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				qPos.add(roleId);

				list = (List<EventAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_EVENTIDANDROLEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first event assistence in the ordered set where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByEventIdAndRoleId_First(long eventId,
		long roleId, OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		List<EventAssistence> list = findByEventIdAndRoleId(eventId, roleId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last event assistence in the ordered set where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByEventIdAndRoleId_Last(long eventId,
		long roleId, OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		int count = countByEventIdAndRoleId(eventId, roleId);

		List<EventAssistence> list = findByEventIdAndRoleId(eventId, roleId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the event assistences before and after the current event assistence in the ordered set where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAssistencePK the primary key of the current event assistence
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence[] findByEventIdAndRoleId_PrevAndNext(
		EventAssistencePK eventAssistencePK, long eventId, long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = findByPrimaryKey(eventAssistencePK);

		Session session = null;

		try {
			session = openSession();

			EventAssistence[] array = new EventAssistenceImpl[3];

			array[0] = getByEventIdAndRoleId_PrevAndNext(session,
					eventAssistence, eventId, roleId, orderByComparator, true);

			array[1] = eventAssistence;

			array[2] = getByEventIdAndRoleId_PrevAndNext(session,
					eventAssistence, eventId, roleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAssistence getByEventIdAndRoleId_PrevAndNext(
		Session session, EventAssistence eventAssistence, long eventId,
		long roleId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

		query.append(_FINDER_COLUMN_EVENTIDANDROLEID_EVENTID_2);

		query.append(_FINDER_COLUMN_EVENTIDANDROLEID_ROLEID_2);

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

		qPos.add(roleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the event assistences where eventId = &#63; and userId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @return the matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventIdAndUserId(long eventId,
		long userId) throws SystemException {
		return findByEventIdAndUserId(eventId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences where eventId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventIdAndUserId(long eventId,
		long userId, int start, int end) throws SystemException {
		return findByEventIdAndUserId(eventId, userId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences where eventId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventIdAndUserId(long eventId,
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				eventId, userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTIDANDUSERID,
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
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_EVENTIDANDUSERID_EVENTID_2);

				query.append(_FINDER_COLUMN_EVENTIDANDUSERID_USERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				qPos.add(userId);

				list = (List<EventAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_EVENTIDANDUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first event assistence in the ordered set where eventId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByEventIdAndUserId_First(long eventId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		List<EventAssistence> list = findByEventIdAndUserId(eventId, userId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last event assistence in the ordered set where eventId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByEventIdAndUserId_Last(long eventId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		int count = countByEventIdAndUserId(eventId, userId);

		List<EventAssistence> list = findByEventIdAndUserId(eventId, userId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the event assistences before and after the current event assistence in the ordered set where eventId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAssistencePK the primary key of the current event assistence
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence[] findByEventIdAndUserId_PrevAndNext(
		EventAssistencePK eventAssistencePK, long eventId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = findByPrimaryKey(eventAssistencePK);

		Session session = null;

		try {
			session = openSession();

			EventAssistence[] array = new EventAssistenceImpl[3];

			array[0] = getByEventIdAndUserId_PrevAndNext(session,
					eventAssistence, eventId, userId, orderByComparator, true);

			array[1] = eventAssistence;

			array[2] = getByEventIdAndUserId_PrevAndNext(session,
					eventAssistence, eventId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAssistence getByEventIdAndUserId_PrevAndNext(
		Session session, EventAssistence eventAssistence, long eventId,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

		query.append(_FINDER_COLUMN_EVENTIDANDUSERID_EVENTID_2);

		query.append(_FINDER_COLUMN_EVENTIDANDUSERID_USERID_2);

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

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the event assistences where userId = &#63; and roleId = &#63;.
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @return the matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByUserIdAndRoleId(long userId, long roleId)
		throws SystemException {
		return findByUserIdAndRoleId(userId, roleId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences where userId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByUserIdAndRoleId(long userId,
		long roleId, int start, int end) throws SystemException {
		return findByUserIdAndRoleId(userId, roleId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences where userId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByUserIdAndRoleId(long userId,
		long roleId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, roleId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERIDANDROLEID,
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
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_USERIDANDROLEID_USERID_2);

				query.append(_FINDER_COLUMN_USERIDANDROLEID_ROLEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(roleId);

				list = (List<EventAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERIDANDROLEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first event assistence in the ordered set where userId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByUserIdAndRoleId_First(long userId,
		long roleId, OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		List<EventAssistence> list = findByUserIdAndRoleId(userId, roleId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last event assistence in the ordered set where userId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByUserIdAndRoleId_Last(long userId, long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		int count = countByUserIdAndRoleId(userId, roleId);

		List<EventAssistence> list = findByUserIdAndRoleId(userId, roleId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the event assistences before and after the current event assistence in the ordered set where userId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAssistencePK the primary key of the current event assistence
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence[] findByUserIdAndRoleId_PrevAndNext(
		EventAssistencePK eventAssistencePK, long userId, long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = findByPrimaryKey(eventAssistencePK);

		Session session = null;

		try {
			session = openSession();

			EventAssistence[] array = new EventAssistenceImpl[3];

			array[0] = getByUserIdAndRoleId_PrevAndNext(session,
					eventAssistence, userId, roleId, orderByComparator, true);

			array[1] = eventAssistence;

			array[2] = getByUserIdAndRoleId_PrevAndNext(session,
					eventAssistence, userId, roleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAssistence getByUserIdAndRoleId_PrevAndNext(
		Session session, EventAssistence eventAssistence, long userId,
		long roleId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

		query.append(_FINDER_COLUMN_USERIDANDROLEID_USERID_2);

		query.append(_FINDER_COLUMN_USERIDANDROLEID_ROLEID_2);

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

		qPos.add(userId);

		qPos.add(roleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the event assistences where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventId(long eventId)
		throws SystemException {
		return findByEventId(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventId(long eventId, int start, int end)
		throws SystemException {
		return findByEventId(eventId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByEventId(long eventId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				eventId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTID,
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

				query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				list = (List<EventAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
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
	 * Finds the first event assistence in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByEventId_First(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		List<EventAssistence> list = findByEventId(eventId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last event assistence in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByEventId_Last(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		int count = countByEventId(eventId);

		List<EventAssistence> list = findByEventId(eventId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the event assistences before and after the current event assistence in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAssistencePK the primary key of the current event assistence
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence[] findByEventId_PrevAndNext(
		EventAssistencePK eventAssistencePK, long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = findByPrimaryKey(eventAssistencePK);

		Session session = null;

		try {
			session = openSession();

			EventAssistence[] array = new EventAssistenceImpl[3];

			array[0] = getByEventId_PrevAndNext(session, eventAssistence,
					eventId, orderByComparator, true);

			array[1] = eventAssistence;

			array[2] = getByEventId_PrevAndNext(session, eventAssistence,
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

	protected EventAssistence getByEventId_PrevAndNext(Session session,
		EventAssistence eventAssistence, long eventId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

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
			Object[] values = orderByComparator.getOrderByValues(eventAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the event assistences where userId = &#63;.
	 *
	 * @param userId the user id to search with
	 * @return the matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
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

				query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<EventAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
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
	 * Finds the first event assistence in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		List<EventAssistence> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last event assistence in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		int count = countByUserId(userId);

		List<EventAssistence> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the event assistences before and after the current event assistence in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAssistencePK the primary key of the current event assistence
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence[] findByUserId_PrevAndNext(
		EventAssistencePK eventAssistencePK, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = findByPrimaryKey(eventAssistencePK);

		Session session = null;

		try {
			session = openSession();

			EventAssistence[] array = new EventAssistenceImpl[3];

			array[0] = getByUserId_PrevAndNext(session, eventAssistence,
					userId, orderByComparator, true);

			array[1] = eventAssistence;

			array[2] = getByUserId_PrevAndNext(session, eventAssistence,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAssistence getByUserId_PrevAndNext(Session session,
		EventAssistence eventAssistence, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the event assistences where roleId = &#63;.
	 *
	 * @param roleId the role id to search with
	 * @return the matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByRoleId(long roleId)
		throws SystemException {
		return findByRoleId(roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByRoleId(long roleId, int start, int end)
		throws SystemException {
		return findByRoleId(roleId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findByRoleId(long roleId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				roleId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ROLEID,
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

				query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

				list = (List<EventAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ROLEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first event assistence in the ordered set where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByRoleId_First(long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		List<EventAssistence> list = findByRoleId(roleId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last event assistence in the ordered set where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a matching event assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence findByRoleId_Last(long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		int count = countByRoleId(roleId);

		List<EventAssistence> list = findByRoleId(roleId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the event assistences before and after the current event assistence in the ordered set where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventAssistencePK the primary key of the current event assistence
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next event assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventAssistenceException if a event assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventAssistence[] findByRoleId_PrevAndNext(
		EventAssistencePK eventAssistencePK, long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchEventAssistenceException, SystemException {
		EventAssistence eventAssistence = findByPrimaryKey(eventAssistencePK);

		Session session = null;

		try {
			session = openSession();

			EventAssistence[] array = new EventAssistenceImpl[3];

			array[0] = getByRoleId_PrevAndNext(session, eventAssistence,
					roleId, orderByComparator, true);

			array[1] = eventAssistence;

			array[2] = getByRoleId_PrevAndNext(session, eventAssistence,
					roleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventAssistence getByRoleId_PrevAndNext(Session session,
		EventAssistence eventAssistence, long roleId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EVENTASSISTENCE_WHERE);

		query.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

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

		qPos.add(roleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(eventAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EventAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the event assistences.
	 *
	 * @return the event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the event assistences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @return the range of event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the event assistences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of event assistences to return
	 * @param end the upper bound of the range of event assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventAssistence> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventAssistence> list = (List<EventAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_EVENTASSISTENCE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_EVENTASSISTENCE;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<EventAssistence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<EventAssistence>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventAssistence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the event assistences where eventId = &#63; and roleId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventIdAndRoleId(long eventId, long roleId)
		throws SystemException {
		for (EventAssistence eventAssistence : findByEventIdAndRoleId(eventId,
				roleId)) {
			remove(eventAssistence);
		}
	}

	/**
	 * Removes all the event assistences where eventId = &#63; and userId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventIdAndUserId(long eventId, long userId)
		throws SystemException {
		for (EventAssistence eventAssistence : findByEventIdAndUserId(eventId,
				userId)) {
			remove(eventAssistence);
		}
	}

	/**
	 * Removes all the event assistences where userId = &#63; and roleId = &#63; from the database.
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserIdAndRoleId(long userId, long roleId)
		throws SystemException {
		for (EventAssistence eventAssistence : findByUserIdAndRoleId(userId,
				roleId)) {
			remove(eventAssistence);
		}
	}

	/**
	 * Removes all the event assistences where eventId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventId(long eventId) throws SystemException {
		for (EventAssistence eventAssistence : findByEventId(eventId)) {
			remove(eventAssistence);
		}
	}

	/**
	 * Removes all the event assistences where userId = &#63; from the database.
	 *
	 * @param userId the user id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (EventAssistence eventAssistence : findByUserId(userId)) {
			remove(eventAssistence);
		}
	}

	/**
	 * Removes all the event assistences where roleId = &#63; from the database.
	 *
	 * @param roleId the role id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRoleId(long roleId) throws SystemException {
		for (EventAssistence eventAssistence : findByRoleId(roleId)) {
			remove(eventAssistence);
		}
	}

	/**
	 * Removes all the event assistences from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (EventAssistence eventAssistence : findAll()) {
			remove(eventAssistence);
		}
	}

	/**
	 * Counts all the event assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @return the number of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventIdAndRoleId(long eventId, long roleId)
		throws SystemException {
		Object[] finderArgs = new Object[] { eventId, roleId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTIDANDROLEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_EVENTIDANDROLEID_EVENTID_2);

				query.append(_FINDER_COLUMN_EVENTIDANDROLEID_ROLEID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				qPos.add(roleId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTIDANDROLEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the event assistences where eventId = &#63; and userId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @param userId the user id to search with
	 * @return the number of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEventIdAndUserId(long eventId, long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { eventId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EVENTIDANDUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_EVENTIDANDUSERID_EVENTID_2);

				query.append(_FINDER_COLUMN_EVENTIDANDUSERID_USERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EVENTIDANDUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the event assistences where userId = &#63; and roleId = &#63;.
	 *
	 * @param userId the user id to search with
	 * @param roleId the role id to search with
	 * @return the number of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserIdAndRoleId(long userId, long roleId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, roleId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERIDANDROLEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_USERIDANDROLEID_USERID_2);

				query.append(_FINDER_COLUMN_USERIDANDROLEID_ROLEID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(roleId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERIDANDROLEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the event assistences where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the number of matching event assistences
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

				query.append(_SQL_COUNT_EVENTASSISTENCE_WHERE);

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
	 * Counts all the event assistences where userId = &#63;.
	 *
	 * @param userId the user id to search with
	 * @return the number of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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
	 * Counts all the event assistences where roleId = &#63;.
	 *
	 * @param roleId the role id to search with
	 * @return the number of matching event assistences
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRoleId(long roleId) throws SystemException {
		Object[] finderArgs = new Object[] { roleId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ROLEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_EVENTASSISTENCE_WHERE);

				query.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(roleId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ROLEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the event assistences.
	 *
	 * @return the number of event assistences
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

				Query q = session.createQuery(_SQL_COUNT_EVENTASSISTENCE);

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
	 * Initializes the event assistence persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.EventAssistence")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<EventAssistence>> listenersList = new ArrayList<ModelListener<EventAssistence>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<EventAssistence>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_EVENTASSISTENCE = "SELECT eventAssistence FROM EventAssistence eventAssistence";
	private static final String _SQL_SELECT_EVENTASSISTENCE_WHERE = "SELECT eventAssistence FROM EventAssistence eventAssistence WHERE ";
	private static final String _SQL_COUNT_EVENTASSISTENCE = "SELECT COUNT(eventAssistence) FROM EventAssistence eventAssistence";
	private static final String _SQL_COUNT_EVENTASSISTENCE_WHERE = "SELECT COUNT(eventAssistence) FROM EventAssistence eventAssistence WHERE ";
	private static final String _FINDER_COLUMN_EVENTIDANDROLEID_EVENTID_2 = "eventAssistence.id.eventId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTIDANDROLEID_ROLEID_2 = "eventAssistence.id.roleId = ?";
	private static final String _FINDER_COLUMN_EVENTIDANDUSERID_EVENTID_2 = "eventAssistence.id.eventId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTIDANDUSERID_USERID_2 = "eventAssistence.id.userId = ?";
	private static final String _FINDER_COLUMN_USERIDANDROLEID_USERID_2 = "eventAssistence.id.userId = ? AND ";
	private static final String _FINDER_COLUMN_USERIDANDROLEID_ROLEID_2 = "eventAssistence.id.roleId = ?";
	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 = "eventAssistence.id.eventId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "eventAssistence.id.userId = ?";
	private static final String _FINDER_COLUMN_ROLEID_ROLEID_2 = "eventAssistence.id.roleId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventAssistence.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventAssistence exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EventAssistence exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(EventAssistencePersistenceImpl.class);
}