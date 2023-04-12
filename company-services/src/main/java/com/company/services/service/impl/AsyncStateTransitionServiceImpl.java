package com.company.services.service.impl;

import com.company.dao.repository.CuCpSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author mukulbansal
 */
@Service
public class AsyncStateTransitionServiceImpl {

    @Autowired
    private CuCpSessionInfoRepository cuCpSessionInfoRepository;

    @Transactional
    public void deleteBySessionId(String stateToken) {
        cuCpSessionInfoRepository.deleteBySessionId(stateToken);
    }
}
