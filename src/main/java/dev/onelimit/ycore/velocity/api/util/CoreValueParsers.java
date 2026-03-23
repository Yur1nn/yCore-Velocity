package dev.onelimit.ycore.velocity.api.util;

import java.util.Locale;

public final class CoreValueParsers {
    private CoreValueParsers() {
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static <E extends Enum<E>> E parseEnum(Class<E> enumType, String value, E fallback) {
        if (value == null || value.isBlank()) {
            return fallback;
        }

        try {
            return Enum.valueOf(enumType, value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return fallback;
        }
    }
}
