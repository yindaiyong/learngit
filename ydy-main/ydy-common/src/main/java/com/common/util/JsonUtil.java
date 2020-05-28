package com.common.util;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {
    
    private static String objectToJson(Object object) {
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
            try {
                json = om.writeValueAsString(object);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return json;
    }
    
    public static String getJson(Object obj){
        return objectToJson(obj);
    }
    
    /*public static String getPageQueryJson(List<?> queryResult,
            long count) {
        PagedQueryResult pagedQueryResult = new PagedQueryResult();
        pagedQueryResult.setResults(queryResult);
        pagedQueryResult.setCount(count);
        return objectToJson(pagedQueryResult);
    }*/
    
    public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return null;
    }
}
