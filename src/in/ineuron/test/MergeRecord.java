package in.ineuron.test;

import java.io.IOException;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Employee;
import in.ineuron.util.HibernateUtil;

public class MergeRecord {

	public static void main(String[] args) throws IOException {

		Session session = null;
		Employee employee = null;
		boolean flag = false;
		Transaction transaction = null;
		Employee employee3 = null;
		
		try {

			
			session = HibernateUtil.getSession();
			
			if(session!=null) {
				 
				 transaction = session.beginTransaction();
				 
				  employee = new Employee();
				  	employee.setEname("Sagar B V");
					employee.setDob(LocalDate.of(2000, 06, 22));
					employee.setEcompany("EY");
					employee.setDoj(LocalDate.of(2023, 04, 27));
					employee.setEcity("Blr");
					employee3 = (Employee) session.merge(employee);
					flag = true;
				 
			}
			
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		
		finally {
			if(flag) {
				transaction.commit();
				System.out.println(employee3);
			}
			else {
				transaction.commit();
				System.out.println("Failed");
			}
		}
		
	}

}
