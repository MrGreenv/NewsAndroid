package com.green.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    String url;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<NewsData> newsData;
    Context context;
    ListView newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        newsData=new ArrayList<>();
        context=this;
        newsList=(ListView)findViewById(R.id.list);

        sharedPreferences=getSharedPreferences("setting", MODE_PRIVATE);
        editor=sharedPreferences.edit();
        url=sharedPreferences.getString("url","https://newsapi.org/v2/top-headlines?sources=the-times-of-india&apiKey=6b98d4c1f8e64477a3323a20b577ad8e");
        new getNews().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.TTI)
        {
            url="https://newsapi.org/v2/top-headlines?sources=the-times-of-india&apiKey=6b98d4c1f8e64477a3323a20b577ad8e";
            editor.putString("url",url);
            editor.commit();
            new getNews().execute();
        }else
        if(id==R.id.TheHindu)
        {
            url="https://newsapi.org/v2/top-headlines?sources=the-hindu&apiKey=6b98d4c1f8e64477a3323a20b577ad8e";
            editor.putString("url",url);
            editor.commit();
            new getNews().execute();
        }
        else if(id==R.id.CNBC)
        {
            url="https://newsapi.org/v2/top-headlines?sources=cnbc&apiKey=6b98d4c1f8e64477a3323a20b577ad8e";
            editor.putString("url",url);
            editor.commit();
            new getNews().execute();
        }
        else if(id==R.id.CNN)
        {
            url="https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=6b98d4c1f8e64477a3323a20b577ad8e";
            editor.putString("url",url);
            editor.commit();
            new getNews().execute();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class getNews extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPostExecute(Void aVoid)
        {
            ListAdapter adapter=new ListAdapter(context,newsData);
            Log.d("url",url);
            newsList.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            HttpHandler httpHandler=new HttpHandler();
            String JSONStr=httpHandler.getJSONString(url);
            if(JSONStr!=null)
            {
                newsData.clear();
                try
                {
                    JSONObject baseOBJ=new JSONObject(JSONStr);
                    JSONArray newsArr=baseOBJ.getJSONArray("articles");
                    for(int i=0;i<newsArr.length();i++)
                    {

                        JSONObject arrbase=newsArr.getJSONObject(i);
                        newsData.add(new NewsData(arrbase.get("title").toString(),"","",i));
                    }
                }catch(Exception e)
                {

                }

            }
            return null;
        }
    }
}
