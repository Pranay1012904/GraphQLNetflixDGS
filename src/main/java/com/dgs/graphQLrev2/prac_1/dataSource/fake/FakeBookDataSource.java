package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import com.microservices.api_gateway.codegen.types.Address;
import com.microservices.api_gateway.codegen.types.Author;
import com.microservices.api_gateway.codegen.types.Book;
import com.microservices.api_gateway.codegen.types.ReleaseHistory;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakeBookDataSource {
    @Autowired
    Faker faker;

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    @PostConstruct
    public void bookList() {
        var addresses = new ArrayList<Address>();
        for (int i = 0; i < 10; i++) {
            //for (int j = 0; j < 3; j++) {
                var address = Address.newBuilder().city(faker.address().cityName())
                        .country(faker.address().country())
                        .street(faker.address().streetName())
                        .zipCode(faker.address().zipCode())
                        .build();
                addresses.add(address);
            //}
            var author = Author.newBuilder()
                    .name(faker.book().author())
                    .originCountry(faker.address().country())
                    .addresses(addresses)
                    .build();
            var releaseHistory = ReleaseHistory.newBuilder()
                    .releasedCountry(faker.country().name())
                    .year(faker.number().numberBetween(1999, 2025))
                    .printedEdition(faker.bool().bool())
                    .build();
            var book = Book.newBuilder()
                    .author(author)
                    .publisher(faker.book().publisher())
                    .released(releaseHistory)
                    .title(faker.book().title())
                    .build();
            BOOK_LIST.add(book);
        }
    }
}
