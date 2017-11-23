import java.util.Scanner;
public class Zad4poprawki{
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
			while(!confirm.toLowerCase().equals("y") && !confirm.toLowerCase().equals("n")){
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
		int[] justDividers;
		int dividersAmount = 0;
		if (numberDivided==1){
			justDividers = new int[0];
		}else{
			int arrayLength=(numberDivided/2);
			dividers = new int[arrayLength];
			for (int i=0; i<arrayLength;i++){
				dividers[i]=0;
			}
			for (int i=0; i<arrayLength;i++){
				if (checkDivider(numberDivided, i+1)){
					dividers[i]=i+1;
					dividersAmount++;
				}
			}
			justDividers = new int[dividersAmount];
			int j=0;
			for(int i=0; i<dividers.length;i++){
				if(dividers[i]!=0){
					justDividers[j]=dividers[i];
					j++;
				}
			}
		}
		return justDividers;
	}
	
	public static boolean checkDivider(int number, int divider){
			return number%divider==0;	
	}
	
	public static void printDividers(int[] dividers){
		for(int i=0; i<dividers.length;i++){
			System.out.print(dividers[i]+", ");
		}
	}

}