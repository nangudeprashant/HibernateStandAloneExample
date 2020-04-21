package com.javaLive.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.javaLive.databaseUtil.HibernateUtil;
import com.javaLive.entity.Student;

public class HQLExample {

	//////PART 1 select query examples
	
	public void selectAllStudents() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Student> students = session.createQuery("from Student", Student.class).list();
			for (Student s : students) {
				System.out.println(s.toString());
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectStudentRange() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query query = session.createQuery("from Student", Student.class);// here persistent class name is Student
			query.setFirstResult(5);
			query.setMaxResults(10);
			List<Student> students = query.list();// will return the records from 5 to 10th number
			for (Student s : students) {
				System.out.println(s.toString());
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//////PART 2 aggregate function example
	
	
	public void aggregateFunctionDemo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List result = session.createQuery("select count(id) from Student").list();
			System.out.println("=================>Student table has "+result.get(0)+" records.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//////PART 3 DML Operations example
	
	
	
	/* HQL dosen't support direct insert statement. We have achieve this task with
	 * the help of POJO.save() method.
	 
	 * Insert into clause of hibernate query language support where records can be
	 * inserted from one object to another object.
	 
	 * Syntax:- String hql = "INSERT INTO Person(firstName, lastName, salary)" +
	 * "SELECT firstName, lastName, salary FROM old_person"; Query query =
	 * session.createQuery(hql); int result = query.executeUpdate();
	 * System.out.println("Rows Affected: " + result);
	 */

	public void updateStudent(int id, String newName) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Student set name= :name where id= :id");
			query.setParameter("id", id);
			query.setParameter("name", newName);
			int result = query.executeUpdate();
			// commit transaction
			transaction.commit();
			System.out.println("Rows Affected: " + result);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Student where id= :id");
			query.setParameter("id", id);
			int result = query.executeUpdate();
			// commit transaction
			transaction.commit();
			System.out.println("Rows Affected: " + result);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HQLExample obj = new HQLExample();
		System.out.println("\nStarting selectAllStudents method..........................");
		obj.selectAllStudents();
		System.out.println("\n\n\nStarting selectStudentRange method..........................");
		obj.selectStudentRange();
		System.out.println("\n\n\nStarting aggregateFunctionDemo method..........................");
		obj.aggregateFunctionDemo();
		System.out.println("\n\n\nStarting updateStudent method..........................");
		obj.updateStudent(101, "newName101");
		//obj.deleteStudent(234);
	}

}
