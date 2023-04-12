package com.company.scheduler.jobs;

import com.company.dao.entity.CuCpSessionInfo;
import com.company.dao.repository.CuCpSessionInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mukulbansal
 */
@Component
public class StateTransitionCleanupJob {
    private static final Logger LOG = LoggerFactory.getLogger(StateTransitionCleanupJob.class);

    @Autowired
    private CuCpSessionInfoRepository cuCpSessionInfoRepository;

    @Transactional
    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void schedule() {
        LOG.debug("Starting State transition cleanup job.");
        List<Integer> idsToDeleteList = new ArrayList<>();
        int pageSize = 10;
        int pageNumber = 0;
        int itemsRetrived = 0;
        do {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<CuCpSessionInfo> cuCpSessionInfos = cuCpSessionInfoRepository.findAll(pageable);
            cuCpSessionInfos.forEach(cuCpOtpInfo -> {
                if (System.currentTimeMillis() > cuCpOtpInfo.getExpiry()) {
                    idsToDeleteList.add(cuCpOtpInfo.getId());
                }
            });
            pageNumber++;
        } while (itemsRetrived == pageSize);
        if (idsToDeleteList.size() > 0) {
            cuCpSessionInfoRepository.deleteByIdIn(idsToDeleteList);
        }

        LOG.debug("Ending State transition cleanup job.");
    }
}
