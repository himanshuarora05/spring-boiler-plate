package com.company.services.service.impl;

import com.company.core.runnables.ContextAwareAndSafeRunnable;
import com.company.core.serdes.GsonSerializer;
import com.company.core.util.UUIDUtil;
import com.company.dao.entity.CuCpSessionInfo;
import com.company.dao.repository.CuCpSessionInfoRepository;
import com.company.services.enums.ServiceLayerResponseEnum;
import com.company.services.enums.StateTransitionMethodTypeEnum;
import com.company.services.model.StateTransitionDataDto;
import com.company.services.service.StateTransitionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 * @author mukulbansal
 */
@Service
public class StateTransitionServiceImpl implements StateTransitionService {

    @Autowired
    @Qualifier("dbCleanupScheduler")
    private TaskExecutor executor;
    @Autowired
    private CuCpSessionInfoRepository cuCpSessionInfoRepository;

    @Autowired
    private AsyncStateTransitionServiceImpl asyncStateTransitionService;

    @Override
    public @NotNull StateTransitionServiceResponse save(StateTransitionDataDto stateTransitionDataDto, StateTransitionMethodTypeEnum stateTransitionMethodTypeEnum) {
        CuCpSessionInfo cuCpSessionInfo = new CuCpSessionInfo();
        cuCpSessionInfo.setData(GsonSerializer.getInstance().toJson(stateTransitionDataDto));
        cuCpSessionInfo.setExpiry(System.currentTimeMillis() + stateTransitionMethodTypeEnum.getDefaultExpiryTimeMillis());
        cuCpSessionInfo.setSessionId(UUIDUtil.getUUID4());
        cuCpSessionInfo.setMethod(stateTransitionMethodTypeEnum.getName());
        cuCpSessionInfo.setCreatedAt(System.currentTimeMillis());
        cuCpSessionInfo.setModifiedAt(System.currentTimeMillis());

        CuCpSessionInfo cuCpSessionInfoSaved = cuCpSessionInfoRepository.save(cuCpSessionInfo);

        StateTransitionServiceResponse stateTransitionServiceResponse = new StateTransitionServiceResponse(ServiceLayerResponseEnum.FAILURE);
        if (null != cuCpSessionInfoSaved) {
            stateTransitionServiceResponse.setServiceLayerResponseEnum(ServiceLayerResponseEnum.SUCCESS);
            stateTransitionServiceResponse.setSessionnId(cuCpSessionInfoSaved.getSessionId());
        }

        return stateTransitionServiceResponse;
    }

    @Override
    public StateTransitionDataDto get(String stateToken) {
        CuCpSessionInfo cuCpSessionInfo = cuCpSessionInfoRepository.findBySessionId(stateToken);
        if (null != cuCpSessionInfo && cuCpSessionInfo.getStale() == 0 && System.currentTimeMillis() < cuCpSessionInfo.getExpiry()) {
            return null != cuCpSessionInfo.getData() ? GsonSerializer.getInstance().fromJson(cuCpSessionInfo.getData(), StateTransitionDataDto.class) : null;
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(String stateToken) {
        if (StringUtils.isNotBlank(stateToken)) {
            executor.execute(new ContextAwareAndSafeRunnable(MDC.getCopyOfContextMap()) {
                @Override
                public void runBusinessLogic() {
                    asyncStateTransitionService.deleteBySessionId(stateToken);
                }
            });
        }
    }

    public class StateTransitionServiceResponse {
        private String sessionnId;
        private ServiceLayerResponseEnum serviceLayerResponseEnum;

        public StateTransitionServiceResponse(ServiceLayerResponseEnum serviceLayerResponseEnum) {
            this.serviceLayerResponseEnum = serviceLayerResponseEnum;
        }

        public String getSessionnId() {
            return sessionnId;
        }

        public void setSessionnId(String sessionnId) {
            this.sessionnId = sessionnId;
        }

        public ServiceLayerResponseEnum getServiceLayerResponseEnum() {
            return serviceLayerResponseEnum;
        }

        public void setServiceLayerResponseEnum(ServiceLayerResponseEnum serviceLayerResponseEnum) {
            this.serviceLayerResponseEnum = serviceLayerResponseEnum;
        }
    }
}
