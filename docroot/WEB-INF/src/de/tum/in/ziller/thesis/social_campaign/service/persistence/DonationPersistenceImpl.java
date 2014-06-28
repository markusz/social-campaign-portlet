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
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException;
import de.tum.in.ziller.thesis.social_campaign.model.Donation;
import de.tum.in.ziller.thesis.social_campaign.model.impl.DonationImpl;
import de.tum.in.ziller.thesis.social_campaign.model.impl.DonationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the donation service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link DonationUtil} to access the donation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Markus Ziller
 * @see DonationPersistence
 * @see DonationUtil
 * @generated
 */
public class DonationPersistenceImpl extends BasePersistenceImpl<Donation>
	implements DonationPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = DonationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_DONATORID = new FinderPath(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBydonatorId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_DONATORID = new FinderPath(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countBydonatorId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the donation in the entity cache if it is enabled.
	 *
	 * @param donation the donation to cache
	 */
	public void cacheResult(Donation donation) {
		EntityCacheUtil.putResult(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationImpl.class, donation.getPrimaryKey(), donation);
	}

	/**
	 * Caches the donations in the entity cache if it is enabled.
	 *
	 * @param donations the donations to cache
	 */
	public void cacheResult(List<Donation> donations) {
		for (Donation donation : donations) {
			if (EntityCacheUtil.getResult(
						DonationModelImpl.ENTITY_CACHE_ENABLED,
						DonationImpl.class, donation.getPrimaryKey(), this) == null) {
				cacheResult(donation);
			}
		}
	}

	/**
	 * Clears the cache for all donations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(DonationImpl.class.getName());
		EntityCacheUtil.clearCache(DonationImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the donation.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Donation donation) {
		EntityCacheUtil.removeResult(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationImpl.class, donation.getPrimaryKey());
	}

	/**
	 * Creates a new donation with the primary key. Does not add the donation to the database.
	 *
	 * @param donationId the primary key for the new donation
	 * @return the new donation
	 */
	public Donation create(long donationId) {
		Donation donation = new DonationImpl();

		donation.setNew(true);
		donation.setPrimaryKey(donationId);

		return donation;
	}

	/**
	 * Removes the donation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the donation to remove
	 * @return the donation that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the donation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param donationId the primary key of the donation to remove
	 * @return the donation that was removed
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation remove(long donationId)
		throws NoSuchDonationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Donation donation = (Donation)session.get(DonationImpl.class,
					new Long(donationId));

			if (donation == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + donationId);
				}

				throw new NoSuchDonationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					donationId);
			}

			return remove(donation);
		}
		catch (NoSuchDonationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Donation removeImpl(Donation donation) throws SystemException {
		donation = toUnwrappedModel(donation);

		Session session = null;

		try {
			session = openSession();

			if (donation.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(DonationImpl.class,
						donation.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(donation);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationImpl.class, donation.getPrimaryKey());

		return donation;
	}

	public Donation updateImpl(
		de.tum.in.ziller.thesis.social_campaign.model.Donation donation,
		boolean merge) throws SystemException {
		donation = toUnwrappedModel(donation);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, donation, merge);

			donation.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(DonationModelImpl.ENTITY_CACHE_ENABLED,
			DonationImpl.class, donation.getPrimaryKey(), donation);

		return donation;
	}

	protected Donation toUnwrappedModel(Donation donation) {
		if (donation instanceof DonationImpl) {
			return donation;
		}

		DonationImpl donationImpl = new DonationImpl();

		donationImpl.setNew(donation.isNew());
		donationImpl.setPrimaryKey(donation.getPrimaryKey());

		donationImpl.setDonationId(donation.getDonationId());
		donationImpl.setDonationAmount(donation.getDonationAmount());
		donationImpl.setDonationDate(donation.getDonationDate());
		donationImpl.setDonatorId(donation.getDonatorId());

		return donationImpl;
	}

	/**
	 * Finds the donation with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the donation to find
	 * @return the donation
	 * @throws com.liferay.portal.NoSuchModelException if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the donation with the primary key or throws a {@link de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException} if it could not be found.
	 *
	 * @param donationId the primary key of the donation to find
	 * @return the donation
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation findByPrimaryKey(long donationId)
		throws NoSuchDonationException, SystemException {
		Donation donation = fetchByPrimaryKey(donationId);

		if (donation == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + donationId);
			}

			throw new NoSuchDonationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				donationId);
		}

		return donation;
	}

	/**
	 * Finds the donation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the donation to find
	 * @return the donation, or <code>null</code> if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the donation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param donationId the primary key of the donation to find
	 * @return the donation, or <code>null</code> if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation fetchByPrimaryKey(long donationId)
		throws SystemException {
		Donation donation = (Donation)EntityCacheUtil.getResult(DonationModelImpl.ENTITY_CACHE_ENABLED,
				DonationImpl.class, donationId, this);

		if (donation == null) {
			Session session = null;

			try {
				session = openSession();

				donation = (Donation)session.get(DonationImpl.class,
						new Long(donationId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (donation != null) {
					cacheResult(donation);
				}

				closeSession(session);
			}
		}

		return donation;
	}

	/**
	 * Finds all the donations where donatorId = &#63;.
	 *
	 * @param donatorId the donator id to search with
	 * @return the matching donations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Donation> findBydonatorId(long donatorId)
		throws SystemException {
		return findBydonatorId(donatorId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the donations where donatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param donatorId the donator id to search with
	 * @param start the lower bound of the range of donations to return
	 * @param end the upper bound of the range of donations to return (not inclusive)
	 * @return the range of matching donations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Donation> findBydonatorId(long donatorId, int start, int end)
		throws SystemException {
		return findBydonatorId(donatorId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the donations where donatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param donatorId the donator id to search with
	 * @param start the lower bound of the range of donations to return
	 * @param end the upper bound of the range of donations to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching donations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Donation> findBydonatorId(long donatorId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				donatorId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Donation> list = (List<Donation>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_DONATORID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(2);
				}

				query.append(_SQL_SELECT_DONATION_WHERE);

				query.append(_FINDER_COLUMN_DONATORID_DONATORID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(donatorId);

				list = (List<Donation>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Donation>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_DONATORID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first donation in the ordered set where donatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param donatorId the donator id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching donation
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException if a matching donation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation findBydonatorId_First(long donatorId,
		OrderByComparator orderByComparator)
		throws NoSuchDonationException, SystemException {
		List<Donation> list = findBydonatorId(donatorId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("donatorId=");
			msg.append(donatorId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDonationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last donation in the ordered set where donatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param donatorId the donator id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching donation
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException if a matching donation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation findBydonatorId_Last(long donatorId,
		OrderByComparator orderByComparator)
		throws NoSuchDonationException, SystemException {
		int count = countBydonatorId(donatorId);

		List<Donation> list = findBydonatorId(donatorId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("donatorId=");
			msg.append(donatorId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDonationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the donations before and after the current donation in the ordered set where donatorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param donationId the primary key of the current donation
	 * @param donatorId the donator id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next donation
	 * @throws de.tum.in.ziller.thesis.social_campaign.NoSuchDonationException if a donation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Donation[] findBydonatorId_PrevAndNext(long donationId,
		long donatorId, OrderByComparator orderByComparator)
		throws NoSuchDonationException, SystemException {
		Donation donation = findByPrimaryKey(donationId);

		Session session = null;

		try {
			session = openSession();

			Donation[] array = new DonationImpl[3];

			array[0] = getBydonatorId_PrevAndNext(session, donation, donatorId,
					orderByComparator, true);

			array[1] = donation;

			array[2] = getBydonatorId_PrevAndNext(session, donation, donatorId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Donation getBydonatorId_PrevAndNext(Session session,
		Donation donation, long donatorId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DONATION_WHERE);

		query.append(_FINDER_COLUMN_DONATORID_DONATORID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(donatorId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(donation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Donation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the donations.
	 *
	 * @return the donations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Donation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the donations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of donations to return
	 * @param end the upper bound of the range of donations to return (not inclusive)
	 * @return the range of donations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Donation> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the donations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of donations to return
	 * @param end the upper bound of the range of donations to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of donations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Donation> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Donation> list = (List<Donation>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_DONATION);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_DONATION;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Donation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Donation>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Donation>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the donations where donatorId = &#63; from the database.
	 *
	 * @param donatorId the donator id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydonatorId(long donatorId) throws SystemException {
		for (Donation donation : findBydonatorId(donatorId)) {
			remove(donation);
		}
	}

	/**
	 * Removes all the donations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Donation donation : findAll()) {
			remove(donation);
		}
	}

	/**
	 * Counts all the donations where donatorId = &#63;.
	 *
	 * @param donatorId the donator id to search with
	 * @return the number of matching donations
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydonatorId(long donatorId) throws SystemException {
		Object[] finderArgs = new Object[] { donatorId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DONATORID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_DONATION_WHERE);

				query.append(_FINDER_COLUMN_DONATORID_DONATORID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(donatorId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DONATORID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the donations.
	 *
	 * @return the number of donations
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

				Query q = session.createQuery(_SQL_COUNT_DONATION);

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
	 * Initializes the donation persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.tum.in.ziller.thesis.social_campaign.model.Donation")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Donation>> listenersList = new ArrayList<ModelListener<Donation>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Donation>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_DONATION = "SELECT donation FROM Donation donation";
	private static final String _SQL_SELECT_DONATION_WHERE = "SELECT donation FROM Donation donation WHERE ";
	private static final String _SQL_COUNT_DONATION = "SELECT COUNT(donation) FROM Donation donation";
	private static final String _SQL_COUNT_DONATION_WHERE = "SELECT COUNT(donation) FROM Donation donation WHERE ";
	private static final String _FINDER_COLUMN_DONATORID_DONATORID_2 = "donation.donatorId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "donation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Donation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Donation exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(DonationPersistenceImpl.class);
}