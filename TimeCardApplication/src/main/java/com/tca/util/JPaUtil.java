package com.tca.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
 
public class JPaUtil {
	
	public static EntityManager entMan;
	
	public static void createEM() {
		entMan = Persistence.createEntityManagerFactory("examplePU").createEntityManager();
	}

	public static void closeEM() {
		entMan.close(); 
		
	}
	

}
