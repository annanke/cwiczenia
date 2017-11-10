public class Trojkat {
    public static void main(String[] args) {
        int a = 5;
        int h = 3;

        // float triangle = a*h/2.0f;
		double triangle = a*h/2.0;
        if (triangle>7) {
            System.out.println("Duzy trojkat");
        }
        else {
            System.out.println("Maly trojkat");
        }
    }
}
