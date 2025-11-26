
import java.util.*;

class wetherRecord {
	String date;
	String city;
	double temperature;

	wetherRecord(String d, String c, double temp) {
		this.date = d;
		this.city = c;
		this.temperature = temp;
	}

	public void display() {
		System.out.println("Date: " + date + ", City: " + city + ", Temperature: " + temperature);

	}
}

class wetherStorage {
	double[][] temperature;
	final double SENTINEL = -9999;

	public wetherStorage(int years, int cities) {
		temperature = new double[years][cities];

		for (int i = 0; i < years; i++) {
			for (int j = 0; j < cities; j++) {
				temperature[i][j] = SENTINEL;
			}
		}
	}

	public void insert(int yearIndex, int cityIndex, double temp) {
		temperature[yearIndex][cityIndex] = temp;
	}

	public void delete(int yearIndex, int cityIndex) {
		temperature[yearIndex][cityIndex] = SENTINEL;
	}

	public void retrieve(int yearIndex, int cityIndex) {
		double temp = temperature[yearIndex][cityIndex];

		if (temp != SENTINEL) {
			System.out.println("Temperature : " + temp);
		} else {
			System.out.println("No Data Available");
		}

	}

	public void populateArray() {
		insert(0, 0, 30.5);
		insert(1, 1, 30.5);
		insert(2, 2, 30.5);

	}

	public void rowMajorAccess() {
		System.out.println("Row major access: ");
		for (int i = 0; i < temperature.length; i++) {
			for (int j = 0; j < temperature[i].length; j++) {
				System.out.print(temperature[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void columnMajorAccess() {
		System.out.println("Column major access: ");
		for (int j = 0; j < temperature[0].length; j++) {
			for (int i = 0; i < temperature.length; i++) {
				System.out.print(temperature[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void handleSparseData() {
		System.out.println("Sparse Data Handling:  ");
		for (int i = 0; i < temperature.length; i++) {
			for (int j = 0; j < temperature[i].length; j++) {
				if (temperature[i][j] == SENTINEL) {
					System.out.print("NA ");
				} else {
					System.out.print(temperature[i][j] + " ");
				}
			}
			System.out.println();
		}

	}

	public void analyzeComplexity() {
		System.out.println("Complexity Analysis:");
		System.out.println("Insert : O(1)");
		System.out.println("Delete : O(1)");
		System.out.println("Retrieve : O(1)");
		System.out.println("Row/Column : O(n*m)");
		System.out.println("Space Complexity : O(n*m)");
	}
}

public class Wether {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		wetherStorage ws = new wetherStorage(3, 3);

		ws.populateArray();

		System.out.println("Enter yearIndex: ");
		int yearIndex = sc.nextInt();

		System.out.println("Enter cityIndex: ");
		int cityIndex = sc.nextInt();

		System.out.println("Enter temperature: ");
		double temp = sc.nextDouble();

		ws.insert(yearIndex, cityIndex, temp);

		System.out.println("Retrieved value: ");
		ws.retrieve(yearIndex, cityIndex);

		ws.rowMajorAccess();

		ws.columnMajorAccess();

		ws.handleSparseData();

		ws.delete(yearIndex, cityIndex);
		System.out.println("After Deleting: ");

		ws.retrieve(yearIndex, cityIndex);

		ws.rowMajorAccess();

		ws.columnMajorAccess();

		ws.handleSparseData();

		ws.analyzeComplexity();

		sc.close();

	}
}