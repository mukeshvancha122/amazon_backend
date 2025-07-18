package com.amazon_backend.product_service.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

public class ReviewDTO {

    @NotNull
    private UUID reviewId;

    @NotNull
    private String reviewText;
}
