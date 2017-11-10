public class LoginGenerator {

    public static void main(String[] args) {

        String[] studentNames = { "Budynek Piotr", 
                                  "Chalupa Krystyna", 
                                  "Wiezowiec Jan",
                                  "Szkieletor Andrzej",
                                  "Domek Marianna" };
		String domain = "@student.agh.edu.pl";
		String[] mailAddressses = new String[studentNames.length];
		String[][] studentsNamesSplit = new String[studentNames.length][2];

		for (int i=0; i<studentNames.length; i++){
			studentsNamesSplit[i]=studentNames[i].split(" ");
			mailAddressses[i]=studentsNamesSplit[i][1].substring(0,3).toLowerCase()+studentNames[i].substring(0,2).toLowerCase()+domain;
		}
		
		for (int i=0; i<mailAddressses.length; i++){
			System.out.println(mailAddressses[i]);
		}
    }
}