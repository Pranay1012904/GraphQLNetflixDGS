package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeMobileAppDataSource;
import com.microservices.api_gateway.codegen.types.MobileApp;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;

@DgsComponent
public class FakeMobileAppDataResolver {

    @DgsQuery
    public List<MobileApp> mobileApps(){
        return FakeMobileAppDataSource.MobileAppList;
    }
}
