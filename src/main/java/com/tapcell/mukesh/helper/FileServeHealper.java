package com.tapcell.mukesh.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.springframework.stereotype.Component;

@Component
public class FileServeHealper {
	String SERVE_DIR = System.getProperty("user.dir")+File.separator+"images";

	public InputStream getResource(String path) {
		
		path = SERVE_DIR+File.separator + path;
		InputStream iS = null;
		try {
			iS = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iS;
	}
}
