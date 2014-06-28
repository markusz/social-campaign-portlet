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
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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

import de.tum.in.ziller.thesis.social_campaign.NoSuchCategoryException;
import de.tum.in.ziller.thesis.social_campaign.model.Category;
import de.tum.in.ziller.thesis.social_campaign.model.impl.CategoryImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.CategoryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the category service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link CategoryUtil} to access the category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see CategoryPersistence
 * @see CategoryUtil
 * @generated
 */
public class CategoryPersistenceImpl extends BasePersistenceImpl<Category>
	implements CategoryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = CategoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CategoryModelImpl.ENTITY_CACHE_ENABLED,
			CategoryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CategoryModelImpl.ENTITY_CACHE_ENABLED,
			CategoryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the category in the entity cache if it is enabled.
	 *
	 * @param category the category to cache
	 */
	public void cacheResult(Category category) {
		EntityCacheUtil.putResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
			CategoryImpl.class, category.getPrimaryKey(), category);
	}

	/**
	 * Caches the categories in the entity cache if it is enabled.
	 *
	 * @param categories the categories to cache
	 */
	public void cacheResult(List<Category> categories) {
		for (Category category : categories) {
			if (EntityCacheUtil.getResult(
						CategoryModelImpl.ENTITY_CACHE_ENABLED,
						CategoryImpl.class, category.getPrimaryKey(), this) == null) {
				cacheResult(category);
			}
		}
	}

	/**
	 * Clears the cache for all categories.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(CategoryImpl.class.getName());
		EntityCacheUtil.clearCache(CategoryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the category.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Category category) {
		EntityCacheUtil.removeResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
			CategoryImpl.class, category.getPrimaryKey());
	}

	/**
	 * Creates a new category with the primary key. Does not add the category to the database.
	 *
	 * @param categoryId the primary key for the new category
	 * @return the new category
	 */
	public Category create(long categoryId) {
		Category category = new CategoryImpl();

		category.setNew(true);
		category.setPrimaryKey(categoryId);

		return category;
	}

	/**
	 * Removes the category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the category to remove
	 * @return the category that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Category remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the category to remove
	 * @return the category that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchCategoryException if a category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Category remove(long categoryId)
		throws NoSuchCategoryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Category category = (Category)session.get(CategoryImpl.class,
					new Long(categoryId));

			if (category == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + categoryId);
				}

				throw new NoSuchCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					categoryId);
			}

			return remove(category);
		}
		catch (NoSuchCategoryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Category removeImpl(Category category) throws SystemException {
		category = toUnwrappedModel(category);

		Session session = null;

		try {
			session = openSession();

			if (category.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(CategoryImpl.class,
						category.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(category);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
			CategoryImpl.class, category.getPrimaryKey());

		return category;
	}

	public Category updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.Category category,
		boolean merge) throws SystemException {
		category = toUnwrappedModel(category);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, category, merge);

			category.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
			CategoryImpl.class, category.getPrimaryKey(), category);

		return category;
	}

	protected Category toUnwrappedModel(Category category) {
		if (category instanceof CategoryImpl) {
			return category;
		}

		CategoryImpl categoryImpl = new CategoryImpl();

		categoryImpl.setNew(category.isNew());
		categoryImpl.setPrimaryKey(category.getPrimaryKey());

		categoryImpl.setCategoryId(category.getCategoryId());
		categoryImpl.setCategoryName(category.getCategoryName());

		return categoryImpl;
	}

	/**
	 * Finds the category with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the category to find
	 * @return the category
	 * @throws com.liferay.portal.NoSuchModelException if a category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Category findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the category with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchCategoryException} if it could not be found.
	 *
	 * @param categoryId the primary key of the category to find
	 * @return the category
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchCategoryException if a category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Category findByPrimaryKey(long categoryId)
		throws NoSuchCategoryException, SystemException {
		Category category = fetchByPrimaryKey(categoryId);

		if (category == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + categoryId);
			}

			throw new NoSuchCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				categoryId);
		}

		return category;
	}

	/**
	 * Finds the category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the category to find
	 * @return the category, or <code>null</code> if a category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Category fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the category to find
	 * @return the category, or <code>null</code> if a category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Category fetchByPrimaryKey(long categoryId)
		throws SystemException {
		Category category = (Category)EntityCacheUtil.getResult(CategoryModelImpl.ENTITY_CACHE_ENABLED,
				CategoryImpl.class, categoryId, this);

		if (category == null) {
			Session session = null;

			try {
				session = openSession();

				category = (Category)session.get(CategoryImpl.class,
						new Long(categoryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (category != null) {
					cacheResult(category);
				}

				closeSession(session);
			}
		}

		return category;
	}

	/**
	 * Finds all the categories.
	 *
	 * @return the categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<Category> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of categories to return
	 * @param end the upper bound of the range of categories to return (not inclusive)
	 * @return the range of categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<Category> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of categories to return
	 * @param end the upper bound of the range of categories to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<Category> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Category> list = (List<Category>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_CATEGORY);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_CATEGORY;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Category>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Category>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Category>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the categories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Category category : findAll()) {
			remove(category);
		}
	}

	/**
	 * Counts all the categories.
	 *
	 * @return the number of categories
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

				Query q = session.createQuery(_SQL_COUNT_CATEGORY);

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
	 * Gets all the Events associated with the category.
	 *
	 * @param pk the primary key of the category to get the associated Events for
	 * @return the Events associated with the category
	 * @throws SystemException if a system exception occurred
	 */
	public List<de.tum.in.ziller.thesis.social_campaign.model.Event> getEvents(
		long pk) throws SystemException {
		return getEvents(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Gets a range of all the Events associated with the category.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the category to get the associated Events for
	 * @param start the lower bound of the range of categories to return
	 * @param end the upper bound of the range of categories to return (not inclusive)
	 * @return the range of Events associated with the category
	 * @throws SystemException if a system exception occurred
	 */
	public List<de.tum.in.ziller.thesis.social_campaign.model.Event> getEvents(
		long pk, int start, int end) throws SystemException {
		return getEvents(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_EVENTS = new FinderPath(de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.ENTITY_CACHE_ENABLED,
			de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.FINDER_CACHE_ENABLED,
			de.tum.in.ziller.thesis.social_campaign.service.persistence.EventPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getEvents",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Gets an ordered range of all the Events associated with the category.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the category to get the associated Events for
	 * @param start the lower bound of the range of categories to return
	 * @param end the upper bound of the range of categories to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of Events associated with the category
	 * @throws SystemException if a system exception occurred
	 */
	public List<de.tum.in.ziller.thesis.social_campaign.model.Event> getEvents(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<de.tum.in.ziller.thesis.social_campaign.model.Event> list = (List<de.tum.in.ziller.thesis.social_campaign.model.Event>)FinderCacheUtil.getResult(FINDER_PATH_GET_EVENTS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETEVENTS.concat(ORDER_BY_CLAUSE)
										.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETEVENTS.concat(de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("zsc_Event",
					de.tum.in.ziller.thesis.social_campaign.model.impl.EventImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<de.tum.in.ziller.thesis.social_campaign.model.Event>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<de.tum.in.ziller.thesis.social_campaign.model.Event>();
				}

				eventPersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_EVENTS, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_EVENTS_SIZE = new FinderPath(de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.ENTITY_CACHE_ENABLED,
			de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.FINDER_CACHE_ENABLED,
			de.tum.in.ziller.thesis.social_campaign.service.persistence.EventPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getEventsSize", new String[] { Long.class.getName() });

	/**
	 * Gets the number of Events associated with the category.
	 *
	 * @param pk the primary key of the category to get the number of associated Events for
	 * @return the number of Events associated with the category
	 * @throws SystemException if a system exception occurred
	 */
	public int getEventsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_EVENTS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETEVENTSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_EVENTS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_EVENT = new FinderPath(de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.ENTITY_CACHE_ENABLED,
			de.tum.in.ziller.thesis.social_campaign.model.impl.EventModelImpl.FINDER_CACHE_ENABLED,
			de.tum.in.ziller.thesis.social_campaign.service.persistence.EventPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsEvent",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Determines whether the Event is associated with the category.
	 *
	 * @param pk the primary key of the category
	 * @param eventPK the primary key of the Event
	 * @return whether the Event is associated with the category
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsEvent(long pk, long eventPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, eventPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_EVENT,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsEvent.contains(pk, eventPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_EVENT,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Determines whether the category has any Events associated with it.
	 *
	 * @param pk the primary key of the category to check for associations with Events
	 * @return whether the category has any Events associated with it
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsEvents(long pk) throws SystemException {
		if (getEventsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the category persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.Category")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Category>> listenersList = new ArrayList<ModelListener<Category>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Category>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsEvent = new ContainsEvent(this);
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
	protected ContainsEvent containsEvent;

	protected class ContainsEvent {
		protected ContainsEvent(CategoryPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSEVENT,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long categoryId, long eventId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(categoryId), new Long(eventId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	private static final String _SQL_SELECT_CATEGORY = "SELECT category FROM Category category";
	private static final String _SQL_COUNT_CATEGORY = "SELECT COUNT(category) FROM Category category";
	private static final String _SQL_GETEVENTS = "SELECT {zsc_Event.*} FROM zsc_Event INNER JOIN zsc_Category ON (zsc_Category.categoryId = zsc_Event.categoryId) WHERE (zsc_Category.categoryId = ?)";
	private static final String _SQL_GETEVENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM zsc_Event WHERE categoryId = ?";
	private static final String _SQL_CONTAINSEVENT = "SELECT COUNT(*) AS COUNT_VALUE FROM zsc_Event WHERE categoryId = ? AND eventId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "category.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Category exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(CategoryPersistenceImpl.class);
}