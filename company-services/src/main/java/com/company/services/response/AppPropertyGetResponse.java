package com.company.services.response;

import com.company.services.enums.ResponseCodeHttpStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public class AppPropertyGetResponse extends ServiceResponse {

    public AppPropertyGetResponse(ResponseCodeHttpStatusEnum response) {
        super(response);
    }

    private Object data;
}
