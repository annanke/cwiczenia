import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.*;

public class ZPliku{
	public static void main(String[] args) throws IOException{ //IOExcept - do FileWriter do File wystarczy FileNotFoundExcept
		File np = new File("ala.txt");
		Scanner read = new Scanner(np);
		String linia1 = read.nextLine();
		System.out.println(linia1);
		
		PrintWriter save = new PrintWriter("ala2.txt"); // tworzony jest nowy plik o podanej nazwie; jesli podamy istniejący - nadpiszemy go
		save.println("o ryba lubi plywac");
		save.close(); //wazne zeby zamykac strumienie wyjscia
		
		System.out.print("Podaj swoje imie: ");
		Scanner scanner = new Scanner(System.in);
		//String name = scanner.next();
		PrintWriter nameFile = new PrintWriter("myName.txt");
		nameFile.println(scanner.next());
		nameFile.close();
		//Scanner readName = new Scanner(new File("myName.txt")); // tu musimy podac bezposrednio plik, bo pod zmienna jest obiekt PrintWriter ktorego nei obsluzy konstruktor - tylko PLIK moze byc argumentem!!	
		File file =new File("myName.txt");
		Scanner readName = new Scanner(file);
		System.out.println("Twoje imie to: "+readName.nextLine());
		FileWriter writer = new FileWriter(file, true); // gdy podamy jako 2 argument true - bedize dopisywal do pliku a nei go nadpisywal
		writer.write("nowa linia");
		writer.flush();
		writer.close();
	}

}