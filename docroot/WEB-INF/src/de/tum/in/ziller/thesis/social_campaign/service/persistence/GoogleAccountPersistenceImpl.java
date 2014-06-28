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

import de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleAccountException;
import de.tum.in.ziller.thesis.social_campaign.model.GoogleAccount;
import de.tum.in.ziller.thesis.social_campaign.model.impl.GoogleAccountImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.GoogleAccountModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the google account service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link GoogleAccountUtil} to access the google account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see GoogleAccountPersistence
 * @see GoogleAccountUtil
 * @generated
 */
public class GoogleAccountPersistenceImpl extends BasePersistenceImpl<GoogleAccount>
	implements GoogleAccountPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = GoogleAccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
			GoogleAccountModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
			GoogleAccountModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the google account in the entity cache if it is enabled.
	 *
	 * @param googleAccount the google account to cache
	 */
	public void cacheResult(GoogleAccount googleAccount) {
		EntityCacheUtil.putResult(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
			GoogleAccountImpl.class, googleAccount.getPrimaryKey(),
			googleAccount);
	}

	/**
	 * Caches the google accounts in the entity cache if it is enabled.
	 *
	 * @param googleAccounts the google accounts to cache
	 */
	public void cacheResult(List<GoogleAccount> googleAccounts) {
		for (GoogleAccount googleAccount : googleAccounts) {
			if (EntityCacheUtil.getResult(
						GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
						GoogleAccountImpl.class, googleAccount.getPrimaryKey(),
						this) == null) {
				cacheResult(googleAccount);
			}
		}
	}

	/**
	 * Clears the cache for all google accounts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(GoogleAccountImpl.class.getName());
		EntityCacheUtil.clearCache(GoogleAccountImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the google account.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(GoogleAccount googleAccount) {
		EntityCacheUtil.removeResult(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
			GoogleAccountImpl.class, googleAccount.getPrimaryKey());
	}

	/**
	 * Creates a new google account with the primary key. Does not add the google account to the database.
	 *
	 * @param userProfileId the primary key for the new google account
	 * @return the new google account
	 */
	public GoogleAccount create(long userProfileId) {
		GoogleAccount googleAccount = new GoogleAccountImpl();

		googleAccount.setNew(true);
		googleAccount.setPrimaryKey(userProfileId);

		return googleAccount;
	}

	/**
	 * Removes the google account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the google account to remove
	 * @return the google account that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the google account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userProfileId the primary key of the google account to remove
	 * @return the google account that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleAccountException if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount remove(long userProfileId)
		throws NoSuchGoogleAccountException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GoogleAccount googleAccount = (GoogleAccount)session.get(GoogleAccountImpl.class,
					new Long(userProfileId));

			if (googleAccount == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
				}

				throw new NoSuchGoogleAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userProfileId);
			}

			return remove(googleAccount);
		}
		catch (NoSuchGoogleAccountException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected GoogleAccount removeImpl(GoogleAccount googleAccount)
		throws SystemException {
		googleAccount = toUnwrappedModel(googleAccount);

		Session session = null;

		try {
			session = openSession();

			if (googleAccount.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(GoogleAccountImpl.class,
						googleAccount.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(googleAccount);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
			GoogleAccountImpl.class, googleAccount.getPrimaryKey());

		return googleAccount;
	}

	public GoogleAccount updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.GoogleAccount googleAccount,
		boolean merge) throws SystemException {
		googleAccount = toUnwrappedModel(googleAccount);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, googleAccount, merge);

			googleAccount.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
			GoogleAccountImpl.class, googleAccount.getPrimaryKey(),
			googleAccount);

		return googleAccount;
	}

	protected GoogleAccount toUnwrappedModel(GoogleAccount googleAccount) {
		if (googleAccount instanceof GoogleAccountImpl) {
			return googleAccount;
		}

		GoogleAccountImpl googleAccountImpl = new GoogleAccountImpl();

		googleAccountImpl.setNew(googleAccount.isNew());
		googleAccountImpl.setPrimaryKey(googleAccount.getPrimaryKey());

		googleAccountImpl.setUserProfileId(googleAccount.getUserProfileId());
		googleAccountImpl.setGoogleAccountId(googleAccount.getGoogleAccountId());

		return googleAccountImpl;
	}

	/**
	 * Finds the google account with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the google account to find
	 * @return the google account
	 * @throws com.liferay.portal.NoSuchModelException if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the google account with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleAccountException} if it could not be found.
	 *
	 * @param userProfileId the primary key of the google account to find
	 * @return the google account
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchGoogleAccountException if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount findByPrimaryKey(long userProfileId)
		throws NoSuchGoogleAccountException, SystemException {
		GoogleAccount googleAccount = fetchByPrimaryKey(userProfileId);

		if (googleAccount == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
			}

			throw new NoSuchGoogleAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userProfileId);
		}

		return googleAccount;
	}

	/**
	 * Finds the google account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the google account to find
	 * @return the google account, or <code>null</code> if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the google account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userProfileId the primary key of the google account to find
	 * @return the google account, or <code>null</code> if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount fetchByPrimaryKey(long userProfileId)
		throws SystemException {
		GoogleAccount googleAccount = (GoogleAccount)EntityCacheUtil.getResult(GoogleAccountModelImpl.ENTITY_CACHE_ENABLED,
				GoogleAccountImpl.class, userProfileId, this);

		if (googleAccount == null) {
			Session session = null;

			try {
				session = openSession();

				googleAccount = (GoogleAccount)session.get(GoogleAccountImpl.class,
						new Long(userProfileId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (googleAccount != null) {
					cacheResult(googleAccount);
				}

				closeSession(session);
			}
		}

		return googleAccount;
	}

	/**
	 * Finds all the google accounts.
	 *
	 * @return the google accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<GoogleAccount> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the google accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of google accounts to return
	 * @param end the upper bound of the range of google accounts to return (not inclusive)
	 * @return the range of google accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<GoogleAccount> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the google accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of google accounts to return
	 * @param end the upper bound of the range of google accounts to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of google accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<GoogleAccount> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<GoogleAccount> list = (List<GoogleAccount>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_GOOGLEACCOUNT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_GOOGLEACCOUNT;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<GoogleAccount>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<GoogleAccount>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<GoogleAccount>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the google accounts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (GoogleAccount googleAccount : findAll()) {
			remove(googleAccount);
		}
	}

	/**
	 * Counts all the google accounts.
	 *
	 * @return the number of google accounts
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

				Query q = session.createQuery(_SQL_COUNT_GOOGLEACCOUNT);

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
	 * Initializes the google account persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.GoogleAccount")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GoogleAccount>> listenersList = new ArrayList<ModelListener<GoogleAccount>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GoogleAccount>)InstanceFactory.newInstance(
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
	@BeanReference(type = CategoryPersistence.class)
	protected CategoryPersistence categoryPersistence;
	@BeanReference(type = EventCreatorPersistence.class)
	protected EventCreatorPersistence eventCreatorPersistence;
	@BeanReference(type = EventAttendencePersistence.class)
	protected EventAttendencePersistence eventAttendencePersistence;
	@BeanReference(type = FacebookAccountPersistence.class)
	protected FacebookAccountPersistence facebookAccountPersistence;
	@BeanReference(type = GoogleAccountPersistence.class)
	protected GoogleAccountPersistence googleAccountPersistence;
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
	private static final String _SQL_SELECT_GOOGLEACCOUNT = "SELECT googleAccount FROM GoogleAccount googleAccount";
	private static final String _SQL_COUNT_GOOGLEACCOUNT = "SELECT COUNT(googleAccount) FROM GoogleAccount googleAccount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "googleAccount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GoogleAccount exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(GoogleAccountPersistenceImpl.class);
}