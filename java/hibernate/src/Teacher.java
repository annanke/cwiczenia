import java.util.Set;

@SuppressWarnings("serial")
public class Teacher extends Person implements java.io.Serializable {

	private long id;
	private String name;
	private String surname;
	private Set<SchoolClass> classes;

	public Set<SchoolClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<SchoolClass> classes) {
		this.classes = classes;
	}

	public Teacher() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String toString() {
		return "Teacher: " + getName() + " " + getSurname();
}

}
