package com.spring.reactive.netfluxexample.controller;

import com.spring.reactive.netfluxexample.domain.Movie;
import com.spring.reactive.netfluxexample.domain.MovieEvent;
import com.spring.reactive.netfluxexample.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return movieService.events(id);
    }

    @GetMapping(value = "{id}")
    Mono<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieByID(id);
    }

    @GetMapping
    Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}

