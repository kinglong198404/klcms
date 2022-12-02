package com.klfront.klcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class WebMainApplication {

	public static void main(String[] args) {
//		String[] paths = System.getProperty("java.ext.dirs").split(";");
//		System.out.println("===================================================");
//		for (String classPath : paths){
//            System.out.println(classPath);
//        }
		SpringApplication.run(WebMainApplication.class, args);
	}
}
