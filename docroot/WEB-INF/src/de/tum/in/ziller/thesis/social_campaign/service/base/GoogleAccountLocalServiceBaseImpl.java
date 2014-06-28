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

package de.tum.in.ziller.thesis.social_campaign.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import de.tum.in.ziller.thesis.social_campaign.model.GoogleAccount;
import de.tum.in.ziller.thesis.social_campaign.service.AssistenceRequestLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.AssistenceRoleLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.CategoryLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.DonationLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.EventAssistenceLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.EventAttendenceLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.EventCreatorLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.EventLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.FacebookAccountLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.FacebookCredentialsLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.GeneralSettingsLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.GoogleAccountLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.GoogleCredentialsLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.NewsLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.NewsTypeLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.SocialSettingsLocalService;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.AssistenceRequestPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.AssistenceRolePersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.CategoryPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.DonationPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAssistencePersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAttendencePersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventCreatorPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventFinder;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.FacebookAccountPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.FacebookCredentialsPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.GeneralSettingsPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.GoogleAccountPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.GoogleCredentialsPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.NewsPersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.NewsTypePersistence;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.SocialSettingsPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the google account local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link de.tum.in.ziller.thesis.social_campaign.service.impl.GoogleAccountLocalServiceImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link de.tum.in.ziller.thesis.social_campaign.service.GoogleAccountLocalServiceUtil} to access the google account local service.
 * </p>
 *
 * @author Markus Ziller
 * @see de.tum.in.ziller.thesis.social_campaign.service.impl.GoogleAccountLocalServiceImpl
 * @see de.tum.in.ziller.thesis.social_campaign.service.GoogleAccountLocalServiceUtil
 * @generated
 */
public abstract class GoogleAccountLocalServiceBaseImpl
	implements GoogleAccountLocalService {
	/**
	 * Adds the google account to the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleAccount the google account to add
	 * @return the google account that was added
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount addGoogleAccount(GoogleAccount googleAccount)
		throws SystemException {
		googleAccount.setNew(true);

		return googleAccountPersistence.update(googleAccount, false);
	}

	/**
	 * Creates a new google account with the primary key. Does not add the google account to the database.
	 *
	 * @param userProfileId the primary key for the new google account
	 * @return the new google account
	 */
	public GoogleAccount createGoogleAccount(long userProfileId) {
		return googleAccountPersistence.create(userProfileId);
	}

	/**
	 * Deletes the google account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userProfileId the primary key of the google account to delete
	 * @throws PortalException if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteGoogleAccount(long userProfileId)
		throws PortalException, SystemException {
		googleAccountPersistence.remove(userProfileId);
	}

	/**
	 * Deletes the google account from the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleAccount the google account to delete
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteGoogleAccount(GoogleAccount googleAccount)
		throws SystemException {
		googleAccountPersistence.remove(googleAccount);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return googleAccountPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @param start the lower bound of the range of model instances to return
	 * @param end the upper bound of the range of model instances to return (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return googleAccountPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @param start the lower bound of the range of model instances to return
	 * @param end the upper bound of the range of model instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return googleAccountPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Counts the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return googleAccountPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Gets the google account with the primary key.
	 *
	 * @param userProfileId the primary key of the google account to get
	 * @return the google account
	 * @throws PortalException if a google account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount getGoogleAccount(long userProfileId)
		throws PortalException, SystemException {
		return googleAccountPersistence.findByPrimaryKey(userProfileId);
	}

	/**
	 * Gets a range of all the google accounts.
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
	public List<GoogleAccount> getGoogleAccounts(int start, int end)
		throws SystemException {
		return googleAccountPersistence.findAll(start, end);
	}

	/**
	 * Gets the number of google accounts.
	 *
	 * @return the number of google accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int getGoogleAccountsCount() throws SystemException {
		return googleAccountPersistence.countAll();
	}

	/**
	 * Updates the google account in the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleAccount the google account to update
	 * @return the google account that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount updateGoogleAccount(GoogleAccount googleAccount)
		throws SystemException {
		googleAccount.setNew(false);

		return googleAccountPersistence.update(googleAccount, true);
	}

	/**
	 * Updates the google account in the database. Also notifies the appropriate model listeners.
	 *
	 * @param googleAccount the google account to update
	 * @param merge whether to merge the google account with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the google account that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public GoogleAccount updateGoogleAccount(GoogleAccount googleAccount,
		boolean merge) throws SystemException {
		googleAccount.setNew(false);

		return googleAccountPersistence.update(googleAccount, merge);
	}

	/**
	 * Gets the Event local service.
	 *
	 * @return the Event local service
	 */
	public EventLocalService getEventLocalService() {
		return eventLocalService;
	}

	/**
	 * Sets the Event local service.
	 *
	 * @param eventLocalService the Event local service
	 */
	public void setEventLocalService(EventLocalService eventLocalService) {
		this.eventLocalService = eventLocalService;
	}

	/**
	 * Gets the Event persistence.
	 *
	 * @return the Event persistence
	 */
	public EventPersistence getEventPersistence() {
		return eventPersistence;
	}

	/**
	 * Sets the Event persistence.
	 *
	 * @param eventPersistence the Event persistence
	 */
	public void setEventPersistence(EventPersistence eventPersistence) {
		this.eventPersistence = eventPersistence;
	}

	/**
	 * Gets the Event finder.
	 *
	 * @return the Event finder
	 */
	public EventFinder getEventFinder() {
		return eventFinder;
	}

	/**
	 * Sets the Event finder.
	 *
	 * @param eventFinder the Event finder
	 */
	public void setEventFinder(EventFinder eventFinder) {
		this.eventFinder = eventFinder;
	}

	/**
	 * Gets the assistence request local service.
	 *
	 * @return the assistence request local service
	 */
	public AssistenceRequestLocalService getAssistenceRequestLocalService() {
		return assistenceRequestLocalService;
	}

	/**
	 * Sets the assistence request local service.
	 *
	 * @param assistenceRequestLocalService the assistence request local service
	 */
	public void setAssistenceRequestLocalService(
		AssistenceRequestLocalService assistenceRequestLocalService) {
		this.assistenceRequestLocalService = assistenceRequestLocalService;
	}

	/**
	 * Gets the assistence request persistence.
	 *
	 * @return the assistence request persistence
	 */
	public AssistenceRequestPersistence getAssistenceRequestPersistence() {
		return assistenceRequestPersistence;
	}

	/**
	 * Sets the assistence request persistence.
	 *
	 * @param assistenceRequestPersistence the assistence request persistence
	 */
	public void setAssistenceRequestPersistence(
		AssistenceRequestPersistence assistenceRequestPersistence) {
		this.assistenceRequestPersistence = assistenceRequestPersistence;
	}

	/**
	 * Gets the event assistence local service.
	 *
	 * @return the event assistence local service
	 */
	public EventAssistenceLocalService getEventAssistenceLocalService() {
		return eventAssistenceLocalService;
	}

	/**
	 * Sets the event assistence local service.
	 *
	 * @param eventAssistenceLocalService the event assistence local service
	 */
	public void setEventAssistenceLocalService(
		EventAssistenceLocalService eventAssistenceLocalService) {
		this.eventAssistenceLocalService = eventAssistenceLocalService;
	}

	/**
	 * Gets the event assistence persistence.
	 *
	 * @return the event assistence persistence
	 */
	public EventAssistencePersistence getEventAssistencePersistence() {
		return eventAssistencePersistence;
	}

	/**
	 * Sets the event assistence persistence.
	 *
	 * @param eventAssistencePersistence the event assistence persistence
	 */
	public void setEventAssistencePersistence(
		EventAssistencePersistence eventAssistencePersistence) {
		this.eventAssistencePersistence = eventAssistencePersistence;
	}

	/**
	 * Gets the assistence role local service.
	 *
	 * @return the assistence role local service
	 */
	public AssistenceRoleLocalService getAssistenceRoleLocalService() {
		return assistenceRoleLocalService;
	}

	/**
	 * Sets the assistence role local service.
	 *
	 * @param assistenceRoleLocalService the assistence role local service
	 */
	public void setAssistenceRoleLocalService(
		AssistenceRoleLocalService assistenceRoleLocalService) {
		this.assistenceRoleLocalService = assistenceRoleLocalService;
	}

	/**
	 * Gets the assistence role persistence.
	 *
	 * @return the assistence role persistence
	 */
	public AssistenceRolePersistence getAssistenceRolePersistence() {
		return assistenceRolePersistence;
	}

	/**
	 * Sets the assistence role persistence.
	 *
	 * @param assistenceRolePersistence the assistence role persistence
	 */
	public void setAssistenceRolePersistence(
		AssistenceRolePersistence assistenceRolePersistence) {
		this.assistenceRolePersistence = assistenceRolePersistence;
	}

	/**
	 * Gets the category local service.
	 *
	 * @return the category local service
	 */
	public CategoryLocalService getCategoryLocalService() {
		return categoryLocalService;
	}

	/**
	 * Sets the category local service.
	 *
	 * @param categoryLocalService the category local service
	 */
	public void setCategoryLocalService(
		CategoryLocalService categoryLocalService) {
		this.categoryLocalService = categoryLocalService;
	}

	/**
	 * Gets the category persistence.
	 *
	 * @return the category persistence
	 */
	public CategoryPersistence getCategoryPersistence() {
		return categoryPersistence;
	}

	/**
	 * Sets the category persistence.
	 *
	 * @param categoryPersistence the category persistence
	 */
	public void setCategoryPersistence(CategoryPersistence categoryPersistence) {
		this.categoryPersistence = categoryPersistence;
	}

	/**
	 * Gets the Event Creator local service.
	 *
	 * @return the Event Creator local service
	 */
	public EventCreatorLocalService getEventCreatorLocalService() {
		return eventCreatorLocalService;
	}

	/**
	 * Sets the Event Creator local service.
	 *
	 * @param eventCreatorLocalService the Event Creator local service
	 */
	public void setEventCreatorLocalService(
		EventCreatorLocalService eventCreatorLocalService) {
		this.eventCreatorLocalService = eventCreatorLocalService;
	}

	/**
	 * Gets the Event Creator persistence.
	 *
	 * @return the Event Creator persistence
	 */
	public EventCreatorPersistence getEventCreatorPersistence() {
		return eventCreatorPersistence;
	}

	/**
	 * Sets the Event Creator persistence.
	 *
	 * @param eventCreatorPersistence the Event Creator persistence
	 */
	public void setEventCreatorPersistence(
		EventCreatorPersistence eventCreatorPersistence) {
		this.eventCreatorPersistence = eventCreatorPersistence;
	}

	/**
	 * Gets the Event Attendence local service.
	 *
	 * @return the Event Attendence local service
	 */
	public EventAttendenceLocalService getEventAttendenceLocalService() {
		return eventAttendenceLocalService;
	}

	/**
	 * Sets the Event Attendence local service.
	 *
	 * @param eventAttendenceLocalService the Event Attendence local service
	 */
	public void setEventAttendenceLocalService(
		EventAttendenceLocalService eventAttendenceLocalService) {
		this.eventAttendenceLocalService = eventAttendenceLocalService;
	}

	/**
	 * Gets the Event Attendence persistence.
	 *
	 * @return the Event Attendence persistence
	 */
	public EventAttendencePersistence getEventAttendencePersistence() {
		return eventAttendencePersistence;
	}

	/**
	 * Sets the Event Attendence persistence.
	 *
	 * @param eventAttendencePersistence the Event Attendence persistence
	 */
	public void setEventAttendencePersistence(
		EventAttendencePersistence eventAttendencePersistence) {
		this.eventAttendencePersistence = eventAttendencePersistence;
	}

	/**
	 * Gets the facebook account local service.
	 *
	 * @return the facebook account local service
	 */
	public FacebookAccountLocalService getFacebookAccountLocalService() {
		return facebookAccountLocalService;
	}

	/**
	 * Sets the facebook account local service.
	 *
	 * @param facebookAccountLocalService the facebook account local service
	 */
	public void setFacebookAccountLocalService(
		FacebookAccountLocalService facebookAccountLocalService) {
		this.facebookAccountLocalService = facebookAccountLocalService;
	}

	/**
	 * Gets the facebook account persistence.
	 *
	 * @return the facebook account persistence
	 */
	public FacebookAccountPersistence getFacebookAccountPersistence() {
		return facebookAccountPersistence;
	}

	/**
	 * Sets the facebook account persistence.
	 *
	 * @param facebookAccountPersistence the facebook account persistence
	 */
	public void setFacebookAccountPersistence(
		FacebookAccountPersistence facebookAccountPersistence) {
		this.facebookAccountPersistence = facebookAccountPersistence;
	}

	/**
	 * Gets the google account local service.
	 *
	 * @return the google account local service
	 */
	public GoogleAccountLocalService getGoogleAccountLocalService() {
		return googleAccountLocalService;
	}

	/**
	 * Sets the google account local service.
	 *
	 * @param googleAccountLocalService the google account local service
	 */
	public void setGoogleAccountLocalService(
		GoogleAccountLocalService googleAccountLocalService) {
		this.googleAccountLocalService = googleAccountLocalService;
	}

	/**
	 * Gets the google account persistence.
	 *
	 * @return the google account persistence
	 */
	public GoogleAccountPersistence getGoogleAccountPersistence() {
		return googleAccountPersistence;
	}

	/**
	 * Sets the google account persistence.
	 *
	 * @param googleAccountPersistence the google account persistence
	 */
	public void setGoogleAccountPersistence(
		GoogleAccountPersistence googleAccountPersistence) {
		this.googleAccountPersistence = googleAccountPersistence;
	}

	/**
	 * Gets the facebook credentials local service.
	 *
	 * @return the facebook credentials local service
	 */
	public FacebookCredentialsLocalService getFacebookCredentialsLocalService() {
		return facebookCredentialsLocalService;
	}

	/**
	 * Sets the facebook credentials local service.
	 *
	 * @param facebookCredentialsLocalService the facebook credentials local service
	 */
	public void setFacebookCredentialsLocalService(
		FacebookCredentialsLocalService facebookCredentialsLocalService) {
		this.facebookCredentialsLocalService = facebookCredentialsLocalService;
	}

	/**
	 * Gets the facebook credentials persistence.
	 *
	 * @return the facebook credentials persistence
	 */
	public FacebookCredentialsPersistence getFacebookCredentialsPersistence() {
		return facebookCredentialsPersistence;
	}

	/**
	 * Sets the facebook credentials persistence.
	 *
	 * @param facebookCredentialsPersistence the facebook credentials persistence
	 */
	public void setFacebookCredentialsPersistence(
		FacebookCredentialsPersistence facebookCredentialsPersistence) {
		this.facebookCredentialsPersistence = facebookCredentialsPersistence;
	}

	/**
	 * Gets the google credentials local service.
	 *
	 * @return the google credentials local service
	 */
	public GoogleCredentialsLocalService getGoogleCredentialsLocalService() {
		return googleCredentialsLocalService;
	}

	/**
	 * Sets the google credentials local service.
	 *
	 * @param googleCredentialsLocalService the google credentials local service
	 */
	public void setGoogleCredentialsLocalService(
		GoogleCredentialsLocalService googleCredentialsLocalService) {
		this.googleCredentialsLocalService = googleCredentialsLocalService;
	}

	/**
	 * Gets the google credentials persistence.
	 *
	 * @return the google credentials persistence
	 */
	public GoogleCredentialsPersistence getGoogleCredentialsPersistence() {
		return googleCredentialsPersistence;
	}

	/**
	 * Sets the google credentials persistence.
	 *
	 * @param googleCredentialsPersistence the google credentials persistence
	 */
	public void setGoogleCredentialsPersistence(
		GoogleCredentialsPersistence googleCredentialsPersistence) {
		this.googleCredentialsPersistence = googleCredentialsPersistence;
	}

	/**
	 * Gets the donation local service.
	 *
	 * @return the donation local service
	 */
	public DonationLocalService getDonationLocalService() {
		return donationLocalService;
	}

	/**
	 * Sets the donation local service.
	 *
	 * @param donationLocalService the donation local service
	 */
	public void setDonationLocalService(
		DonationLocalService donationLocalService) {
		this.donationLocalService = donationLocalService;
	}

	/**
	 * Gets the donation persistence.
	 *
	 * @return the donation persistence
	 */
	public DonationPersistence getDonationPersistence() {
		return donationPersistence;
	}

	/**
	 * Sets the donation persistence.
	 *
	 * @param donationPersistence the donation persistence
	 */
	public void setDonationPersistence(DonationPersistence donationPersistence) {
		this.donationPersistence = donationPersistence;
	}

	/**
	 * Gets the news local service.
	 *
	 * @return the news local service
	 */
	public NewsLocalService getNewsLocalService() {
		return newsLocalService;
	}

	/**
	 * Sets the news local service.
	 *
	 * @param newsLocalService the news local service
	 */
	public void setNewsLocalService(NewsLocalService newsLocalService) {
		this.newsLocalService = newsLocalService;
	}

	/**
	 * Gets the news persistence.
	 *
	 * @return the news persistence
	 */
	public NewsPersistence getNewsPersistence() {
		return newsPersistence;
	}

	/**
	 * Sets the news persistence.
	 *
	 * @param newsPersistence the news persistence
	 */
	public void setNewsPersistence(NewsPersistence newsPersistence) {
		this.newsPersistence = newsPersistence;
	}

	/**
	 * Gets the news type local service.
	 *
	 * @return the news type local service
	 */
	public NewsTypeLocalService getNewsTypeLocalService() {
		return newsTypeLocalService;
	}

	/**
	 * Sets the news type local service.
	 *
	 * @param newsTypeLocalService the news type local service
	 */
	public void setNewsTypeLocalService(
		NewsTypeLocalService newsTypeLocalService) {
		this.newsTypeLocalService = newsTypeLocalService;
	}

	/**
	 * Gets the news type persistence.
	 *
	 * @return the news type persistence
	 */
	public NewsTypePersistence getNewsTypePersistence() {
		return newsTypePersistence;
	}

	/**
	 * Sets the news type persistence.
	 *
	 * @param newsTypePersistence the news type persistence
	 */
	public void setNewsTypePersistence(NewsTypePersistence newsTypePersistence) {
		this.newsTypePersistence = newsTypePersistence;
	}

	/**
	 * Gets the general settings local service.
	 *
	 * @return the general settings local service
	 */
	public GeneralSettingsLocalService getGeneralSettingsLocalService() {
		return generalSettingsLocalService;
	}

	/**
	 * Sets the general settings local service.
	 *
	 * @param generalSettingsLocalService the general settings local service
	 */
	public void setGeneralSettingsLocalService(
		GeneralSettingsLocalService generalSettingsLocalService) {
		this.generalSettingsLocalService = generalSettingsLocalService;
	}

	/**
	 * Gets the general settings persistence.
	 *
	 * @return the general settings persistence
	 */
	public GeneralSettingsPersistence getGeneralSettingsPersistence() {
		return generalSettingsPersistence;
	}

	/**
	 * Sets the general settings persistence.
	 *
	 * @param generalSettingsPersistence the general settings persistence
	 */
	public void setGeneralSettingsPersistence(
		GeneralSettingsPersistence generalSettingsPersistence) {
		this.generalSettingsPersistence = generalSettingsPersistence;
	}

	/**
	 * Gets the social settings local service.
	 *
	 * @return the social settings local service
	 */
	public SocialSettingsLocalService getSocialSettingsLocalService() {
		return socialSettingsLocalService;
	}

	/**
	 * Sets the social settings local service.
	 *
	 * @param socialSettingsLocalService the social settings local service
	 */
	public void setSocialSettingsLocalService(
		SocialSettingsLocalService socialSettingsLocalService) {
		this.socialSettingsLocalService = socialSettingsLocalService;
	}

	/**
	 * Gets the social settings persistence.
	 *
	 * @return the social settings persistence
	 */
	public SocialSettingsPersistence getSocialSettingsPersistence() {
		return socialSettingsPersistence;
	}

	/**
	 * Sets the social settings persistence.
	 *
	 * @param socialSettingsPersistence the social settings persistence
	 */
	public void setSocialSettingsPersistence(
		SocialSettingsPersistence socialSettingsPersistence) {
		this.socialSettingsPersistence = socialSettingsPersistence;
	}

	/**
	 * Gets the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Gets the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Gets the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Gets the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Gets the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Gets the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query to perform
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = googleAccountPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = EventLocalService.class)
	protected EventLocalService eventLocalService;
	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = EventFinder.class)
	protected EventFinder eventFinder;
	@BeanReference(type = AssistenceRequestLocalService.class)
	protected AssistenceRequestLocalService assistenceRequestLocalService;
	@BeanReference(type = AssistenceRequestPersistence.class)
	protected AssistenceRequestPersistence assistenceRequestPersistence;
	@BeanReference(type = EventAssistenceLocalService.class)
	protected EventAssistenceLocalService eventAssistenceLocalService;
	@BeanReference(type = EventAssistencePersistence.class)
	protected EventAssistencePersistence eventAssistencePersistence;
	@BeanReference(type = AssistenceRoleLocalService.class)
	protected AssistenceRoleLocalService assistenceRoleLocalService;
	@BeanReference(type = AssistenceRolePersistence.class)
	protected AssistenceRolePersistence assistenceRolePersistence;
	@BeanReference(type = CategoryLocalService.class)
	protected CategoryLocalService categoryLocalService;
	@BeanReference(type = CategoryPersistence.class)
	protected CategoryPersistence categoryPersistence;
	@BeanReference(type = EventCreatorLocalService.class)
	protected EventCreatorLocalService eventCreatorLocalService;
	@BeanReference(type = EventCreatorPersistence.class)
	protected EventCreatorPersistence eventCreatorPersistence;
	@BeanReference(type = EventAttendenceLocalService.class)
	protected EventAttendenceLocalService eventAttendenceLocalService;
	@BeanReference(type = EventAttendencePersistence.class)
	protected EventAttendencePersistence eventAttendencePersistence;
	@BeanReference(type = FacebookAccountLocalService.class)
	protected FacebookAccountLocalService facebookAccountLocalService;
	@BeanReference(type = FacebookAccountPersistence.class)
	protected FacebookAccountPersistence facebookAccountPersistence;
	@BeanReference(type = GoogleAccountLocalService.class)
	protected GoogleAccountLocalService googleAccountLocalService;
	@BeanReference(type = GoogleAccountPersistence.class)
	protected GoogleAccountPersistence googleAccountPersistence;
	@BeanReference(type = FacebookCredentialsLocalService.class)
	protected FacebookCredentialsLocalService facebookCredentialsLocalService;
	@BeanReference(type = FacebookCredentialsPersistence.class)
	protected FacebookCredentialsPersistence facebookCredentialsPersistence;
	@BeanReference(type = GoogleCredentialsLocalService.class)
	protected GoogleCredentialsLocalService googleCredentialsLocalService;
	@BeanReference(type = GoogleCredentialsPersistence.class)
	protected GoogleCredentialsPersistence googleCredentialsPersistence;
	@BeanReference(type = DonationLocalService.class)
	protected DonationLocalService donationLocalService;
	@BeanReference(type = DonationPersistence.class)
	protected DonationPersistence donationPersistence;
	@BeanReference(type = NewsLocalService.class)
	protected NewsLocalService newsLocalService;
	@BeanReference(type = NewsPersistence.class)
	protected NewsPersistence newsPersistence;
	@BeanReference(type = NewsTypeLocalService.class)
	protected NewsTypeLocalService newsTypeLocalService;
	@BeanReference(type = NewsTypePersistence.class)
	protected NewsTypePersistence newsTypePersistence;
	@BeanReference(type = GeneralSettingsLocalService.class)
	protected GeneralSettingsLocalService generalSettingsLocalService;
	@BeanReference(type = GeneralSettingsPersistence.class)
	protected GeneralSettingsPersistence generalSettingsPersistence;
	@BeanReference(type = SocialSettingsLocalService.class)
	protected SocialSettingsLocalService socialSettingsLocalService;
	@BeanReference(type = SocialSettingsPersistence.class)
	protected SocialSettingsPersistence socialSettingsPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}