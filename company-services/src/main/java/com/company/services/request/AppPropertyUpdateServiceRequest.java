package com.company.services.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public class AppPropertyUpdateServiceRequest extends AbstractServiceRequest {

    private String key;
    private String value;

    public boolean isValid(){
        return StringUtils.isNotBlank(key)&& StringUtils.isNotBlank(value);
    }

}
