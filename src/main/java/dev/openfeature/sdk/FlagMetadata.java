package dev.openfeature.sdk;

import dev.openfeature.sdk.exceptions.GeneralError;
import dev.openfeature.sdk.exceptions.ParseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Immutable Flag Metadata representation. Implementation is backed by a {@link Map} and immutability is provided
 * through builder and accessors.
 */
public class FlagMetadata {
    private final Map<String, Object> metadata;

    private FlagMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    /**
     * Retrieve a {@link String} value for the given key. If a value is not found, {@link GeneralError} will be thrown.
     * If value exist but of another type, {@link ParseError} will be thrown.
     *
     * @param key flag metadata key to retrieve
     */
    public String getString(final String key) {
        return getValue(key, String.class);
    }

    /**
     * Retrieve an {@link Integer} value for the given key.
     * If a value is not found, {@link GeneralError} will be thrown.
     * If value exist but of another type, {@link ParseError} will be thrown.
     *
     * @param key flag metadata key to retrieve
     */
    public Integer getInteger(final String key) {
        return getValue(key, Integer.class);
    }

    /**
     * Retrieve an {@link Float} value for the given key. If a value is not found, {@link GeneralError} will be thrown.
     * If value exist but of another type, {@link ParseError} will be thrown.
     *
     * @param key flag metadata key to retrieve
     */
    public Float getFloat(final String key) {
        return getValue(key, Float.class);
    }

    /**
     * Retrieve an {@link Double} value for the given key.
     * If a value is not found, {@link GeneralError} will be thrown.
     * If value exist but of another type, {@link ParseError} will be thrown.
     *
     * @param key flag metadata key to retrieve
     */
    public Double getDouble(final String key) {
        return getValue(key, Double.class);
    }

    /**
     * Retrieve an {@link Boolean} value for the given key.
     * If a value is not found, {@link GeneralError} will be thrown.
     * If value exist but of another type, {@link ParseError} will be thrown.
     *
     * @param key flag metadata key to retrieve
     */
    public Boolean getBoolean(final String key) {
        return getValue(key, Boolean.class);
    }

    private <T> T getValue(final String key, final Class<T> type) {
        final Object o = metadata.get(key);

        if (o == null) {
            throw new GeneralError("key " + key + " does not exist in metadata");
        }

        try {
            return type.cast(o);
        } catch (ClassCastException e) {
            throw new ParseError(
                    "wrong type for key " + key
                            + ". Expected" + type.getSimpleName() + "but got " + o.getClass().getSimpleName(), e);
        }
    }


    /**
     * Obtain a builder for {@link FlagMetadata}.
     */
    public static FlagMetadataBuilder builder() {
        return new FlagMetadataBuilder();
    }

    /**
     * Immutable builder for {@link FlagMetadata}.
     */
    public static class FlagMetadataBuilder {
        private final Map<String, Object> metadata;

        private FlagMetadataBuilder() {
            metadata = new HashMap<>();
        }

        /**
         * Add String value to the metadata.
         *
         * @param key   flag metadata key to add
         * @param value flag metadata value to add
         */
        public FlagMetadataBuilder addString(final String key, final String value) {
            metadata.put(key, value);
            return this;
        }

        /**
         * Add Integer value to the metadata.
         *
         * @param key   flag metadata key to add
         * @param value flag metadata value to add
         */
        public FlagMetadataBuilder addInteger(final String key, final Integer value) {
            metadata.put(key, value);
            return this;
        }

        /**
         * Add Float value to the metadata.
         *
         * @param key   flag metadata key to add
         * @param value flag metadata value to add
         */
        public FlagMetadataBuilder addFloat(final String key, final Float value) {
            metadata.put(key, value);
            return this;
        }

        /**
         * Add Double value to the metadata.
         *
         * @param key   flag metadata key to add
         * @param value flag metadata value to add
         */
        public FlagMetadataBuilder addDouble(final String key, final Double value) {
            metadata.put(key, value);
            return this;
        }

        /**
         * Add Boolean value to the metadata.
         *
         * @param key   flag metadata key to add
         * @param value flag metadata value to add
         */
        public FlagMetadataBuilder addBoolean(final String key, final Boolean value) {
            metadata.put(key, value);
            return this;
        }

        /**
         * Retrieve {@link FlagMetadata} with provided key,value pairs.
         */
        public FlagMetadata build() {
            return new FlagMetadata(this.metadata);
        }

    }
}