package com.example.ashok.trackify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

public class Friendlist extends AppCompatActivity {
    private static final String DEFAULT="N/A";
    TextView tv2;
    int m_Text;
    int id;
    map1frag mapfrag;
    public GoogleMap map;

       @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        tv2=(TextView) findViewById(R.id.textView2);
        mapfrag = new map1frag();

           //code to obtain google map named map
           try {
               if (map == null) {
                   map = ((MapFragment) getFragmentManager()
                           .findFragmentById(R.id.map)).getMap();
               }
           } catch (Exception e) {
               e.printStackTrace();
           }


           Intent intent= getIntent();
           id  = intent.getIntExtra("userID",-1);

           if (id == -1){
               tv2.setText("Could'nt retreive id");
           }else{
               tv2.setText("Your id is "+ id);
           }


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
                return mapfrag;
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
               return "Track a buddy";
           }
            else
                return "Map";
               }
    }

    public void createTrackDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enter id of user you want to track");
        //custom title
        TextView title = new TextView(this);
        title.setText("Enter id");
        title.setBackgroundColor(Color.argb(255,0,150,136));
        title.setPadding(30, 30, 30, 30);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        input.setHint("Location title");
        input.setHintTextColor(Color.argb(255,114,114,114));
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = Integer.parseInt(input.getText().toString());
                sendTrackRequest(id, m_Text);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public  void sendTrackRequest( int userId, int receiverId){
        //code to send track request
    }


}
