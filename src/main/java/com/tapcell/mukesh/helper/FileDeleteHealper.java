package com.tapcell.mukesh.helper;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Component;

@Component
public class FileDeleteHealper {
	@Value("${project.image}")
	private String DELETE_DIR;
//	String DELETE_DIR = null;
//	try {
//		DELETE_DIR = new ClassPathResource("/").getFile().getAbsolutePath();
//	} catch (Exception e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
	
	@Transactional
	public void deleteProductImage(String string)
    {

		string = string.substring(string.indexOf("filepath=")+9);
		System.out.println(string);
		System.out.println("hello");
		Path path = Paths.get(DELETE_DIR+File.separator+string);
		
         try { 
        	 
             
             if(Files.deleteIfExists(path)) { 
                System.out.println( " is deleted!");
             } else {
                System.out.println("Delete operation is failed.");
                }
          }
            catch(Exception e)
            {
                System.out.println("Failed to Delete image !!");
            }
    }

	
	@Transactional
    public void deleteNoticePdf(String string)
    {
		string = string.substring(string.indexOf("filepath=")+9);
		System.out.println(string);
		Path path = Paths.get(DELETE_DIR+File.separator+string);
         try { 
        	 
             
             if(Files.deleteIfExists(path)) { 
                System.out.println( " is deleted!");
             } else {
                System.out.println("Delete operation is failed.");
                }
          }
            catch(Exception e)
            {
                System.out.println("Failed to Delete Notice !!");
            }
    }
}
