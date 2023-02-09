package SortAndSearch;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class Algorithm {
	/*
	 writeFile: ghi mảng input đầu vào và kết quả vào file
	 tham số 1: mảng input, mảng kết quả
	 tham số 2: file ghi vào
	 tham số 3: dùng để sử dụng cách ghi phù hợp tùy theo kết quả đầu ra
	 (nếu ghi mảng thì phải có '.0', nếu ghi vị trí thì ghi không có '.0')
	 */
	public static void writeFile(float[] arr, File f, boolean use) {
		try {
			FileWriter fw = new FileWriter(f);
			for (int i = 0; i < arr.length; i++) {
				if (use) {
					fw.write(arr[i] + " ");//convert float element to string
				}
				else {
					fw.write((int)arr[i] + " ");
				}
			}
			fw.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	
	/*
	 readFile: đọc file trong file input
	 kết quả trả về: mảng
	 */
	public static float[] readFile() throws FileNotFoundException{
		String temp = "";
		try {
			FileReader fr = new FileReader("INPUT.txt");
			BufferedReader br = new BufferedReader(fr);
			temp = br.readLine();
			br.close();
			fr.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		//convert string to float element
		String[] arrS = temp.split("\\s");//tách các phần tử theo khoảng trắng
		float[] arr = new float[arrS.length];
		for (int i = 0; i < arrS.length; i++) {
			arr[i] = Float.parseFloat(arrS[i]);
		}
		return arr;
	}
	
	/*
	 bubbleSort: sắp xếp theo thuật toán Bubble sort
	 tham số 1: mảng cần sắp xếp
	 tham số 2: để sử dụng hàm display() ở chức năng 3 và vô hiệu hóa hàm display() ở chức năng 7
	 Kết quả trả về: mảng đã sắp xếp
	 */
	public static float[] bubbleSort(float[] arr, boolean display) {
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if (arr[j] > arr[j+1]) {//compare element and the one next to it
					float temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
			
			//display == true để có thể sắp xếp mà không in từng bước ở chức năng 7
			if (display == true) {
				display(arr);
			}
		}
		return arr;
	}
	
	/*
	 selectionSort: sắp xếp theo thuật toán Selection sort
	 tham số 1: mảng cần sắp xếp
	 Kết quả trả về: mảng đã sắp xếp
	 */
	public static float[] selectionSort(float[] arr) {
		for (int i = 0; i < arr.length -1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {//if arr[j] < arr[min] -> min = j -> swap
					min = j;
				}
			}
			float temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
			display(arr);
		}
		return arr;
	}
	
	/*
	 insertionSort: sắp xếp theo thuật toán Insertion sort
	 tham số 1: mảng cần sắp xếp
	 Kết quả trả về: mảng đã sắp xếp
	 */
	public static float[] insertionSort(float[] arr) {
		for (int i = 1; i < arr.length; i++) {
			float current = arr[i];
			int j = i - 1;
			//Move elements of arr[0..i-1], that are greater than key
			while (j >= 0 && arr[j] >= current) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = current;
			display(arr);
		}
		return arr;
	}
	
	/*
	 linearSearch: search chỉ số phần tử lớn hơn phần tử x trong mảng bằng thuật toán tìm kiếm tuyến tính
	 tham số 1: giá trị x
	 tham số 2: mảng dùng để tìm
	 tham số 3: file để lưu kết quả
	 Kết quả trả về: vị trí các phần tử có giá trị lớn hơn x 
	 */
	public static void linearSearch(float x, float[] arr, File f) {
		//to save the result since we don't know how many greater value
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > x) {
				//save the result
				index.add(i);
			}
		}
		//convert to float to write to output file
		float[] answer = new float[index.size()];
		if (index.size() == 0) {
			System.out.println("There no greater value");
		}
		else {
			System.out.println("Index is: ");
			for (int i = 0; i < answer.length; i++) {
				System.out.print(index.get(i) + " ");//convert to float and print out
				answer[i] = (float)index.get(i);
				writeFile(answer, f, false);
			}
		}
		System.out.println();
	}
	
	/*
	 binarySearch: tìm kiếm chỉ số phần tử đầu tiên bằng phần tử x trong mảng bằng thuật toán tìm kiến nhị phân
	 tham số 1: giá trị x
	 tham số 2: mảng dùng để tìm kiếm
	 tham số 3: chỉ số bên trái
	 tham số 4: chỉ số bên phải
	 tham số 5: file để lưu kết quả
	 kết quả: vị trí của phần tử đầu tiên bằng với x
	 */
	public static int binarySearch(float x, float[] arr,int left, int right, File f) {
		int mid;
		
		if (right >= left) {
			mid = left + (right - left)/2;
			
			//giả sử mảng có phần tử trùng
			if ((mid == 0 || arr[mid-1] != x) && arr[mid] == x) {
				return mid;
			}
			if (arr[mid] >= x) {
				return binarySearch(x, arr, left, mid-1,f);
			}
			else {
				return binarySearch(x, arr, mid+1, right,f);
			}
		}
		return -1;
	}
	
	/*
	 display: để biểu diễn các phần tử của mảng theo từng bước sau khi đổi chỗ
	 */
	public static void display(float[] arr) {
		for (int k = 0; k < arr.length; k++) {
			System.out.print(arr[k] + " ");
		}
		System.out.println();
	}
}