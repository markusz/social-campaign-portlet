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

import de.tum.in.ziller.thesis.social_campaign.NoSuchSocialSettingsException;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.model.impl.SocialSettingsImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.SocialSettingsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the social settings service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link SocialSettingsUtil} to access the social settings persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see SocialSettingsPersistence
 * @see SocialSettingsUtil
 * @generated
 */
public class SocialSettingsPersistenceImpl extends BasePersistenceImpl<SocialSettings>
	implements SocialSettingsPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = SocialSettingsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
			SocialSettingsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
			SocialSettingsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the social settings in the entity cache if it is enabled.
	 *
	 * @param socialSettings the social settings to cache
	 */
	public void cacheResult(SocialSettings socialSettings) {
		EntityCacheUtil.putResult(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
			SocialSettingsImpl.class, socialSettings.getPrimaryKey(),
			socialSettings);
	}

	/**
	 * Caches the social settingses in the entity cache if it is enabled.
	 *
	 * @param socialSettingses the social settingses to cache
	 */
	public void cacheResult(List<SocialSettings> socialSettingses) {
		for (SocialSettings socialSettings : socialSettingses) {
			if (EntityCacheUtil.getResult(
						SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
						SocialSettingsImpl.class,
						socialSettings.getPrimaryKey(), this) == null) {
				cacheResult(socialSettings);
			}
		}
	}

	/**
	 * Clears the cache for all social settingses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(SocialSettingsImpl.class.getName());
		EntityCacheUtil.clearCache(SocialSettingsImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the social settings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(SocialSettings socialSettings) {
		EntityCacheUtil.removeResult(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
			SocialSettingsImpl.class, socialSettings.getPrimaryKey());
	}

	/**
	 * Creates a new social settings with the primary key. Does not add the social settings to the database.
	 *
	 * @param userProfileId the primary key for the new social settings
	 * @return the new social settings
	 */
	public SocialSettings create(long userProfileId) {
		SocialSettings socialSettings = new SocialSettingsImpl();

		socialSettings.setNew(true);
		socialSettings.setPrimaryKey(userProfileId);

		return socialSettings;
	}

	/**
	 * Removes the social settings with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the social settings to remove
	 * @return the social settings that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a social settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialSettings remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the social settings with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userProfileId the primary key of the social settings to remove
	 * @return the social settings that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchSocialSettingsException if a social settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialSettings remove(long userProfileId)
		throws NoSuchSocialSettingsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SocialSettings socialSettings = (SocialSettings)session.get(SocialSettingsImpl.class,
					new Long(userProfileId));

			if (socialSettings == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
				}

				throw new NoSuchSocialSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userProfileId);
			}

			return remove(socialSettings);
		}
		catch (NoSuchSocialSettingsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialSettings removeImpl(SocialSettings socialSettings)
		throws SystemException {
		socialSettings = toUnwrappedModel(socialSettings);

		Session session = null;

		try {
			session = openSession();

			if (socialSettings.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(SocialSettingsImpl.class,
						socialSettings.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(socialSettings);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
			SocialSettingsImpl.class, socialSettings.getPrimaryKey());

		return socialSettings;
	}

	public SocialSettings updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.SocialSettings socialSettings,
		boolean merge) throws SystemException {
		socialSettings = toUnwrappedModel(socialSettings);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, socialSettings, merge);

			socialSettings.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
			SocialSettingsImpl.class, socialSettings.getPrimaryKey(),
			socialSettings);

		return socialSettings;
	}

	protected SocialSettings toUnwrappedModel(SocialSettings socialSettings) {
		if (socialSettings instanceof SocialSettingsImpl) {
			return socialSettings;
		}

		SocialSettingsImpl socialSettingsImpl = new SocialSettingsImpl();

		socialSettingsImpl.setNew(socialSettings.isNew());
		socialSettingsImpl.setPrimaryKey(socialSettings.getPrimaryKey());

		socialSettingsImpl.setUserProfileId(socialSettings.getUserProfileId());
		socialSettingsImpl.setAutoPublishOnFacebook(socialSettings.isAutoPublishOnFacebook());
		socialSettingsImpl.setAutoPublishOnGoogle(socialSettings.isAutoPublishOnGoogle());
		socialSettingsImpl.setStandardCalendarId(socialSettings.getStandardCalendarId());

		return socialSettingsImpl;
	}

	/**
	 * Finds the social settings with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the social settings to find
	 * @return the social settings
	 * @throws com.liferay.portal.NoSuchModelException if a social settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialSettings findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the social settings with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchSocialSettingsException} if it could not be found.
	 *
	 * @param userProfileId the primary key of the social settings to find
	 * @return the social settings
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchSocialSettingsException if a social settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialSettings findByPrimaryKey(long userProfileId)
		throws NoSuchSocialSettingsException, SystemException {
		SocialSettings socialSettings = fetchByPrimaryKey(userProfileId);

		if (socialSettings == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
			}

			throw new NoSuchSocialSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userProfileId);
		}

		return socialSettings;
	}

	/**
	 * Finds the social settings with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the social settings to find
	 * @return the social settings, or <code>null</code> if a social settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialSettings fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the social settings with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userProfileId the primary key of the social settings to find
	 * @return the social settings, or <code>null</code> if a social settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialSettings fetchByPrimaryKey(long userProfileId)
		throws SystemException {
		SocialSettings socialSettings = (SocialSettings)EntityCacheUtil.getResult(SocialSettingsModelImpl.ENTITY_CACHE_ENABLED,
				SocialSettingsImpl.class, userProfileId, this);

		if (socialSettings == null) {
			Session session = null;

			try {
				session = openSession();

				socialSettings = (SocialSettings)session.get(SocialSettingsImpl.class,
						new Long(userProfileId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (socialSettings != null) {
					cacheResult(socialSettings);
				}

				closeSession(session);
			}
		}

		return socialSettings;
	}

	/**
	 * Finds all the social settingses.
	 *
	 * @return the social settingses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialSettings> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the social settingses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of social settingses to return
	 * @param end the upper bound of the range of social settingses to return (not inclusive)
	 * @return the range of social settingses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialSettings> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the social settingses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of social settingses to return
	 * @param end the upper bound of the range of social settingses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of social settingses
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialSettings> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SocialSettings> list = (List<SocialSettings>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_SOCIALSETTINGS);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_SOCIALSETTINGS;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SocialSettings>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SocialSettings>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<SocialSettings>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the social settingses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SocialSettings socialSettings : findAll()) {
			remove(socialSettings);
		}
	}

	/**
	 * Counts all the social settingses.
	 *
	 * @return the number of social settingses
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

				Query q = session.createQuery(_SQL_COUNT_SOCIALSETTINGS);

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
	 * Initializes the social settings persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.SocialSettings")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SocialSettings>> listenersList = new ArrayList<ModelListener<SocialSettings>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SocialSettings>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_SOCIALSETTINGS = "SELECT socialSettings FROM SocialSettings socialSettings";
	private static final String _SQL_COUNT_SOCIALSETTINGS = "SELECT COUNT(socialSettings) FROM SocialSettings socialSettings";
	private static final String _ORDER_BY_ENTITY_ALIAS = "socialSettings.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SocialSettings exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(SocialSettingsPersistenceImpl.class);
}