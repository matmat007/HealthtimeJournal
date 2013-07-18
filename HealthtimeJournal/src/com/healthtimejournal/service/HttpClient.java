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
	
	//Add Php Urls
	private static String POST_URL = "http://192.168.1.105/healthtime/Test/add_post.php";
	private static String ADD_SHARING_DOCTOR_URL = "http://192.168.1.105/healthtime/Test/add_sharing_doctor.php";
	private static String ADD_DOCTOR_URL = "http://192.168.1.105/healthtime/Test/add_doctor.php";
	private static String ADD_COMMENT_URL = "http://192.168.1.105/healthtime/Test/add_comment.php";
	private static String ADD_MEDICAL_HISTORY_URL = "http://192.168.1.105/healthtime/Test/add_medical_history.php";
	private static String ADD_GALLERY_URL = "http://192.168.1.105/healthtime/Test/add_gallery.php";
	private static String ADD_FAMILY_URL = "http://192.168.1.105/healthtime/Test/add_family.php";
	private static String ADD_SHARING_URL = "http://192.168.1.105/healthtime/Test/add_sharing.php";
	private static String ADD_CHILD_URL = "http://192.168.1.105/healthtime/Test/add_child.php";
	
	/*Retrieve Php URLs*/
	private static String HASHTAG_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_tags.php";
	private static final String RETRIEVE_CHILD_URL = "http://192.168.1.105/healthtime/Test/retrieve_child.php";
	private static final String RETRIEVE_POST_BY_CHILD_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_post_by_child.php";
	private static final String RETRIEVE_COMMENT_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_comment.php";
	private static final String RETRIEVE_ALL_POST_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_post.php";
	private static final String RETRIEVE_CHILD_BY_SEARCH_URL = "http://192.168.1.105/healthtime/Test/retrieve_child_by_search.php";
	private static final String RETRIEVE_POST_BY_PARENT_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_post_by_parent.php";
	private static final String RETRIEVE_CHILD_BY_FAMILY_URL = "http://192.168.1.105/healthtime/Test/retrieve_child_by_family.php";
	private static final String RETRIEVE_DOCTOR_URL = "http://192.168.1.105/healthtime/Test/retrieve_doctor.php";
	private static final String RETRIEVE_DOCTOR_BY_SEARCH_URL = "http://192.168.1.105/healthtime/Test/retrieve_doctor_by_search.php";
	private static final String RETRIEVE_POST_OF_PARENT_BY_CHILD_URL = "http://192.168.1.105/healthtime/Test/retrieve_all_all_post_of_parent_by_child.php";
	private static final String RETRIEVE_SHARED_TO_PARENT_ACCOUNTS_URL = "http://192.168.1.105/healthtime/Test/retrieve_shared_to_parent_accounts.php";
	private static final String RETRIEVE_PARENT_URL = "http://192.168.1.105/healthtime/Test/retrieve_parent.php";
	private static final String RETRIEVE_PARENT_BY_SEARCH_URL = "http://192.168.1.105/healthtime/Test/retrieve_parent_by_search.php";
	private static final String RETRIEVE_SHARED_BY_PARENT_ACCOUNTS_URL = "http://192.168.1.105/healthtime/Test/retrieve_shared_by_parent_accounts.php";
	private static final String RETRIEVE_MEDICAL_HISTORY_URL = "http://192.168.1.105/healthtime/Test/retrieve_parent_medical_history.php";
	private static final String RETRIEVE_SHARED_TO_DOCTOR_ACCOUNTS_URL = "http://192.168.1.105/healthtime/Test/retrieve_shared_to_doctor_accounts.php";

	HttpURLConnection conn = null;
	InputStream is = null;
	
	public String loginUser(String email, String pass){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", email));
		params.add(new BasicNameValuePair("password", pass));
		
		return client.makeHttpRequest(LOGIN_URL, "POST", params);
		
	}
	
	//Add Methods
	
	public String registerUser(ParentModel parent){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("first_name", parent.getFirstName()));
		params.add(new BasicNameValuePair("last_name", parent.getLastName()));
		params.add(new BasicNameValuePair("gender", parent.getGender()));
		params.add(new BasicNameValuePair("email", parent.getEmail()));
		params.add(new BasicNameValuePair("password", parent.getPassword()));
		
		return client.makeHttpRequest(REGISTER_URL, "POST", params);
	}
	
	public String addPost(String post){

		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("to_parent_id", "1"));
		params.add(new BasicNameValuePair("from_parent_id", "1"));
		params.add(new BasicNameValuePair("child_id", "1"));
		params.add(new BasicNameValuePair("post_content", post));
		params.add(new BasicNameValuePair("file_id", "1"));
		
		return client.makeHttpRequest(POST_URL, "POST", params);
	}
	
	//End of Add Methods
	
	//--------------------
	
	//Retrieve Methods
	
	public String retrieve_all_hashtags(){
		
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
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_POST_BY_CHILD_URL, "GET", params);
	}

	public String retrieve_all_comment(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_COMMENT_URL, "GET", params);
	}
	
	public String retrieve_all_post(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_ALL_POST_URL, "GET", params);
	}
	
	public String retrieve_shared_to_doctor_accounts(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_SHARED_TO_DOCTOR_ACCOUNTS_URL, "GET", params);
	}
	
	public String retrieve_medical_history(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_MEDICAL_HISTORY_URL, "GET", params);
	}
	
	public String retrieve_shared_by_parent_accounts(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_SHARED_BY_PARENT_ACCOUNTS_URL, "GET", params);
	}
	
	public String retrieve_post_by_parent(int parent_id, int child_id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parent_id", String.valueOf(parent_id)));
		params.add(new BasicNameValuePair("child_id", String.valueOf(child_id)));
		
		return client.makeHttpRequest(RETRIEVE_POST_BY_PARENT_URL, "GET", params);
	}
	
	public String retrieve_parent_by_search(String name){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", name));
		
		return client.makeHttpRequest(RETRIEVE_PARENT_BY_SEARCH_URL, "GET", params);
	}
	
	public String retrieve_shared_to_parent_accounts(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_SHARED_TO_PARENT_ACCOUNTS_URL, "GET", params);
	}
	
	public String retrieve_parent(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_PARENT_URL, "GET", params);
	}
	
	public String retrieve_all_post_of_parent_by_child(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_POST_OF_PARENT_BY_CHILD_URL, "GET", params);
	}
	
	public String retrieve_doctor(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_DOCTOR_URL, "GET", params);
	}
	
	public String retrieve_child_by_search(String name){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", name));
		
		return client.makeHttpRequest(RETRIEVE_CHILD_BY_SEARCH_URL, "GET", params);
	}
	
	public String retrieve_doctor_by_search(String name){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", name));
		
		return client.makeHttpRequest(RETRIEVE_DOCTOR_BY_SEARCH_URL, "GET", params);
	}
	
	public String retrieve_child_by_family(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_CHILD_BY_FAMILY_URL, "GET", params);
	}
	
//End of Retrieve Methods
}
