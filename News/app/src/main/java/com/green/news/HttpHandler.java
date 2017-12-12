package com.green.news;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler
{
    public String getJSONString(String reqURL)
    {
        String response=null;
        try
        {
            URL url=new URL(reqURL);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is=new BufferedInputStream(conn.getInputStream());
            response=convertStream(is);
        }catch(Exception e)
        {
            Log.d("GetJSON",e.toString());
        }
        return response;
    }
    private String convertStream(InputStream is)
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder sb=new StringBuilder();
        try
        {
            while((line=reader.readLine())!=null)
            {
                sb.append(line).append("\n");
            }

        }catch(Exception e)
        {
            Log.d("ConvertString",e.toString());
        }finally{
            try{
                is.close();
            }catch(Exception e){}
        }
        return sb.toString();
    }
}
