package com.javaLive.main;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.javaLive.databaseUtil.HibernateUtil;
import com.javaLive.entity.Student;

public class CallingStoredProcedureInHibernate {
	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Stored procedure query
			System.out.println("\n:::: Find student name ::::");
            
			StoredProcedureQuery studentName = session.createStoredProcedureQuery("getStudentName", Student.class);
			studentName.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
			studentName.registerStoredProcedureParameter("name", String.class, ParameterMode.OUT);

			int id = 101;
			studentName.setParameter("id", id);
            /*String name="";
            studentName.setParameter("name", name);*/
			studentName.execute();
			/*String studName = (String) studentName.getOutputParameterValue("name");
			System.out.println("Name of the employee with id " + id + " is " + studName);*/
			List dlist = (List) studentName.getResultList();
			System.out.println(dlist.toString());
			
		}
	}
}
