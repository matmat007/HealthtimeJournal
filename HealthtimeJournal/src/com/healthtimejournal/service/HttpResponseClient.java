package com.healthtimejournal.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URLEncodedUtils;

import android.util.Log;
 
public class HttpResponseClient {
 
    static InputStream is = null;
    private HttpURLConnection conn = null;
 
    public String makeHttpRequest(String url, String method, List<NameValuePair> params) {
        try {
            if(method == "POST"){
                /*DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
 
                HttpResponse httpResponse = (HttpResponse)httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();*/
            	String paramString = URLEncodedUtils.format(params, "utf-8");
            	conn = (HttpURLConnection)(new URL(url)).openConnection();
    			conn.setRequestMethod("POST");
    			conn.setDoInput(true);
    			conn.setDoOutput(true);
    			conn.setFixedLengthStreamingMode(paramString.getBytes().length);
    			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    			conn.setRequestProperty("Connection", "close");
    			PrintWriter out = new PrintWriter(conn.getOutputStream());
    			out.print(paramString);
    			out.close();
    			conn.connect();
            }
            else if(method == "GET"){
                // request method is GET
            	Log.d("url", url);
                /*DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = (HttpResponse)httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();*/
            	String paramString = URLEncodedUtils.format(params, "utf-8");
            	url += "?" + paramString;
            	
            	conn = (HttpURLConnection)(new URL(url)).openConnection();
    			conn.setRequestMethod("GET");
    			conn.setDoInput(true);
    			conn.setDoOutput(true);
    			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    			conn.setRequestProperty("Connection", "close");
    			conn.connect();
            }
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            /*BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            //json = sb.toString();*/
        	
        	Log.d("URL", conn.getURL().toString());
            StringBuffer buffer = new StringBuffer();
			is = conn.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (  (line = br.readLine()) != null )
				buffer.append(line + "\r\n");
			
			is.close();
			conn.disconnect();
			Log.d("respones", buffer.toString());
			return buffer.toString();
        	
        	
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        
        return null;
    }
}
