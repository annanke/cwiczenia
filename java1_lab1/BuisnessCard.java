import java.util.Scanner;
import java.lang.Math;
public class BuisnessCard{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Podaj swoje imie: ");
		String name = scanner.next();
		
		System.out.println("Podaj swoje nazwisko: ");
		String surname = scanner.next();
		
		System.out.println("Podaj swoj nr tel: ");
		int tel = scanner.nextInt();
		
		System.out.println("Podaj miasto, w ktorym mieszkasz: ");
		String city = scanner.next();
		
		String contactData = "* "+"tel. "+tel+" adres: "+city;
		int contactLength = contactData.length();
		
		String nameData = "* "+name+" "+surname;
		int nameDataLength = nameData.length();
		
		String starLine = "";
		int starLineLength=Math.max(contactLength, nameDataLength);
		for (int step=1; step<=starLineLength+2; step++){
			starLine=starLine+"*";
		}
		
		String endLine="";
		int endLineLength=starLineLength-Math.min(contactLength, nameDataLength);
		for (int step=1; step<=endLineLength; step++){
			endLine=endLine+" ";
		}
		
		if (nameDataLength<contactLength){
			nameData=nameData+endLine;
		}else{
			contactData=contactData+endLine;
		}
		
		System.out.println(starLine);
		System.out.println(nameData+" *");
		System.out.println(contactData+" *");
		System.out.println(starLine);
	}
}