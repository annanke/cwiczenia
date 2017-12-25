package am.j2.kalendarz;

import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
import java.time.LocalDate;

public class Calendar {
	private Scanner scanner;
	private int chosenDayIndex=0; 
	private int monthForListing=0;
	private int yearForListing=0;
	private int chosenYear=0;
	private String[] daysOfYear= new String[1];
	private String[][] years = new String[10][1];
	private int actualYear=LocalDate.now().getYear();
	private LocalDate actualYearJan= LocalDate.of(actualYear, 1, 1);
	
	//konstruktor zbuduj
	public Calendar(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void addEvent() {
		LocalDate chosenData=whichData(actualYearJan);
		chosenYear=chosenData.getYear();
		int daysInYear=daysInYear(chosenYear);
		int indexOfYear = chosenYear-actualYear;
		if(years[indexOfYear].length==1){
			daysOfYear = new String[daysInYear];
			years[indexOfYear]=daysOfYear;
		}
		chosenDayIndex = searchForDayOfYear(chosenYear, chosenData);
		System.out.println("Please provide an event: ");
		years[indexOfYear][chosenDayIndex]=readEvent(); // Dlaczego nie dziala z nextline?
	}
	
	public void filterEventOfMonth() {
		do{
			yearForListing=readNumber(actualYear+10, "For listing please provide a year in range of 10 years starting form "+actualYear+": ");
		}while(actualYear>yearForListing);					
	
		if(years[yearForListing-actualYear].length>1){
			monthForListing=readNumber(12, "Please provide a month for listing: [1-12] ");
			listEventsOfMonth(monthForListing, yearForListing, years, actualYear);
		}else{
			System.out.println("You don't have any events for this year yet");
		}
	}
	
	private int readNumber(int rangeTop, String toPrint){
		//Scanner scanner = new Scanner(System.in);
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
	
	private String readEvent(){
		//Scanner scanner = new Scanner(System.in);
		boolean ifNotCorrect =true;
		String readTxt=""; 
		while(ifNotCorrect==true){
			System.out.print("Provide a text of event: ");
			
			if(scanner.hasNext()==true){
				readTxt=scanner.nextLine();
				ifNotCorrect=false;
			}
		}
		return readTxt;
		//scanner.close();
	}
		
	private LocalDate whichData(LocalDate actualYearJan){
		LocalDate inTenYears = LocalDate.of(LocalDate.now().getYear()+10, 12, 31);
		LocalDate chosenData = null;
		do{
			System.out.println("Caledar works only for 10 next years. Please provide a data from this year or in next 10 years");
			int chosenYear=readNumber(inTenYears.getYear(), "which year? [yyyy]: ");
			int chosenMonth=readNumber(12, "which month (1-12)? [mm]: ");
			LocalDate dataToTestDaysNr = LocalDate.of(chosenYear, chosenMonth, 1);
			int chosenDay=readNumber(dataToTestDaysNr.lengthOfMonth(), "which day (1-"+dataToTestDaysNr.lengthOfMonth()+")? [dd]: "); // wprowadzic granice zalezna od miesiaca
			System.out.println("Yourdata: "+chosenYear+"-"+chosenMonth+"-"+chosenDay);
			chosenData = LocalDate.of(chosenYear, chosenMonth, chosenDay);
		}while(chosenData.isBefore(actualYearJan) || chosenData.isAfter(inTenYears));
		return chosenData;
	}
	
	private int daysInYear(int year){
		int daysInYear;
		if ((year%4==0 && year%100!=0) || (year%400==0)){
			daysInYear=366;
		}else{
			daysInYear=365;
		}return daysInYear;
	}
	
	private int searchForDayOfYear(int chosenYear, LocalDate chosenData){
		LocalDate firstJanOfChosenYear = LocalDate.of(chosenYear, 1, 1);
		long startDate = firstJanOfChosenYear.toEpochDay();
		long endDate = chosenData.toEpochDay();
		long diffLong = endDate-startDate;
		int diff = (int)diffLong;
		return diff;
	}
		
	private void listEventsOfMonth(int monthForListing, int yearForListing, String[][] years, int actualYear){
		boolean isAnythingAdded=false;
		LocalDate firstDayOfChosenMonth = LocalDate.of(yearForListing, monthForListing, 1);
		int daysInMonth=firstDayOfChosenMonth.lengthOfMonth();
		LocalDate lastDayOfChosenMonth = LocalDate.of(yearForListing, monthForListing, daysInMonth);
		int startingDayIndex=searchForDayOfYear(yearForListing, firstDayOfChosenMonth);
		int endingDayIndex=searchForDayOfYear(yearForListing, lastDayOfChosenMonth);
		System.out.println("listing events of "+monthForListing+"."+yearForListing+":");
		for (int i=startingDayIndex; i<=endingDayIndex; i++){
			if (years[yearForListing-actualYear][i]!=null){
				System.out.println("on "+LocalDate.ofYearDay(yearForListing, i+1)+" is: "+ years[yearForListing-actualYear][i]);
				isAnythingAdded=true;
			}
		}
		if (isAnythingAdded==false){
			System.out.println("There is no events in this month");
		}	
	}
	
	public void listAllEvents(){
		System.out.println("a list of all events:");
		for (int i=0; i<10; i++){
			for (int j=0; j<years[i].length;j++){
				if (years[i][j]!=null){
					System.out.println("on "+LocalDate.ofYearDay(actualYear+i, j+1)+" event: "+ years[i][j]);
				}
			}
		}
	}
}
