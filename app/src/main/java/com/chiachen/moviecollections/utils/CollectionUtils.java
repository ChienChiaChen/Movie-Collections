package com.chiachen.moviecollections.utils;


import java.util.Collection;
import java.util.Map;

public class CollectionUtils {

    public static boolean isNullOrEmpty(final Collection<?> c) {
        return null == c || c.isEmpty();
    }

    public static boolean isNullOrEmpty(final Map<?, ?> m) {
        return null == m || m.isEmpty();
    }
}
