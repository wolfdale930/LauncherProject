package com.example.android.launcherproject;

import java.util.Comparator;

class SortAlpha implements Comparator<AppDetail>{
    @Override
    public int compare(AppDetail a, AppDetail b){
        return a.label.charAt(0)<b.label.charAt(0)?-1:a.label.charAt(0)==b.label.charAt(0)?0:1;
    }
}
