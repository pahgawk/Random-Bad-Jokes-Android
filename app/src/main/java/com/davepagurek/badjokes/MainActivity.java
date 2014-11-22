package com.davepagurek.badjokes;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;

import android.transition.Transition;
import android.transition.Explode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.Window;

import com.davepagurek.badjokes.GetJSONTask;


public class MainActivity extends Activity {

    public int last = -1;
    public String URL_GET_JOKE = "http://davepagurek.com/badjokes/joke/";
    public String URL_ADD_JOKE = "http://davepagurek.com/badjokes/add/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //set the transition
        /*Transition ts = new Explode();
        ts.setStartDelay(2000);
        //set the duration
        ts.setDuration(5000);
        getWindow().setEnterTransition(ts);
        getWindow().setExitTransition(ts);*/

        setContentView(R.layout.activity_main);

        Log.wtf("test", "got here 0");

        HttpResponse httpResponse = null;
        HttpEntity httpEntity=null;
        String json=null;
        InputStream is = null;

        String q = "";
        String a = "";

        String url = URL_GET_JOKE + "?last=" + last;

        GetJSONTask request = new GetJSONTask();

        request.setCallbackInstance(this);
        request.execute(url);
    }

    public void launchJoke(int id, String q, String a) {
        last = id;
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("com.davepagurek.badjokes.q", q);
        intent.putExtra("com.davepagurek.badjokes.a", a);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
