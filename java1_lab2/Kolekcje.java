import java.util.ArrayList;
import java.util.HashSet;
public class Kolekcje{
	public static void main(String[] args){
		String[] names = new String[]{"Ala", "Ola", "Asia", "Basia", "Asia", "Ola", "Ela", "Ela", "Ewa", "Ala"};		
		ArrayList<String> nameList = new ArrayList<>();
		
		for (int i=0; i<names.length; i++){
			nameList.add(names[i]);
		}
		System.out.println("List");		
		for (String i : nameList){
			System.out.println(i);
		}
		System.out.println("trzeci element listy to: "+ nameList.get(2) );
		System.out.println("czy zawiera Basia? "+nameList.contains("Basia"));
		System.out.println("czy zawiera Kasia? "+nameList.contains("Kasia"));
		nameList.remove("Basia");
		System.out.println("czy wciaz zawiera Basia? "+nameList.contains("Basia"));
			
		HashSet<String> nameSet = new HashSet<>();
		for (int i=0; i<names.length; i++){
			nameSet.add(names[i]);
		}
		System.out.println("hashSet");			
		for (String i : nameSet){
			System.out.println(i);
		}
		System.out.println("czy zawiera Asia? "+nameSet.contains("Asia"));
		System.out.println("czy zawiera Kasia? "+nameSet.contains("Kasia"));
		nameSet.remove("Asia");
		System.out.println("czy wciaz zawiera Asia? "+nameSet.contains("Asia"));
	}
}