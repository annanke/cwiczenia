public class LoginGenerator3 {

    public static void main(String[] args) {

        String[] studentNames = { "Budynek Piotr", 
                                  "Chalupa Krystyna", 
                                  "Wiezowiec Jan",
                                  "Szkieletor Andrzej",
                                  "Domek Marianna" };
		String domain = "@student.agh.edu.pl";
		String[] mailAddressses = new String[studentNames.length];
		int indexSpacji;

		for (int i=0; i<studentNames.length; i++){
			indexSpacji=studentNames[i].indexOf(" ");
			mailAddressses[i]=studentNames[i].substring(indexSpacji+1,indexSpacji+3).toLowerCase()+studentNames[i].substring(0,2).toLowerCase()+domain;
		}
		
		for (int i=0; i<mailAddressses.length; i++){
			System.out.println(mailAddressses[i]);
		}
    }
}