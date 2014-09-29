package com.gtcafe.sdna.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface About {
	
	//
	String FULL_NAME 	= "SDNA Lab - Core";
	String VERSION 		= "v0.0.4.1";
	String BUILD_ID 	= new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	String BUILD_TYPE 	= "daily";

	String AUTHOR_FULL_NAME = "Rick KY Hwang";
	String CONTACT_EMAIL 	= "rick_kyhwang@hotmail.com";
	String WEBSITE_URL 		= "http://rickmidi.blogspot.com";
	
	String ABOUT_THIS_SOFTWARE = String.format("%s %s (%s, %s)",
			FULL_NAME, VERSION, BUILD_ID, BUILD_TYPE
		);
	String ABOUT_AUTHOR = String.format("Author: %s <%s>\n%s",
			AUTHOR_FULL_NAME, CONTACT_EMAIL, WEBSITE_URL
		);
}
