package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeBookDataSource;
import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeHelloDataSource;
import com.microservices.api_gateway.codegen.DgsConstants;
import com.microservices.api_gateway.codegen.types.Hello;
import com.microservices.api_gateway.codegen.types.SmartSearchResult;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DgsComponent
public class SmartSearchResultResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearchResult(@InputArgument(name = "keyword") Optional<String> keyword) {
        List<SmartSearchResult> list = new ArrayList<>();
        if (StringUtils.isBlank(keyword.get())) {
            list.addAll(FakeHelloDataSource.HELLO_LIST);
            list.addAll(FakeBookDataSource.BOOK_LIST);
        } else if (StringUtils.containsIgnoreCase("BOOK", keyword.get())) {
            list.addAll(FakeBookDataSource.BOOK_LIST);
        } else if (StringUtils.containsIgnoreCase("HELLO", keyword.get())) {
            list.addAll(FakeHelloDataSource.HELLO_LIST);
        }
        return list;
    }
}
