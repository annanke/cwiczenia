public class BubbleSort { 
	public static void printNumbers(int[] numbers){
		for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
	}
	public static void swap(int index1, int index2, int[] numbers){
	    int temp;
        temp = numbers[index2];
        numbers[index2] = numbers[index1];
        numbers[index1] = temp;
	}
	
	public static boolean isGreaterThan(int num1, int num2){
		if (num1 > num2) {
			return true;
		}else{
			return false;
		}
	}
	public static int[] sort(int[] numbers){
		for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (isGreaterThan(numbers[j], numbers[j + 1])) {
					swap(j, j+1, numbers);               
				}
            }
        }
		return numbers;
	}
    public static void main(String[] args) {

        int[] numbers = new int[] { 4, 2, 0, 2, 10, 1, 9, 12, 3, 5 };

		sort(numbers);

        printNumbers(numbers);
    }
}