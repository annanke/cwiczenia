import java.util.Scanner;
import java.time.LocalDate;
import java.util.Random;
import java.lang.Math;
public class Data{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Wybierz jedna z dostepnych opcji:\n1 - recznie podana data\n2 - losowa data");
		int menu = scanner.nextInt();
		LocalDate date = null;
		switch(menu){
			case 1:
				date = loadUserDate();
				System.out.println("data: "+date);
				break;
			case 2:
				date=loadRandomDate();
				System.out.println("data: "+date);
				break;
			default:
				System.out.println("you have chosen a wrong number");
				break;
		}
		System.out.println("the difference beetween this day and your data in years:");
		System.out.println(Math.abs(LocalDate.now().getYear()-date.getYear()));
	}	
	
	public static LocalDate loadUserDate(){
			Scanner scanner = new Scanner(System.in);
			int year;
			int month;
			int day;
			System.out.println("Please provide a year");
			year = scanner.nextInt();
			System.out.println("Please provide a month");
			month = scanner.nextInt();
			System.out.println("Please provide a day");
			day = scanner.nextInt();
//			System.out.println("data: "+day+"-"+month+"-"+year);
//			LocalDate date = LocalDate.of(year, month, day); // wywolanie statyczne na calej klasie localDate; of to funkcja
			
			return LocalDate.of(year, month, day);
	}
	public static LocalDate loadRandomDate(){
		int year;
		int month;
		int day;
		year = generateRandomNumber(1900, 2015);
		month = generateRandomNumber(1, 12);
		day = generateRandomNumber(1, 28);
//		LocalDate date = LocalDate.of(year, month, day);
		return LocalDate.of(year, month, day);
	}
	public static int generateRandomNumber(int from, int to){
		Random random = new Random();
		int range = to-from;
		int randomNr = random.nextInt(range+1);
		randomNr=randomNr+from;
		return randomNr;
	}
}