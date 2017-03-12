package com.example.android.launcherproject;

import java.util.Comparator;

class SortTime implements Comparator<AppDetail> {
    @Override
    public int compare(AppDetail a, AppDetail b){
        return a.installed<b.installed?1:a.installed==b.installed?0:-1;
    }
}
