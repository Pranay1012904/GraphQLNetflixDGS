package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import com.microservices.api_gateway.codegen.types.Address;
import com.microservices.api_gateway.codegen.types.Author;
import com.microservices.api_gateway.codegen.types.MobileApp;
import com.microservices.api_gateway.codegen.types.MobileAppCategory;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeMobileAppDataSource {

    @Autowired
    private Faker faker;

    public static final List<MobileApp> MobileAppList = new ArrayList<>();

    @PostConstruct
    public void getMobileApp() throws MalformedURLException {
        var addresses = new ArrayList<Address>();
        for (int i = 0; i < 10; i++) {

            var author = Author.newBuilder()
                    .name(faker.app().author())
                    .originCountry(faker.address().country())
                    .addresses(addresses)
                    .build();

            var address = Address.newBuilder().city(faker.address().cityName())
                    .country(faker.address().country())
                    .street(faker.address().streetName())
                    .zipCode(faker.address().zipCode())
                    .build();
            addresses.add(address);

            var mobileApp = MobileApp.newBuilder()
                    .appID(UUID.randomUUID().toString())
                    .name(faker.app().name())
                    .version(faker.app().version())
                    .author(author)
                    .platform(getMobileAppPlatform())
                    .releaseDate(LocalDate.now().minusDays(5))
                    .downloaded(faker.random().nextInt(1, 10000))
                    .homepage(new URL("https://" + faker.internet().url()))
                    .category(MobileAppCategory.values()[faker.random().nextInt(MobileAppCategory.values().length)])
                    .build();
            MobileAppList.add(mobileApp);
        }
    }

    private List<String> getMobileAppPlatform() {
        switch (ThreadLocalRandom.current().nextInt(1, 12) % 3) {
            case 0:
                return List.of("Android");
            case 1:
                return List.of("iOS");
            default:
                return List.of("Android", "iOS");
        }
    }
}
