package com.test.sb.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    private Integer helpfulnessNumerator;
    private Integer helpfulnessDenominator;
    private Integer score;
    private LocalDateTime time;
    private String summary;
    private String text;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
}
