package com.company.dao.repository;

import com.company.dao.entity.CuCpSessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mukulbansal
 */
@Repository
public interface CuCpSessionInfoRepository extends JpaRepository<CuCpSessionInfo, Integer> {

    void deleteByIdIn(List<Integer> ids);

    CuCpSessionInfo findBySessionId(String sessionId);

    void deleteBySessionId(String stateToken);
}
