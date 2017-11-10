import java.util.Scanner;
public class SredniaOcen{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("podaj liczbę ocen ucznia: ");
		int marksNr = scanner.nextInt();
		
		int[] oceny = new int[marksNr];
		int sum=0;
		
		System.out.println("podaj oceny ucznia: ");
		
		for (int step=0; step<marksNr; step++){
			oceny[step]=scanner.nextInt();

		}
		
		int max=oceny[0];
		for (int step=1; step<marksNr; step++){
				if (oceny[step]>oceny[step-1]){
				max=oceny[step];
			}
		}
		
		System.out.println("Srednia ocen ucznia: "+average(oceny));
		System.out.println("najwyzsza ocena: " +max);
	}
	
	public static double average(int[] marks) {
		int sum = 0;
		for(int i=0; i<marks.length; i++) {
			sum += marks[i];
		}
		return sum/(double)marks.length;
	}
}