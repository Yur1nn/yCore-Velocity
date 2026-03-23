package dev.onelimit.ycore.velocity.api.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ConfigValueReader {
    private ConfigValueReader() {
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> map(Object input) {
        if (input instanceof Map<?, ?> source) {
            return (Map<String, Object>) source;
        }
        return Map.of();
    }

    public static String string(Object input, String fallback) {
        if (input == null) {
            return fallback;
        }
        String value = String.valueOf(input);
        return value.isEmpty() ? fallback : value;
    }

    public static int integer(Object input, int fallback) {
        if (input == null) {
            return fallback;
        }
        if (input instanceof Number n) {
            return n.intValue();
        }
        try {
            return Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    public static boolean bool(Object input, boolean fallback) {
        if (input == null) {
            return fallback;
        }
        if (input instanceof Boolean b) {
            return b;
        }
        return Boolean.parseBoolean(String.valueOf(input));
    }

    public static List<String> stringList(Object raw) {
        List<String> values = new ArrayList<>();
        if (!(raw instanceof List<?> list)) {
            return values;
        }

        for (Object item : list) {
            String value = string(item, "");
            if (!value.isEmpty()) {
                values.add(value);
            }
        }
        return values;
    }
}
