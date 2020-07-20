package com.thedrinkwholesale.dws.dto;



import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;


public class LoginFlag {

    private Map<String,Object> data = new HashMap<>();

    public void idError() {
        data.put("flag",false);
        data.put("error","id");

    }

    public void passwordError() {
        data.put("flag",false);
        data.put("error","password");

    }

    public void successLogin() {
        data.put("flag",true);

    }

    public Map<String,Object> result() {

        return data;

    }


}
