package com.example.ashok.trackify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainTrackifyRegister extends AppCompatActivity {

    private EditText et1, et2,et3,et4;
    private String username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trackifyregister);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }

    public void savecredentials(View view) {

        final String name= et3.getText().toString();
        final String username= et1.getText().toString();
        final int age= Integer.parseInt(et4.getText().toString());
        final String password= et2.getText().toString();

        Response.Listener<String > responselistener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse =new JSONObject(response);
                    boolean success=jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(MainTrackifyRegister.this,"Registration Succesfull !!",Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(MainTrackifyRegister.this,MainTrackify1.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainTrackifyRegister.this);
                        builder.setMessage("Register Failed")
                                .setNegativeButton("retry",null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RegisterRequest registerRequest =new RegisterRequest(name,username,age,password,responselistener);
        RequestQueue requestQueue= Volley.newRequestQueue(MainTrackifyRegister.this);
        requestQueue.add(registerRequest);
    }
    }



