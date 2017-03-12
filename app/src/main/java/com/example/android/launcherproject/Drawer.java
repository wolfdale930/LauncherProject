package com.example.android.launcherproject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drawer extends Activity{

    private PackageManager packageManager;
    private List<AppDetail> listApps;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        loadApps();

        loadListView();
        addClickListner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getTitle().toString()){
            case "Sort Alphabetically" : item.setTitle("Sort with Installed Time");
                                            Collections.sort(listApps,new SortAlpha());
                                            break;

            case "Sort with Installed Time" :item.setTitle("Sort Alphabetically");
                                                Collections.sort(listApps,new SortTime());
                                                break;

            default : super.onOptionsItemSelected(item);
        }
        loadListView();
        return true;
    }


    private void loadApps(){
        packageManager = getPackageManager();
        listApps = new ArrayList<>();
        Intent i = new Intent(Intent.ACTION_MAIN,null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> available = packageManager.queryIntentActivities(i,0);
        for (ResolveInfo info : available){
            try {
                AppDetail appDetail = new AppDetail();
                appDetail.installed = getApplicationContext().getPackageManager().getPackageInfo(info.activityInfo.packageName, 0).firstInstallTime;
                appDetail.icon = info.activityInfo.loadIcon(packageManager);
                appDetail.name = info.activityInfo.packageName;
                appDetail.label = info.loadLabel(packageManager);
                listApps.add(appDetail);
            }
            catch (Exception e){
                Toast toast = Toast.makeText(getApplicationContext(),"Error at loadApps",Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }

    private void loadListView(){
        listView = (ListView)findViewById(R.id.apps_list);
        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,R.layout.list_items,listApps){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                if(convertView==null){
                    convertView = getLayoutInflater().inflate(R.layout.list_items,null);
                }
                ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
                appIcon.setImageDrawable(listApps.get(position).icon);

                TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
                appLabel.setText(listApps.get(position).label);

                return convertView;
            }
        };

        listView.setAdapter(adapter);
    }

    private void addClickListner(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = packageManager.getLaunchIntentForPackage(listApps.get(position).name.toString());
                Drawer.this.startActivity(i);
            }
        });
    }


}
