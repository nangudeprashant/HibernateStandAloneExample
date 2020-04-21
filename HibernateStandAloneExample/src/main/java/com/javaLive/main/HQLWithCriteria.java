package com.javaLive.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.javaLive.databaseUtil.HibernateUtil;
import com.javaLive.entity.Student;

/**
 * @author JavaLive.com
 * Please refer CriteriaRestrictionOrderNotes.doc file for more explanation.
 */
public class HQLWithCriteria {

	public void getStudentListGreaterThanCriteria() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Criteria c=session.createCriteria(Student.class);  
			c.add(Restrictions.gt("id",110));//Use of Restriction class
			List<Student> list=c.list();  
			for (Student s : list) {
				System.out.println(s.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public void getStudentListWithAscendingOrderOfName() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Criteria c=session.createCriteria(Student.class);  
			c.addOrder(Order.asc("name"));
			List<Student> list=c.list();  
			for (Student s : list) {
				System.out.println(s.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	public static void main(String[] args) {
		HQLWithCriteria obj=new HQLWithCriteria();
		//obj.getStudentListGreaterThanCriteria();
		obj.getStudentListWithAscendingOrderOfName();
	}
	
}
