/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonNavigator {
    private JSONObject jsonObject;

    public JsonNavigator(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return null;
            }
        }
        return value.getString(path[path.length - 1]);
    }

    public int getIntValue(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return 0;
            }
        }
        return value.getIntValue(path[path.length - 1]);
    }
    
    public Integer getInteger(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return null;
            }
        }
        return value.getInteger(path[path.length - 1]);
    }
    
    public double getDoubleValue(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return 0d;
            }
        }
        return value.getDoubleValue(path[path.length - 1]);
    }

    public Double getDouble(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return null;
            }
        }
        return value.getDouble(path[path.length - 1]);
    }
    
    public JsonNavigator getJsonNavigator(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return null;
            }
        }
        return new JsonNavigator(value.getJSONObject(path[path.length - 1]));
    }
    
    public JsonNavigator[] getArray(String... path) {
        JSONObject value = jsonObject;
        for (int i = 0; i < path.length - 1; i++) {
            value = value.getJSONObject(path[i]);
            if (null == value) {
                return null;
            }
        }
        JSONArray array = value.getJSONArray(path[path.length - 1]);
        JsonNavigator[] navArray = new JsonNavigator[array.size()];
        for (int i = 0; i < array.size(); i++) {
            navArray[i] = new JsonNavigator(array.getJSONObject(i));
        }
        return navArray;
    }
}
