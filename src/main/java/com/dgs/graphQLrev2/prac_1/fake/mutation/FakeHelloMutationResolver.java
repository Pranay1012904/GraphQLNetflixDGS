package com.dgs.graphQLrev2.prac_1.fake.mutation;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeHelloDataSource;
import com.microservices.api_gateway.codegen.DgsConstants;
import com.microservices.api_gateway.codegen.types.Hello;
import com.microservices.api_gateway.codegen.types.HelloInput;
import com.netflix.graphql.dgs.*;

import java.util.List;

@DgsComponent
public class FakeHelloMutationResolver {
    //We can alo use
    //@DgsMutation
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.AddHello)
    public int addHello(HelloInput helloInput) {
        var newHello = Hello.newBuilder()
                .test(helloInput.getText())
                .randomNumber(helloInput.getNumber())
                .build();
        FakeHelloDataSource.HELLO_LIST.add(newHello);
        return FakeHelloDataSource.HELLO_LIST.size();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.ReplaceHelloText)
    public List<Hello> replaceHelloText(HelloInput helloInput) {
        FakeHelloDataSource.HELLO_LIST
                .stream()
                .filter(h -> h.getRandomNumber() == helloInput.getNumber())
                .forEach(h -> h.setTest(helloInput.getText()));
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME,
             field = DgsConstants.MUTATION.DeleteHello)
    public int deleteHello(int number) {
        var updatedHello = FakeHelloDataSource.HELLO_LIST.stream()
                .filter(h -> h.getRandomNumber() != number).toList();
        return updatedHello.size();
    }
}
