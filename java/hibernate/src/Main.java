import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		//main.addNewData();
		//main.printSchools();
		main.executeQueries();
		main.close();

	}

	private void executeQueries() {
		Transaction transaction = session.beginTransaction(); //2. - usuniecie wybranej szkoly
		String hqlDelete = "DELETE FROM School WHERE name='UE'";
		Query query = session.createQuery(hqlDelete);
		query.executeUpdate();
		transaction.commit();
		String hql = "FROM School"; // sprawdzam, ze usuniete zosta³o AE 
        Query query2 = session.createQuery(hql);
        List results = query2.list();
        System.out.println("Schools: "+results);
/*		String hql = "FROM School S WHERE S.name='UE'";
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (School s : results) {
			session.delete(s);
		}
		transaction.commit();*/
		
		
        String hqlc = "select count(distinct name) from School"; // 3. - tylko niepowtarzajace sie nazwy
      	Query query3 = session.createQuery(hqlc);
      	System.out.println("Schools amount: "+( (Integer) query3.iterate().next() ).intValue());
        
      	String countStudents = "select count(distinct pesel) from Student"; //4. - tylko niepowtarzajace sie 
      	Query query4 = session.createQuery(countStudents);
      	System.out.println("Number of students: "+ ( (Integer) query4.iterate().next() ).intValue());  
      	
/*      String schoolsWith2Calsses = "select sc from SchoolClass as sc"; 
      	Query query5 = session.createQuery(schoolsWith2Calsses);
      	List<SchoolClass> results5 = query5.list();
      	for (SchoolClass sc : results5){
      		System.out.println( sc.getProfile());
      	}
      	 results5.forEach(sc -> System.out.println(sc.getProfile()));

      	String schoolsWith2Calsses = "from (select sc.school, count(sc.profile) as profileAmount from SchoolClass as sc group by sc.school) where profileAmount >=2"; 
     */
		String schoolsWith2Calsses = "SELECT COUNT(S) FROM School S WHERE size(S.classes)>=2";
		Query query5 = session.createQuery(schoolsWith2Calsses);
		Integer schoolsProfileCount = (Integer) query5.uniqueResult();
		System.out.println("Schools profile count: " + schoolsProfileCount);
      	
      	
        String matFiz = "SELECT s FROM School s INNER JOIN s.classes classes WHERE classes.profile = 'mat-fiz' and classes.currentYear>=2"; 
      	Query query6 = session.createQuery(matFiz);
        List results6 = query6.list();
      	System.out.println("mat-fiz profile above 2nd year in: "+ results6 );
      	
      	Query query7 = session.createQuery("from School where id= :id");
      	query7.setLong("id", 3);
      	School school = (School) query7.uniqueResult();
		Transaction transaction7 = session.beginTransaction();
      	school.setAddress("ul. Slowackiego 15");
      	session.saveOrUpdate(school);
		transaction7.commit();
      

        
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}

	private void printSchools() {
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools and classes");
		for (School s : schools) {
			System.out.println(s);
			for (SchoolClass c : s.getClasses()) {
				System.out.println(c);  // w klasie jest zdef funkcja toString
				System.out.println("Students: "); 
				for (Student uczen : c.getStudents()) {
					System.out.println(uczen); 
				}
			}
		}
	}

	private void jdbcTest() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("org.sqlite.JDBC");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlite:school.db", "", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM schools";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("name");
				String address = rs.getString("address");

				// Display values
				System.out.println("Name: " + name);
				System.out.println(" address: " + address);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end jdbcTest
	
	private void addNewData() {
		School nowaSzkola = new School();
		//nowaSzkola.setId(3); //nie dajemy id
		nowaSzkola.setName("PAN");
		nowaSzkola.setAddress("Niezapomianjek 8");
		SchoolClass kataliza =new SchoolClass();
		SchoolClass adsorpcja =new SchoolClass();
		
		Student uczen1=new Student();
		Student uczen2=new Student();
		Student uczen3=new Student();
		//uczen1.setId(8);
		uczen1.setName("Janek");
		uczen1.setSurname("Kowal");
		uczen1.setPesel("123456789");
		
		uczen2.setName("Jan");
		uczen2.setSurname("Kowalny");
		uczen2.setPesel("123456781");
		
		uczen3.setName("Arek");
		uczen3.setSurname("Kowalski");
		uczen3.setPesel("123456785");
		
		
		kataliza.setProfile("kataliza");
		kataliza.setCurrentYear(3);
		kataliza.setStartYear(2017);
		Set<Student> studentsKataliza = new HashSet<>();
		kataliza.setStudents(studentsKataliza);
		studentsKataliza.add(uczen1);
		
		
		adsorpcja.setProfile("adsorpcja");
		adsorpcja.setCurrentYear(2);
		adsorpcja.setStartYear(2017);
		Set<Student> studentsAdsorpcja = new HashSet<>();
		adsorpcja.setStudents(studentsAdsorpcja);
		studentsAdsorpcja.add(uczen2);
		studentsAdsorpcja.add(uczen3);
		
		Set<SchoolClass> classesPAN = new HashSet<>();
		classesPAN.add(kataliza);
		classesPAN.add(adsorpcja);
		
		nowaSzkola.setClasses(classesPAN);
		
		Transaction transaction = session.beginTransaction();
		session.save(nowaSzkola); // gdzie newSchool to instancja nowej szko³y
		transaction.commit();
		
	}
}
