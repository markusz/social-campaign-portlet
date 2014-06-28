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

import de.tum.in.ziller.thesis.social_campaign.NoSuchNewsException;
import de.tum.in.ziller.thesis.social_campaign.model.News;
import de.tum.in.ziller.thesis.social_campaign.model.impl.NewsImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.NewsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the news service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link NewsUtil} to access the news persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see NewsPersistence
 * @see NewsUtil
 * @generated
 */
public class NewsPersistenceImpl extends BasePersistenceImpl<News>
	implements NewsPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = NewsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the news in the entity cache if it is enabled.
	 *
	 * @param news the news to cache
	 */
	public void cacheResult(News news) {
		EntityCacheUtil.putResult(NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsImpl.class, news.getPrimaryKey(), news);
	}

	/**
	 * Caches the newses in the entity cache if it is enabled.
	 *
	 * @param newses the newses to cache
	 */
	public void cacheResult(List<News> newses) {
		for (News news : newses) {
			if (EntityCacheUtil.getResult(NewsModelImpl.ENTITY_CACHE_ENABLED,
						NewsImpl.class, news.getPrimaryKey(), this) == null) {
				cacheResult(news);
			}
		}
	}

	/**
	 * Clears the cache for all newses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(NewsImpl.class.getName());
		EntityCacheUtil.clearCache(NewsImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the news.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(News news) {
		EntityCacheUtil.removeResult(NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsImpl.class, news.getPrimaryKey());
	}

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param newsId the primary key for the new news
	 * @return the new news
	 */
	public News create(long newsId) {
		News news = new NewsImpl();

		news.setNew(true);
		news.setPrimaryKey(newsId);

		return news;
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the news to remove
	 * @return the news that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a news with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public News remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsId the primary key of the news to remove
	 * @return the news that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchNewsException if a news with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public News remove(long newsId) throws NoSuchNewsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			News news = (News)session.get(NewsImpl.class, new Long(newsId));

			if (news == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + newsId);
				}

				throw new NoSuchNewsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					newsId);
			}

			return remove(news);
		}
		catch (NoSuchNewsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected News removeImpl(News news) throws SystemException {
		news = toUnwrappedModel(news);

		Session session = null;

		try {
			session = openSession();

			if (news.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(NewsImpl.class,
						news.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(news);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsImpl.class, news.getPrimaryKey());

		return news;
	}

	public News updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.News news, boolean merge)
		throws SystemException {
		news = toUnwrappedModel(news);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, news, merge);

			news.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NewsModelImpl.ENTITY_CACHE_ENABLED,
			NewsImpl.class, news.getPrimaryKey(), news);

		return news;
	}

	protected News toUnwrappedModel(News news) {
		if (news instanceof NewsImpl) {
			return news;
		}

		NewsImpl newsImpl = new NewsImpl();

		newsImpl.setNew(news.isNew());
		newsImpl.setPrimaryKey(news.getPrimaryKey());

		newsImpl.setNewsId(news.getNewsId());
		newsImpl.setNewsTypeId(news.getNewsTypeId());
		newsImpl.setTitle(news.getTitle());
		newsImpl.setLink(news.getLink());
		newsImpl.setContent(news.getContent());
		newsImpl.setPublishDate(news.getPublishDate());
		newsImpl.setPublisherId(news.getPublisherId());

		return newsImpl;
	}

	/**
	 * Finds the news with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the news to find
	 * @return the news
	 * @throws com.liferay.portal.NoSuchModelException if a news with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public News findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the news with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchNewsException} if it could not be found.
	 *
	 * @param newsId the primary key of the news to find
	 * @return the news
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchNewsException if a news with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public News findByPrimaryKey(long newsId)
		throws NoSuchNewsException, SystemException {
		News news = fetchByPrimaryKey(newsId);

		if (news == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + newsId);
			}

			throw new NoSuchNewsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				newsId);
		}

		return news;
	}

	/**
	 * Finds the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the news to find
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public News fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsId the primary key of the news to find
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public News fetchByPrimaryKey(long newsId) throws SystemException {
		News news = (News)EntityCacheUtil.getResult(NewsModelImpl.ENTITY_CACHE_ENABLED,
				NewsImpl.class, newsId, this);

		if (news == null) {
			Session session = null;

			try {
				session = openSession();

				news = (News)session.get(NewsImpl.class, new Long(newsId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (news != null) {
					cacheResult(news);
				}

				closeSession(session);
			}
		}

		return news;
	}

	/**
	 * Finds all the newses.
	 *
	 * @return the newses
	 * @throws SystemException if a system exception occurred
	 */
	public List<News> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses to return
	 * @param end the upper bound of the range of newses to return (not inclusive)
	 * @return the range of newses
	 * @throws SystemException if a system exception occurred
	 */
	public List<News> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses to return
	 * @param end the upper bound of the range of newses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of newses
	 * @throws SystemException if a system exception occurred
	 */
	public List<News> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<News> list = (List<News>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_NEWS);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_NEWS.concat(NewsModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<News>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<News>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<News>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the newses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (News news : findAll()) {
			remove(news);
		}
	}

	/**
	 * Counts all the newses.
	 *
	 * @return the number of newses
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

				Query q = session.createQuery(_SQL_COUNT_NEWS);

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
	 * Initializes the news persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.News")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<News>> listenersList = new ArrayList<ModelListener<News>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<News>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_NEWS = "SELECT news FROM News news";
	private static final String _SQL_COUNT_NEWS = "SELECT COUNT(news) FROM News news";
	private static final String _ORDER_BY_ENTITY_ALIAS = "news.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No News exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(NewsPersistenceImpl.class);
}