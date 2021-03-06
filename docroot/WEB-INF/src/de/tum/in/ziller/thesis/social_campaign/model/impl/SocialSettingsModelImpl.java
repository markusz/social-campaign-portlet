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
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.tum.in.ziller.thesis.social_campaign.model.SocialSettings;
import de.tum.in.ziller.thesis.social_campaign.model.SocialSettingsModel;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the SocialSettings service. Represents a row in the &quot;zsc_SocialSettings&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.tum.in.ziller.thesis.social_campaign.model.SocialSettingsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialSettingsImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. All methods that expect a social settings model instance should use the {@link de.tum.in.ziller.thesis.social_campaign.model.SocialSettings} interface instead.
 * </p>
 *
 * @author Markus Ziller
 * @see SocialSettingsImpl
 * @see de.tum.in.ziller.thesis.social_campaign.model.SocialSettings
 * @see de.tum.in.ziller.thesis.social_campaign.model.SocialSettingsModel
 * @generated
 */
public class SocialSettingsModelImpl extends BaseModelImpl<SocialSettings>
	implements SocialSettingsModel {
	public static final String TABLE_NAME = "zsc_SocialSettings";
	public static final Object[][] TABLE_COLUMNS = {
			{ "userProfileId", new Integer(Types.BIGINT) },
			{ "autoPublishOnFacebook", new Integer(Types.BOOLEAN) },
			{ "autoPublishOnGoogle", new Integer(Types.BOOLEAN) },
			{ "standardCalendarId", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table zsc_SocialSettings (userProfileId LONG not null primary key,autoPublishOnFacebook BOOLEAN,autoPublishOnGoogle BOOLEAN,standardCalendarId VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table zsc_SocialSettings";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.SocialSettings"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.SocialSettings"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.tum.in.ziller.thesis.social_campaign.model.SocialSettings"));

	public SocialSettingsModelImpl() {
	}

	public long getPrimaryKey() {
		return _userProfileId;
	}

	public void setPrimaryKey(long pk) {
		setUserProfileId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userProfileId);
	}

	public long getUserProfileId() {
		return _userProfileId;
	}

	public void setUserProfileId(long userProfileId) {
		_userProfileId = userProfileId;
	}

	public boolean getAutoPublishOnFacebook() {
		return _autoPublishOnFacebook;
	}

	public boolean isAutoPublishOnFacebook() {
		return _autoPublishOnFacebook;
	}

	public void setAutoPublishOnFacebook(boolean autoPublishOnFacebook) {
		_autoPublishOnFacebook = autoPublishOnFacebook;
	}

	public boolean getAutoPublishOnGoogle() {
		return _autoPublishOnGoogle;
	}

	public boolean isAutoPublishOnGoogle() {
		return _autoPublishOnGoogle;
	}

	public void setAutoPublishOnGoogle(boolean autoPublishOnGoogle) {
		_autoPublishOnGoogle = autoPublishOnGoogle;
	}

	public String getStandardCalendarId() {
		if (_standardCalendarId == null) {
			return StringPool.BLANK;
		}
		else {
			return _standardCalendarId;
		}
	}

	public void setStandardCalendarId(String standardCalendarId) {
		_standardCalendarId = standardCalendarId;
	}

	public SocialSettings toEscapedModel() {
		if (isEscapedModel()) {
			return (SocialSettings)this;
		}
		else {
			return (SocialSettings)Proxy.newProxyInstance(SocialSettings.class.getClassLoader(),
				new Class[] { SocialSettings.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					SocialSettings.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		SocialSettingsImpl clone = new SocialSettingsImpl();

		clone.setUserProfileId(getUserProfileId());
		clone.setAutoPublishOnFacebook(getAutoPublishOnFacebook());
		clone.setAutoPublishOnGoogle(getAutoPublishOnGoogle());
		clone.setStandardCalendarId(getStandardCalendarId());

		return clone;
	}

	public int compareTo(SocialSettings socialSettings) {
		long pk = socialSettings.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SocialSettings socialSettings = null;

		try {
			socialSettings = (SocialSettings)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = socialSettings.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{userProfileId=");
		sb.append(getUserProfileId());
		sb.append(", autoPublishOnFacebook=");
		sb.append(getAutoPublishOnFacebook());
		sb.append(", autoPublishOnGoogle=");
		sb.append(getAutoPublishOnGoogle());
		sb.append(", standardCalendarId=");
		sb.append(getStandardCalendarId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"de.tum.in.ziller.thesis.social_campaign.model.SocialSettings");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userProfileId</column-name><column-value><![CDATA[");
		sb.append(getUserProfileId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>autoPublishOnFacebook</column-name><column-value><![CDATA[");
		sb.append(getAutoPublishOnFacebook());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>autoPublishOnGoogle</column-name><column-value><![CDATA[");
		sb.append(getAutoPublishOnGoogle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>standardCalendarId</column-name><column-value><![CDATA[");
		sb.append(getStandardCalendarId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userProfileId;
	private boolean _autoPublishOnFacebook;
	private boolean _autoPublishOnGoogle;
	private String _standardCalendarId;
	private transient ExpandoBridge _expandoBridge;
}