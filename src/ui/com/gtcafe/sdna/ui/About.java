package com.gtcafe.sdna.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public interface About {
	
	//
	String FULL_NAME 	= "SDNA Studio";
	String VERSION 		= "v0.0.3";
	String BUILD_ID 	= new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	String BUILD_LEVEL 	= "daily"; // stable, 

	String AUTHOR_FULL_NAME = "Rick KY Hwang";
	String CONTACT_EMAIL 	= "rick_kyhwang@hotmail.com";
	String WEBSITE_URL 		= "http://rickmidi.blogspot.com";
	
	String ABOUT_THIS_SOFTWARE = String.format("%s %s (b%s, %s)",
			FULL_NAME, VERSION, BUILD_ID, BUILD_LEVEL
		);
	
	String ABOUT_AUTHOR = String.format("Author: %s <%s>\n%s",
			AUTHOR_FULL_NAME, CONTACT_EMAIL, WEBSITE_URL
		);
}
