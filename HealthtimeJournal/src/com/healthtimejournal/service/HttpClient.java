package com.healthtimejournal.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.healthtimejournal.model.ParentModel;

public class HttpClient {
	
	private static String REGISTER_URL = "http://192.168.1.105/healthtime/Test/add_parent.php";
	private static String LOGIN_URL = "http://192.168.1.105/healthtime/Test/login.php";
	private static String RETRIEVE_POST_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_post.php?id=";
	private static String POST_URL = "http://192.168.1.105/healthtime/Test/add_post.php";
	private static String HASHTAG_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_tags.php";
	private static String RETRIEVE_CHILD_URL = "http://192.168.1.105/healthtime/Test/retrieve_child.php";
	private static String RETRIEVE_POST_BY_CHILD_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_post_by_child.php";
	
	HttpURLConnection conn = null;
	InputStream is = null;
	
	public String registerUser(ParentModel parent){
		
		/*try {
			String lemessage = "first_name=" + URLEncoder.encode(parent.getFirstName(), "UTF-8")+
								"&last_name=" + URLEncoder.encode(parent.getLastName(),"UTF-8")+
								"&gender=" + URLEncoder.encode(parent.getGender(),"UTF-8")+
								"&email=" + URLEncoder.encode(parent.getEmail(), "UTF-8")+
								"&password=" + URLEncoder.encode(parent.getPassword(),"UTF-8");
			conn = (HttpURLConnection)(new URL(REGISTER_URL)).openConnection();
			Log.d("msg", lemessage);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setFixedLengthStreamingMode(lemessage.getBytes().length);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(lemessage);
			out.close();
			conn.connect();
		
		StringBuffer buffer = new StringBuffer();
		is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while (  (line = br.readLine()) != null )
			buffer.append(line + "\r\n");
		
		is.close();
		conn.disconnect();
		return buffer.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("first_name", parent.getFirstName()));
		params.add(new BasicNameValuePair("last_name", parent.getLastName()));
		params.add(new BasicNameValuePair("gender", parent.getGender()));
		params.add(new BasicNameValuePair("email", parent.getEmail()));
		params.add(new BasicNameValuePair("password", parent.getPassword()));
		
		return client.makeHttpRequest(REGISTER_URL, "POST", params);
	}
	
	public String loginUser(String email, String pass){
		
		/*try {
			String lemessage = "username=" + URLEncoder.encode(email, "UTF-8")+
								"&password=" + URLEncoder.encode(pass,"UTF-8");
			conn = (HttpURLConnection)(new URL(LOGIN_URL)).openConnection();
			Log.d("msg", lemessage);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setFixedLengthStreamingMode(lemessage.getBytes().length);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(lemessage);
			out.close();
			conn.connect();
		
		StringBuffer buffer = new StringBuffer();
		is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while (  (line = br.readLine()) != null )
			buffer.append(line + "\r\n");
		
		is.close();
		Log.d("response", buffer.toString());
		conn.disconnect();
		return buffer.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", email));
		params.add(new BasicNameValuePair("password", pass));
		
		return client.makeHttpRequest(LOGIN_URL, "POST", params);
		
	}
	
	public String getPost(int id){
			
			/*try {
				conn = (HttpURLConnection)(new URL(RETRIEVE_URL + id)).openConnection();
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.connect();
			
			StringBuffer buffer = new StringBuffer();
			is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (  (line = br.readLine()) != null )
				buffer.append(line + "\r\n");
			
			is.close();
			Log.d("response", buffer.toString());
			conn.disconnect();
			return buffer.toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;*/
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("to_parent_id", "1"));
		
		return client.makeHttpRequest(RETRIEVE_POST_URL, "GET", params);
	}
	
	public String addPost(String post){
		/*try {
			String lemessage = "to_parent_id=" + URLEncoder.encode("1", "UTF-8")+
								"&from_parent_id=" + URLEncoder.encode("1","UTF-8")+
								"&child_id=" + URLEncoder.encode("1","UTF-8")+
								"&post_content=" + URLEncoder.encode(post, "UTF-8")+
								"&file_id=" + URLEncoder.encode("1","UTF-8");
			conn = (HttpURLConnection)(new URL(POST_URL)).openConnection();
			Log.d("msg", lemessage);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setFixedLengthStreamingMode(lemessage.getBytes().length);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(lemessage);
			out.close();
			conn.connect();
		
		StringBuffer buffer = new StringBuffer();
		is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while (  (line = br.readLine()) != null )
			buffer.append(line + "\r\n");
		
		is.close();
		conn.disconnect();
		return buffer.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("to_parent_id", "1"));
		params.add(new BasicNameValuePair("from_parent_id", "1"));
		params.add(new BasicNameValuePair("child_id", "1"));
		params.add(new BasicNameValuePair("post_content", post));
		params.add(new BasicNameValuePair("file_id", "1"));
		
		return client.makeHttpRequest(POST_URL, "POST", params);
	}
	public String retrieve_all_hashtags(){
		
		/*try {
			conn = (HttpURLConnection)(new URL(HASHTAG_URL)).openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");
		
			conn.connect();
		
		StringBuffer buffer = new StringBuffer();
		is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while (  (line = br.readLine()) != null )
			buffer.append(line + "\r\n");
		
		is.close();
		Log.d("response", buffer.toString());
		conn.disconnect();
		return buffer.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		return client.makeHttpRequest(HASHTAG_URL, "GET", params);
		
	}

	public String retrieve_child(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_CHILD_URL, "GET", params);
	}
	
	public String retrieve_post_by_child(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("child_id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_POST_BY_CHILD_URL, "GET", params);
	}



}
