package com.monitor.cache;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by artur on 2/1/17.
 */
@Service
@Configurable
public class SensorHistoryCache {

    private HashMap<String, String> historyCash;

    public SensorHistoryCache(){
        historyCash = new HashMap<>();
    }
    public void addHistory(String sensorName, String value){
        historyCash.put(sensorName, value);
    }

    public HashMap<String, String> getHistoryCash() {
        return historyCash;
    }
}
