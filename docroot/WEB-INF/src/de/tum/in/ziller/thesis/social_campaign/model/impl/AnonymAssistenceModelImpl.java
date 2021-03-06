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
import com.liferay.portal.model.impl.BaseModelImpl;

import de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence;
import de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistenceModel;
import de.tum.in.ziller.thesis.social_campaign.service.persistence.AnonymAssistencePK;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the AnonymAssistence service. Represents a row in the &quot;zsc_AnonymAssistence&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistenceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnonymAssistenceImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. All methods that expect a anonym assistence model instance should use the {@link de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence} interface instead.
 * </p>
 *
 * @author Markus Ziller
 * @see AnonymAssistenceImpl
 * @see de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence
 * @see de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistenceModel
 * @generated
 */
public class AnonymAssistenceModelImpl extends BaseModelImpl<AnonymAssistence>
	implements AnonymAssistenceModel {
	public static final String TABLE_NAME = "zsc_AnonymAssistence";
	public static final Object[][] TABLE_COLUMNS = {
			{ "eventId", new Integer(Types.BIGINT) },
			{ "roleId", new Integer(Types.BIGINT) },
			{ "quantity", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table zsc_AnonymAssistence (eventId LONG not null,roleId LONG not null,quantity LONG,primary key (eventId, roleId))";
	public static final String TABLE_SQL_DROP = "drop table zsc_AnonymAssistence";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence"));

	public AnonymAssistenceModelImpl() {
	}

	public AnonymAssistencePK getPrimaryKey() {
		return new AnonymAssistencePK(_eventId, _roleId);
	}

	public void setPrimaryKey(AnonymAssistencePK pk) {
		setEventId(pk.eventId);
		setRoleId(pk.roleId);
	}

	public Serializable getPrimaryKeyObj() {
		return new AnonymAssistencePK(_eventId, _roleId);
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public long getQuantity() {
		return _quantity;
	}

	public void setQuantity(long quantity) {
		_quantity = quantity;
	}

	public AnonymAssistence toEscapedModel() {
		if (isEscapedModel()) {
			return (AnonymAssistence)this;
		}
		else {
			return (AnonymAssistence)Proxy.newProxyInstance(AnonymAssistence.class.getClassLoader(),
				new Class[] { AnonymAssistence.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		AnonymAssistenceImpl clone = new AnonymAssistenceImpl();

		clone.setEventId(getEventId());
		clone.setRoleId(getRoleId());
		clone.setQuantity(getQuantity());

		return clone;
	}

	public int compareTo(AnonymAssistence anonymAssistence) {
		AnonymAssistencePK pk = anonymAssistence.getPrimaryKey();

		return getPrimaryKey().compareTo(pk);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AnonymAssistence anonymAssistence = null;

		try {
			anonymAssistence = (AnonymAssistence)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		AnonymAssistencePK pk = anonymAssistence.getPrimaryKey();

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
		StringBundler sb = new StringBundler(7);

		sb.append("{eventId=");
		sb.append(getEventId());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append(", quantity=");
		sb.append(getQuantity());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append(
			"de.tum.in.ziller.thesis.social_campaign.model.AnonymAssistence");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>eventId</column-name><column-value><![CDATA[");
		sb.append(getEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quantity</column-name><column-value><![CDATA[");
		sb.append(getQuantity());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _eventId;
	private long _roleId;
	private long _quantity;
}