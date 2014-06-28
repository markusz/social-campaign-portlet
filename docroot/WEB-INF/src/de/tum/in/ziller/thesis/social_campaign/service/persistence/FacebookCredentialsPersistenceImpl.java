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

import de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookCredentialsException;
import de.tum.in.ziller.thesis.social_campaign.model.FacebookCredentials;
import de.tum.in.ziller.thesis.social_campaign.model.impl.FacebookCredentialsImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.FacebookCredentialsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the facebook credentials service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link FacebookCredentialsUtil} to access the facebook credentials persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see FacebookCredentialsPersistence
 * @see FacebookCredentialsUtil
 * @generated
 */
public class FacebookCredentialsPersistenceImpl extends BasePersistenceImpl<FacebookCredentials>
	implements FacebookCredentialsPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FacebookCredentialsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			FacebookCredentialsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			FacebookCredentialsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the facebook credentials in the entity cache if it is enabled.
	 *
	 * @param facebookCredentials the facebook credentials to cache
	 */
	public void cacheResult(FacebookCredentials facebookCredentials) {
		EntityCacheUtil.putResult(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			FacebookCredentialsImpl.class, facebookCredentials.getPrimaryKey(),
			facebookCredentials);
	}

	/**
	 * Caches the facebook credentialses in the entity cache if it is enabled.
	 *
	 * @param facebookCredentialses the facebook credentialses to cache
	 */
	public void cacheResult(List<FacebookCredentials> facebookCredentialses) {
		for (FacebookCredentials facebookCredentials : facebookCredentialses) {
			if (EntityCacheUtil.getResult(
						FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
						FacebookCredentialsImpl.class,
						facebookCredentials.getPrimaryKey(), this) == null) {
				cacheResult(facebookCredentials);
			}
		}
	}

	/**
	 * Clears the cache for all facebook credentialses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(FacebookCredentialsImpl.class.getName());
		EntityCacheUtil.clearCache(FacebookCredentialsImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the facebook credentials.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(FacebookCredentials facebookCredentials) {
		EntityCacheUtil.removeResult(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			FacebookCredentialsImpl.class, facebookCredentials.getPrimaryKey());
	}

	/**
	 * Creates a new facebook credentials with the primary key. Does not add the facebook credentials to the database.
	 *
	 * @param userId the primary key for the new facebook credentials
	 * @return the new facebook credentials
	 */
	public FacebookCredentials create(long userId) {
		FacebookCredentials facebookCredentials = new FacebookCredentialsImpl();

		facebookCredentials.setNew(true);
		facebookCredentials.setPrimaryKey(userId);

		return facebookCredentials;
	}

	/**
	 * Removes the facebook credentials with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the facebook credentials to remove
	 * @return the facebook credentials that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a facebook credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookCredentials remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the facebook credentials with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the facebook credentials to remove
	 * @return the facebook credentials that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookCredentialsException if a facebook credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookCredentials remove(long userId)
		throws NoSuchFacebookCredentialsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FacebookCredentials facebookCredentials = (FacebookCredentials)session.get(FacebookCredentialsImpl.class,
					new Long(userId));

			if (facebookCredentials == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userId);
				}

				throw new NoSuchFacebookCredentialsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userId);
			}

			return remove(facebookCredentials);
		}
		catch (NoSuchFacebookCredentialsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FacebookCredentials removeImpl(
		FacebookCredentials facebookCredentials) throws SystemException {
		facebookCredentials = toUnwrappedModel(facebookCredentials);

		Session session = null;

		try {
			session = openSession();

			if (facebookCredentials.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FacebookCredentialsImpl.class,
						facebookCredentials.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(facebookCredentials);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			FacebookCredentialsImpl.class, facebookCredentials.getPrimaryKey());

		return facebookCredentials;
	}

	public FacebookCredentials updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.FacebookCredentials facebookCredentials,
		boolean merge) throws SystemException {
		facebookCredentials = toUnwrappedModel(facebookCredentials);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, facebookCredentials, merge);

			facebookCredentials.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
			FacebookCredentialsImpl.class, facebookCredentials.getPrimaryKey(),
			facebookCredentials);

		return facebookCredentials;
	}

	protected FacebookCredentials toUnwrappedModel(
		FacebookCredentials facebookCredentials) {
		if (facebookCredentials instanceof FacebookCredentialsImpl) {
			return facebookCredentials;
		}

		FacebookCredentialsImpl facebookCredentialsImpl = new FacebookCredentialsImpl();

		facebookCredentialsImpl.setNew(facebookCredentials.isNew());
		facebookCredentialsImpl.setPrimaryKey(facebookCredentials.getPrimaryKey());

		facebookCredentialsImpl.setUserId(facebookCredentials.getUserId());
		facebookCredentialsImpl.setAccessToken(facebookCredentials.getAccessToken());
		facebookCredentialsImpl.setTokenExpirationDate(facebookCredentials.getTokenExpirationDate());

		return facebookCredentialsImpl;
	}

	/**
	 * Finds the facebook credentials with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the facebook credentials to find
	 * @return the facebook credentials
	 * @throws com.liferay.portal.NoSuchModelException if a facebook credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookCredentials findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the facebook credentials with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookCredentialsException} if it could not be found.
	 *
	 * @param userId the primary key of the facebook credentials to find
	 * @return the facebook credentials
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookCredentialsException if a facebook credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookCredentials findByPrimaryKey(long userId)
		throws NoSuchFacebookCredentialsException, SystemException {
		FacebookCredentials facebookCredentials = fetchByPrimaryKey(userId);

		if (facebookCredentials == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userId);
			}

			throw new NoSuchFacebookCredentialsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userId);
		}

		return facebookCredentials;
	}

	/**
	 * Finds the facebook credentials with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the facebook credentials to find
	 * @return the facebook credentials, or <code>null</code> if a facebook credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookCredentials fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the facebook credentials with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the facebook credentials to find
	 * @return the facebook credentials, or <code>null</code> if a facebook credentials with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookCredentials fetchByPrimaryKey(long userId)
		throws SystemException {
		FacebookCredentials facebookCredentials = (FacebookCredentials)EntityCacheUtil.getResult(FacebookCredentialsModelImpl.ENTITY_CACHE_ENABLED,
				FacebookCredentialsImpl.class, userId, this);

		if (facebookCredentials == null) {
			Session session = null;

			try {
				session = openSession();

				facebookCredentials = (FacebookCredentials)session.get(FacebookCredentialsImpl.class,
						new Long(userId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (facebookCredentials != null) {
					cacheResult(facebookCredentials);
				}

				closeSession(session);
			}
		}

		return facebookCredentials;
	}

	/**
	 * Finds all the facebook credentialses.
	 *
	 * @return the facebook credentialses
	 * @throws SystemException if a system exception occurred
	 */
	public List<FacebookCredentials> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the facebook credentialses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of facebook credentialses to return
	 * @param end the upper bound of the range of facebook credentialses to return (not inclusive)
	 * @return the range of facebook credentialses
	 * @throws SystemException if a system exception occurred
	 */
	public List<FacebookCredentials> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the facebook credentialses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of facebook credentialses to return
	 * @param end the upper bound of the range of facebook credentialses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of facebook credentialses
	 * @throws SystemException if a system exception occurred
	 */
	public List<FacebookCredentials> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<FacebookCredentials> list = (List<FacebookCredentials>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_FACEBOOKCREDENTIALS);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_FACEBOOKCREDENTIALS;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<FacebookCredentials>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FacebookCredentials>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FacebookCredentials>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the facebook credentialses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (FacebookCredentials facebookCredentials : findAll()) {
			remove(facebookCredentials);
		}
	}

	/**
	 * Counts all the facebook credentialses.
	 *
	 * @return the number of facebook credentialses
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

				Query q = session.createQuery(_SQL_COUNT_FACEBOOKCREDENTIALS);

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
	 * Initializes the facebook credentials persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.FacebookCredentials")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FacebookCredentials>> listenersList = new ArrayList<ModelListener<FacebookCredentials>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FacebookCredentials>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_FACEBOOKCREDENTIALS = "SELECT facebookCredentials FROM FacebookCredentials facebookCredentials";
	private static final String _SQL_COUNT_FACEBOOKCREDENTIALS = "SELECT COUNT(facebookCredentials) FROM FacebookCredentials facebookCredentials";
	private static final String _ORDER_BY_ENTITY_ALIAS = "facebookCredentials.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FacebookCredentials exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(FacebookCredentialsPersistenceImpl.class);
}