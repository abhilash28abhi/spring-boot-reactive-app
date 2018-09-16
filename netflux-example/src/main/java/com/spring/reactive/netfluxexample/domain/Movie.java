package com.spring.reactive.netfluxexample.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

//Mongo Db document
@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Movie {

    private String id;
    @NonNull
    private String movieTitle;
}
