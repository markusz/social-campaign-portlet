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

import de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException;
import de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest;
import de.tum.in.ziller.thesis.social_campaign.model.impl.AssistenceRequestImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.AssistenceRequestModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the assistence request service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link AssistenceRequestUtil} to access the assistence request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see AssistenceRequestPersistence
 * @see AssistenceRequestUtil
 * @generated
 */
public class AssistenceRequestPersistenceImpl extends BasePersistenceImpl<AssistenceRequest>
	implements AssistenceRequestPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AssistenceRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_EVENTID = new FinderPath(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByEventId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTID = new FinderPath(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByEventId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the assistence request in the entity cache if it is enabled.
	 *
	 * @param assistenceRequest the assistence request to cache
	 */
	public void cacheResult(AssistenceRequest assistenceRequest) {
		EntityCacheUtil.putResult(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestImpl.class, assistenceRequest.getPrimaryKey(),
			assistenceRequest);
	}

	/**
	 * Caches the assistence requests in the entity cache if it is enabled.
	 *
	 * @param assistenceRequests the assistence requests to cache
	 */
	public void cacheResult(List<AssistenceRequest> assistenceRequests) {
		for (AssistenceRequest assistenceRequest : assistenceRequests) {
			if (EntityCacheUtil.getResult(
						AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
						AssistenceRequestImpl.class,
						assistenceRequest.getPrimaryKey(), this) == null) {
				cacheResult(assistenceRequest);
			}
		}
	}

	/**
	 * Clears the cache for all assistence requests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(AssistenceRequestImpl.class.getName());
		EntityCacheUtil.clearCache(AssistenceRequestImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the assistence request.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(AssistenceRequest assistenceRequest) {
		EntityCacheUtil.removeResult(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestImpl.class, assistenceRequest.getPrimaryKey());
	}

	/**
	 * Creates a new assistence request with the primary key. Does not add the assistence request to the database.
	 *
	 * @param assistenceRequestPK the primary key for the new assistence request
	 * @return the new assistence request
	 */
	public AssistenceRequest create(AssistenceRequestPK assistenceRequestPK) {
		AssistenceRequest assistenceRequest = new AssistenceRequestImpl();

		assistenceRequest.setNew(true);
		assistenceRequest.setPrimaryKey(assistenceRequestPK);

		return assistenceRequest;
	}

	/**
	 * Removes the assistence request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the assistence request to remove
	 * @return the assistence request that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove((AssistenceRequestPK)primaryKey);
	}

	/**
	 * Removes the assistence request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assistenceRequestPK the primary key of the assistence request to remove
	 * @return the assistence request that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest remove(AssistenceRequestPK assistenceRequestPK)
		throws NoSuchAssistenceRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssistenceRequest assistenceRequest = (AssistenceRequest)session.get(AssistenceRequestImpl.class,
					assistenceRequestPK);

			if (assistenceRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						assistenceRequestPK);
				}

				throw new NoSuchAssistenceRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assistenceRequestPK);
			}

			return remove(assistenceRequest);
		}
		catch (NoSuchAssistenceRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssistenceRequest removeImpl(AssistenceRequest assistenceRequest)
		throws SystemException {
		assistenceRequest = toUnwrappedModel(assistenceRequest);

		Session session = null;

		try {
			session = openSession();

			if (assistenceRequest.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssistenceRequestImpl.class,
						assistenceRequest.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(assistenceRequest);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestImpl.class, assistenceRequest.getPrimaryKey());

		return assistenceRequest;
	}

	public AssistenceRequest updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest assistenceRequest,
		boolean merge) throws SystemException {
		assistenceRequest = toUnwrappedModel(assistenceRequest);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assistenceRequest, merge);

			assistenceRequest.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRequestImpl.class, assistenceRequest.getPrimaryKey(),
			assistenceRequest);

		return assistenceRequest;
	}

	protected AssistenceRequest toUnwrappedModel(
		AssistenceRequest assistenceRequest) {
		if (assistenceRequest instanceof AssistenceRequestImpl) {
			return assistenceRequest;
		}

		AssistenceRequestImpl assistenceRequestImpl = new AssistenceRequestImpl();

		assistenceRequestImpl.setNew(assistenceRequest.isNew());
		assistenceRequestImpl.setPrimaryKey(assistenceRequest.getPrimaryKey());

		assistenceRequestImpl.setEventId(assistenceRequest.getEventId());
		assistenceRequestImpl.setRoleId(assistenceRequest.getRoleId());
		assistenceRequestImpl.setQuantity(assistenceRequest.getQuantity());

		return assistenceRequestImpl;
	}

	/**
	 * Finds the assistence request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the assistence request to find
	 * @return the assistence request
	 * @throws com.liferay.portal.NoSuchModelException if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((AssistenceRequestPK)primaryKey);
	}

	/**
	 * Finds the assistence request with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException} if it could not be found.
	 *
	 * @param assistenceRequestPK the primary key of the assistence request to find
	 * @return the assistence request
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest findByPrimaryKey(
		AssistenceRequestPK assistenceRequestPK)
		throws NoSuchAssistenceRequestException, SystemException {
		AssistenceRequest assistenceRequest = fetchByPrimaryKey(assistenceRequestPK);

		if (assistenceRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assistenceRequestPK);
			}

			throw new NoSuchAssistenceRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assistenceRequestPK);
		}

		return assistenceRequest;
	}

	/**
	 * Finds the assistence request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the assistence request to find
	 * @return the assistence request, or <code>null</code> if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((AssistenceRequestPK)primaryKey);
	}

	/**
	 * Finds the assistence request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assistenceRequestPK the primary key of the assistence request to find
	 * @return the assistence request, or <code>null</code> if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest fetchByPrimaryKey(
		AssistenceRequestPK assistenceRequestPK) throws SystemException {
		AssistenceRequest assistenceRequest = (AssistenceRequest)EntityCacheUtil.getResult(AssistenceRequestModelImpl.ENTITY_CACHE_ENABLED,
				AssistenceRequestImpl.class, assistenceRequestPK, this);

		if (assistenceRequest == null) {
			Session session = null;

			try {
				session = openSession();

				assistenceRequest = (AssistenceRequest)session.get(AssistenceRequestImpl.class,
						assistenceRequestPK);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (assistenceRequest != null) {
					cacheResult(assistenceRequest);
				}

				closeSession(session);
			}
		}

		return assistenceRequest;
	}

	/**
	 * Finds all the assistence requests where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the matching assistence requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRequest> findByEventId(long eventId)
		throws SystemException {
		return findByEventId(eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the assistence requests where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param start the lower bound of the range of assistence requests to return
	 * @param end the upper bound of the range of assistence requests to return (not inclusive)
	 * @return the range of matching assistence requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRequest> findByEventId(long eventId, int start,
		int end) throws SystemException {
		return findByEventId(eventId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the assistence requests where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param start the lower bound of the range of assistence requests to return
	 * @param end the upper bound of the range of assistence requests to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching assistence requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRequest> findByEventId(long eventId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				eventId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AssistenceRequest> list = (List<AssistenceRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTID,
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

				query.append(_SQL_SELECT_ASSISTENCEREQUEST_WHERE);

				query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				list = (List<AssistenceRequest>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssistenceRequest>();
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
	 * Finds the first assistence request in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching assistence request
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException if a matching assistence request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest findByEventId_First(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchAssistenceRequestException, SystemException {
		List<AssistenceRequest> list = findByEventId(eventId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAssistenceRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last assistence request in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching assistence request
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException if a matching assistence request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest findByEventId_Last(long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchAssistenceRequestException, SystemException {
		int count = countByEventId(eventId);

		List<AssistenceRequest> list = findByEventId(eventId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAssistenceRequestException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the assistence requests before and after the current assistence request in the ordered set where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assistenceRequestPK the primary key of the current assistence request
	 * @param eventId the event id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next assistence request
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRequestException if a assistence request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRequest[] findByEventId_PrevAndNext(
		AssistenceRequestPK assistenceRequestPK, long eventId,
		OrderByComparator orderByComparator)
		throws NoSuchAssistenceRequestException, SystemException {
		AssistenceRequest assistenceRequest = findByPrimaryKey(assistenceRequestPK);

		Session session = null;

		try {
			session = openSession();

			AssistenceRequest[] array = new AssistenceRequestImpl[3];

			array[0] = getByEventId_PrevAndNext(session, assistenceRequest,
					eventId, orderByComparator, true);

			array[1] = assistenceRequest;

			array[2] = getByEventId_PrevAndNext(session, assistenceRequest,
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

	protected AssistenceRequest getByEventId_PrevAndNext(Session session,
		AssistenceRequest assistenceRequest, long eventId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSISTENCEREQUEST_WHERE);

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
			Object[] values = orderByComparator.getOrderByValues(assistenceRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssistenceRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the assistence requests.
	 *
	 * @return the assistence requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the assistence requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of assistence requests to return
	 * @param end the upper bound of the range of assistence requests to return (not inclusive)
	 * @return the range of assistence requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the assistence requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of assistence requests to return
	 * @param end the upper bound of the range of assistence requests to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of assistence requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRequest> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AssistenceRequest> list = (List<AssistenceRequest>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_ASSISTENCEREQUEST);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_ASSISTENCEREQUEST;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssistenceRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssistenceRequest>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssistenceRequest>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the assistence requests where eventId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventId(long eventId) throws SystemException {
		for (AssistenceRequest assistenceRequest : findByEventId(eventId)) {
			remove(assistenceRequest);
		}
	}

	/**
	 * Removes all the assistence requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssistenceRequest assistenceRequest : findAll()) {
			remove(assistenceRequest);
		}
	}

	/**
	 * Counts all the assistence requests where eventId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @return the number of matching assistence requests
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

				query.append(_SQL_COUNT_ASSISTENCEREQUEST_WHERE);

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
	 * Counts all the assistence requests.
	 *
	 * @return the number of assistence requests
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

				Query q = session.createQuery(_SQL_COUNT_ASSISTENCEREQUEST);

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
	 * Initializes the assistence request persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.AssistenceRequest")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssistenceRequest>> listenersList = new ArrayList<ModelListener<AssistenceRequest>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssistenceRequest>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_ASSISTENCEREQUEST = "SELECT assistenceRequest FROM AssistenceRequest assistenceRequest";
	private static final String _SQL_SELECT_ASSISTENCEREQUEST_WHERE = "SELECT assistenceRequest FROM AssistenceRequest assistenceRequest WHERE ";
	private static final String _SQL_COUNT_ASSISTENCEREQUEST = "SELECT COUNT(assistenceRequest) FROM AssistenceRequest assistenceRequest";
	private static final String _SQL_COUNT_ASSISTENCEREQUEST_WHERE = "SELECT COUNT(assistenceRequest) FROM AssistenceRequest assistenceRequest WHERE ";
	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 = "assistenceRequest.id.eventId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assistenceRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssistenceRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssistenceRequest exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(AssistenceRequestPersistenceImpl.class);
}