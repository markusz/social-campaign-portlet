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

package de.tum.in.ziller.thesis.social_campaign.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;

import de.tum.in.ziller.thesis.social_campaign.model.EventAttendence;
import de.tum.in.ziller.thesis.social_campaign.model.EventAttendenceModel;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.EventAttendencePK;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the EventAttendence service. Represents a row in the &quot;zsc_EventAttendence&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.tum.in.ziller.thesis.social_campaign.model.EventAttendenceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EventAttendenceImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. All methods that expect a Event Attendence model instance should use the {@link de.tum.in.ziller.thesis.social_campaign.model.EventAttendence} interface instead.
 * </p>
 *
 * @author Markus Ziller
 * @see EventAttendenceImpl
 * @see de.tum.in.ziller.thesis.social_campaign.model.EventAttendence
 * @see de.tum.in.ziller.thesis.social_campaign.model.EventAttendenceModel
 * @generated
 */
public class EventAttendenceModelImpl extends BaseModelImpl<EventAttendence>
	implements EventAttendenceModel {
	public static final String TABLE_NAME = "zsc_EventAttendence";
	public static final Object[][] TABLE_COLUMNS = {
			{ "eventId", new Integer(Types.BIGINT) },
			{ "userProfileId", new Integer(Types.BIGINT) },
			{ "correspondingGoogleEvent", new Integer(Types.VARCHAR) },
			{ "correspondingGoogleCalendarId", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table zsc_EventAttendence (eventId LONG not null,userProfileId LONG not null,correspondingGoogleEvent VARCHAR(400) null,correspondingGoogleCalendarId VARCHAR(75) null,primary key (eventId, userProfileId))";
	public static final String TABLE_SQL_DROP = "drop table zsc_EventAttendence";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.EventAttendence"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.EventAttendence"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.tum.in.ziller.thesis.social_campaign.model.EventAttendence"));

	public EventAttendenceModelImpl() {
	}

	public EventAttendencePK getPrimaryKey() {
		return new EventAttendencePK(_eventId, _userProfileId);
	}

	public void setPrimaryKey(EventAttendencePK pk) {
		setEventId(pk.eventId);
		setUserProfileId(pk.userProfileId);
	}

	public Serializable getPrimaryKeyObj() {
		return new EventAttendencePK(_eventId, _userProfileId);
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public long getUserProfileId() {
		return _userProfileId;
	}

	public void setUserProfileId(long userProfileId) {
		_userProfileId = userProfileId;
	}

	public String getCorrespondingGoogleEvent() {
		if (_correspondingGoogleEvent == null) {
			return StringPool.BLANK;
		}
		else {
			return _correspondingGoogleEvent;
		}
	}

	public void setCorrespondingGoogleEvent(String correspondingGoogleEvent) {
		_correspondingGoogleEvent = correspondingGoogleEvent;
	}

	public String getCorrespondingGoogleCalendarId() {
		if (_correspondingGoogleCalendarId == null) {
			return StringPool.BLANK;
		}
		else {
			return _correspondingGoogleCalendarId;
		}
	}

	public void setCorrespondingGoogleCalendarId(
		String correspondingGoogleCalendarId) {
		_correspondingGoogleCalendarId = correspondingGoogleCalendarId;
	}

	public EventAttendence toEscapedModel() {
		if (isEscapedModel()) {
			return (EventAttendence)this;
		}
		else {
			return (EventAttendence)Proxy.newProxyInstance(EventAttendence.class.getClassLoader(),
				new Class[] { EventAttendence.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		EventAttendenceImpl clone = new EventAttendenceImpl();

		clone.setEventId(getEventId());
		clone.setUserProfileId(getUserProfileId());
		clone.setCorrespondingGoogleEvent(getCorrespondingGoogleEvent());
		clone.setCorrespondingGoogleCalendarId(getCorrespondingGoogleCalendarId());

		return clone;
	}

	public int compareTo(EventAttendence eventAttendence) {
		EventAttendencePK pk = eventAttendence.getPrimaryKey();

		return getPrimaryKey().compareTo(pk);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		EventAttendence eventAttendence = null;

		try {
			eventAttendence = (EventAttendence)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		EventAttendencePK pk = eventAttendence.getPrimaryKey();

		if (getPrimaryKey().equals(pk)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{eventId=");
		sb.append(getEventId());
		sb.append(", userProfileId=");
		sb.append(getUserProfileId());
		sb.append(", correspondingGoogleEvent=");
		sb.append(getCorrespondingGoogleEvent());
		sb.append(", correspondingGoogleCalendarId=");
		sb.append(getCorrespondingGoogleCalendarId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"de.tum.in.ziller.thesis.social_campaign.model.EventAttendence");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>eventId</column-name><column-value><![CDATA[");
		sb.append(getEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userProfileId</column-name><column-value><![CDATA[");
		sb.append(getUserProfileId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>correspondingGoogleEvent</column-name><column-value><![CDATA[");
		sb.append(getCorrespondingGoogleEvent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>correspondingGoogleCalendarId</column-name><column-value><![CDATA[");
		sb.append(getCorrespondingGoogleCalendarId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _eventId;
	private long _userProfileId;
	private String _correspondingGoogleEvent;
	private String _correspondingGoogleCalendarId;
}