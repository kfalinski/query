package core.utils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toCollection;

/**
 * Created by Krzysztof on 2014-11-24.
 */
public class GeoCollectors {
    public static <T> Collector<T, ?, Set<T>> toOrderedSet() {
        return toCollection(LinkedHashSet::new);
    }

    public static <T> Collector<T, ?, Set<T>> toOrderedSet(int size) {
        return toCollection(() -> new LinkedHashSet<>(size));
    }
}