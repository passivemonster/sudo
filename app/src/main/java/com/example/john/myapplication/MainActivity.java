package com.example.john.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView sudoTitle ;
    TextView sudoInfo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sudoTitle = findViewById(R.id.sudoTitle) ;
        sudoInfo = findViewById(R.id.sudoInfo) ;
    }

    private class Background extends AsyncTask<Void, Void, String> {

        Context context ;

        Background(Context ctx){
            context=ctx ;

        }
        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }
}
