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

import de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException;
import de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence;
import de.tum.in.ziller.thesis.social_campaign.model.impl.AnonymAssistenceImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.AnonymAssistenceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the anonym assistence service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link AnonymAssistenceUtil} to access the anonym assistence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see AnonymAssistencePersistence
 * @see AnonymAssistenceUtil
 * @generated
 */
public class AnonymAssistencePersistenceImpl extends BasePersistenceImpl<AnonymAssistence>
	implements AnonymAssistencePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AnonymAssistenceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_EVENTIDANDROLEID = new FinderPath(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByEventIdAndRoleId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_EVENTIDANDROLEID = new FinderPath(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByEventIdAndRoleId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the anonym assistence in the entity cache if it is enabled.
	 *
	 * @param anonymAssistence the anonym assistence to cache
	 */
	public void cacheResult(AnonymAssistence anonymAssistence) {
		EntityCacheUtil.putResult(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceImpl.class, anonymAssistence.getPrimaryKey(),
			anonymAssistence);
	}

	/**
	 * Caches the anonym assistences in the entity cache if it is enabled.
	 *
	 * @param anonymAssistences the anonym assistences to cache
	 */
	public void cacheResult(List<AnonymAssistence> anonymAssistences) {
		for (AnonymAssistence anonymAssistence : anonymAssistences) {
			if (EntityCacheUtil.getResult(
						AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
						AnonymAssistenceImpl.class,
						anonymAssistence.getPrimaryKey(), this) == null) {
				cacheResult(anonymAssistence);
			}
		}
	}

	/**
	 * Clears the cache for all anonym assistences.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(AnonymAssistenceImpl.class.getName());
		EntityCacheUtil.clearCache(AnonymAssistenceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the anonym assistence.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(AnonymAssistence anonymAssistence) {
		EntityCacheUtil.removeResult(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceImpl.class, anonymAssistence.getPrimaryKey());
	}

	/**
	 * Creates a new anonym assistence with the primary key. Does not add the anonym assistence to the database.
	 *
	 * @param anonymAssistencePK the primary key for the new anonym assistence
	 * @return the new anonym assistence
	 */
	public AnonymAssistence create(AnonymAssistencePK anonymAssistencePK) {
		AnonymAssistence anonymAssistence = new AnonymAssistenceImpl();

		anonymAssistence.setNew(true);
		anonymAssistence.setPrimaryKey(anonymAssistencePK);

		return anonymAssistence;
	}

	/**
	 * Removes the anonym assistence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the anonym assistence to remove
	 * @return the anonym assistence that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove((AnonymAssistencePK)primaryKey);
	}

	/**
	 * Removes the anonym assistence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param anonymAssistencePK the primary key of the anonym assistence to remove
	 * @return the anonym assistence that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence remove(AnonymAssistencePK anonymAssistencePK)
		throws NoSuchAnonymAssistenceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AnonymAssistence anonymAssistence = (AnonymAssistence)session.get(AnonymAssistenceImpl.class,
					anonymAssistencePK);

			if (anonymAssistence == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						anonymAssistencePK);
				}

				throw new NoSuchAnonymAssistenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					anonymAssistencePK);
			}

			return remove(anonymAssistence);
		}
		catch (NoSuchAnonymAssistenceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymAssistence removeImpl(AnonymAssistence anonymAssistence)
		throws SystemException {
		anonymAssistence = toUnwrappedModel(anonymAssistence);

		Session session = null;

		try {
			session = openSession();

			if (anonymAssistence.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AnonymAssistenceImpl.class,
						anonymAssistence.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(anonymAssistence);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceImpl.class, anonymAssistence.getPrimaryKey());

		return anonymAssistence;
	}

	public AnonymAssistence updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence anonymAssistence,
		boolean merge) throws SystemException {
		anonymAssistence = toUnwrappedModel(anonymAssistence);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, anonymAssistence, merge);

			anonymAssistence.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
			AnonymAssistenceImpl.class, anonymAssistence.getPrimaryKey(),
			anonymAssistence);

		return anonymAssistence;
	}

	protected AnonymAssistence toUnwrappedModel(
		AnonymAssistence anonymAssistence) {
		if (anonymAssistence instanceof AnonymAssistenceImpl) {
			return anonymAssistence;
		}

		AnonymAssistenceImpl anonymAssistenceImpl = new AnonymAssistenceImpl();

		anonymAssistenceImpl.setNew(anonymAssistence.isNew());
		anonymAssistenceImpl.setPrimaryKey(anonymAssistence.getPrimaryKey());

		anonymAssistenceImpl.setEventId(anonymAssistence.getEventId());
		anonymAssistenceImpl.setRoleId(anonymAssistence.getRoleId());
		anonymAssistenceImpl.setQuantity(anonymAssistence.getQuantity());

		return anonymAssistenceImpl;
	}

	/**
	 * Finds the anonym assistence with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonym assistence to find
	 * @return the anonym assistence
	 * @throws com.liferay.portal.NoSuchModelException if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((AnonymAssistencePK)primaryKey);
	}

	/**
	 * Finds the anonym assistence with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException} if it could not be found.
	 *
	 * @param anonymAssistencePK the primary key of the anonym assistence to find
	 * @return the anonym assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence findByPrimaryKey(
		AnonymAssistencePK anonymAssistencePK)
		throws NoSuchAnonymAssistenceException, SystemException {
		AnonymAssistence anonymAssistence = fetchByPrimaryKey(anonymAssistencePK);

		if (anonymAssistence == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					anonymAssistencePK);
			}

			throw new NoSuchAnonymAssistenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				anonymAssistencePK);
		}

		return anonymAssistence;
	}

	/**
	 * Finds the anonym assistence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonym assistence to find
	 * @return the anonym assistence, or <code>null</code> if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((AnonymAssistencePK)primaryKey);
	}

	/**
	 * Finds the anonym assistence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param anonymAssistencePK the primary key of the anonym assistence to find
	 * @return the anonym assistence, or <code>null</code> if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence fetchByPrimaryKey(
		AnonymAssistencePK anonymAssistencePK) throws SystemException {
		AnonymAssistence anonymAssistence = (AnonymAssistence)EntityCacheUtil.getResult(AnonymAssistenceModelImpl.ENTITY_CACHE_ENABLED,
				AnonymAssistenceImpl.class, anonymAssistencePK, this);

		if (anonymAssistence == null) {
			Session session = null;

			try {
				session = openSession();

				anonymAssistence = (AnonymAssistence)session.get(AnonymAssistenceImpl.class,
						anonymAssistencePK);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (anonymAssistence != null) {
					cacheResult(anonymAssistence);
				}

				closeSession(session);
			}
		}

		return anonymAssistence;
	}

	/**
	 * Finds all the anonym assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @return the matching anonym assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnonymAssistence> findByEventIdAndRoleId(long eventId,
		long roleId) throws SystemException {
		return findByEventIdAndRoleId(eventId, roleId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the anonym assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of anonym assistences to return
	 * @param end the upper bound of the range of anonym assistences to return (not inclusive)
	 * @return the range of matching anonym assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnonymAssistence> findByEventIdAndRoleId(long eventId,
		long roleId, int start, int end) throws SystemException {
		return findByEventIdAndRoleId(eventId, roleId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the anonym assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param start the lower bound of the range of anonym assistences to return
	 * @param end the upper bound of the range of anonym assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching anonym assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnonymAssistence> findByEventIdAndRoleId(long eventId,
		long roleId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				eventId, roleId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AnonymAssistence> list = (List<AnonymAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_EVENTIDANDROLEID,
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

				query.append(_SQL_SELECT_ANONYMASSISTENCE_WHERE);

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

				list = (List<AnonymAssistence>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AnonymAssistence>();
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
	 * Finds the first anonym assistence in the ordered set where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching anonym assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException if a matching anonym assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence findByEventIdAndRoleId_First(long eventId,
		long roleId, OrderByComparator orderByComparator)
		throws NoSuchAnonymAssistenceException, SystemException {
		List<AnonymAssistence> list = findByEventIdAndRoleId(eventId, roleId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAnonymAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last anonym assistence in the ordered set where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching anonym assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException if a matching anonym assistence could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence findByEventIdAndRoleId_Last(long eventId,
		long roleId, OrderByComparator orderByComparator)
		throws NoSuchAnonymAssistenceException, SystemException {
		int count = countByEventIdAndRoleId(eventId, roleId);

		List<AnonymAssistence> list = findByEventIdAndRoleId(eventId, roleId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("eventId=");
			msg.append(eventId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAnonymAssistenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the anonym assistences before and after the current anonym assistence in the ordered set where eventId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param anonymAssistencePK the primary key of the current anonym assistence
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next anonym assistence
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAnonymAssistenceException if a anonym assistence with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnonymAssistence[] findByEventIdAndRoleId_PrevAndNext(
		AnonymAssistencePK anonymAssistencePK, long eventId, long roleId,
		OrderByComparator orderByComparator)
		throws NoSuchAnonymAssistenceException, SystemException {
		AnonymAssistence anonymAssistence = findByPrimaryKey(anonymAssistencePK);

		Session session = null;

		try {
			session = openSession();

			AnonymAssistence[] array = new AnonymAssistenceImpl[3];

			array[0] = getByEventIdAndRoleId_PrevAndNext(session,
					anonymAssistence, eventId, roleId, orderByComparator, true);

			array[1] = anonymAssistence;

			array[2] = getByEventIdAndRoleId_PrevAndNext(session,
					anonymAssistence, eventId, roleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymAssistence getByEventIdAndRoleId_PrevAndNext(
		Session session, AnonymAssistence anonymAssistence, long eventId,
		long roleId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ANONYMASSISTENCE_WHERE);

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
			Object[] values = orderByComparator.getOrderByValues(anonymAssistence);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymAssistence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the anonym assistences.
	 *
	 * @return the anonym assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnonymAssistence> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the anonym assistences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonym assistences to return
	 * @param end the upper bound of the range of anonym assistences to return (not inclusive)
	 * @return the range of anonym assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnonymAssistence> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the anonym assistences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonym assistences to return
	 * @param end the upper bound of the range of anonym assistences to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of anonym assistences
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnonymAssistence> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AnonymAssistence> list = (List<AnonymAssistence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_ANONYMASSISTENCE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_ANONYMASSISTENCE;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AnonymAssistence>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AnonymAssistence>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AnonymAssistence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the anonym assistences where eventId = &#63; and roleId = &#63; from the database.
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEventIdAndRoleId(long eventId, long roleId)
		throws SystemException {
		for (AnonymAssistence anonymAssistence : findByEventIdAndRoleId(
				eventId, roleId)) {
			remove(anonymAssistence);
		}
	}

	/**
	 * Removes all the anonym assistences from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AnonymAssistence anonymAssistence : findAll()) {
			remove(anonymAssistence);
		}
	}

	/**
	 * Counts all the anonym assistences where eventId = &#63; and roleId = &#63;.
	 *
	 * @param eventId the event id to search with
	 * @param roleId the role id to search with
	 * @return the number of matching anonym assistences
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

				query.append(_SQL_COUNT_ANONYMASSISTENCE_WHERE);

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
	 * Counts all the anonym assistences.
	 *
	 * @return the number of anonym assistences
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

				Query q = session.createQuery(_SQL_COUNT_ANONYMASSISTENCE);

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
	 * Initializes the anonym assistence persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AnonymAssistence>> listenersList = new ArrayList<ModelListener<AnonymAssistence>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AnonymAssistence>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_ANONYMASSISTENCE = "SELECT anonymAssistence FROM AnonymAssistence anonymAssistence";
	private static final String _SQL_SELECT_ANONYMASSISTENCE_WHERE = "SELECT anonymAssistence FROM AnonymAssistence anonymAssistence WHERE ";
	private static final String _SQL_COUNT_ANONYMASSISTENCE = "SELECT COUNT(anonymAssistence) FROM AnonymAssistence anonymAssistence";
	private static final String _SQL_COUNT_ANONYMASSISTENCE_WHERE = "SELECT COUNT(anonymAssistence) FROM AnonymAssistence anonymAssistence WHERE ";
	private static final String _FINDER_COLUMN_EVENTIDANDROLEID_EVENTID_2 = "anonymAssistence.id.eventId = ? AND ";
	private static final String _FINDER_COLUMN_EVENTIDANDROLEID_ROLEID_2 = "anonymAssistence.id.roleId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "anonymAssistence.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AnonymAssistence exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AnonymAssistence exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(AnonymAssistencePersistenceImpl.class);
}