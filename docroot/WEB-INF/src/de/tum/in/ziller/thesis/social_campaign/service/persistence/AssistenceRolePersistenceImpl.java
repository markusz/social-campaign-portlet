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

import de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRoleException;
import de.tum.in.ziller.thesis.social_campaign.model.AssistenceRole;
import de.tum.in.ziller.thesis.social_campaign.model.impl.AssistenceRoleImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.AssistenceRoleModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the assistence role service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link AssistenceRoleUtil} to access the assistence role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see AssistenceRolePersistence
 * @see AssistenceRoleUtil
 * @generated
 */
public class AssistenceRolePersistenceImpl extends BasePersistenceImpl<AssistenceRole>
	implements AssistenceRolePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AssistenceRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRoleModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRoleModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the assistence role in the entity cache if it is enabled.
	 *
	 * @param assistenceRole the assistence role to cache
	 */
	public void cacheResult(AssistenceRole assistenceRole) {
		EntityCacheUtil.putResult(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRoleImpl.class, assistenceRole.getPrimaryKey(),
			assistenceRole);
	}

	/**
	 * Caches the assistence roles in the entity cache if it is enabled.
	 *
	 * @param assistenceRoles the assistence roles to cache
	 */
	public void cacheResult(List<AssistenceRole> assistenceRoles) {
		for (AssistenceRole assistenceRole : assistenceRoles) {
			if (EntityCacheUtil.getResult(
						AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
						AssistenceRoleImpl.class,
						assistenceRole.getPrimaryKey(), this) == null) {
				cacheResult(assistenceRole);
			}
		}
	}

	/**
	 * Clears the cache for all assistence roles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(AssistenceRoleImpl.class.getName());
		EntityCacheUtil.clearCache(AssistenceRoleImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the assistence role.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(AssistenceRole assistenceRole) {
		EntityCacheUtil.removeResult(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRoleImpl.class, assistenceRole.getPrimaryKey());
	}

	/**
	 * Creates a new assistence role with the primary key. Does not add the assistence role to the database.
	 *
	 * @param roleId the primary key for the new assistence role
	 * @return the new assistence role
	 */
	public AssistenceRole create(long roleId) {
		AssistenceRole assistenceRole = new AssistenceRoleImpl();

		assistenceRole.setNew(true);
		assistenceRole.setPrimaryKey(roleId);

		return assistenceRole;
	}

	/**
	 * Removes the assistence role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the assistence role to remove
	 * @return the assistence role that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a assistence role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRole remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the assistence role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param roleId the primary key of the assistence role to remove
	 * @return the assistence role that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRoleException if a assistence role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRole remove(long roleId)
		throws NoSuchAssistenceRoleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssistenceRole assistenceRole = (AssistenceRole)session.get(AssistenceRoleImpl.class,
					new Long(roleId));

			if (assistenceRole == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + roleId);
				}

				throw new NoSuchAssistenceRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					roleId);
			}

			return remove(assistenceRole);
		}
		catch (NoSuchAssistenceRoleException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssistenceRole removeImpl(AssistenceRole assistenceRole)
		throws SystemException {
		assistenceRole = toUnwrappedModel(assistenceRole);

		Session session = null;

		try {
			session = openSession();

			if (assistenceRole.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssistenceRoleImpl.class,
						assistenceRole.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(assistenceRole);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRoleImpl.class, assistenceRole.getPrimaryKey());

		return assistenceRole;
	}

	public AssistenceRole updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.AssistenceRole assistenceRole,
		boolean merge) throws SystemException {
		assistenceRole = toUnwrappedModel(assistenceRole);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assistenceRole, merge);

			assistenceRole.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
			AssistenceRoleImpl.class, assistenceRole.getPrimaryKey(),
			assistenceRole);

		return assistenceRole;
	}

	protected AssistenceRole toUnwrappedModel(AssistenceRole assistenceRole) {
		if (assistenceRole instanceof AssistenceRoleImpl) {
			return assistenceRole;
		}

		AssistenceRoleImpl assistenceRoleImpl = new AssistenceRoleImpl();

		assistenceRoleImpl.setNew(assistenceRole.isNew());
		assistenceRoleImpl.setPrimaryKey(assistenceRole.getPrimaryKey());

		assistenceRoleImpl.setRoleId(assistenceRole.getRoleId());
		assistenceRoleImpl.setName(assistenceRole.getName());

		return assistenceRoleImpl;
	}

	/**
	 * Finds the assistence role with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the assistence role to find
	 * @return the assistence role
	 * @throws com.liferay.portal.NoSuchModelException if a assistence role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the assistence role with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRoleException} if it could not be found.
	 *
	 * @param roleId the primary key of the assistence role to find
	 * @return the assistence role
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchAssistenceRoleException if a assistence role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRole findByPrimaryKey(long roleId)
		throws NoSuchAssistenceRoleException, SystemException {
		AssistenceRole assistenceRole = fetchByPrimaryKey(roleId);

		if (assistenceRole == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + roleId);
			}

			throw new NoSuchAssistenceRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				roleId);
		}

		return assistenceRole;
	}

	/**
	 * Finds the assistence role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the assistence role to find
	 * @return the assistence role, or <code>null</code> if a assistence role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRole fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the assistence role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param roleId the primary key of the assistence role to find
	 * @return the assistence role, or <code>null</code> if a assistence role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AssistenceRole fetchByPrimaryKey(long roleId)
		throws SystemException {
		AssistenceRole assistenceRole = (AssistenceRole)EntityCacheUtil.getResult(AssistenceRoleModelImpl.ENTITY_CACHE_ENABLED,
				AssistenceRoleImpl.class, roleId, this);

		if (assistenceRole == null) {
			Session session = null;

			try {
				session = openSession();

				assistenceRole = (AssistenceRole)session.get(AssistenceRoleImpl.class,
						new Long(roleId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (assistenceRole != null) {
					cacheResult(assistenceRole);
				}

				closeSession(session);
			}
		}

		return assistenceRole;
	}

	/**
	 * Finds all the assistence roles.
	 *
	 * @return the assistence roles
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRole> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the assistence roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of assistence roles to return
	 * @param end the upper bound of the range of assistence roles to return (not inclusive)
	 * @return the range of assistence roles
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRole> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the assistence roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of assistence roles to return
	 * @param end the upper bound of the range of assistence roles to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of assistence roles
	 * @throws SystemException if a system exception occurred
	 */
	public List<AssistenceRole> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AssistenceRole> list = (List<AssistenceRole>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_ASSISTENCEROLE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_ASSISTENCEROLE;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AssistenceRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssistenceRole>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssistenceRole>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the assistence roles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AssistenceRole assistenceRole : findAll()) {
			remove(assistenceRole);
		}
	}

	/**
	 * Counts all the assistence roles.
	 *
	 * @return the number of assistence roles
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

				Query q = session.createQuery(_SQL_COUNT_ASSISTENCEROLE);

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
	 * Initializes the assistence role persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.AssistenceRole")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssistenceRole>> listenersList = new ArrayList<ModelListener<AssistenceRole>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssistenceRole>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_ASSISTENCEROLE = "SELECT assistenceRole FROM AssistenceRole assistenceRole";
	private static final String _SQL_COUNT_ASSISTENCEROLE = "SELECT COUNT(assistenceRole) FROM AssistenceRole assistenceRole";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assistenceRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssistenceRole exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AssistenceRolePersistenceImpl.class);
}