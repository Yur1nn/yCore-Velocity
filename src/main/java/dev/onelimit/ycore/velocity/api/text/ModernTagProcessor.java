package dev.onelimit.ycore.velocity.api.text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ModernTagProcessor {
    private static final Pattern MODERN_BLOCK_PATTERN = Pattern.compile("<modern>(.*?)</modern>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Map<Character, String> MODERN_MAP = createMap();

    public String apply(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        Matcher matcher = MODERN_BLOCK_PATTERN.matcher(input);
        StringBuffer output = new StringBuffer();
        while (matcher.find()) {
            String converted = convert(matcher.group(1));
            matcher.appendReplacement(output, Matcher.quoteReplacement(converted));
        }
        matcher.appendTail(output);
        return output.toString();
    }

    private String convert(String input) {
        StringBuilder builder = new StringBuilder(input.length());
        for (char character : input.toCharArray()) {
            String mapped = MODERN_MAP.get(Character.toLowerCase(character));
            builder.append(mapped != null ? mapped : character);
        }
        return builder.toString();
    }

    private static Map<Character, String> createMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('a', "ᴀ");
        map.put('b', "ʙ");
        map.put('c', "ᴄ");
        map.put('d', "ᴅ");
        map.put('e', "ᴇ");
        map.put('f', "ꜰ");
        map.put('g', "ɢ");
        map.put('h', "ʜ");
        map.put('i', "ɪ");
        map.put('j', "ᴊ");
        map.put('k', "ᴋ");
        map.put('l', "ʟ");
        map.put('m', "ᴍ");
        map.put('n', "ɴ");
        map.put('o', "ᴏ");
        map.put('p', "ᴘ");
        map.put('q', "ǫ");
        map.put('r', "ʀ");
        map.put('s', "ѕ");
        map.put('t', "ᴛ");
        map.put('u', "ᴜ");
        map.put('v', "ᴠ");
        map.put('w', "ᴡ");
        map.put('x', "х");
        map.put('y', "ʏ");
        map.put('z', "ᴢ");
        return map;
    }
}
