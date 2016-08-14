package com.pugetsound.pstourguide.pstourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Grid_Menu extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid__menu);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //TO REMOVE ONCE MENU IS COMPLETE, THESE TOASTS TELL US WHAT WE CLICK ON
                //SEE IMAGEADAPTER.CLASS TO SEE THE IMAGES CURRENTLY IN MENU
                //THOSE IMAGES ARE PLACEHOLDERS, WE COULD DO BETTER
                Toast.makeText(Grid_Menu.this, "" + position,
                Toast.LENGTH_SHORT).show();
                //goes to the maps page, currently in place 0 in the menu
                if (position==0) {
                    Intent intent = new Intent(Grid_Menu.this, MapsActivity.class);
                    startActivity(intent);
                }
                //goes to wherever you tell it to
                if (position==1) {
                    //Intent intent = new Intent(Grid_Menu.this, UNKNOWN_ACTIVITY.class);
                    //startActivity(intent);
                }
                //confused about its destination in life
                if (position==2) {
                    //Intent intent = new Intent(Grid_Menu.this, UKNOWN_ACTIVITY.class);
                    //startActivity(intent);
                }
                //lost
                if (position==3) {
                    //Intent intent = new Intent(Grid_Menu.this, UNKNOWN_ACTIVITY.class);
                    //startActivity(intent);
                }
                //goes to the red dot page, currently in place 4  in the menu
                if (position==4) {
                    Intent intent = new Intent(Grid_Menu.this, RedDotActivity.class);
                    startActivity(intent);
                }
                //sees opportunities slide away every day, dead inside
                if (position==5) {
                    //Intent intent = new Intent(Grid_Menu.this, UNKNOWN_ACTIVITY.class);
                    //startActivity(intent);
                }
            }
        });
    }
}
