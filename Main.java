package SortAndSearch;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class Main {
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		float arr[] = {};// main array
		int menuNum = 0;
		boolean exit = false;
		int n;
		
		//create input file
		File inputFile = new File("INPUT.txt");
		
		while (exit == false) {
			menuNum = displayMenu(input);
			switch (menuNum) {
			
			//Insert array
			case 1:
				System.out.println("Enter number of elements: ");
				n = input.nextInt();
				float[] arr1 = new float[n];//temp array
				int i = 0;
				
				System.out.println("Input element: ");
				for (i = 0; i < n; i++) {
					arr1[i] = input.nextFloat();
				}
				System.out.println();
				Algorithm.writeFile(arr1, inputFile, true);
				break;
				
			//Read from file
			case 2:
				System.out.println("Read from file: ");
				arr = Algorithm.readFile();
				for (i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				break;
				
			//Sorting with Bubble sort
			case 3:
				float[] arr3 = arr.clone();//
				File outputFile = new File("OUTPUT1.txt");
				arr3 = Algorithm.bubbleSort(arr3, true);//boolean để sd hàm display() in từng bước 
				Algorithm.writeFile(arr3, outputFile, true);
				break;
				
			//Sorting with Selection sort
			case 4:
				float[] arr4 = arr.clone();
				outputFile = new File("OUTPUT2.txt");
				arr4 = Algorithm.selectionSort(arr4);
				Algorithm.writeFile(arr4, outputFile, true);
				break;
				
			//Sorting with Insertion sort
			case 5:
				float[] arr5 = arr.clone();
				outputFile = new File("OUTPUT3.txt");
				arr5 = Algorithm.insertionSort(arr5);
				Algorithm.writeFile(arr5, outputFile, true);
				break;
				
			//Searching with Linear search
			case 6:
				outputFile = new File("OUTPUT4.txt");
				System.out.println("Input value: ");
				float searchV = input.nextFloat();
				Algorithm.linearSearch(searchV, arr, outputFile);
				break;
				
			//Searching with Binary search
			case 7:
				outputFile = new File("OUTPUT5.txt");
				System.out.println("Input value: ");
				searchV = input.nextFloat();
				float[] arr7 = arr.clone();
				//sorting array
				arr7 = Algorithm.bubbleSort(arr7, false);// bỏ qua hàm display() chỉ so sánh
				//search by BinarySearch
				int result = Algorithm.binarySearch(searchV, arr7,0, arr7.length-1, outputFile);
				if (result != -1) {
					System.out.println("Index of first element: " + result);
					Algorithm.writeFile(result + " ", outputFile);
				}
				else {
					System.out.println("There no equal value");
				}
				break;
			case 8:
				exit = true;
				break;
			}
		}
		
		
	}
	
	public static int displayMenu(Scanner input) {
		System.out.println("1. Enter data");
		System.out.println("2. Display data to the screen");
		System.out.println("3. Bubble Sort");
		System.out.println("4. Selection Sort");
		System.out.println("5. Insertion Sort");
		System.out.println("6. Linear Search - search for the index of the element greater value index");
		System.out.println("7. Binary Search - search for the index of the first element equal to value");
		System.out.println("8. Exit");
		System.out.println("Your choice: ");
		return input.nextInt();
	}
	
}

