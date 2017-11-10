import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class LoginGenerator2 {

    public static void main(String[] args) {

        String[] studentNames = { "Budynek Piotr", 
                                  "Chalupa Krystyna", 
                                  "Wiezowiec Jan",
                                  "Szkieletor Andrzej",
                                  "Domek Marianna" };
		String domain = "@student.agh.edu.pl";
		String[] mailAddressses = new String[studentNames.length];
		String[][] studentsNamesSplit = new String[studentNames.length][2];
		
		Pattern wzor = Pattern.compile("(..)[^ ]* (...).*");
		Matcher wynik;
		
		for (int i=0; i<studentNames.length; i++){
			wynik = wzor.matcher(studentNames[i].toLowerCase());
			if (wynik.matches()){
				mailAddressses[i]=wynik.group(2)+wynik.group(1)+domain;
			}else{
				System.out.print("error");
			}	
		}
		
		for (int i=0; i<mailAddressses.length; i++){
			System.out.println(mailAddressses[i]);
		}
    }
}