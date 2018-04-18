package pl.edu.agh.ki.mwo.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.edu.agh.ki.mwo.model.School;
import pl.edu.agh.ki.mwo.model.SchoolClass;
import pl.edu.agh.ki.mwo.model.Student;

public class DatabaseConnector {

	protected static DatabaseConnector instance = null;

	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}

	Session session;

	protected DatabaseConnector() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void teardown() {
		session.close();
		HibernateUtil.shutdown();
		instance = null;
	}

	public Iterable<School> getSchools() {

		String hql = "FROM School";
		Query query = session.createQuery(hql);
		List schools = query.list();

		return schools;
	}

	public School getSchool(String schoolId) {
		String hql = "FROM School S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		return results.get(0);
	}

	public void addSchool(School school) {
		Transaction transaction = session.beginTransaction();
		session.save(school);
		transaction.commit();
	}

	public void deleteSchool(String schoolId) {
		String hql = "FROM School S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (School s : results) {
			session.delete(s);
		}
		transaction.commit();
	}

	public void editSchool(String schoolId, String name, String address) {
		String hql = "FROM School S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (School s : results) {
			s.setName(name);
			s.setAddress(address);
			session.update(s);
		}
		transaction.commit();
	}

	public Iterable<SchoolClass> getSchoolClasses() {

		String hql = "FROM SchoolClass";
		// String hql = "FROM SchoolClass as sc join sc.school as school";
		Query query = session.createQuery(hql);
		List schoolClasses = query.list();

		return schoolClasses;
	}

	public void addSchoolClass(SchoolClass schoolClass, String schoolId) {
		/*
		 * Transaction transaction = session.beginTransaction();
		 * session.save(schoolClass);
		 */
		String hql = "FROM School S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(schoolClass);
		} else {
			School school = results.get(0);
			school.addClass(schoolClass);
			session.save(school);
		}
		transaction.commit();
	}

	public void deleteSchoolClass(String schoolClassId) {
		String hql = "FROM SchoolClass S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<SchoolClass> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (SchoolClass s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
	public void editSchoolClass(String schoolClassId, String schoolClassProfile, String schoolClassStartYear,
			String schoolClassCurrentYear) {
		String hql = "FROM SchoolClass S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<SchoolClass> results = query.list();
		//String hqlSchool = "FROM School S WHERE S.id=" + schoolId;
		//Query querySchool = session.createQuery(hqlSchool);
		//List<School> schoolResults = querySchool.list();
		Transaction transaction = session.beginTransaction();
		for (SchoolClass s : results) {
			s.setProfile(schoolClassProfile);
			s.setStartYear(Integer.valueOf(schoolClassStartYear));
			s.setCurrentYear(Integer.valueOf(schoolClassCurrentYear));
			//s.setSchool(schoolResults.get(0));
			session.update(s);
		}
		transaction.commit();
		
	}

	public Iterable<Student> getStudents() {
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		List students = query.list();

		return students;
	}



}
