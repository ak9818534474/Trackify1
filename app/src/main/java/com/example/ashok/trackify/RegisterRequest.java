package com.example.ashok.trackify;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashok on 7/15/2016.
 */
public class RegisterRequest extends StringRequest {


       private static final String Register_Request_url ="http://newbeginning.comxa.com/Register.php";
       private Map<String,String>params;

    public RegisterRequest(String name,String username,int age,String pass,Response.Listener<String>listener){
        super (Method.POST,Register_Request_url,listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",pass);
        params.put("age",age +"");

    }
public Map<String,String >getParams(){
    return params;
}


}
