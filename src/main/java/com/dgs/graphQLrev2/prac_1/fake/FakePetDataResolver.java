package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakePetDataSource;
import com.microservices.api_gateway.codegen.DgsConstants;
import com.microservices.api_gateway.codegen.types.Cat;
import com.microservices.api_gateway.codegen.types.Dog;
import com.microservices.api_gateway.codegen.types.Pet;
import com.microservices.api_gateway.codegen.types.PetFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakePetDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.Pets)
    public List<Pet> getPets(@InputArgument(name = "petFilter", collectionType = PetFilter.class)
                             Optional<PetFilter> petFilter) {
        if(petFilter.isEmpty())
        return FakePetDataSource.PET_LIST;

           var filteredList= FakePetDataSource.PET_LIST.stream()
                    .filter(pet-> filterPet(petFilter.get(),pet)).toList();
           return filteredList;
    }

    public boolean filterPet(PetFilter filter, Pet pet){
        if(filter.getPetType().name().equalsIgnoreCase(Dog.class.getSimpleName())){
            return pet instanceof Dog;
        }else if(filter.getPetType().name().equalsIgnoreCase(Cat.class.getSimpleName())){
            return pet instanceof  Cat;
        }else return false;
    }
}
