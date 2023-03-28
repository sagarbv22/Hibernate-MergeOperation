package in.ineuron.test;

import java.io.IOException;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Employee;
import in.ineuron.util.HibernateUtil;

public class LoadAndMerge {

	public static void main(String[] args) throws IOException {

		Session session = null;
		Employee employee2 = null;
		boolean flag = false;
		Transaction transaction = null;
		Employee employee3 = null;
		Employee employee1 = null;
		try {

			session = HibernateUtil.getSession();

			if (session != null) {
				employee1 = session.get(Employee.class, 1);
				System.out.println("After loading the record from DB" + employee1);
				transaction = session.beginTransaction();

				employee2 = new Employee();
				employee2.setEid(1);
				employee2.setEname("Soma Sundar");
				employee2.setDob(LocalDate.of(2000, 06, 9));
				employee2.setEcompany("Deloite");
				employee2.setDoj(LocalDate.of(2023, 04, 27));
				employee2.setEcity("Blr");
				employee3 = (Employee) session.merge(employee2);
				flag = true;

			}

		} catch (HibernateException e) {

			e.printStackTrace();
		}

		finally {
			if (flag) {
				transaction.commit();
				System.out.println(employee3);
				System.out.println(employee3.hashCode() + "=" + employee1.hashCode());
			} else {
				transaction.commit();
				System.out.println("Failed");
			}
		}

	}

}
