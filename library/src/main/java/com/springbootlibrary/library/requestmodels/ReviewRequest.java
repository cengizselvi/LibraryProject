package com.springbootlibrary.library.requestmodels;


import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {

    public Long bookId;

    private Optional<String> reviewDescription;
}
