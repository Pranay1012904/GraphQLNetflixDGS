package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservices.api_gateway.codegen.types.Stock;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

@Configuration
public class FakeStockDataSource {

    @Autowired
    private Faker faker;

    public Stock randomStock(){
        return Stock.newBuilder()
                .lastTradeDateTime(OffsetDateTime.now())
                .price(faker.random().nextInt(10,1000))
                .symbol(faker.stock().nyseSymbol())
                .build();
    }
}
