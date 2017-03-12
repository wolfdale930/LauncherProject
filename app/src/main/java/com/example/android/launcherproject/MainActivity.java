package com.example.android.launcherproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = this.getSharedPreferences("MyPrefFile",0);
        boolean con = pref.getBoolean("firstLaunch",true);
        if(con){
            Intent intent = new Intent(this, FirstLaunchActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firstLaunch",false);
            editor.apply();
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
    }

    public void openDrawer(View view){
        Intent i = new Intent(this, Drawer.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){

    }


}
