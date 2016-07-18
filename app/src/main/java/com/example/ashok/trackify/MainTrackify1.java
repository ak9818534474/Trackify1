package com.example.ashok.trackify;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainTrackify1 extends AppCompatActivity {

    private EditText et1, et2;
    private String username, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trackify1);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

    }
  public void register(View view){
      Intent intent =new Intent(this,MainTrackifyRegister.class);
      et1.setText("");
      et2.setText("");
      startActivity(intent);

  }


    public void login(View view) {

        username =et1.getText().toString();
        pass =et2.getText().toString();
        Response.Listener<String> listener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success=jsonObject.getBoolean("success");
                  if(success){
                      String name=jsonObject.getString("name");
                      String age= (String) jsonObject.get("age");
                      Intent intent=new Intent(MainTrackify1.this,Friendlist.class);
                      intent.putExtra("name",name);
                      intent.putExtra("username",username);
                      intent.putExtra("age",age);
                     //////////////////////////////////////////////////////////////////////////
                      Intent intent2=new Intent(MainTrackify1.this,FriendviewFragmnet.class);
                      intent2.putExtra("name",name);
                      intent2.putExtra("username",username);
                      intent2.putExtra("age",age);

                      startActivity(intent);
                      finish();


                  }
                    else{
                      AlertDialog.Builder builder=new AlertDialog.Builder(MainTrackify1.this);
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
       LoginRequest loginRequest =new LoginRequest(username,pass,listener);
        RequestQueue requestQueue= Volley.newRequestQueue(MainTrackify1.this);
        requestQueue.add(loginRequest);
    }

}