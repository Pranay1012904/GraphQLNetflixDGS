package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeBookDataSource;
import com.microservices.api_gateway.codegen.DgsConstants;
import com.microservices.api_gateway.codegen.types.Book;
import com.microservices.api_gateway.codegen.types.ReleaseHistory;
import com.microservices.api_gateway.codegen.types.ReleaseHistoryInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;

@DgsComponent
public class FakeBookDataResolver {

    @DgsQuery
    public List<Book> books() {

        return FakeBookDataSource.BOOK_LIST;

    }

    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.BooksByReleased
    )
    public List<Book> getBooksByReleased(DataFetchingEnvironment dataFetchingEnvironment) {

        var releasedMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("releasedInput");
        var releaseInput=ReleaseHistoryInput.newBuilder()
                .printedEdition((Boolean)releasedMap.get(DgsConstants.RELEASEHISTORYINPUT.PrintedEdition))
                .year((int)releasedMap.get(DgsConstants.RELEASEHISTORYINPUT.Year))
                .build();
        List<Book> books=FakeBookDataSource.BOOK_LIST.stream()
                .filter(book-> this.matchBookByRelease(releaseInput,book.getReleased()))
                .toList();
        return books;
    }

    private Boolean matchBookByRelease(ReleaseHistoryInput input, ReleaseHistory element){
        return (input.getPrintedEdition()==element.getPrintedEdition() &&
        input.getYear()==element.getYear());

    }

}
