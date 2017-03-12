package com.example.android.launcherproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class FirstLaunchActivity extends Activity {

    private static int cases = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);
        Button button = (Button)findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)findViewById(R.id.welcome_text);
                switch(cases) {
                        case 1:
                        textView.setText("Thank You for Choosing Neo Launcher");
                        cases++;
                        break;
                        case 2:
                        textView.setText("Tap the bottom right icon to open app drawer");
                        cases++;
                        break;
                        case 3:
                        textView.setText("Try doing that!!");
                        cases++;
                        break;
                        case 4:
                        onResume();
                        break;
                        default:
                        cases = 1;
                        break;
                }
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(cases==4)
            finish();
    }

}
