package com.company.services.service;

import com.company.services.enums.StateTransitionMethodTypeEnum;
import com.company.services.model.StateTransitionDataDto;
import com.company.services.service.impl.StateTransitionServiceImpl;

import javax.validation.constraints.NotNull;

/**
 * @author mukulbansal
 */
public interface StateTransitionService {

    @NotNull StateTransitionServiceImpl.StateTransitionServiceResponse save(StateTransitionDataDto stateTransitionDataDto, StateTransitionMethodTypeEnum stateTransitionMethodTypeEnum);

    StateTransitionDataDto get(String stateToken);

    void delete(String stateToken);
}
