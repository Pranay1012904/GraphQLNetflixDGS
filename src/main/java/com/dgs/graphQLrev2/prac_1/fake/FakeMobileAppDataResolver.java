package com.dgs.graphQLrev2.prac_1.fake;

import com.dgs.graphQLrev2.prac_1.dataSource.fake.FakeMobileAppDataSource;
import com.microservices.api_gateway.codegen.DgsConstants;
import com.microservices.api_gateway.codegen.types.MobileApp;
import com.microservices.api_gateway.codegen.types.MobileAppFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakeMobileAppDataResolver {

    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.MobileApps//"mobileApps"
    )
    public List<MobileApp> mobileApps(@InputArgument(name = "mobileAppFilter", collectionType = MobileAppFilter.class)
                                      Optional<MobileAppFilter> filter) {
        if (filter.isEmpty())
            return FakeMobileAppDataSource.MobileAppList;

        return FakeMobileAppDataSource.MobileAppList.stream()
                .filter(mobileApp -> this.matchFilter(filter.get(), mobileApp))
                .toList();
    }

    private boolean matchFilter(MobileAppFilter mobileAppFilter, MobileApp mobileApp) {
        if (StringUtils.isNotBlank(mobileAppFilter.getName()) && StringUtils.isNotBlank(mobileAppFilter.getVersion())) {
            var isAppMatch = StringUtils.containsIgnoreCase(mobileApp.getName(),
                    StringUtils.defaultIfBlank(mobileAppFilter.getName(), StringUtils.EMPTY))
                    && StringUtils.containsIgnoreCase(mobileApp.getVersion(),
                    StringUtils.defaultIfBlank(mobileApp.getName(), StringUtils.EMPTY));

            if (!isAppMatch) {
                return false;
            }
        }

        if (StringUtils.isNotBlank(mobileAppFilter.getPlatform())
                && !mobileApp.getPlatform().get(0).toLowerCase().contains(mobileAppFilter.getPlatform().toLowerCase())) {
            return false;
        }

        if (StringUtils.isNotBlank(mobileAppFilter.getAuthor().getName()) &&
                !mobileApp.getAuthor().getName().equalsIgnoreCase(StringUtils.defaultIfBlank(mobileAppFilter.getAuthor().getName().trim().toLowerCase(), StringUtils.EMPTY))) {
            return false;
        }

        return true;
    }


}
