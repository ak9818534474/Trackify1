package com.example.ashok.trackify;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class FriendviewFragmnet extends Fragment {


    ListView listView;
    List<String>friendlist;

    public FriendviewFragmnet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View Fragmnetview = inflater.inflate(R.layout.fragment_friendview_fragmnet, container, false);
        String username ,name;

        Bundle b = getActivity().getIntent().getExtras();
        username = b.getString("username");
        name = b.getString("name");


        friendlist=new ArrayList<String>();
        friendlist.add(username);
        friendlist.add(name);


        return Fragmnetview;
    }

     private  class MyAdapter extends BaseAdapter{

         @Override
         public int getCount() {
             return friendlist.size();
         }

         @Override
         public Object getItem(int position) {
             return friendlist.get(position);
         }

         @Override
         public long getItemId(int position) {
             return 0;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             View rowview= getActivity().getLayoutInflater().inflate(R.layout.row,null);
             TextView tx=(TextView)rowview.findViewById(R.id.textView3);
             tx.setText(friendlist.get(position));


             return rowview;
         }
     }
}
