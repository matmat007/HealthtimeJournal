package com.healthtimejournal.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Base64Decoder {
	
	public Bitmap decodeBase64(String input) {
	    byte[] decodedByte = Base64.decode(input, Base64.DEFAULT);
	    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
	}

}
