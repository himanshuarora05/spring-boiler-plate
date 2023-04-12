package com.company.services.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mukulbansal
 */
public class StateTransitionDataDto {

    private Map<Integer, Object> map;

    public StateTransitionDataDto() {
        map = new HashMap<>();
    }

    public StateTransitionDataDto(Map<Integer, Object> map) {
        this.map = map;
    }

    public void addStateTransitionData(int type, Object object) {
        map.put(type, object);
    }

    public Object getFromStateTransitionData(int type) {
        return map.get(type);
    }

    public Map<Integer,Object> getMap() { return this.map; }

    public void setMap(Map<Integer,Object> map) { this.map = map; }

    public void mergeStateTransitionDataDto(StateTransitionDataDto newStateTransitionDataDto) {
        if(null == newStateTransitionDataDto){
            return;
        }
        if (null == this.getMap()) {
            this.setMap(newStateTransitionDataDto.getMap());
        }else {
            Map<Integer, Object> existingStateIntegerObjectMap = this.getMap();
            Map<Integer, Object> newstateIntegerObjectMap = newStateTransitionDataDto.getMap();
            existingStateIntegerObjectMap.putAll(newstateIntegerObjectMap);
            this.setMap(existingStateIntegerObjectMap);
        }
    }
}
