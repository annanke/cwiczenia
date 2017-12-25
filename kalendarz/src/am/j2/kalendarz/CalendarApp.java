package am.j2.kalendarz;

//import java.time.LocalDate;
import java.util.Scanner;

public class CalendarApp {
	//public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args){
		int menu;
		boolean isContinue=true;
		
		System.out.println("Hi! thank you for using our calendar!");
		Calendar calendar = new Calendar();
		do{
			menu=readNumber(4, "Please choose the option\n1 - add new event; 2 - find events of month; 3 - list all events; 4 - close calendar : ");
			switch(menu){
				case 1: //add an event
					calendar.addEvent();
					break;
					
				case 2: //filter events of specified month
					calendar.filterEventOfMonth();
					break;
				
				case 3: //listing all events in a Calendar
					calendar.listAllEvents();
					break;
									
				case 4: //exit
					System.out.println("Bye!");
					isContinue=false;
					break;
					
				default:
					System.out.println("wrong value");
			}
		}while(isContinue);
		//scanner.close();
	}
	public static int readNumber(int rangeTop, String toPrint){
		Scanner scanner = new Scanner(System.in);
		boolean ifNotCorrect =true;
		int readNr=0; 
		while(ifNotCorrect==true){
			System.out.print(toPrint);
			
			if(scanner.hasNextInt()==true){
				readNr=scanner.nextInt();
				ifNotCorrect=false;
				if((readNr<=0 || readNr>rangeTop)){
					System.out.println("you have to type a number from a correct range");
					ifNotCorrect=true;
				}
			}else{
				System.out.println("you have to type a number");
				ifNotCorrect=true;
				scanner.next();
			}
		//scanner.close();
		}return readNr;
	}
}
