package com.test.sb.service;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CsvFileReaderServiceImplTest {
    private static final String EMPTY_FILE_NAME = "src/test/resources/empty.csv";
    private static final String FILE_WITH_DATA_NAME = "src/test/resources/test.csv";
    private static final String NOT_EXISTED_FILE_NAME = "src/test/resources/123.csv";
    private static final List<String> EMPTY_LIST = Collections.emptyList();
    private static final List<String> LIST_WITH_DATA = List.of("Id,ProductId,UserId,ProfileName,"
                    + "HelpfulnessNumerator,HelpfulnessDenominator,Score,Time,Summary,Text",
            "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,1303862400,Good Quality Dog Food,I have"
                    + " bought several of the Vitality canned dog food products and have found "
                    + "them all to be of good quality. The product looks more like a stew than a"
                    + " processed meat and it smells better. My Labrador is finicky and she "
                    + "appreciates this product better than  most.",
            "2,B00813GRG4,A1D87F6ZCVE5NK,dll pa,0,0,1,1346976000,Not as Advertised,\"Product "
                    + "arrived labeled as Jumbo Salted Peanuts...the peanuts were actually "
                    + "small sized unsalted. Not sure if this was an error or if the vendor "
                    + "intended to represent the product as \"\"Jumbo\"\".\"",
            "3,B000LQOCH0,ABXLMWJIXXAIN,\"Natalia Corres \"\"Natalia Corres\"\"\",1,1,4,"
                    + "1219017600,\"\"\"Delight\"\" says it all\",\"This is a confection that"
                    + " has been around a few centuries.  It is a light, pillowy citrus gelatin "
                    + "with nuts - in this case Filberts. And it is cut into tiny squares "
                    + "and then liberally coated with powdered sugar.  And it is a tiny mouthful"
                    + " of heaven.  Not too chewy, and very flavorful.  I highly recommend this "
                    + "yummy treat.  If you are familiar with the story of C.S. Lewis' \"\"The "
                    + "Lion, The Witch, and The Wardrobe\"\" - this is the treat that seduces "
                    + "Edmund into selling out his Brother and Sisters to the Witch.\"");

    @Autowired
    private CsvFileReaderService service;

    @Test
    public void readFile_OK() {
        List<String> actual = service.readFile(FILE_WITH_DATA_NAME);
        actual.forEach(System.out::println);
        assertEquals(actual, LIST_WITH_DATA);
    }

    @Test
    public void readEmptyFile() {
        List<String> actual = service.readFile(EMPTY_FILE_NAME);
        assertEquals(actual, EMPTY_LIST);
    }

    @Test(expected = RuntimeException.class)
    public void readNotExistedFile() {
        service.readFile(NOT_EXISTED_FILE_NAME);
    }
}