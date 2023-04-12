package com.company.services.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public abstract class AbstractServiceRequest {
    private String userId;

    public boolean isValid(){
        return Boolean.TRUE;
    }
}
