package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeCompanyDataSource;
import com.microservices.api_gateway.codegen.types.Company;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

@DgsComponent
public class FakeCompanyDataResolver {

    @DgsQuery
    public static Company company() {
        return FakeCompanyDataSource.company;
    }
}
