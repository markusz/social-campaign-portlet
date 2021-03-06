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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.tum.in.ziller.thesis.social_campaign.model.News;
import de.tum.in.ziller.thesis.social_campaign.model.NewsModel;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the News service. Represents a row in the &quot;zsc_News&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.tum.in.ziller.thesis.social_campaign.model.NewsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. All methods that expect a news model instance should use the {@link de.tum.in.ziller.thesis.social_campaign.model.News} interface instead.
 * </p>
 *
 * @author Markus Ziller
 * @see NewsImpl
 * @see de.tum.in.ziller.thesis.social_campaign.model.News
 * @see de.tum.in.ziller.thesis.social_campaign.model.NewsModel
 * @generated
 */
public class NewsModelImpl extends BaseModelImpl<News> implements NewsModel {
	public static final String TABLE_NAME = "zsc_News";
	public static final Object[][] TABLE_COLUMNS = {
			{ "newsId", new Integer(Types.BIGINT) },
			{ "newsTypeId", new Integer(Types.BIGINT) },
			{ "title", new Integer(Types.VARCHAR) },
			{ "link", new Integer(Types.VARCHAR) },
			{ "content", new Integer(Types.VARCHAR) },
			{ "publishDate", new Integer(Types.TIMESTAMP) },
			{ "publisherId", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table zsc_News (newsId LONG not null primary key,newsTypeId LONG,title VARCHAR(75) null,link VARCHAR(75) null,content TEXT null,publishDate DATE null,publisherId LONG)";
	public static final String TABLE_SQL_DROP = "drop table zsc_News";
	public static final String ORDER_BY_JPQL = " ORDER BY news.publishDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY zsc_News.publishDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.News"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.tum.in.ziller.thesis.social_campaign.model.News"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.tum.in.ziller.thesis.social_campaign.model.News"));

	public NewsModelImpl() {
	}

	public long getPrimaryKey() {
		return _newsId;
	}

	public void setPrimaryKey(long pk) {
		setNewsId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_newsId);
	}

	public long getNewsId() {
		return _newsId;
	}

	public void setNewsId(long newsId) {
		_newsId = newsId;
	}

	public long getNewsTypeId() {
		return _newsTypeId;
	}

	public void setNewsTypeId(long newsTypeId) {
		_newsTypeId = newsTypeId;
	}

	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getLink() {
		if (_link == null) {
			return StringPool.BLANK;
		}
		else {
			return _link;
		}
	}

	public void setLink(String link) {
		_link = link;
	}

	public String getContent() {
		if (_content == null) {
			return StringPool.BLANK;
		}
		else {
			return _content;
		}
	}

	public void setContent(String content) {
		_content = content;
	}

	public Date getPublishDate() {
		return _publishDate;
	}

	public void setPublishDate(Date publishDate) {
		_publishDate = publishDate;
	}

	public long getPublisherId() {
		return _publisherId;
	}

	public void setPublisherId(long publisherId) {
		_publisherId = publisherId;
	}

	public News toEscapedModel() {
		if (isEscapedModel()) {
			return (News)this;
		}
		else {
			return (News)Proxy.newProxyInstance(News.class.getClassLoader(),
				new Class[] { News.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					News.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		NewsImpl clone = new NewsImpl();

		clone.setNewsId(getNewsId());
		clone.setNewsTypeId(getNewsTypeId());
		clone.setTitle(getTitle());
		clone.setLink(getLink());
		clone.setContent(getContent());
		clone.setPublishDate(getPublishDate());
		clone.setPublisherId(getPublisherId());

		return clone;
	}

	public int compareTo(News news) {
		int value = 0;

		value = DateUtil.compareTo(getPublishDate(), news.getPublishDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		News news = null;

		try {
			news = (News)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = news.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{newsId=");
		sb.append(getNewsId());
		sb.append(", newsTypeId=");
		sb.append(getNewsTypeId());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", link=");
		sb.append(getLink());
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", publishDate=");
		sb.append(getPublishDate());
		sb.append(", publisherId=");
		sb.append(getPublisherId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("de.tum.in.ziller.thesis.social_campaign.model.News");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>newsId</column-name><column-value><![CDATA[");
		sb.append(getNewsId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newsTypeId</column-name><column-value><![CDATA[");
		sb.append(getNewsTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>link</column-name><column-value><![CDATA[");
		sb.append(getLink());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publishDate</column-name><column-value><![CDATA[");
		sb.append(getPublishDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publisherId</column-name><column-value><![CDATA[");
		sb.append(getPublisherId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _newsId;
	private long _newsTypeId;
	private String _title;
	private String _link;
	private String _content;
	private Date _publishDate;
	private long _publisherId;
	private transient ExpandoBridge _expandoBridge;
}