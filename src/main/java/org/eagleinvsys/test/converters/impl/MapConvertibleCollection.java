package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapConvertibleCollection implements ConvertibleCollection {
    private List<Map<String, String>> collection;


    public MapConvertibleCollection(List<Map<String, String>> collection) {
        this.collection = collection;
    }

    @Override
    public Collection<String> getHeaders() {
        final int FIRST_INDEX = 0;
        return collection.isEmpty() ?
                Collections.emptySet(): collection.get(FIRST_INDEX).keySet();
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return collection.stream()
                .map(x -> new ConvertibleMessage() {
                    @Override
                    public String getElement(String elementId) {
                        return x.get(elementId);
                    }
                }).collect(Collectors.toList());
    }
}
