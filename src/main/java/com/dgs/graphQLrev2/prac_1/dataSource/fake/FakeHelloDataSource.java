package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import com.microservices.api_gateway.codegen.types.Hello;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakeHelloDataSource {

    @Autowired
    private Faker faker;

    public static final List<Hello> HELLO_LIST=new ArrayList<>();

    @PostConstruct
    private void postConstruct(){
        for(int i=0;i<20;i++){
            var hello=Hello.newBuilder().randomNumber(faker.random().nextInt(100,5000))
                    .test(faker.company().name())
                    .build();
            HELLO_LIST.add(hello);
        }
    }
}
