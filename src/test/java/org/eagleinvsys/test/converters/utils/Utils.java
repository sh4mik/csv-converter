package org.eagleinvsys.test.converters.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static List<Map<String, String>> createSmallCollection() {
        List<Map<String, String>> collection = new ArrayList<>();
        for (int i = 1; i <= 10000; i*=10) {
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                map.put(String.valueOf(j), String.valueOf(j + i));
            }
            collection.add(map);
        }

        return collection;
    }

    public static List<Map<String, String>> createBigCollection() {
        List<Map<String, String>> collection = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < 1000; j++) {
                map.put(String.valueOf(j), String.valueOf(i));
            }
            collection.add(map);
        }

        return collection;
    }
}
