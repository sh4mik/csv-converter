package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.eagleinvsys.test.converters.utils.Utils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class StandardCsvConverterTests {

    // TODO: implement JUnit 5 tests for StandardCsvConverter
    @Test
    public void testEmpty() {
        StandardCsvConverter converter = new StandardCsvConverter(new CsvConverter());
        List<Map<String, String>> collection = new ArrayList<>();
        OutputStream outputStream = new ByteArrayOutputStream();
        converter.convert(collection, outputStream);
        Assert.assertEquals(
                "",
                outputStream.toString());
    }

    @Test
    public void testOne() {
        StandardCsvConverter converter = new StandardCsvConverter(new CsvConverter());
        List<Map<String, String>> collection = new ArrayList<>();
        collection.add(Map.of(String.valueOf(1), String.valueOf(1)));
        OutputStream outputStream = new ByteArrayOutputStream();
        converter.convert(collection, outputStream);
        Assert.assertEquals(
                "1\n" +
                        "1\n",
                outputStream.toString());
    }

    @Test
    public void testSmall() {
        StandardCsvConverter converter = new StandardCsvConverter(new CsvConverter());
        List<Map<String, String>> collection = Utils.createSmallCollection();
        OutputStream outputStream = new ByteArrayOutputStream();
        converter.convert(collection, outputStream);
        Assert.assertEquals(
                "0,1,2,3,4,5,6,7,8,9\n" +
                        "1,2,3,4,5,6,7,8,9,10\n" +
                        "10,11,12,13,14,15,16,17,18,19\n" +
                        "100,101,102,103,104,105,106,107,108,109\n" +
                        "1000,1001,1002,1003,1004,1005,1006,1007,1008,1009\n" +
                        "10000,10001,10002,10003,10004,10005,10006,10007,10008,10009\n",
                outputStream.toString());
    }

    @Ignore
    @Test
    public void timeTest() {
        // checking is streams more effective
        StandardCsvConverter converter = new StandardCsvConverter(new CsvConverter());
        List<Map<String, String>> collection = Utils.createBigCollection();
        OutputStream nullOutputStream = new OutputStream() {
            @Override
            public void write(int i) throws IOException {}
        };

        long time = System.currentTimeMillis();
        converter.convert(collection, nullOutputStream);
        // cycle: 1000-1050
        // stream 609 - 700:
        System.out.println(System.currentTimeMillis() - time);
    }
}