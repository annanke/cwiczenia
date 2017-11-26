import java.util.HashMap;
import java.util.ArrayList;

public class Library {

    private static String[] BOOKS = {
			"Priests Of Dawn",
            "Chicken Abroad",
            "Lord With Sins",
            "Chicken Abroad",
            "Fish And Horses",
            "Mistress Of Devotion",
            "Bandit Of The Void",
            "Lord With Sins",
            "Guardians And Gangsters",
            "Lions Of Dread",
            "Chicken Abroad",
            "Future Of Fire",
            "Priests Of Dawn",
            "Fish And Horses",
            "Chicken Abroad",
            "Fish And Horses",
            "Guardians And Gangsters",
            "Inception Of Heaven",
            "Lord With Sins",
            "Future Of Fire",
            "Driving Into The Depths",
            "Starting The Demons",
            "Maid With Blue Eyes",
            "Mistress Of Devotion",
            "Lovers In The Forest",
            "Fish And Horses",
            "Maid With Blue Eyes",
            "Destruction Of The Faceless Ones",
            "Guardians And Gangsters",
            "Chicken Abroad"};

    public static HashMap<String, Integer> createBooksCounter() {
		HashMap<String, Integer> books = new HashMap<>();
		for (String book : BOOKS){
			countBook(books, book);
		}
        return books;
    }

    public static void countBook(HashMap<String, Integer> booksCounter, String book) {
		if (booksCounter.containsKey(book)){
			booksCounter.put(book, booksCounter.get(book)+1);
		}else{
			booksCounter.put(book, 1);
		}
	}

    public static ArrayList<String> findWhiteRavens(HashMap<String, Integer> booksCounter) {
        ArrayList<String> whiteRavens = new ArrayList<>();
		for (String book : booksCounter.keySet()){
			if (booksCounter.get(book)==1){
				whiteRavens.add(book);
			}
		}
		return whiteRavens;
    }

    public static void printWhiteRavens(ArrayList<String> whiteRavens) {
			for (String book : whiteRavens){
			System.out.println(book);
		}
    }
	public static void main (String[] args){
		printWhiteRavens(findWhiteRavens(createBooksCounter()));
		
	}
}