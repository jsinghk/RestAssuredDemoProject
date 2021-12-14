package com.raexample.utils;

import io.restassured.response.Response;
import org.slf4j.*;
import java.util.*;

public class Storage {

    private static Storage instance;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private Map<String, Object> memory = new HashMap<>();

    public static Storage getInstance(){
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }

    public void rememberTheValue(String key, Object value){
        log.trace("Put into memory: key '{}', value '{}'", new Object[]{key, value});
        memory.put(key, Optional.ofNullable(value).orElse(null));
    }

    public void rememberTheResponse(Response response){
        this.rememberTheValue("response" , response);
    }

    public String whatIsTheValue(String key){
        if(memory.containsKey(key)){
            return Optional.ofNullable(memory.get(key).toString()).orElse("");
        } else {
            throw new RuntimeException("Key : '" + key + "' not found");
        }
    }

    public Object whatIsTheObject(String key){
        if(memory.containsKey(key)){
            return Optional.ofNullable(memory.get(key)).orElse(null);
        } else {
            throw new RuntimeException("Key : '" + key + "' not found");
        }
    }

}