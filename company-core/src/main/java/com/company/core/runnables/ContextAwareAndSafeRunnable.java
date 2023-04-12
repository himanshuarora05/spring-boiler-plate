package com.company.core.runnables;

import org.slf4j.MDC;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mukulbansal
 */
@Component
@Scope("prototype")
public abstract class ContextAwareAndSafeRunnable implements Runnable{

    private Map<String, String> contextMap;

    public ContextAwareAndSafeRunnable(Map<String, String> contextMap) {
        this.contextMap = contextMap;
    }

    @Override
    public void run() {
        try{
            if(null != contextMap) { MDC.setContextMap(contextMap); }
            runBusinessLogic();
        }finally{
            MDC.clear();
        }
    }

    public abstract void runBusinessLogic();
}
