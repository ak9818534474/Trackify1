package com.example.ashok.trackify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

public class Friendlist extends AppCompatActivity {
    private static final String DEFAULT="N/A";
    TextView tv2;
       @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        tv2=(TextView) findViewById(R.id.textView2);

           Intent intent= getIntent();
           String name=intent.getStringExtra("name");
           String username=intent.getStringExtra("username");
           Integer age=intent.getIntExtra("age",1);

           tv2.setText(" name = " + name + " username = " + username + " age = " + age);

    ViewPager vp=(ViewPager)findViewById(R.id.pageview);
    vp.setAdapter(new Myadapter(getSupportFragmentManager()));

    }

    public class Myadapter extends FragmentPagerAdapter{


        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new FriendviewFragmnet();
            }
        else{
                return new SupportMapFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
           if(position==0)
           {

               return "Friendlist";
           }
            else
                return "Map";


               }
    }


}
