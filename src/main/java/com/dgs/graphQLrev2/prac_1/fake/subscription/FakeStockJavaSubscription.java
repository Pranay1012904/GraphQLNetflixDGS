package com.dgs.graphQLrev2.prac_1.fake.subscription;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeStockDataSource;
import com.microservices.api_gateway.codegen.DgsConstants;
import com.microservices.api_gateway.codegen.types.Stock;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.time.Duration;

@DgsComponent
public class FakeStockJavaSubscription {

    @Autowired
    private FakeStockDataSource dataSource;

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE,field = DgsConstants.SUBSCRIPTION.RandomStock)
    public Publisher<Stock> randomStock(){

        return Flux.interval(Duration.ofSeconds(1)).map(t->dataSource.randomStock());
    }
}
