package com.javaLive.entity;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javaLive.databaseUtil.*;

public class App {
    public static void main(String[] args) {
        Student student = new Student(63,"Name61", "Address61");
        Student student1 = new Student(64,"Name62", "Address62");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
			for(Student s:students){
				System.out.println(s.toString());
			};
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}