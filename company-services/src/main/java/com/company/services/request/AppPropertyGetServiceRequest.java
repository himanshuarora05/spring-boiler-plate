package com.company.services.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public class AppPropertyGetServiceRequest extends AbstractServiceRequest {

    private String secret;

    public boolean isValid(){
        return StringUtils.isNotBlank(secret);
    }
}
