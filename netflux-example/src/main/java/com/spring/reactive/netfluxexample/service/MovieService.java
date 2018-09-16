package com.spring.reactive.netfluxexample.service;

import com.spring.reactive.netfluxexample.domain.Movie;
import com.spring.reactive.netfluxexample.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieEvent> events(String movieId);

    Mono<Movie> getMovieByID (String id);

    Flux<Movie> getAllMovies();
}
