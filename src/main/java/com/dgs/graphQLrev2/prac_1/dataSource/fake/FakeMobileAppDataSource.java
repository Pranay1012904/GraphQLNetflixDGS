package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import com.microservices.api_gateway.codegen.types.Address;
import com.microservices.api_gateway.codegen.types.Author;
import com.microservices.api_gateway.codegen.types.MobileApp;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeMobileAppDataSource {

    @Autowired
    private Faker faker;

    public static final List<MobileApp> MobileAppList = new ArrayList<>();

    @PostConstruct
    public void getMobileApp() {
        var addresses= new ArrayList<Address>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 10); i++) {

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
                    .name(faker.app().name())
                    .version(faker.app().version())
                    .author(author)
                    .platform(getMobileAppPlatform())
                    .build();
            MobileAppList.add(mobileApp);
        }
    }

    private List<String> getMobileAppPlatform() {
        switch (ThreadLocalRandom.current().nextInt(1, 12)%3) {
            case 0:
                return List.of("Android");
            case 1:
                return List.of("iOS");
            default:
                return List.of("Android", "iOS");
        }
    }
}
