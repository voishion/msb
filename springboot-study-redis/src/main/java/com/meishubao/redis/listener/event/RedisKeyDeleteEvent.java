/*
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meishubao.redis.listener.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.data.redis.core.RedisKeyspaceEvent;
import org.springframework.data.redis.core.convert.MappingRedisConverter.BinaryKeyspaceIdentifier;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * {@link RedisKeyDeleteEvent} is a Redis specific {@link ApplicationEvent} published when a particular key in Redis
 * expires. It can hold the value of the delete key next to the key, but is not required to do so.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 * @since 1.7
 */
public class RedisKeyDeleteEvent<T> extends RedisKeyspaceEvent {

	/**
	 * Use {@literal UTF-8} as default charset.
	 */
	private static final Charset CHARSET = StandardCharsets.UTF_8;

	private final BinaryKeyspaceIdentifier objectId;
	private final @Nullable Object value;

	/**
	 * Creates new {@link RedisKeyDeleteEvent}.
	 *
	 * @param key the delete key.
	 */
	public RedisKeyDeleteEvent(byte[] key) {
		this(key, null);
	}

	/**
	 * Creates new {@link RedisKeyDeleteEvent}
	 *
	 * @param key the delete key.
	 * @param value the value of the delete key. Can be {@literal null}.
	 */
	public RedisKeyDeleteEvent(byte[] key, @Nullable Object value) {
		this(null, key, value);
	}

	/**
	 * Creates new {@link RedisKeyDeleteEvent}
	 *
	 * @param channel the Pub/Sub channel through which this event was received.
	 * @param key the delete key.
	 * @param value the value of the delete key. Can be {@literal null}.
	 * @since 1.8
	 */
	public RedisKeyDeleteEvent(@Nullable String channel, byte[] key, @Nullable Object value) {
		super(channel, key);

		if (BinaryKeyspaceIdentifier.isValid(key)) {
			this.objectId = BinaryKeyspaceIdentifier.of(key);
		} else {
			this.objectId = null;
		}

		this.value = value;
	}

	/**
	 * Gets the keyspace in which the expiration occured.
	 *
	 * @return {@literal null} if it could not be determined.
	 */
	public String getKeyspace() {
		return objectId != null ? new String(objectId.getKeyspace(), CHARSET) : null;
	}

	/**
	 * Get the expired objects id.
	 *
	 * @return the expired objects id.
	 */
	public byte[] getId() {
		return objectId != null ? objectId.getId() : getSource();
	}

	/**
	 * Get the expired Object
	 *
	 * @return {@literal null} if not present.
	 */
	@Nullable
	public Object getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	@Override
	public String toString() {

		byte[] id = getId();
		return "RedisKeyDeleteEvent [keyspace=" + getKeyspace() + ", id=" + (id == null ? null : new String(id)) + "]";
	}

}