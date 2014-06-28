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

import de.tum.in.ziller.thesis.social_campaign.model.Category;
import de.tum.in.ziller.thesis.social_campaign.model.CategoryModel;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the Category service. Represents a row in the &quot;zsc_Category&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.tum.in.ziller.thesis.social_campaign.model.CategoryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CategoryImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. All methods that expect a category model instance should use the {@link de.tum.in.ziller.thesis.social_campaign.model.Category} interface instead.
 * </p>
 *
 * @author Markus Ziller
 * @see CategoryImpl
 * @see de.tum.in.ziller.thesis.social_campaign.model.Category
 * @see de.tum.in.ziller.thesis.social_campaign.model.CategoryModel
 * @generated
 */
public class CategoryModelImpl extends BaseModelImpl<Category>
	implements CategoryModel {
	public static final String TABLE_NAME = "zsc_Category";
	public static final Object[][] TABLE_COLUMNS = {
			{ "categoryId", new Integer(Types.BIGINT) },
			{ "categoryName", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table zsc_Category (categoryId LONG not null primary key,categoryName VARCHAR(400) null)";
	public static final String TABLE_SQL_DROP = "drop table zsc_Category";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.Category"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.Category"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.tum.in.ziller.thesis.social_campaign.model.Category"));

	public CategoryModelImpl() {
	}

	public long getPrimaryKey() {
		return _categoryId;
	}

	public void setPrimaryKey(long pk) {
		setCategoryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_categoryId);
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public String getCategoryName() {
		if (_categoryName == null) {
			return StringPool.BLANK;
		}
		else {
			return _categoryName;
		}
	}

	public void setCategoryName(String categoryName) {
		_categoryName = categoryName;
	}

	public Category toEscapedModel() {
		if (isEscapedModel()) {
			return (Category)this;
		}
		else {
			return (Category)Proxy.newProxyInstance(Category.class.getClassLoader(),
				new Class[] { Category.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					Category.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		CategoryImpl clone = new CategoryImpl();

		clone.setCategoryId(getCategoryId());
		clone.setCategoryName(getCategoryName());

		return clone;
	}

	public int compareTo(Category category) {
		long pk = category.getPrimaryKey();

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

		Category category = null;

		try {
			category = (Category)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = category.getPrimaryKey();

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
		StringBundler sb = new StringBundler(5);

		sb.append("{categoryId=");
		sb.append(getCategoryId());
		sb.append(", categoryName=");
		sb.append(getCategoryName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("de.tum.in.ziller.thesis.social_campaign.model.Category");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>categoryId</column-name><column-value><![CDATA[");
		sb.append(getCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>categoryName</column-name><column-value><![CDATA[");
		sb.append(getCategoryName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _categoryId;
	private String _categoryName;
	private transient ExpandoBridge _expandoBridge;
}