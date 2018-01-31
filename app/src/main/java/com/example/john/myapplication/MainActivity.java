package com.example.john.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URLEncoder;

import javax.print.DocFlavor;

import sun.net.www.protocol.http.HttpURLConnection;

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




    private class BackgroundWorker extends AsyncTask<String,Void,String> {

        Context context;

        AlertDialog alertDialog;
        BackgroundWorker(Context ctx){
            context=ctx;

        }




        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url="http://mark21ironman.co.nf/login.php";

            if (type.equals("login")){

                try {
                    String user_name = params[1];
                    String password = params[2];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream= httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String port_data = URLEncoder.
                            encode("user_name","UTF-8")+"="+URLEncoder.
                            encode(user_name,"UTF-8")+"&"+URLEncoder.
                            encode("password","UTF-8")+"="+URLEncoder.
                            encode(password,"UTF-8");

                    bufferedWriter.write(port_data);

                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    InputStream inputStream= httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader
                            (new InputStreamReader(inputStream,"ISO-8859-1"));
                    String result="";
                    String line="";
                    while ((line = bufferedReader.readLine())!=null){
                        result+=line;

                    }

                    bufferedReader.close();
                    inputStream.close();

                    httpURLConnection.disconnect();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            alertDialog= new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Login Status");



        }

        @Override
        protected void onPostExecute(String result) {
            //alertDialog.setMessage(result);
            //alertDialog.show();

            Toast.makeText(context,result.toString(), Toast.LENGTH_SHORT).show();
            alertDialog.setMessage(result);
            alertDialog.show();



        }

        @Override
        protected void onProgressUpdate(Void
                                                ... values) {
            super.onProgressUpdate(values);
        }
    }

}
