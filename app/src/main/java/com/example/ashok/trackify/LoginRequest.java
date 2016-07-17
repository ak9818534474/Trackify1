package com.example.ashok.trackify;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashok on 7/16/2016.
 */
public class LoginRequest extends StringRequest {

    private static final String Login_Request_url = "http://newbeginning.comxa.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String pass, Response.Listener<String> listener) {
        super(Request.Method.POST, Login_Request_url, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", pass);


    }

    public Map<String, String> getParams() {
        return params;
    }
}
