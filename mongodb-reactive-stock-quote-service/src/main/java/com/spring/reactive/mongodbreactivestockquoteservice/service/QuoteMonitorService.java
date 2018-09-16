package com.spring.reactive.mongodbreactivestockquoteservice.service;

import com.spring.reactive.mongodbreactivestockquoteservice.client.StockQuoteClient;
import com.spring.reactive.mongodbreactivestockquoteservice.domain.Quote;
import com.spring.reactive.mongodbreactivestockquoteservice.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


//Using Application Listner is Spring way of doing what Command Line Runner does in Spring boot
// when the application context is loaded.
@Service
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private StockQuoteClient stockQuoteClient;
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        stockQuoteClient.getQuoteStream()
                .log("quote-monitor-service")
                .subscribe(quote -> {
                    Mono<Quote> savedQuote = quoteRepository.save(quote);
                    System.out.println("Saved quote Id is : " + savedQuote.block().getId());
                });
    }
}
