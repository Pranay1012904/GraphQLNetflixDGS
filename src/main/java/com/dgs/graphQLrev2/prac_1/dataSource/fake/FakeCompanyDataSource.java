package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import com.microservices.api_gateway.codegen.types.Company;
import com.microservices.api_gateway.codegen.types.CompanyAddress;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeCompanyDataSource {

    @Autowired
    private Faker faker;

    public static Company company = null;

    @PostConstruct
    public void getData() {

        var address = CompanyAddress.newBuilder()
                .city(faker.address().cityName())
                .country(faker.address().country())
                .build();

        company = Company.newBuilder()
                .name(faker.company().name())
                .industry(faker.company().industry())
                .address(address).build();

    }
}
