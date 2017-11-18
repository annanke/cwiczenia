import java.util.Scanner;
public class Zad4{
// program podaje dzielniki liczby naturalnej
	public static void main(String[] args){
		System.out.println("program podaje dzielniki zadanej liczby naturalnej");
		String confirm = "new";
		Scanner scanner = new Scanner(System.in);
		do{
			confirm = "new";
			int myNr=readNumber();
			System.out.println("dzelniki liczby "+myNr+" to:");
			printDividers(findDividers(myNr));
			System.out.println(myNr);
			while(confirm.toLowerCase().equals("y")==false && confirm.toLowerCase().equals("n")==false){
				System.out.print("czy chcesz sprawdzic inna liczbe? [y/n]");
				confirm = scanner.next();
			}
		}while(confirm.toLowerCase().equals("y"));
	}
	public static int readNumber(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Podaj liczbe naturalna: ");
		int readNr = scanner.nextInt();
		while (readNr<=0){
			System.out.println("Podana liczba nie jest naturalna");
			System.out.print("Podaj liczbe naturalna: ");
			readNr = scanner.nextInt();
		}
		return readNr;
	}

	public static int[] findDividers(int numberDivided){
		int[] dividers;
		if (numberDivided==1){
			dividers = new int[]{0};
		}else{
			int arrayLength=(numberDivided/2);
			dividers = new int[arrayLength];
			for (int i=0; i<arrayLength;i++){
				dividers[i]=0;
			}
			for (int i=0; i<arrayLength;i++){
				if (checkDivider(numberDivided, i+1)){
					dividers[i]=i+1;
				}
			}
		}
		return dividers;
	}
	
	public static boolean checkDivider(int number, int divider){
			return (number%divider==0);	
	}
	
	public static void printDividers(int[] dividers){
		for(int i=0; i<dividers.length;i++){
			if(dividers[i]!=0){
				System.out.print(dividers[i]+", ");
			}
		}
	}

}