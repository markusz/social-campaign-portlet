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

import de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleCredentialsException;
import de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials;
import de.tum.in.ziller.thesis.social_campaign.model.impl.GoogleCredentialsImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.GoogleCredentialsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the google credentials service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link GoogleCredentialsUtil} to access the google credentials persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see GoogleCredentialsPersistence
 * @see GoogleCredentialsUtil
 * @generated
 */
public class GoogleCredentialsPersistenceImpl extends BasePersistenceImpl<GoogleCredentials>
	implements GoogleCredentialsPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = GoogleCredentialsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			GoogleCredentialsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			GoogleCredentialsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the google credentials in the entity cache if it is enabled.
	 *
	 * @param googleCredentials the google credentials to cache
	 */
	public void cacheResult(GoogleCredentials googleCredentials) {
		EntityCacheUtil.putResult(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			GoogleCredentialsImpl.class, googleCredentials.getPrimaryKey(),
			googleCredentials);
	}

	/**
	 * Caches the google credentialses in the entity cache if it is enabled.
	 *
	 * @param googleCredentialses the google credentialses to cache
	 */
	public void cacheResult(List<GoogleCredentials> googleCredentialses) {
		for (GoogleCredentials googleCredentials : googleCredentialses) {
			if (EntityCacheUtil.getResult(
						GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
						GoogleCredentialsImpl.class,
						googleCredentials.getPrimaryKey(), this) == null) {
				cacheResult(googleCredentials);
			}
		}
	}

	/**
	 * Clears the cache for all google credentialses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(GoogleCredentialsImpl.class.getName());
		EntityCacheUtil.clearCache(GoogleCredentialsImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the google credentials.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(GoogleCredentials googleCredentials) {
		EntityCacheUtil.removeResult(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			GoogleCredentialsImpl.class, googleCredentials.getPrimaryKey());
	}

	/**
	 * Creates a new google credentials with the primary key. Does not add the google credentials to the database.
	 *
	 * @param userId the primary key for the new google credentials
	 * @return the new google credentials
	 */
	public GoogleCredentials create(long userId) {
		GoogleCredentials googleCredentials = new GoogleCredentialsImpl();

		googleCredentials.setNew(true);
		googleCredentials.setPrimaryKey(userId);

		return googleCredentials;
	}

	/**
	 * Removes the google credentials with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the google credentials to remove
	 * @return the google credentials that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a google credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleCredentials remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the google credentials with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the google credentials to remove
	 * @return the google credentials that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleCredentialsException if a google credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleCredentials remove(long userId)
		throws NoSuchGoogleCredentialsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GoogleCredentials googleCredentials = (GoogleCredentials)session.get(GoogleCredentialsImpl.class,
					new Long(userId));

			if (googleCredentials == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userId);
				}

				throw new NoSuchGoogleCredentialsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userId);
			}

			return remove(googleCredentials);
		}
		catch (NoSuchGoogleCredentialsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected GoogleCredentials removeImpl(GoogleCredentials googleCredentials)
		throws SystemException {
		googleCredentials = toUnwrappedModel(googleCredentials);

		Session session = null;

		try {
			session = openSession();

			if (googleCredentials.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(GoogleCredentialsImpl.class,
						googleCredentials.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(googleCredentials);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			GoogleCredentialsImpl.class, googleCredentials.getPrimaryKey());

		return googleCredentials;
	}

	public GoogleCredentials updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials googleCredentials,
		boolean merge) throws SystemException {
		googleCredentials = toUnwrappedModel(googleCredentials);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, googleCredentials, merge);

			googleCredentials.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			GoogleCredentialsImpl.class, googleCredentials.getPrimaryKey(),
			googleCredentials);

		return googleCredentials;
	}

	protected GoogleCredentials toUnwrappedModel(
		GoogleCredentials googleCredentials) {
		if (googleCredentials instanceof GoogleCredentialsImpl) {
			return googleCredentials;
		}

		GoogleCredentialsImpl googleCredentialsImpl = new GoogleCredentialsImpl();

		googleCredentialsImpl.setNew(googleCredentials.isNew());
		googleCredentialsImpl.setPrimaryKey(googleCredentials.getPrimaryKey());

		googleCredentialsImpl.setUserId(googleCredentials.getUserId());
		googleCredentialsImpl.setGoogleAccountId(googleCredentials.getGoogleAccountId());
		googleCredentialsImpl.setRefreshToken(googleCredentials.getRefreshToken());
		googleCredentialsImpl.setAccessToken(googleCredentials.getAccessToken());
		googleCredentialsImpl.setTokenExpirationDate(googleCredentials.getTokenExpirationDate());

		return googleCredentialsImpl;
	}

	/**
	 * Finds the google credentials with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the google credentials to find
	 * @return the google credentials
	 * @throws com.liferay.portal.NoSuchModelException if a google credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleCredentials findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the google credentials with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleCredentialsException} if it could not be found.
	 *
	 * @param userId the primary key of the google credentials to find
	 * @return the google credentials
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleCredentialsException if a google credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleCredentials findByPrimaryKey(long userId)
		throws NoSuchGoogleCredentialsException, SystemException {
		GoogleCredentials googleCredentials = fetchByPrimaryKey(userId);

		if (googleCredentials == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userId);
			}

			throw new NoSuchGoogleCredentialsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userId);
		}

		return googleCredentials;
	}

	/**
	 * Finds the google credentials with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the google credentials to find
	 * @return the google credentials, or <code>null</code> if a google credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleCredentials fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the google credentials with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the google credentials to find
	 * @return the google credentials, or <code>null</code> if a google credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleCredentials fetchByPrimaryKey(long userId)
		throws SystemException {
		GoogleCredentials googleCredentials = (GoogleCredentials)EntityCacheUtil.getResult(GoogleCredentialsModelImpl.ENTITY_CACHE_ENABLED,
				GoogleCredentialsImpl.class, userId, this);

		if (googleCredentials == null) {
			Session session = null;

			try {
				session = openSession();

				googleCredentials = (GoogleCredentials)session.get(GoogleCredentialsImpl.class,
						new Long(userId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (googleCredentials != null) {
					cacheResult(googleCredentials);
				}

				closeSession(session);
			}
		}

		return googleCredentials;
	}

	/**
	 * Finds all the google credentialses.
	 *
	 * @return the google credentialses
	 * @throws SystemException if a system exception occurred
	 */
	public List<GoogleCredentials> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the google credentialses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of google credentialses to return
	 * @param end the upper bound of the range of google credentialses to return (not inclusive)
	 * @return the range of google credentialses
	 * @throws SystemException if a system exception occurred
	 */
	public List<GoogleCredentials> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the google credentialses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of google credentialses to return
	 * @param end the upper bound of the range of google credentialses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of google credentialses
	 * @throws SystemException if a system exception occurred
	 */
	public List<GoogleCredentials> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<GoogleCredentials> list = (List<GoogleCredentials>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_GOOGLECREDENTIALS);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_GOOGLECREDENTIALS;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<GoogleCredentials>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<GoogleCredentials>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<GoogleCredentials>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the google credentialses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (GoogleCredentials googleCredentials : findAll()) {
			remove(googleCredentials);
		}
	}

	/**
	 * Counts all the google credentialses.
	 *
	 * @return the number of google credentialses
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

				Query q = session.createQuery(_SQL_COUNT_GOOGLECREDENTIALS);

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
	 * Initializes the google credentials persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.GoogleCredentials")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GoogleCredentials>> listenersList = new ArrayList<ModelListener<GoogleCredentials>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GoogleCredentials>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_GOOGLECREDENTIALS = "SELECT googleCredentials FROM GoogleCredentials googleCredentials";
	private static final String _SQL_COUNT_GOOGLECREDENTIALS = "SELECT COUNT(googleCredentials) FROM GoogleCredentials googleCredentials";
	private static final String _ORDER_BY_ENTITY_ALIAS = "googleCredentials.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GoogleCredentials exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(GoogleCredentialsPersistenceImpl.class);
}