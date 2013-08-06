package com.healthtimejournal.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.healthtimejournal.model.ChildModel;
import com.healthtimejournal.model.CommentModel;
import com.healthtimejournal.model.DiseaseDictionaryModel;
import com.healthtimejournal.model.DoctorModel;
import com.healthtimejournal.model.Event;
import com.healthtimejournal.model.FamilyModel;
import com.healthtimejournal.model.GalleryModel;
import com.healthtimejournal.model.ParentModel;
import com.healthtimejournal.model.ParentSicknessModel;
import com.healthtimejournal.model.PostModel;
import com.healthtimejournal.model.SharingDoctorModel;
import com.healthtimejournal.model.SharingModel;

public class HttpClient {
	
	private static String REGISTER_URL = "http://172.16.3.156/healthtime/Test/add_parent.php";
	private static String LOGIN_URL = "http://172.16.3.156/healthtime/Test/login.php";
	
	//Upload URL
	private static final String UPLOAD_IMAGE = "http://172.16.3.156/healthtime/Test/upload.php";
	
	//Add Php Urls
	private static final String POST_URL = "http://172.16.3.156/healthtime/Test/add_post.php";
	private static final String ADD_SHARING_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/add_sharing_doctor.php";
	private static final String ADD_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/add_doctor.php";
	private static final String ADD_COMMENT_URL = "http://172.16.3.156/healthtime/Test/add_comment.php";
	private static final String ADD_MEDICAL_HISTORY_URL = "http://172.16.3.156/healthtime/Test/add_medical_history.php";
	private static final String ADD_FAMILY_URL = "http://172.16.3.156/healthtime/Test/add_family.php";
	private static final String ADD_SHARING_URL = "http://172.16.3.156/healthtime/Test/add_sharing.php";
	private static final String ADD_CHILD_URL = "http://172.16.3.156/healthtime/Test/add_child.php";
	private static final String ADD_DISEASE_URL = "http://172.16.3.156/healthtime/Test/add_disease_dictionary.php";
	private static final String EVENT_URL = "http://172.16.3.156/healthtime/Test/add_event.php";
	
	//Delete Php Urls
	private static final String DELETE_CHILD_URL = "http://172.16.3.156/healthtime/Test/delete_child.php";
	private static final String DELETE_COMMENT_URL = "http://172.16.3.156/healthtime/Test/delete_comment.php";
	private static final String DELETE_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/delete_doctor.php";
	private static final String DELETE_POST_URL = "http://172.16.3.156/healthtime/Test/delete_post.php";
	private static final String DELETE_SHARING_URL = "http://172.16.3.156/healthtime/Test/delete_sharing.php";
	private static final String DELETE_SHARING_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/delete_sharing_doctor.php";
	private static final String DELETE_DISEASE_URL = "http://172.16.3.156/healthtime/Test/delete_disease_dictionary.php";

	//Edit Php Urls
	private static final String EDIT_SHARING_URL = "http://172.16.3.156/healthtime/Test/edit_sharing.php";
	private static final String EDIT_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/edit_doctor.php";
	private static final String EDIT_MEDICAL_HISTORY_URL = "http://172.16.3.156/healthtime/Test/edit_medical_history.php";
	private static final String EDIT_DISEASE_URL = "http://172.16.3.156/healthtime/Test/edit_disease_dictionary.php";
	private static final String EDIT_PARENT_PHOTO_URL = "http://172.16.3.156/healthtime/Test/edit_parent_photo.php";
	private static final String EDIT_FAMILY_URL = "http://172.16.3.156/healthtime/Test/edit_family.php";
	
	//Retrieve Php URLs
	private static final String HASHTAG_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_tags.php";
	private static final String RETRIEVE_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_child.php";
	private static final String RETRIEVE_POST_BY_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_post_by_child.php";
	private static final String RETRIEVE_COMMENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_comment.php";
	private static final String RETRIEVE_ALL_EVENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_event.php";
	private static final String RETRIEVE_ALL_POST_BY_EVENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_post_by_event.php";
	private static final String RETRIEVE_CHILD_BY_SEARCH_URL = "http://172.16.3.156/healthtime/Test/retrieve_child_by_search.php";
	private static final String RETRIEVE_POST_BY_PARENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_post_by_parent.php";
	private static final String RETRIEVE_CHILD_BY_FAMILY_URL = "http://172.16.3.156/healthtime/Test/retrieve_child_by_family.php";
	private static final String RETRIEVE_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/retrieve_doctor.php";
	private static final String RETRIEVE_DOCTOR_BY_PARENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_doctor_by_parent.php";
	private static final String RETRIEVE_DOCTOR_BY_SEARCH_URL = "http://172.16.3.156/healthtime/Test/retrieve_doctor_by_search.php";
	private static final String RETRIEVE_POST_OF_PARENT_BY_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_all_post_of_parent_by_child.php";
	private static final String RETRIEVE_SHARED_TO_PARENT_ACCOUNTS_URL = "http://172.16.3.156/healthtime/Test/retrieve_shared_to_parent_accounts.php";
	private static final String RETRIEVE_PARENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_parent.php";
	private static final String RETRIEVE_PARENT_BY_SEARCH_URL = "http://172.16.3.156/healthtime/Test/retrieve_parent_by_search.php";
	private static final String RETRIEVE_SHARED_BY_PARENT_ACCOUNTS_URL = "http://172.16.3.156/healthtime/Test/retrieve_shared_by_parent_accounts.php";
	private static final String RETRIEVE_MEDICAL_HISTORY_URL = "http://172.16.3.156/healthtime/Test/retrieve_parent_medical_history.php";
	private static final String RETRIEVE_SHARED_TO_DOCTOR_ACCOUNTS_URL = "http://172.16.3.156/healthtime/Test/retrieve_shared_to_doctor_accounts.php";
	private static final String RETRIEVE_FAMILY_URL = "http://172.16.3.156/healthtime/Test/retrieve_family.php";
	private static final String RETRIEVE_SHARED_FROM_FAMILY_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_shared_from_family_child.php";
	private static final String RETRIEVE_SHARED_TO_PARENT_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_shared_to_parent_child.php";
	private static final String RETRIEVE_DISEASE_URL = "http://172.16.3.156/healthtime/Test/retrieve_disease_dictionary.php";
	private static final String RETRIEVE_GALLERY_BY_PARENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_gallery_by_parent.php";
	private static final String RETRIEVE_GALLERY_BY_POST_URL = "http://172.16.3.156/healthtime/Test/retrieve_gallery_by_post.php";
	private static final String RETRIEVE_GALLERY_BY_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_gallery_by_child.php";
	private static final String RETRIEVE_GALLERY_LAST_UPLOAD_URL = "http://172.16.3.156/healthtime/Test/retrieve_gallery_last_upload.php";
	private static final String RETRIEVE_ALL_PARENT_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_parent.php";
	private static final String RETRIEVE_ALL_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_child.php";
	private static final String RETRIEVE_ALL_DOCTOR_URL = "http://172.16.3.156/healthtime/Test/retrieve_all_doctor.php";
	private static final String RETRIEVE_SHARING_BY_CHILD_URL = "http://172.16.3.156/healthtime/Test/retrieve_sharing_by_child.php";
	
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
		if(parent.getGender().equals("Male")){
			params.add(new BasicNameValuePair("gender", "1"));
		}
		else
			params.add(new BasicNameValuePair("gender", "2"));
		params.add(new BasicNameValuePair("email", parent.getEmail()));
		params.add(new BasicNameValuePair("blood_type",parent.getBloodType()));
		params.add(new BasicNameValuePair("password", parent.getPassword()));
		
		return client.makeHttpRequest(REGISTER_URL, "POST", params);
	}
	
	public String addEvent(Event event){

		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("to_parent_id", String.valueOf(event.getToParentId())));
		params.add(new BasicNameValuePair("from_parent_id", String.valueOf(event.getFromParentId())));
		params.add(new BasicNameValuePair("child_id", String.valueOf(event.getChildId())));
		params.add(new BasicNameValuePair("event_content", event.getEventContent()));
		params.add(new BasicNameValuePair("event_category", String.valueOf(event.getEventCategory())));
		params.add(new BasicNameValuePair("file_id", String.valueOf(event.getFileId())));
		return client.makeHttpRequest(EVENT_URL, "POST", params);
	}
	
	public String addPost(PostModel post){

		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("event_id", String.valueOf(post.getEventId())));
		params.add(new BasicNameValuePair("from_parent_id", String.valueOf(post.getFromParentId())));
		params.add(new BasicNameValuePair("post_content", post.getPostContent()));
		params.add(new BasicNameValuePair("file_id", String.valueOf(post.getFileId())));
		return client.makeHttpRequest(POST_URL, "POST", params);
	}
	
	public String addSharingDoctor(SharingDoctorModel doctor){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("doctor_id", String.valueOf(doctor.getDoctorId())));
		params.add(new BasicNameValuePair("parent_id", String.valueOf(doctor.getParentId())));
		params.add(new BasicNameValuePair("child_id", String.valueOf(doctor.getChildId())));
		
		return client.makeHttpRequest(ADD_SHARING_DOCTOR_URL, "POST", params);
		
	}
	
	public String addDoctor(DoctorModel doctor){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parent_id", String.valueOf(doctor.getParentId())));
		params.add(new BasicNameValuePair("specialty", doctor.getSpecialty()));
		params.add(new BasicNameValuePair("hospital", doctor.getHospital()));
		params.add(new BasicNameValuePair("hospital_address", doctor.getHospitalAddress()));
		params.add(new BasicNameValuePair("consultation", doctor.getConsultation()));
		params.add(new BasicNameValuePair("contact1", doctor.getContact1()));
		params.add(new BasicNameValuePair("contact2", doctor.getContact2()));
		
		return client.makeHttpRequest(ADD_DOCTOR_URL, "POST", params);
		
	}
	
	public String addComment(CommentModel comment){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parent_id", String.valueOf(comment.getParentId())));
		params.add(new BasicNameValuePair("post_id", String.valueOf(comment.getPostId())));
		params.add(new BasicNameValuePair("comment_content", comment.getCommentContent()));
		
		return client.makeHttpRequest(ADD_COMMENT_URL, "POST", params);
		
	}
	
	public String addMedicalHistory(ParentSicknessModel medicalHistory){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parent_id", String.valueOf(medicalHistory.getParentId())));
		params.add(new BasicNameValuePair("anemia", String.valueOf(medicalHistory.getAnemia())));
		params.add(new BasicNameValuePair("asthma", String.valueOf(medicalHistory.getAsthma())));
		params.add(new BasicNameValuePair("bleeding_disorder", String.valueOf(medicalHistory.getBleedingDis())));
		params.add(new BasicNameValuePair("diabetes", String.valueOf(medicalHistory.getDiabetes())));
		params.add(new BasicNameValuePair("epilepsy", String.valueOf(medicalHistory.getEpilepsy())));
		params.add(new BasicNameValuePair("heart_disorder", String.valueOf(medicalHistory.getHeartDis())));
		params.add(new BasicNameValuePair("high_blood", String.valueOf(medicalHistory.getHighBlood())));
		params.add(new BasicNameValuePair("high_cholesterol", String.valueOf(medicalHistory.getHighCho())));
		params.add(new BasicNameValuePair("liver_disorder", String.valueOf(medicalHistory.getLiverDis())));
		params.add(new BasicNameValuePair("kidney_disorder", String.valueOf(medicalHistory.getKidneyDis())));
		params.add(new BasicNameValuePair("nasal_allergy", String.valueOf(medicalHistory.getNasalAll())));
		params.add(new BasicNameValuePair("tuberculosis", String.valueOf(medicalHistory.getTuberculosis())));
		
		return client.makeHttpRequest(ADD_MEDICAL_HISTORY_URL, "POST", params);
		
	}
	
	@SuppressWarnings("deprecation")
	public void addGallery(GalleryModel onegallery){
		
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		DataInputStream inStream = null; 

		int parentid = onegallery.getParentId();
		String exsistingFileName = onegallery.getFilename();

		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary =  "*****";

		int bytesRead, bytesAvailable, bufferSize;

		byte[] buffer;

		int maxBufferSize = 1*1024*1024;

		try {

			FileInputStream fileInputStream = new FileInputStream(new File(exsistingFileName) );

			URL url = new URL(UPLOAD_IMAGE);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

			dos = new DataOutputStream( conn.getOutputStream() );

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"parent_id\"" + lineEnd);
			dos.writeBytes(lineEnd);
	        dos.writeBytes(String.valueOf(parentid));
	        dos.writeBytes(lineEnd);
	        dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"uploadfile\";filename=\"" + exsistingFileName +"\"" + lineEnd);
			dos.writeBytes(lineEnd);

			Log.e("MediaPlayer","Headers are written");

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			dos.writeBytes(lineEnd);

			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			fileInputStream.close();
			dos.flush();
			dos.close();
		}
		catch (MalformedURLException ex) {
		}
		catch (IOException ioe) {
		}

		try {
			inStream = new DataInputStream ( conn.getInputStream() );
			String str;

			while (( str = inStream.readLine()) != null) {
				Log.e("MediaPlayer","Server Response: "+str);
			}
			inStream.close();
		}
		catch (IOException ioex){
		}
		
	}
	
	public String addFamily(FamilyModel family){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("father_id", String.valueOf(family.getFatherId())));
		params.add(new BasicNameValuePair("mother_id", String.valueOf(family.getMotherId())));
		
		return client.makeHttpRequest(ADD_FAMILY_URL, "POST", params);
		
	}
	
	public String addSharing(SharingModel sharing){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("to_parent_id", String.valueOf(sharing.getToParentId())));
		params.add(new BasicNameValuePair("from_family_id", String.valueOf(sharing.getFromFamilyId())));
		params.add(new BasicNameValuePair("child_id", String.valueOf(sharing.getChildId())));
		params.add(new BasicNameValuePair("privilege", String.valueOf(sharing.getPrivilege())));
		
		return client.makeHttpRequest(ADD_SHARING_URL, "POST", params);
		
	}
	
	public String addChild(ChildModel child){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("first_name", child.getFirstName()));
		params.add(new BasicNameValuePair("last_name", child.getLastName()));
		if(child.getGender().equals("Male")){
			params.add(new BasicNameValuePair("gender", "1"));
		}
		else
			params.add(new BasicNameValuePair("gender", "2"));
		params.add(new BasicNameValuePair("birthdate", child.getBirthdate()));
		params.add(new BasicNameValuePair("blood_type", child.getBloodType()));
		params.add(new BasicNameValuePair("family_id", String.valueOf(child.getFamilyId())));
		
		return client.makeHttpRequest(ADD_CHILD_URL, "POST", params);
		
	}
	
	public String addDisease(DiseaseDictionaryModel disease){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("disease_dictionary_id", String.valueOf(disease.getDiseaseDictionaryId())));
		params.add(new BasicNameValuePair("name", disease.getName()));
		params.add(new BasicNameValuePair("description", disease.getDescription()));
		params.add(new BasicNameValuePair("symptom", disease.getSymptom()));
		params.add(new BasicNameValuePair("treatment", disease.getTreatment()));
		
		return client.makeHttpRequest(ADD_DISEASE_URL, "POST", params);
		
	}
	
	//End of Add Methods
	
	//--------------------
	
	//Delete Methods
	
	public String deleteChild(ChildModel child){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("child_id", String.valueOf(child.getChildId())));
		params.add(new BasicNameValuePair("first_name", child.getFirstName()));
		params.add(new BasicNameValuePair("last_name", child.getLastName()));
		params.add(new BasicNameValuePair("gender", child.getGender()));
		params.add(new BasicNameValuePair("birthdate", child.getBirthdate()));
		params.add(new BasicNameValuePair("blood_type", child.getBloodType()));
		params.add(new BasicNameValuePair("family_id", String.valueOf(child.getFamilyId())));
		
		return client.makeHttpRequest(DELETE_CHILD_URL, "POST", params);
	}
	
	public String deleteComment(CommentModel comment){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("comment_id", String.valueOf(comment.getCommentId())));
		params.add(new BasicNameValuePair("parent_id", String.valueOf(comment.getParentId())));
		params.add(new BasicNameValuePair("post_id", String.valueOf(comment.getPostId())));
		params.add(new BasicNameValuePair("comment_date", comment.getCommentDate()));
		params.add(new BasicNameValuePair("comment_content", comment.getCommentContent()));
		
		return client.makeHttpRequest(DELETE_COMMENT_URL, "POST", params);

	}
	
	public String deleteDoctor(DoctorModel doctor){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("doctor_id", String.valueOf(doctor.getDoctorId())));
		params.add(new BasicNameValuePair("parent_id", String.valueOf(doctor.getParentId())));
		params.add(new BasicNameValuePair("specialty", doctor.getSpecialty()));
		params.add(new BasicNameValuePair("hospital", doctor.getHospital()));
		params.add(new BasicNameValuePair("hospital_address", doctor.getHospitalAddress()));
		params.add(new BasicNameValuePair("consultation", doctor.getConsultation()));
		params.add(new BasicNameValuePair("contact1", doctor.getContact1()));
		params.add(new BasicNameValuePair("contact2", doctor.getContact2()));
		
		return client.makeHttpRequest(DELETE_DOCTOR_URL, "POST", params);
	}
	
	public String deletePost(PostModel post){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("post_id", String.valueOf(post.getPostId())));
		params.add(new BasicNameValuePair("from_parent_id", String.valueOf(post.getFromParentId())));
		params.add(new BasicNameValuePair("post_content", post.getPostContent()));
		params.add(new BasicNameValuePair("file_id", String.valueOf(post.getFileId())));
		
		return client.makeHttpRequest(DELETE_POST_URL, "POST", params);
	}
	
	public String deleteSharing(SharingModel sharing){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sharing_id", String.valueOf(sharing.getSharingId())));
		params.add(new BasicNameValuePair("to_parent_id", String.valueOf(sharing.getToParentId())));
		params.add(new BasicNameValuePair("from_family_id", String.valueOf(sharing.getFromFamilyId())));
		params.add(new BasicNameValuePair("child_id", String.valueOf(sharing.getChildId())));
		params.add(new BasicNameValuePair("privilege", String.valueOf(sharing.getPrivilege())));
		
		return client.makeHttpRequest(DELETE_SHARING_URL, "POST", params);
		
	}
	
	public String deleteSharingDoctor(SharingDoctorModel doctor){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sharing_doctor_id", String.valueOf(doctor.getSharingDoctorId())));
		params.add(new BasicNameValuePair("doctor_id", String.valueOf(doctor.getDoctorId())));
		params.add(new BasicNameValuePair("parent_id", String.valueOf(doctor.getParentId())));
		params.add(new BasicNameValuePair("child_id", String.valueOf(doctor.getChildId())));
		
		return client.makeHttpRequest(DELETE_SHARING_DOCTOR_URL, "POST", params);
	}
	
	public String deleteDisease(DiseaseDictionaryModel disease){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("disease_dictionary_id", String.valueOf(disease.getDiseaseDictionaryId())));
		params.add(new BasicNameValuePair("name", disease.getName()));
		params.add(new BasicNameValuePair("description", disease.getDescription()));
		params.add(new BasicNameValuePair("symptom", disease.getSymptom()));
		params.add(new BasicNameValuePair("treatment", disease.getTreatment()));
		
		return client.makeHttpRequest(DELETE_DISEASE_URL, "POST", params);
		
	}
	
	//End of Delete Methods
	
	//----------------------
	
	//Edit Methods
	
	public String editSharing(SharingModel sharing){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sharing_id", String.valueOf(sharing.getSharingId())));
		params.add(new BasicNameValuePair("privilege", String.valueOf(sharing.getPrivilege())));
		
		return client.makeHttpRequest(EDIT_SHARING_URL, "POST", params);
		
	}
	
	public String editDoctor(DoctorModel doctor){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("doctor_id", String.valueOf(doctor.getDoctorId())));
		params.add(new BasicNameValuePair("parent_id", String.valueOf(doctor.getParentId())));
		params.add(new BasicNameValuePair("specialty", doctor.getSpecialty()));
		params.add(new BasicNameValuePair("hospital", doctor.getHospital()));
		params.add(new BasicNameValuePair("hospital_address", doctor.getHospitalAddress()));
		params.add(new BasicNameValuePair("consultation", doctor.getConsultation()));
		params.add(new BasicNameValuePair("contact1", doctor.getContact1()));
		params.add(new BasicNameValuePair("contact2", doctor.getContact2()));
		
		return client.makeHttpRequest(EDIT_DOCTOR_URL, "POST", params);
	}
	
	public String editMedicalHistory(ParentSicknessModel medicalHistory){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("medical_history_id", String.valueOf(medicalHistory.getParentSicknessId())));
		params.add(new BasicNameValuePair("parent_id", String.valueOf(medicalHistory.getParentId())));
		params.add(new BasicNameValuePair("anemia", String.valueOf(medicalHistory.getAnemia())));
		params.add(new BasicNameValuePair("asthma", String.valueOf(medicalHistory.getAsthma())));
		params.add(new BasicNameValuePair("bleeding_disorder", String.valueOf(medicalHistory.getBleedingDis())));
		params.add(new BasicNameValuePair("diabetes", String.valueOf(medicalHistory.getDiabetes())));
		params.add(new BasicNameValuePair("epilepsy", String.valueOf(medicalHistory.getEpilepsy())));
		params.add(new BasicNameValuePair("heart_disorder", String.valueOf(medicalHistory.getHeartDis())));
		params.add(new BasicNameValuePair("high_blood", String.valueOf(medicalHistory.getHighBlood())));
		params.add(new BasicNameValuePair("high_cholesterol", String.valueOf(medicalHistory.getHighCho())));
		params.add(new BasicNameValuePair("liver_disorder", String.valueOf(medicalHistory.getLiverDis())));
		params.add(new BasicNameValuePair("kidney_disorder", String.valueOf(medicalHistory.getKidneyDis())));
		params.add(new BasicNameValuePair("nasal_allergy", String.valueOf(medicalHistory.getNasalAll())));
		params.add(new BasicNameValuePair("tuberculosis", String.valueOf(medicalHistory.getTuberculosis())));
		
		return client.makeHttpRequest(EDIT_MEDICAL_HISTORY_URL, "POST", params);
	}
	
	public String editDisease(DiseaseDictionaryModel disease){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("disease_dictionary_id", String.valueOf(disease.getDiseaseDictionaryId())));
		params.add(new BasicNameValuePair("name", disease.getName()));
		params.add(new BasicNameValuePair("description", disease.getDescription()));
		params.add(new BasicNameValuePair("symptom", disease.getSymptom()));
		params.add(new BasicNameValuePair("treatment", disease.getTreatment()));
		
		return client.makeHttpRequest(EDIT_DISEASE_URL, "POST", params);
		
	}
	
	public String editParentPhoto(ParentModel parent){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("parent_id", String.valueOf(parent.getParentId())));
		params.add(new BasicNameValuePair("parent_image_id", String.valueOf(parent.getParentImageId())));
		
		return client.makeHttpRequest(EDIT_PARENT_PHOTO_URL, "POST", params);
	}
	
	public String editFamily(FamilyModel family){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("family_id", String.valueOf(family.getFamilyId())));
		params.add(new BasicNameValuePair("father_id", String.valueOf(family.getFatherId())));
		params.add(new BasicNameValuePair("mother_id", String.valueOf(family.getMotherId())));
		
		return client.makeHttpRequest(EDIT_FAMILY_URL, "POST", params);
	}
	
	//End of Edit Methods
	
	//----------------------
	
	//Retrieve Methods
	
	public String retrieve_all_hashtags(){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		return client.makeHttpRequest(HASHTAG_URL, "GET", params);
		
	}
	
	public String retrieve_disease_dictionary(){
		
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		return client.makeHttpRequest(RETRIEVE_DISEASE_URL, "GET", params);
		
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
	
	public String retrieve_all_event(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_ALL_EVENT_URL, "GET", params);
	}
	
	public String retrieve_all_post_by_event(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_ALL_POST_BY_EVENT_URL, "GET", params);
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
	
	public String retrieve_doctor_by_parent(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_DOCTOR_BY_PARENT_URL, "GET", params);
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
	
	public String retrieve_family(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_FAMILY_URL, "GET", params);
	}
	
	public String retrieve_shared_from_family_child(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_SHARED_FROM_FAMILY_CHILD_URL, "GET", params);
	}
	
	public String retrieve_shared_to_parent_child(int family_id, int shared_parent_id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("family_id", String.valueOf(family_id)));
		params.add(new BasicNameValuePair("shared_parent_id", String.valueOf(shared_parent_id)));
		
		return client.makeHttpRequest(RETRIEVE_SHARED_TO_PARENT_CHILD_URL, "GET", params);
	}
	
	public String retrieve_gallery_by_post(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_GALLERY_BY_POST_URL, "GET", params);
	}
	
	public String retrieve_gallery_by_parent(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_GALLERY_BY_PARENT_URL, "GET", params);
	}
	
	public String retrieve_gallery_by_child(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_GALLERY_BY_CHILD_URL, "GET", params);
	}
	
	public String retrieve_gallery_last_upload(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_GALLERY_LAST_UPLOAD_URL, "GET", params);
	}
	
	public String retrieve_all_parent(){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		return client.makeHttpRequest(RETRIEVE_ALL_PARENT_URL, "GET", params);
	}
	
	public String retrieve_all_child(){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		return client.makeHttpRequest(RETRIEVE_ALL_CHILD_URL, "GET", params);
	}
	
	public String retrieve_all_doctor(){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		return client.makeHttpRequest(RETRIEVE_ALL_DOCTOR_URL, "GET", params);
	}
	
	public String retrieve_sharing_by_child(int id){
		HttpResponseClient client = new HttpResponseClient();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", String.valueOf(id)));
		
		return client.makeHttpRequest(RETRIEVE_SHARING_BY_CHILD_URL, "GET", params);
	}
	
//End of Retrieve Methods
}
