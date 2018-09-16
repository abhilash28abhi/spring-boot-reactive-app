package com.spring.reactive.netfluxexample.bootstrap;

import com.spring.reactive.netfluxexample.domain.Movie;
import com.spring.reactive.netfluxexample.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class BootstrapCLR implements CommandLineRunner {

    @Autowired
    private final MovieRepository movieRepository;

    public BootstrapCLR (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //clear old data
        movieRepository.deleteAll().thenMany(Flux.just("Silence of the lambdas", "Aeon flux", "Enter the mono void",
            "The fluxinator", "Back to the future", "Lord of the fluxes")
            .map(title -> new Movie(title))
            .flatMap(movieRepository::save))
            .subscribe(null, null, () -> {
                movieRepository.findAll().subscribe(System.out::println);
            });
    }
}
