package dev.onelimit.ycore.velocity.api.util;

import java.util.Map;

public final class CorePlaceholders {
    private CorePlaceholders() {
    }

    public static String replaceNamed(String input, Map<String, ?> values) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String output = input;
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            String key = entry.getKey();
            if (key == null || key.isBlank()) {
                continue;
            }

            String value = entry.getValue() == null ? "" : String.valueOf(entry.getValue());
            output = output.replace("%" + key + "%", value);
            output = output.replace("{" + key + "}", value);
            output = output.replace("<" + key + ">", value);
        }

        return output;
    }

    public static String replaceExact(String input, Map<String, String> tokens) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String output = input;
        for (Map.Entry<String, String> entry : tokens.entrySet()) {
            String token = entry.getKey();
            if (token == null || token.isEmpty()) {
                continue;
            }

            String value = entry.getValue() == null ? "" : entry.getValue();
            output = output.replace(token, value);
        }

        return output;
    }
}
