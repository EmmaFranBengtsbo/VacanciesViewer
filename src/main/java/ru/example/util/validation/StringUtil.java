package ru.example.util.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static int countMatches(String string, String sub) {
        if (isEmpty(string) || isEmpty(sub)) {
            return 0;
        }
        int index = 0;
        int count = 0;
        while (true) {
            index = string.indexOf(sub, index);
            if (index != -1) {
                count++;
                index += sub.length();
            } else {
                break;
            }
        }
        return count;
    }

    public static Map<String, List<String>> getMessage(String string, String propertyKey, String msgKey) {
        if (countMatches(string, propertyKey) != countMatches(string, msgKey)) {
            return null;
        }
        int index = 0;
        StringBuilder propertyBuilder = new StringBuilder("");
        StringBuilder messageBuilder = new StringBuilder("");
        Map<String, List<String>> result = new HashMap<>();
        while (true) {
            index = string.indexOf(msgKey, index);
            if (index != -1) {
                index += msgKey.length();
                while (string.charAt(index) != '\'') {
                    messageBuilder.append(string.charAt(index));
                    index++;
                }
                index = string.indexOf(propertyKey, index);
                index += propertyKey.length();
                while (string.charAt(index) != ',') {
                    propertyBuilder.append(string.charAt(index));
                    index++;
                }
                String key = propertyBuilder.toString();
                String value = messageBuilder.toString();
                if (!result.containsKey(key)) {
                    List<String> newList = new ArrayList<>();
                    newList.add(value);
                    result.put(key, newList);
                } else {
                    result.get(key).add(value);
                }
                propertyBuilder = new StringBuilder("");
                messageBuilder = new StringBuilder("");
            } else {
                break;
            }
        }
        return result;
    }
}
