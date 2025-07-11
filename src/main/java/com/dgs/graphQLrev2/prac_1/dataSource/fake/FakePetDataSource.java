package com.dgs.graphQLrev2.prac_1.dataSource.fake;

import com.microservices.api_gateway.codegen.types.Cat;
import com.microservices.api_gateway.codegen.types.Dog;
import com.microservices.api_gateway.codegen.types.Pet;
import com.microservices.api_gateway.codegen.types.PetFoodType;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakePetDataSource {

    @Autowired
    private Faker faker;

    public static final List<Pet> PET_LIST=new ArrayList<>();

    @PostConstruct
    public void populatePetList(){
        for(int i=0;i<10;i++){
            switch(i%2){
                case 0:
                   var dog= Dog.newBuilder()
                            .name(faker.dog().name())
                            .breed(faker.dog().breed())
                            .foodType(PetFoodType.values()[faker.random().nextInt(PetFoodType.values().length)])
                            .size(faker.dog().size())
                            .coatLength(faker.dog().coatLength())
                            .build();
                    PET_LIST.add(dog);
                    break;
                default:
                    var cat= Cat.newBuilder()
                            .name(faker.cat().name())
                            .breed(faker.cat().breed())
                            .foodType(PetFoodType.values()[PetFoodType.values().length-1])
                            .registry(faker.cat().registry())
                            .build();
                    PET_LIST.add(cat);
            }
        }
    }

}
