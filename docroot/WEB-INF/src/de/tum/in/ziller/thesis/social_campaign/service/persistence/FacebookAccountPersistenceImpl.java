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

import de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookAccountException;
import de.tum.in.ziller.thesis.social_campaign.model.FacebookAccount;
import de.tum.in.ziller.thesis.social_campaign.model.impl.FacebookAccountImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.FacebookAccountModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the facebook account service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link FacebookAccountUtil} to access the facebook account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see FacebookAccountPersistence
 * @see FacebookAccountUtil
 * @generated
 */
public class FacebookAccountPersistenceImpl extends BasePersistenceImpl<FacebookAccount>
	implements FacebookAccountPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FacebookAccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
			FacebookAccountModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
			FacebookAccountModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the facebook account in the entity cache if it is enabled.
	 *
	 * @param facebookAccount the facebook account to cache
	 */
	public void cacheResult(FacebookAccount facebookAccount) {
		EntityCacheUtil.putResult(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
			FacebookAccountImpl.class, facebookAccount.getPrimaryKey(),
			facebookAccount);
	}

	/**
	 * Caches the facebook accounts in the entity cache if it is enabled.
	 *
	 * @param facebookAccounts the facebook accounts to cache
	 */
	public void cacheResult(List<FacebookAccount> facebookAccounts) {
		for (FacebookAccount facebookAccount : facebookAccounts) {
			if (EntityCacheUtil.getResult(
						FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
						FacebookAccountImpl.class,
						facebookAccount.getPrimaryKey(), this) == null) {
				cacheResult(facebookAccount);
			}
		}
	}

	/**
	 * Clears the cache for all facebook accounts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(FacebookAccountImpl.class.getName());
		EntityCacheUtil.clearCache(FacebookAccountImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the facebook account.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(FacebookAccount facebookAccount) {
		EntityCacheUtil.removeResult(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
			FacebookAccountImpl.class, facebookAccount.getPrimaryKey());
	}

	/**
	 * Creates a new facebook account with the primary key. Does not add the facebook account to the database.
	 *
	 * @param userProfileId the primary key for the new facebook account
	 * @return the new facebook account
	 */
	public FacebookAccount create(long userProfileId) {
		FacebookAccount facebookAccount = new FacebookAccountImpl();

		facebookAccount.setNew(true);
		facebookAccount.setPrimaryKey(userProfileId);

		return facebookAccount;
	}

	/**
	 * Removes the facebook account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the facebook account to remove
	 * @return the facebook account that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a facebook account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookAccount remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the facebook account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userProfileId the primary key of the facebook account to remove
	 * @return the facebook account that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookAccountException if a facebook account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookAccount remove(long userProfileId)
		throws NoSuchFacebookAccountException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FacebookAccount facebookAccount = (FacebookAccount)session.get(FacebookAccountImpl.class,
					new Long(userProfileId));

			if (facebookAccount == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
				}

				throw new NoSuchFacebookAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userProfileId);
			}

			return remove(facebookAccount);
		}
		catch (NoSuchFacebookAccountException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FacebookAccount removeImpl(FacebookAccount facebookAccount)
		throws SystemException {
		facebookAccount = toUnwrappedModel(facebookAccount);

		Session session = null;

		try {
			session = openSession();

			if (facebookAccount.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FacebookAccountImpl.class,
						facebookAccount.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(facebookAccount);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
			FacebookAccountImpl.class, facebookAccount.getPrimaryKey());

		return facebookAccount;
	}

	public FacebookAccount updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.FacebookAccount facebookAccount,
		boolean merge) throws SystemException {
		facebookAccount = toUnwrappedModel(facebookAccount);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, facebookAccount, merge);

			facebookAccount.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
			FacebookAccountImpl.class, facebookAccount.getPrimaryKey(),
			facebookAccount);

		return facebookAccount;
	}

	protected FacebookAccount toUnwrappedModel(FacebookAccount facebookAccount) {
		if (facebookAccount instanceof FacebookAccountImpl) {
			return facebookAccount;
		}

		FacebookAccountImpl facebookAccountImpl = new FacebookAccountImpl();

		facebookAccountImpl.setNew(facebookAccount.isNew());
		facebookAccountImpl.setPrimaryKey(facebookAccount.getPrimaryKey());

		facebookAccountImpl.setUserProfileId(facebookAccount.getUserProfileId());
		facebookAccountImpl.setFacebookAccountId(facebookAccount.getFacebookAccountId());

		return facebookAccountImpl;
	}

	/**
	 * Finds the facebook account with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the facebook account to find
	 * @return the facebook account
	 * @throws com.liferay.portal.NoSuchModelException if a facebook account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the facebook account with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookAccountException} if it could not be found.
	 *
	 * @param userProfileId the primary key of the facebook account to find
	 * @return the facebook account
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchFacebookAccountException if a facebook account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookAccount findByPrimaryKey(long userProfileId)
		throws NoSuchFacebookAccountException, SystemException {
		FacebookAccount facebookAccount = fetchByPrimaryKey(userProfileId);

		if (facebookAccount == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
			}

			throw new NoSuchFacebookAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userProfileId);
		}

		return facebookAccount;
	}

	/**
	 * Finds the facebook account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the facebook account to find
	 * @return the facebook account, or <code>null</code> if a facebook account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookAccount fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the facebook account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userProfileId the primary key of the facebook account to find
	 * @return the facebook account, or <code>null</code> if a facebook account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FacebookAccount fetchByPrimaryKey(long userProfileId)
		throws SystemException {
		FacebookAccount facebookAccount = (FacebookAccount)EntityCacheUtil.getResult(FacebookAccountModelImpl.ENTITY_CACHE_ENABLED,
				FacebookAccountImpl.class, userProfileId, this);

		if (facebookAccount == null) {
			Session session = null;

			try {
				session = openSession();

				facebookAccount = (FacebookAccount)session.get(FacebookAccountImpl.class,
						new Long(userProfileId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (facebookAccount != null) {
					cacheResult(facebookAccount);
				}

				closeSession(session);
			}
		}

		return facebookAccount;
	}

	/**
	 * Finds all the facebook accounts.
	 *
	 * @return the facebook accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<FacebookAccount> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the facebook accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of facebook accounts to return
	 * @param end the upper bound of the range of facebook accounts to return (not inclusive)
	 * @return the range of facebook accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<FacebookAccount> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the facebook accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of facebook accounts to return
	 * @param end the upper bound of the range of facebook accounts to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of facebook accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<FacebookAccount> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<FacebookAccount> list = (List<FacebookAccount>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_FACEBOOKACCOUNT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_FACEBOOKACCOUNT;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<FacebookAccount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FacebookAccount>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FacebookAccount>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the facebook accounts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (FacebookAccount facebookAccount : findAll()) {
			remove(facebookAccount);
		}
	}

	/**
	 * Counts all the facebook accounts.
	 *
	 * @return the number of facebook accounts
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

				Query q = session.createQuery(_SQL_COUNT_FACEBOOKACCOUNT);

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
	 * Initializes the facebook account persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.FacebookAccount")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FacebookAccount>> listenersList = new ArrayList<ModelListener<FacebookAccount>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FacebookAccount>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_FACEBOOKACCOUNT = "SELECT facebookAccount FROM FacebookAccount facebookAccount";
	private static final String _SQL_COUNT_FACEBOOKACCOUNT = "SELECT COUNT(facebookAccount) FROM FacebookAccount facebookAccount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "facebookAccount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FacebookAccount exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(FacebookAccountPersistenceImpl.class);
}