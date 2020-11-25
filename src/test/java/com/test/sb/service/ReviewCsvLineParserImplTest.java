package com.test.sb.service;

import com.test.sb.dto.Review;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewCsvLineParserImplTest {
    public static final long ID = 1L;
    public static final String PRODUCT_ID = "B001E4KFG0";
    public static final String USER_ID = "A3SGXH7AUHU8GW";
    public static final String PROFILE_NAME = "delmartian";
    public static final int HELPFULNESS_NUMERATOR = 1;
    public static final int HELPFULNESS_DENOMINATOR = 1;
    public static final int SCORE = 5;
    public static final long SECONDS = 1303862400L;
    public static final String WRONG_FORMAT_LINE = "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1";
    public static final String EMPTY_LINE = "";

    @Autowired
    private CsvLineParser<Review> parser;

    @Test
    public void testParser_OK() {
        Review expected = Review.builder()
                .id(ID)
                .productId(PRODUCT_ID)
                .userId(USER_ID)
                .profileName(PROFILE_NAME)
                .helpfulnessNumerator(HELPFULNESS_NUMERATOR)
                .helpfulnessDenominator(HELPFULNESS_DENOMINATOR)
                .score(SCORE)
                .time(LocalDateTime.ofInstant(Instant
                        .ofEpochSecond(SECONDS), ZoneId.systemDefault()))
                .summary("Good Quality Dog Food")
                .text("I have bought several of the Vitality canned dog food products and"
                        + " have found them all to be of good quality. The product looks more"
                        + " like a stew than a processed meat and it smells better. My Labrador"
                        + " is finicky and she appreciates this product better than  most.")
                .build();
        Review actual = parser.parseData("1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,"
                + "1303862400,Good Quality Dog Food,I have bought several of the Vitality canned"
                + " dog food products and have found them all to be of good quality. The product"
                + " looks more like a stew than a processed meat and it smells better. My Labrador"
                + " is finicky and she appreciates this product better than  most.");
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void parseWrongFormatLine() {
        parser.parseData(WRONG_FORMAT_LINE);
    }

    @Test(expected = RuntimeException.class)
    public void parseEmptyLine() {
        parser.parseData(EMPTY_LINE);
    }
}
