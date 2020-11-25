package com.test.sb.service;

import com.opencsv.CSVParser;
import com.test.sb.dto.Review;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Service;

@Service
public class ReviewCsvLineParserImpl implements CsvLineParser<Review> {
    private static final int ID_INDEX = 0;
    private static final int PRODUCT_ID_INDEX = 1;
    private static final int USER_ID_INDEX = 2;
    private static final int PROFILE_NAME_INDEX = 3;
    private static final int NUMERATOR_INDEX = 4;
    private static final int DENOMINATOR_INDEX = 5;
    private static final int SCORE_INDEX = 6;
    private static final int TIME_INDEX = 7;
    private static final int SUMMARY_INDEX = 8;
    private static final int TEXT_INDEX = 9;

    @Override
    public Review parseData(String line) {
        CSVParser parser = new CSVParser();
        try {
            String[] values = parser.parseLine(line);
            Review review = new Review();
            review.setId(Long.parseLong(values[ID_INDEX]));
            review.setProductId(values[PRODUCT_ID_INDEX]);
            review.setUserId(values[USER_ID_INDEX]);
            review.setProfileName(values[PROFILE_NAME_INDEX]);
            review.setHelpfulnessNumerator(Integer.parseInt(values[NUMERATOR_INDEX]));
            review.setHelpfulnessDenominator(Integer.parseInt(values[DENOMINATOR_INDEX]));
            review.setScore(Integer.parseInt(values[SCORE_INDEX]));
            review.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                    Long.parseLong(values[TIME_INDEX])), ZoneId.systemDefault()));
            review.setSummary(values[SUMMARY_INDEX]);
            review.setText(values[TEXT_INDEX]);
            return review;
        } catch (IOException e) {
            throw new RuntimeException("Exception while parsing line: " + line, e);
        }
    }
}
