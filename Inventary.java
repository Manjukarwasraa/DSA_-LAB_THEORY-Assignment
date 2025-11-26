import java.util.*;

class InventaryItem {
	String ID;
	String name;
	double price;

	public InventaryItem(String id, String n, double p) {
		this.ID = id;
		this.name = n;
		this.price = p;
	}

	public void display(int qty) {
		System.out.println("ID: " + ID + ", Name: " + name + ", Price: " + price + ", Quantity: " + qty);
	}
}

class InventaryStorage {
	InventaryItem[][] items;
	int[][] quantity;
	final String EMPTY = null;

	public InventaryStorage(int rows, int cols) {
		items = new InventaryItem[rows][cols];
		quantity = new int[rows][cols];
	}

	public void insert(int rows, int cols, String id, String n, double p, int qty) {
		items[rows][cols] = new InventaryItem(id, n, p);
		quantity[rows][cols] = qty;
	}

	public void delete(int rows, int cols) {
		items[rows][cols] = null;
		quantity[rows][cols] = 0;
	}

	public void retrieve(int rows, int cols) {
		if (items[rows][cols] != null) {
			items[rows][cols].display(quantity[rows][cols]);
		} else {
			System.out.println("No items to Display");
		}
	}

	public void rowMajorAccess() {
		System.out.println("Row Major Access: ");
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				if (items[i][j] != null) {
					System.out.print(items[i][j].name + "(" + quantity[i][j] + ") ");
				} else {
					System.out.print("EMPTY ");
				}
			}
			System.out.println();
		}
	}

	public void columnMajorAccess() {
		System.out.println("Column Major Access: ");
		for (int j = 0; j < items[0].length; j++) {
			for (int i = 0; i < items.length; i++) {
				if (items[i][j] != null) {
					System.out.print(items[i][j].name + "(" + quantity[i][j] + ") ");
				} else {
					System.out.print("EMPTY ");
				}
			}
			System.out.println();
		}
	}

	public void handleSparseData() {
		System.out.println("Row Major Access: ");
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				if (items[i][j] == null) {
					System.out.print("NA ");
				} else {
					System.out.print(quantity[i][j] + " ");
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

	public void populateArray() {
		insert(0, 0, "101", "Milk", 63.00, 2);
		insert(1, 1, "102", "Bread", 95.00, 5);
		insert(2, 2, "103", "Paneer", 1000.00, 10);
	}
}

public class Inventary {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		InventaryStorage storage = new InventaryStorage(3, 3);

		storage.populateArray();

		System.out.println("Enter rows:");
		int rows = sc.nextInt();

		System.out.println("Enter cols:");
		int cols = sc.nextInt();

		System.out.println("Enter ID:");
		String ID = sc.next();

		System.out.println("Enter Name:");
		String name = sc.next();

		System.out.println("Enter Price:");
		double price = sc.nextDouble();

		System.out.println("Enter Quantity:");
		int qty = sc.nextInt();

		storage.insert(rows, cols, ID, name, price, qty);

		System.out.println("\nRetrieved Item:");
		storage.retrieve(rows, cols);

		storage.rowMajorAccess();

		storage.columnMajorAccess();

		storage.handleSparseData();

		System.out.println("\nDeleting item...");
		storage.delete(rows, cols);

		System.out.println("After deletion:");
		storage.retrieve(rows, cols);

		storage.rowMajorAccess();

		storage.columnMajorAccess();

		storage.handleSparseData();

		storage.analyzeComplexity();

		sc.close();

	}
}
