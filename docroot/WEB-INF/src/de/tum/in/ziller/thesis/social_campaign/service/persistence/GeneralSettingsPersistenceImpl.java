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

import de.tum.in.ziller.thesis.social_campaign.NoSuchGeneralSettingsException;
import de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings;
import de.tum.in.ziller.thesis.social_campaign.model.impl.GeneralSettingsImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.GeneralSettingsModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the general settings service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link GeneralSettingsUtil} to access the general settings persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see GeneralSettingsPersistence
 * @see GeneralSettingsUtil
 * @generated
 */
public class GeneralSettingsPersistenceImpl extends BasePersistenceImpl<GeneralSettings>
	implements GeneralSettingsPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = GeneralSettingsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
			GeneralSettingsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
			GeneralSettingsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the general settings in the entity cache if it is enabled.
	 *
	 * @param generalSettings the general settings to cache
	 */
	public void cacheResult(GeneralSettings generalSettings) {
		EntityCacheUtil.putResult(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
			GeneralSettingsImpl.class, generalSettings.getPrimaryKey(),
			generalSettings);
	}

	/**
	 * Caches the general settingses in the entity cache if it is enabled.
	 *
	 * @param generalSettingses the general settingses to cache
	 */
	public void cacheResult(List<GeneralSettings> generalSettingses) {
		for (GeneralSettings generalSettings : generalSettingses) {
			if (EntityCacheUtil.getResult(
						GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
						GeneralSettingsImpl.class,
						generalSettings.getPrimaryKey(), this) == null) {
				cacheResult(generalSettings);
			}
		}
	}

	/**
	 * Clears the cache for all general settingses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(GeneralSettingsImpl.class.getName());
		EntityCacheUtil.clearCache(GeneralSettingsImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the general settings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(GeneralSettings generalSettings) {
		EntityCacheUtil.removeResult(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
			GeneralSettingsImpl.class, generalSettings.getPrimaryKey());
	}

	/**
	 * Creates a new general settings with the primary key. Does not add the general settings to the database.
	 *
	 * @param userProfileId the primary key for the new general settings
	 * @return the new general settings
	 */
	public GeneralSettings create(long userProfileId) {
		GeneralSettings generalSettings = new GeneralSettingsImpl();

		generalSettings.setNew(true);
		generalSettings.setPrimaryKey(userProfileId);

		return generalSettings;
	}

	/**
	 * Removes the general settings with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the general settings to remove
	 * @return the general settings that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a general settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GeneralSettings remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the general settings with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userProfileId the primary key of the general settings to remove
	 * @return the general settings that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchGeneralSettingsException if a general settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GeneralSettings remove(long userProfileId)
		throws NoSuchGeneralSettingsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GeneralSettings generalSettings = (GeneralSettings)session.get(GeneralSettingsImpl.class,
					new Long(userProfileId));

			if (generalSettings == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
				}

				throw new NoSuchGeneralSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userProfileId);
			}

			return remove(generalSettings);
		}
		catch (NoSuchGeneralSettingsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected GeneralSettings removeImpl(GeneralSettings generalSettings)
		throws SystemException {
		generalSettings = toUnwrappedModel(generalSettings);

		Session session = null;

		try {
			session = openSession();

			if (generalSettings.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(GeneralSettingsImpl.class,
						generalSettings.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(generalSettings);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
			GeneralSettingsImpl.class, generalSettings.getPrimaryKey());

		return generalSettings;
	}

	public GeneralSettings updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings generalSettings,
		boolean merge) throws SystemException {
		generalSettings = toUnwrappedModel(generalSettings);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, generalSettings, merge);

			generalSettings.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
			GeneralSettingsImpl.class, generalSettings.getPrimaryKey(),
			generalSettings);

		return generalSettings;
	}

	protected GeneralSettings toUnwrappedModel(GeneralSettings generalSettings) {
		if (generalSettings instanceof GeneralSettingsImpl) {
			return generalSettings;
		}

		GeneralSettingsImpl generalSettingsImpl = new GeneralSettingsImpl();

		generalSettingsImpl.setNew(generalSettings.isNew());
		generalSettingsImpl.setPrimaryKey(generalSettings.getPrimaryKey());

		generalSettingsImpl.setUserProfileId(generalSettings.getUserProfileId());
		generalSettingsImpl.setDisplayName(generalSettings.getDisplayName());
		generalSettingsImpl.setInofficialEventsSearchRadius(generalSettings.getInofficialEventsSearchRadius());
		generalSettingsImpl.setHomebase(generalSettings.getHomebase());
		generalSettingsImpl.setHomebaseLatitude(generalSettings.getHomebaseLatitude());
		generalSettingsImpl.setHomebaseLongitude(generalSettings.getHomebaseLongitude());

		return generalSettingsImpl;
	}

	/**
	 * Finds the general settings with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the general settings to find
	 * @return the general settings
	 * @throws com.liferay.portal.NoSuchModelException if a general settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GeneralSettings findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the general settings with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchGeneralSettingsException} if it could not be found.
	 *
	 * @param userProfileId the primary key of the general settings to find
	 * @return the general settings
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchGeneralSettingsException if a general settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GeneralSettings findByPrimaryKey(long userProfileId)
		throws NoSuchGeneralSettingsException, SystemException {
		GeneralSettings generalSettings = fetchByPrimaryKey(userProfileId);

		if (generalSettings == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userProfileId);
			}

			throw new NoSuchGeneralSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userProfileId);
		}

		return generalSettings;
	}

	/**
	 * Finds the general settings with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the general settings to find
	 * @return the general settings, or <code>null</code> if a general settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GeneralSettings fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the general settings with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userProfileId the primary key of the general settings to find
	 * @return the general settings, or <code>null</code> if a general settings with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GeneralSettings fetchByPrimaryKey(long userProfileId)
		throws SystemException {
		GeneralSettings generalSettings = (GeneralSettings)EntityCacheUtil.getResult(GeneralSettingsModelImpl.ENTITY_CACHE_ENABLED,
				GeneralSettingsImpl.class, userProfileId, this);

		if (generalSettings == null) {
			Session session = null;

			try {
				session = openSession();

				generalSettings = (GeneralSettings)session.get(GeneralSettingsImpl.class,
						new Long(userProfileId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (generalSettings != null) {
					cacheResult(generalSettings);
				}

				closeSession(session);
			}
		}

		return generalSettings;
	}

	/**
	 * Finds all the general settingses.
	 *
	 * @return the general settingses
	 * @throws SystemException if a system exception occurred
	 */
	public List<GeneralSettings> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the general settingses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of general settingses to return
	 * @param end the upper bound of the range of general settingses to return (not inclusive)
	 * @return the range of general settingses
	 * @throws SystemException if a system exception occurred
	 */
	public List<GeneralSettings> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the general settingses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of general settingses to return
	 * @param end the upper bound of the range of general settingses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of general settingses
	 * @throws SystemException if a system exception occurred
	 */
	public List<GeneralSettings> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<GeneralSettings> list = (List<GeneralSettings>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_GENERALSETTINGS);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_GENERALSETTINGS;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<GeneralSettings>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<GeneralSettings>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<GeneralSettings>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the general settingses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (GeneralSettings generalSettings : findAll()) {
			remove(generalSettings);
		}
	}

	/**
	 * Counts all the general settingses.
	 *
	 * @return the number of general settingses
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

				Query q = session.createQuery(_SQL_COUNT_GENERALSETTINGS);

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
	 * Initializes the general settings persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.GeneralSettings")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GeneralSettings>> listenersList = new ArrayList<ModelListener<GeneralSettings>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GeneralSettings>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_GENERALSETTINGS = "SELECT generalSettings FROM GeneralSettings generalSettings";
	private static final String _SQL_COUNT_GENERALSETTINGS = "SELECT COUNT(generalSettings) FROM GeneralSettings generalSettings";
	private static final String _ORDER_BY_ENTITY_ALIAS = "generalSettings.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GeneralSettings exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(GeneralSettingsPersistenceImpl.class);
}