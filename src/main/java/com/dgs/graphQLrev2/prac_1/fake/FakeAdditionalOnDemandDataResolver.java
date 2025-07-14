package com.dgs.graphQLrev2.prac_1.fake;

import com.microservices.api_gateway.codegen.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@DgsComponent
public class FakeAdditionalOnDemandDataResolver {//HTTP-HEADER

    @DgsData(parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.AditionalOnRequest)
    public String aditionalOnRequest(
            @RequestHeader(name = "optionalHeader", required = false) String optionalHeader,
            @RequestHeader(name = "mandatoryHeader", required = true) String mandatoryHeader,
            @RequestParam(name = "optionalParam", required = false) String optionalParam,
            @RequestParam(name = "mandatoryParam", required = true) String mandatoryParam
    ) {
        var sb = new StringBuilder();
        sb.append("OptionalHeader :" + optionalHeader);
        sb.append("MandatoryHeader :" + mandatoryHeader);
        sb.append("OptionalParam :" + optionalParam);
        sb.append("MandatoryParam :" + mandatoryParam);
        return sb.toString();
    }
}
