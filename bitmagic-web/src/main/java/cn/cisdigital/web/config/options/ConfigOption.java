/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.cisdigital.web.config.options;


import static cn.cisdigital.web.config.options.Preconditions.checkNotNull;


/**
 * A {@code ConfigOption} describes a configuration parameter. It encapsulates the configuration
 * key, deprecated older versions of the key, and an optional default value for the configuration
 * parameter.
 *
 * <p>{@code ConfigOptions} are built via the {@link ConfigOptions} class. Once created, a config
 * option is immutable.
 *
 * @param <T> The type of value associated with the configuration option.
 */

public class ConfigOption<T> {
    public static final String EMPTY_DESCRIPTION = "";


    // ------------------------------------------------------------------------

    /**
     * 配置项的当前key
     */
    private final String key;

    /**
     * 配置项的默认值
     */
    private final T defaultValue;

    /**
     * 描述
     */
    private final String description;

    /**
     * 该配置项的值类型，支持枚举，集合
     *
     * <ul>
     *   <li>typeClass == atomic class (e.g. {@code Integer.class}) -> {@code ConfigOption<Integer>}
     *   <li>typeClass == {@code Map.class} -> {@code ConfigOption<Map<String, String>>}
     *   <li>typeClass == atomic class and isList == true for {@code ConfigOption<List<Integer>>}
     * </ul>
     */
    private final Class<?> clazz;

    /**
     * 是否列表
     */
    private final boolean isList;

    /**
     * true 不能为空
     */
    private final boolean notNull;

    // ------------------------------------------------------------------------

    Class<?> getClazz() {
        return clazz;
    }

    boolean isList() {
        return isList;
    }

    boolean notNull() {
        return notNull;
    }

    /**
     * Creates a new config option with fallback keys.
     *
     * @param key          The current key for that config option
     * @param clazz        describes type of the ConfigOption, see description of the clazz field
     * @param description  Description for that option
     * @param defaultValue The default value for this option
     * @param isList       tells if the ConfigOption describes a list option, see description of the clazz
     *                     field
     */
    ConfigOption(
            String key,
            Class<?> clazz,
            String description,
            T defaultValue,
            boolean isList, boolean notNull) {
        this.key = checkNotNull(key);
        this.description = description;
        this.defaultValue = defaultValue;
        this.clazz = checkNotNull(clazz);
        this.isList = isList;
        this.notNull = notNull;
    }

    /**
     * Gets the configuration key.
     *
     * @return The configuration key
     */
    public String key() {
        return key;
    }

    /**
     * Checks if this option has a default value.
     *
     * @return True if it has a default value, false if not.
     */
    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    /**
     * Returns the default value, or null, if there is no default value.
     *
     * @return The default value, or null.
     */
    public T defaultValue() {
        return defaultValue;
    }


    /**
     * Returns the description of this option.
     *
     * @return The option's description.
     */
    public String description() {
        return description;
    }

    // ------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && o.getClass() == ConfigOption.class) {
            ConfigOption<?> that = (ConfigOption<?>) o;
            return this.key.equals(that.key)
                    && (this.defaultValue == null
                    ? that.defaultValue == null
                    : (that.defaultValue != null
                    && this.defaultValue.equals(that.defaultValue)));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 31 * key.hashCode()
                + (defaultValue != null ? defaultValue.hashCode() : 0);
    }

    @Override
    public String toString() {
        return String.format(
                "Key: '%s' , default: %s",
                key, defaultValue);
    }
}
