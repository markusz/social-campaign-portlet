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

import de.tum.in.ziller.thesis.social_campaign.NoSuchNewsTypeException;
import de.tum.in.ziller.thesis.social_campaign.model.NewsType;
import de.tum.in.ziller.thesis.social_campaign.model.impl.NewsTypeImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.NewsTypeModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the news type service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link NewsTypeUtil} to access the news type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see NewsTypePersistence
 * @see NewsTypeUtil
 * @generated
 */
public class NewsTypePersistenceImpl extends BasePersistenceImpl<NewsType>
	implements NewsTypePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = NewsTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
			NewsTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
			NewsTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the news type in the entity cache if it is enabled.
	 *
	 * @param newsType the news type to cache
	 */
	public void cacheResult(NewsType newsType) {
		EntityCacheUtil.putResult(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
			NewsTypeImpl.class, newsType.getPrimaryKey(), newsType);
	}

	/**
	 * Caches the news types in the entity cache if it is enabled.
	 *
	 * @param newsTypes the news types to cache
	 */
	public void cacheResult(List<NewsType> newsTypes) {
		for (NewsType newsType : newsTypes) {
			if (EntityCacheUtil.getResult(
						NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
						NewsTypeImpl.class, newsType.getPrimaryKey(), this) == null) {
				cacheResult(newsType);
			}
		}
	}

	/**
	 * Clears the cache for all news types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(NewsTypeImpl.class.getName());
		EntityCacheUtil.clearCache(NewsTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the news type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(NewsType newsType) {
		EntityCacheUtil.removeResult(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
			NewsTypeImpl.class, newsType.getPrimaryKey());
	}

	/**
	 * Creates a new news type with the primary key. Does not add the news type to the database.
	 *
	 * @param newsTypeId the primary key for the new news type
	 * @return the new news type
	 */
	public NewsType create(long newsTypeId) {
		NewsType newsType = new NewsTypeImpl();

		newsType.setNew(true);
		newsType.setPrimaryKey(newsTypeId);

		return newsType;
	}

	/**
	 * Removes the news type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news type to remove
	 * @return the news type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a news type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the news type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsTypeId the primary key of the news type to remove
	 * @return the news type that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchNewsTypeException if a news type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsType remove(long newsTypeId)
		throws NoSuchNewsTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NewsType newsType = (NewsType)session.get(NewsTypeImpl.class,
					new Long(newsTypeId));

			if (newsType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + newsTypeId);
				}

				throw new NoSuchNewsTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					newsTypeId);
			}

			return remove(newsType);
		}
		catch (NoSuchNewsTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsType removeImpl(NewsType newsType) throws SystemException {
		newsType = toUnwrappedModel(newsType);

		Session session = null;

		try {
			session = openSession();

			if (newsType.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(NewsTypeImpl.class,
						newsType.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(newsType);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
			NewsTypeImpl.class, newsType.getPrimaryKey());

		return newsType;
	}

	public NewsType updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.NewsType newsType,
		boolean merge) throws SystemException {
		newsType = toUnwrappedModel(newsType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, newsType, merge);

			newsType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
			NewsTypeImpl.class, newsType.getPrimaryKey(), newsType);

		return newsType;
	}

	protected NewsType toUnwrappedModel(NewsType newsType) {
		if (newsType instanceof NewsTypeImpl) {
			return newsType;
		}

		NewsTypeImpl newsTypeImpl = new NewsTypeImpl();

		newsTypeImpl.setNew(newsType.isNew());
		newsTypeImpl.setPrimaryKey(newsType.getPrimaryKey());

		newsTypeImpl.setNewsTypeId(newsType.getNewsTypeId());
		newsTypeImpl.setNewsType(newsType.getNewsType());

		return newsTypeImpl;
	}

	/**
	 * Finds the news type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the news type to find
	 * @return the news type
	 * @throws com.liferay.portal.NoSuchModelException if a news type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the news type with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchNewsTypeException} if it could not be found.
	 *
	 * @param newsTypeId the primary key of the news type to find
	 * @return the news type
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchNewsTypeException if a news type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsType findByPrimaryKey(long newsTypeId)
		throws NoSuchNewsTypeException, SystemException {
		NewsType newsType = fetchByPrimaryKey(newsTypeId);

		if (newsType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + newsTypeId);
			}

			throw new NoSuchNewsTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				newsTypeId);
		}

		return newsType;
	}

	/**
	 * Finds the news type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news type to find
	 * @return the news type, or <code>null</code> if a news type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the news type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsTypeId the primary key of the news type to find
	 * @return the news type, or <code>null</code> if a news type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsType fetchByPrimaryKey(long newsTypeId)
		throws SystemException {
		NewsType newsType = (NewsType)EntityCacheUtil.getResult(NewsTypeModelImpl.ENTITY_CACHE_ENABLED,
				NewsTypeImpl.class, newsTypeId, this);

		if (newsType == null) {
			Session session = null;

			try {
				session = openSession();

				newsType = (NewsType)session.get(NewsTypeImpl.class,
						new Long(newsTypeId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (newsType != null) {
					cacheResult(newsType);
				}

				closeSession(session);
			}
		}

		return newsType;
	}

	/**
	 * Finds all the news types.
	 *
	 * @return the news types
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the news types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of news types to return
	 * @param end the upper bound of the range of news types to return (not inclusive)
	 * @return the range of news types
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsType> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the news types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of news types to return
	 * @param end the upper bound of the range of news types to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of news types
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsType> list = (List<NewsType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_NEWSTYPE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_NEWSTYPE;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<NewsType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<NewsType>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<NewsType>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the news types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (NewsType newsType : findAll()) {
			remove(newsType);
		}
	}

	/**
	 * Counts all the news types.
	 *
	 * @return the number of news types
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

				Query q = session.createQuery(_SQL_COUNT_NEWSTYPE);

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
	 * Initializes the news type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.NewsType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NewsType>> listenersList = new ArrayList<ModelListener<NewsType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NewsType>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_NEWSTYPE = "SELECT newsType FROM NewsType newsType";
	private static final String _SQL_COUNT_NEWSTYPE = "SELECT COUNT(newsType) FROM NewsType newsType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "newsType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NewsType exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(NewsTypePersistenceImpl.class);
}