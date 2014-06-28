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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import de.tum.in.ziller.thesis.social_campaign.NoSuchEventCreatorException;
import de.tum.in.ziller.thesis.social_campaign.model.EventCreator;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventCreatorImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.EventCreatorModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the Event Creator service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link EventCreatorUtil} to access the Event Creator persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see EventCreatorPersistence
 * @see EventCreatorUtil
 * @generated
 */
public class EventCreatorPersistenceImpl extends BasePersistenceImpl<EventCreator>
	implements EventCreatorPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = EventCreatorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
			EventCreatorModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
			EventCreatorModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the Event Creator in the entity cache if it is enabled.
	 *
	 * @param eventCreator the Event Creator to cache
	 */
	public void cacheResult(EventCreator eventCreator) {
		EntityCacheUtil.putResult(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
			EventCreatorImpl.class, eventCreator.getPrimaryKey(), eventCreator);
	}

	/**
	 * Caches the Event Creators in the entity cache if it is enabled.
	 *
	 * @param eventCreators the Event Creators to cache
	 */
	public void cacheResult(List<EventCreator> eventCreators) {
		for (EventCreator eventCreator : eventCreators) {
			if (EntityCacheUtil.getResult(
						EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
						EventCreatorImpl.class, eventCreator.getPrimaryKey(),
						this) == null) {
				cacheResult(eventCreator);
			}
		}
	}

	/**
	 * Clears the cache for all Event Creators.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(EventCreatorImpl.class.getName());
		EntityCacheUtil.clearCache(EventCreatorImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the Event Creator.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(EventCreator eventCreator) {
		EntityCacheUtil.removeResult(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
			EventCreatorImpl.class, eventCreator.getPrimaryKey());
	}

	/**
	 * Creates a new Event Creator with the primary key. Does not add the Event Creator to the database.
	 *
	 * @param eventCreatorPK the primary key for the new Event Creator
	 * @return the new Event Creator
	 */
	public EventCreator create(EventCreatorPK eventCreatorPK) {
		EventCreator eventCreator = new EventCreatorImpl();

		eventCreator.setNew(true);
		eventCreator.setPrimaryKey(eventCreatorPK);

		return eventCreator;
	}

	/**
	 * Removes the Event Creator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the Event Creator to remove
	 * @return the Event Creator that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a Event Creator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventCreator remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove((EventCreatorPK)primaryKey);
	}

	/**
	 * Removes the Event Creator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventCreatorPK the primary key of the Event Creator to remove
	 * @return the Event Creator that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventCreatorException if a Event Creator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventCreator remove(EventCreatorPK eventCreatorPK)
		throws NoSuchEventCreatorException, SystemException {
		Session session = null;

		try {
			session = openSession();

			EventCreator eventCreator = (EventCreator)session.get(EventCreatorImpl.class,
					eventCreatorPK);

			if (eventCreator == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						eventCreatorPK);
				}

				throw new NoSuchEventCreatorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					eventCreatorPK);
			}

			return remove(eventCreator);
		}
		catch (NoSuchEventCreatorException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventCreator removeImpl(EventCreator eventCreator)
		throws SystemException {
		eventCreator = toUnwrappedModel(eventCreator);

		Session session = null;

		try {
			session = openSession();

			if (eventCreator.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(EventCreatorImpl.class,
						eventCreator.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(eventCreator);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
			EventCreatorImpl.class, eventCreator.getPrimaryKey());

		return eventCreator;
	}

	public EventCreator updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.EventCreator eventCreator,
		boolean merge) throws SystemException {
		eventCreator = toUnwrappedModel(eventCreator);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, eventCreator, merge);

			eventCreator.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
			EventCreatorImpl.class, eventCreator.getPrimaryKey(), eventCreator);

		return eventCreator;
	}

	protected EventCreator toUnwrappedModel(EventCreator eventCreator) {
		if (eventCreator instanceof EventCreatorImpl) {
			return eventCreator;
		}

		EventCreatorImpl eventCreatorImpl = new EventCreatorImpl();

		eventCreatorImpl.setNew(eventCreator.isNew());
		eventCreatorImpl.setPrimaryKey(eventCreator.getPrimaryKey());

		eventCreatorImpl.setEventId(eventCreator.getEventId());
		eventCreatorImpl.setUserProfileId(eventCreator.getUserProfileId());

		return eventCreatorImpl;
	}

	/**
	 * Finds the Event Creator with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the Event Creator to find
	 * @return the Event Creator
	 * @throws com.liferay.portal.NoSuchModelException if a Event Creator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventCreator findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((EventCreatorPK)primaryKey);
	}

	/**
	 * Finds the Event Creator with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchEventCreatorException} if it could not be found.
	 *
	 * @param eventCreatorPK the primary key of the Event Creator to find
	 * @return the Event Creator
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchEventCreatorException if a Event Creator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventCreator findByPrimaryKey(EventCreatorPK eventCreatorPK)
		throws NoSuchEventCreatorException, SystemException {
		EventCreator eventCreator = fetchByPrimaryKey(eventCreatorPK);

		if (eventCreator == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + eventCreatorPK);
			}

			throw new NoSuchEventCreatorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				eventCreatorPK);
		}

		return eventCreator;
	}

	/**
	 * Finds the Event Creator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the Event Creator to find
	 * @return the Event Creator, or <code>null</code> if a Event Creator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventCreator fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((EventCreatorPK)primaryKey);
	}

	/**
	 * Finds the Event Creator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventCreatorPK the primary key of the Event Creator to find
	 * @return the Event Creator, or <code>null</code> if a Event Creator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public EventCreator fetchByPrimaryKey(EventCreatorPK eventCreatorPK)
		throws SystemException {
		EventCreator eventCreator = (EventCreator)EntityCacheUtil.getResult(EventCreatorModelImpl.ENTITY_CACHE_ENABLED,
				EventCreatorImpl.class, eventCreatorPK, this);

		if (eventCreator == null) {
			Session session = null;

			try {
				session = openSession();

				eventCreator = (EventCreator)session.get(EventCreatorImpl.class,
						eventCreatorPK);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (eventCreator != null) {
					cacheResult(eventCreator);
				}

				closeSession(session);
			}
		}

		return eventCreator;
	}

	/**
	 * Finds all the Event Creators.
	 *
	 * @return the Event Creators
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventCreator> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the Event Creators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of Event Creators to return
	 * @param end the upper bound of the range of Event Creators to return (not inclusive)
	 * @return the range of Event Creators
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventCreator> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the Event Creators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of Event Creators to return
	 * @param end the upper bound of the range of Event Creators to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of Event Creators
	 * @throws SystemException if a system exception occurred
	 */
	public List<EventCreator> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<EventCreator> list = (List<EventCreator>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_EVENTCREATOR);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_EVENTCREATOR;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<EventCreator>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<EventCreator>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<EventCreator>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the Event Creators from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (EventCreator eventCreator : findAll()) {
			remove(eventCreator);
		}
	}

	/**
	 * Counts all the Event Creators.
	 *
	 * @return the number of Event Creators
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

				Query q = session.createQuery(_SQL_COUNT_EVENTCREATOR);

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
	 * Initializes the Event Creator persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.EventCreator")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<EventCreator>> listenersList = new ArrayList<ModelListener<EventCreator>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<EventCreator>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_EVENTCREATOR = "SELECT eventCreator FROM EventCreator eventCreator";
	private static final String _SQL_COUNT_EVENTCREATOR = "SELECT COUNT(eventCreator) FROM EventCreator eventCreator";
	private static final String _ORDER_BY_ENTITY_ALIAS = "eventCreator.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EventCreator exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(EventCreatorPersistenceImpl.class);
}