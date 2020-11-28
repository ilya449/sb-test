package com.test.sb.service.parser;

import com.opencsv.CSVParser;
import com.test.sb.dto.ReviewDto;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Service;

@Service
public class ReviewDtoCsvLineParserImpl implements CsvLineParser<ReviewDto> {
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
    public ReviewDto parseData(String line) {
        CSVParser parser = new CSVParser();
        try {
            String[] values = parser.parseLine(line);
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId(Long.parseLong(values[ID_INDEX]));
            reviewDto.setProductId(values[PRODUCT_ID_INDEX]);
            reviewDto.setUserId(values[USER_ID_INDEX]);
            reviewDto.setProfileName(values[PROFILE_NAME_INDEX]);
            reviewDto.setHelpfulnessNumerator(Integer.parseInt(values[NUMERATOR_INDEX]));
            reviewDto.setHelpfulnessDenominator(Integer.parseInt(values[DENOMINATOR_INDEX]));
            reviewDto.setScore(Integer.parseInt(values[SCORE_INDEX]));
            reviewDto.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                    Long.parseLong(values[TIME_INDEX])), ZoneId.systemDefault()));
            reviewDto.setSummary(values[SUMMARY_INDEX]);
            reviewDto.setText(values[TEXT_INDEX]);
            return reviewDto;
        } catch (IOException e) {
            throw new RuntimeException("Exception while parsing line: " + line, e);
        }
    }
}
