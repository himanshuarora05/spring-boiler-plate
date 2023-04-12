package com.company.services.rest.request;

import com.company.services.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public class AppPropertyUpdateRequest extends BaseRequest {

    @NotNull(message = "000002")
    private String key;
    @NotNull(message = "000002")
    private String value;
    @NotNull(message = "000002")
    private String secret;
}
