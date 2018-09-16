package com.spring.reactive.mongodbreactivestockquoteservice;

import com.spring.reactive.mongodbreactivestockquoteservice.client.StockQuoteClient;
import com.spring.reactive.mongodbreactivestockquoteservice.domain.Quote;
import com.spring.reactive.mongodbreactivestockquoteservice.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Component
public class QuoteRunner implements CommandLineRunner {
    @Autowired
    private StockQuoteClient stockQuoteClient;
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public void run(String... args) throws Exception {
        Flux<Quote> quoteFlux = quoteRepository.findWithTailableCursorBy();

        Disposable disposable = quoteFlux.subscribe(quote -> {
            System.out.println("*********** ID : " + quote.getId());
        });
        disposable.dispose();
    }
}
