package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeHelloDataSource;
import com.microservices.api_gateway.codegen.types.Hello;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;

@DgsComponent
public class FakeHelloDataResolver {

    @DgsQuery
    public List<Hello> allHellos(){
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsQuery
    public Hello oneHello(){
        return FakeHelloDataSource.HELLO_LIST.get(0);
    }
}
