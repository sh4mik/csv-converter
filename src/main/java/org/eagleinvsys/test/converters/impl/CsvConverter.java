package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.stream.StreamSupport;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        Collection<String> headers = collectionToConvert.getHeaders();

        if (headers.isEmpty()) {
            return;
        }

        StringJoiner headersJoiner = new StringJoiner(",", "", "\n");
        headers.forEach(headersJoiner::add);
        try {
            outputStream.write(headersJoiner.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Iterable<ConvertibleMessage> records = collectionToConvert.getRecords();
        StreamSupport.stream(records.spliterator(), false)
                .map(record -> {
                    StringJoiner recordJoiner = new StringJoiner(",", "", "\n");
                    headers.forEach(header -> recordJoiner.add(record.getElement(header)));
                    return recordJoiner.toString();
                })
                .forEach(x -> {
                    try {
                        outputStream.write(x.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}