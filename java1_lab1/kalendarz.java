import java.util.Scanner;
import java.util.Calendar; 
import java.util.GregorianCalendar;  
import java.util.concurrent.TimeUnit;

public class kalendarz{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int menu;
		int chosenDayIndex=0; 
		int monthForListing=0;
		int yearForListing=0;
		int chosenYear=0;
		boolean isContinue=true;
		String[] daysOfYear= new String[1];
		String[][] years = new String[10][1];
		GregorianCalendar actualYearJan= new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 0, 1);
		int actualYear=actualYearJan.get(Calendar.YEAR);
		
		System.out.println("Hi! thank you for using our calendar!");
		do{
			menu=readNumber(4, "Please choose the option\n1 - add new event; 2 - find events of month; 3 - list all events; 4 - close calendar : ");
			switch(menu){
				case 1: //add an event
					GregorianCalendar chosenData=whichData();
					chosenYear=chosenData.get(Calendar.YEAR);
					int daysInYear=daysInYear(chosenYear);
					int indexOfYear = chosenYear-actualYear;
					if(years[indexOfYear].length==1){
						daysOfYear = new String[daysInYear];
						years[indexOfYear]=daysOfYear;
					}
					chosenDayIndex = whereToAddEvent(chosenYear, chosenData);
					System.out.println("Please provide an event: ");
					years[indexOfYear][chosenDayIndex]=scanner.nextLine();
					break;
					
				case 2: //filter events of specified month
					do{
						yearForListing=readNumber(actualYear+10, "For listing please provide a year in range of 10 years starting form "+actualYear+": ");
					}while(actualYear>yearForListing);					

					if(years[yearForListing-actualYear].length>1){
						monthForListing=readNumber(12, "Please provide a month for listing: [1-12] ");
						listEvents(monthForListing, yearForListing, years, actualYear);
					}else{
						System.out.println("You don't have any events for this year yet");
					}
					break;
				
				case 3:
					listAllEvents();
					break;
									
				case 4:
					System.out.println("Bye!");
					isContinue=false;
					break;
					
				default:
					System.out.println("wrong value");
			}
		}while(isContinue);
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
		}return readNr;
	}
	
	public static GregorianCalendar whichData(){
		GregorianCalendar actualYearJan= new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 0, 1);
		GregorianCalendar inTenYears = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+10, 11, 31);
		GregorianCalendar chosenData = new GregorianCalendar();
		do{
			System.out.println("Caledar works only for 10 next years. Please provide a data from this year or in next 10 years");
			int chosenYear=readNumber(inTenYears.get(Calendar.YEAR), "which year? [yyyy]: ");
			int chosenMonth=readNumber(12, "which month (1-12)? [mm]: ");
			GregorianCalendar dataToTestDaysNr = new GregorianCalendar(chosenYear, chosenMonth-1, 1);
			int chosenDay=readNumber(dataToTestDaysNr.getActualMaximum(Calendar.DAY_OF_MONTH), "which day (1-"+dataToTestDaysNr.getActualMaximum(Calendar.DAY_OF_MONTH)+")? [dd]: "); // wprowadzic granice zalezna od miesiaca
			System.out.println("Yourdata: "+chosenYear+"-"+chosenMonth+"-"+chosenDay);
			chosenData = new GregorianCalendar(chosenYear, chosenMonth-1, chosenDay);
		}while(chosenData.before(actualYearJan) || chosenData.after(inTenYears));
		return chosenData;
	}
	
	public static int daysInYear(int year){
		int daysInYear;
		if ((year%4==0 && year%100!=0) || (year%400==0)){
			daysInYear=366;
		}else{
			daysInYear=365;
		}return daysInYear;
	}
	
	public static int whereToAddEvent(int chosenYear, GregorianCalendar chosenData){
		GregorianCalendar firstJanOfChosenYear = new GregorianCalendar(chosenYear, 0, 1);
		long startDate = firstJanOfChosenYear.getTimeInMillis();
		long endDate = chosenData.getTimeInMillis();
		long diffLong = TimeUnit.MILLISECONDS.toDays(endDate-startDate);
		int diff = (int)diffLong;
		return diff;
	}
		
	public static void listEvents(int monthForListing, int yearForListing, String[][] years, int actualYear){
		boolean isAnythingAdded=false;
		GregorianCalendar firstDayOfChosenMonth = new GregorianCalendar(yearForListing, monthForListing-1, 1);
		int daysInMonth=firstDayOfChosenMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
		GregorianCalendar lastDayOfChosenMonth = new GregorianCalendar(yearForListing, monthForListing-1, daysInMonth);
		int startingDayIndex=whereToAddEvent(yearForListing, firstDayOfChosenMonth);
		int endingDayIndex=whereToAddEvent(yearForListing, lastDayOfChosenMonth);
		System.out.println("listing events of "+monthForListing+"."+yearForListing+":");
		for (int i=startingDayIndex; i<=endingDayIndex; i++){
			if (years[yearForListing-actualYear][i]!=null){
				System.out.println("your event: "+ years[yearForListing-actualYear][i]);
				isAnythingAdded=true;
			}
		}
		if (isAnythingAdded==false){
			System.out.println("There is no events in this month");
		}	
	}
	
	public static void listAllEvents(){
		System.out.println("list all");
	}
}