import java.util.*;
import java.io.*;

public class VehicleModelDriver {
	public static void main(String[] args) throws IOException {
		int mainMenuChoice = 0;
		Scanner kb = new Scanner(System.in);
		while (mainMenuChoice != 8) {
			try {
				System.out.print("1. Display Information for all models.\n"
						+ "2. Display Information for a particular model.\n"
						+ "3. Update stock info for a particular model.\n" + "4. Rent vehicle(s) to a customer.\n"
						+ "5. Get vehicle(s) from a customer.\n" + "6. Add New Model.\n" + "7. Delete Model.\n"
						+ "8. Exit.\n" + "\n" + "Please select your choice: ");
				mainMenuChoice = kb.nextInt();
				switch (mainMenuChoice) {
				case 1:
					choice1();
					break;

				case 2:
					choice2();
					break;

				case 3:
					choice3();
					break;

				case 4:
					choice4();
					break;

				case 5:
					choice5();
					break;

				case 6:
					choice6();
					break;

				case 7:
					choice7();
					break;

				case 8:
					kb.close();
					// Bye-bye :(
					break;

				default:
					System.out.print("Error: Wrong choice.\n");
					enterToContinue();
					break;
				}

			} catch (InputMismatchException e) {
				kb.next();
				System.out.println(e);
				enterToContinue();
			} catch (IllegalArgumentException e) {
				System.out.println(e);
				enterToContinue();
			} catch (IOException e) {
				System.out.println(e);
				enterToContinue();
			}
		} // end of loop
	}

	public static void enterToContinue() {
		Scanner kbE = new Scanner(System.in);
		System.out.print("\nPress Enter key to continue . . .\n");
		kbE.nextLine();
	}

	public static void updetar(VehicleModel[] array) throws IOException {
		PrintWriter pwriter = new PrintWriter("vehicles.txt");
		for (int i = 0; i <= array.length - 2; i++) {
			pwriter.println(array[i].toString());
		}
		pwriter.print(array[array.length - 1].toString());
		pwriter.close();

		System.out.println("Vehicle file has been updated . . . ");
	}

	public static int countLines() throws IOException {
		int count = 0;
		FileInputStream inSC = new FileInputStream("vehicles.txt");
		Scanner FileScannerC = new Scanner(inSC);
		while (FileScannerC.hasNextLine()) {
			FileScannerC.nextLine();
			count++;
		} // loop End
		FileScannerC.close();
		return count;
	}

	public static VehicleModel searchForVehicle() throws IOException, InputMismatchException {
		boolean found = false;
		VehicleModel theVehicle = null;

		FileInputStream inSC = new FileInputStream("vehicles.txt");
		Scanner FileScannerC = new Scanner(inSC);
		Scanner kb = new Scanner(System.in);

		System.out.print("\nEnter model name: ");
		String userModelName = kb.next();
		String line;
		while (FileScannerC.hasNextLine()) {
			line = FileScannerC.nextLine();
			if (line.toLowerCase().indexOf(userModelName.toLowerCase()) != -1) {
				Scanner linescan = new Scanner(line);
				found = true;
				theVehicle = new VehicleModel(linescan.next(), linescan.nextInt(), linescan.nextInt(),
						linescan.nextDouble());
				linescan.close();
			}
		} // loop End
		FileScannerC.close();
		if (!found) {
			System.out.println("Error: No such model.");
			return null;
		} else {
			return theVehicle;
		}
	}

	public static void choice1() throws IOException {
		FileInputStream input = new FileInputStream("vehicles.txt");
		Scanner inputscan = new Scanner(input);

		System.out.printf("%-20s%30s%40s%40s%n%n", "Model name", "Stock Quantity", "Number of rented vehicles",
				"Rental Price Per Day (SAR)");
		while (inputscan.hasNextLine()) {
			System.out.printf("%-20s%30d%40d%40.2f%n", inputscan.next(), inputscan.nextInt(), inputscan.nextInt(),
					inputscan.nextDouble());
		}
		inputscan.close();
		enterToContinue();
	}

	public static void choice2() throws IOException {
		VehicleModel VehicleInfo = searchForVehicle();

		if (VehicleInfo != null) {
			System.out.printf("%-20s%30s%40s%40s%n%n", "Model name", "Stock Quantity", "Number of rented vehicles",
					"Rental Price Per Day (SAR)");
			System.out.print(VehicleInfo.toString() + "\n");
		}
		enterToContinue();
	}

	public static void choice3() throws IOException, InputMismatchException {
		VehicleModel theVehicle = searchForVehicle();

		if (theVehicle != null) {
			VehicleModel[] dataArray = new VehicleModel[countLines()];

			FileInputStream inS = new FileInputStream("vehicles.txt");
			Scanner FileScanner = new Scanner(inS);
			for (int i = 0; i < dataArray.length; i++) {
				dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
						FileScanner.nextDouble());
			}
			FileScanner.close();

			for (int i = 0; i < dataArray.length; i++) {
				if (dataArray[i].equals(theVehicle)) {
					choice3subMenu(dataArray, i);
				}
			}
		}
		enterToContinue();
	}

	public static void choice3subMenu(VehicleModel[] dataArray, int c) throws IOException, InputMismatchException {
		boolean subMenuComplete = false;
		int subMenuChoice = 0;
		Scanner kb = new Scanner(System.in);

		while (!subMenuComplete) {
			System.out.print(
					"1. Update stock quantity.\n" + "2. Update rental price per day.\n" + "\n" + "Enter your choice: ");
			subMenuChoice = kb.nextInt();

			switch (subMenuChoice) {
			case 1:
				System.out.printf("Available stock for %s is %d Vehicles,and %d rented vehicles.%n",
						dataArray[c].getModelName(), dataArray[c].getStockQuantity(),
						dataArray[c].getNumRentedVehicles());
				System.out.print("Enter new stock quantity: ");
				dataArray[c].setStockQuantity(kb.nextInt());

				updetar(dataArray);
				System.out.printf("Model %s information updated.%n", dataArray[c].getModelName());
				subMenuComplete = true;
				break;
			case 2:
				System.out.printf("Currrent rental price per day for %s is %.2f Saudi Riyals.%n",
						dataArray[c].getModelName(), dataArray[c].getRentalPricePerDay());
				System.out.print("Enter new rental price per day [SAR]: ");
				dataArray[c].setRentalPricePerDay(kb.nextDouble());

				updetar(dataArray);
				System.out.printf("Model %s information updated.%n", dataArray[c].getModelName());
				subMenuComplete = true;
				break;
			default:
				System.out.print("Error: Wrong choice.\n");
				break;
			}
		} // loop end
	}

	public static void choice4() throws IOException, InputMismatchException {
		int vehiclesNum, days;
		double total = 0, subTotal;
		String tInfo = String.format("%-20s%10s%20s%30s%20s%n", "model", "Quantity", "Number of days",
				"Rental price per day(SAR)", "Subtotal(SAR)");
		boolean done = false;
		char YN;
		FileOutputStream outB = new FileOutputStream("bills.txt");
		PrintWriter pwriter = new PrintWriter(outB);
		pwriter.print(tInfo);
		Scanner kb = new Scanner(System.in);

		VehicleModel[] dataArray = new VehicleModel[countLines()];

		FileInputStream inS = new FileInputStream("vehicles.txt");
		Scanner FileScanner = new Scanner(inS);
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
					FileScanner.nextDouble());
		}
		FileScanner.close();

		while (!done) {
			VehicleModel theVehicle = searchForVehicle();

			if (theVehicle != null) {

				for (int i = 0; i <= dataArray.length - 1; i++) {

					if (dataArray[i].equals(theVehicle)) {
						System.out.printf("Available stock for %s is %d vehicles, and %d rented vehicles.%n",
								dataArray[i].getModelName(), dataArray[i].getStockQuantity(),
								dataArray[i].getNumRentedVehicles());
						System.out.print("Enter the required number of vehicles: ");
						vehiclesNum = kb.nextInt();
						System.out.print("Enter the required number of days [1 - 31]: ");
						days = kb.nextInt();
						subTotal = dataArray[i].rentVehicleModel(vehiclesNum, days);
						tInfo = tInfo + String.format("%n%-20s%10d%20d%30.2f%20.2f", dataArray[i].getModelName(),
								vehiclesNum, days, dataArray[i].getRentalPricePerDay(), subTotal);
						pwriter.printf("%n%-20s%10d%20d%30.2f%20.2f", dataArray[i].getModelName(), vehiclesNum, days,
								dataArray[i].getRentalPricePerDay(), subTotal);
						total = total + subTotal;

						updetar(dataArray);
						System.out.printf("Model %s information updated.%n%n", dataArray[i].getModelName());
					}
				}
				do {
				System.out.print("Do you want to rent another model? (y/n): ");
				YN = kb.next().charAt(0);
				if (YN == 'n') {
					done = true;
					if (total != 0) {
						System.out.println(tInfo);
						System.out.printf("%80s%20.2f", "Total", total);

						pwriter.printf("%n%80s%20.2f", "Total", total);
						pwriter.close();
					}
				} else if (YN == 'y') {
					done = false;
				} 
				}while(YN != 'n' && YN != 'y');
			}
		} // loop End
		FileScanner.close();
		outB.close();
		enterToContinue();
	}

	public static void choice5() throws IOException, InputMismatchException {
		int returnedVehicles;
		Scanner kb = new Scanner(System.in);

		VehicleModel[] dataArray = new VehicleModel[countLines()];

		FileInputStream inS = new FileInputStream("vehicles.txt");
		Scanner FileScanner = new Scanner(inS);
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
					FileScanner.nextDouble());
		}
		FileScanner.close();

		VehicleModel theVehicle = searchForVehicle();

		if (theVehicle != null) {
			for (int i = 0; i <= dataArray.length - 1; i++) {
				if (dataArray[i].equals(theVehicle)) {
					if (dataArray[i].getNumRentedVehicles() == 0) {
						System.out.println("Error: No vehcles rented for this model.");
					} else {
						System.out.printf("Current number of rented vehicles for %s is %d%n",
								dataArray[i].getModelName(), dataArray[i].getNumRentedVehicles());
						System.out.print("Enter number of vehicles returned: ");
						returnedVehicles = kb.nextInt();
						dataArray[i].getVehiclesFromCustomer(returnedVehicles);

						updetar(dataArray);
						System.out.printf("Model %s information updated.%n%n", dataArray[i].getModelName());
					}
				}
			}
		}
		enterToContinue();
	}

	public static void choice6() throws IOException, InputMismatchException {
		int i = 0;
		int stock;
		double priceD;
		boolean found = false;
		Scanner kb = new Scanner(System.in);

		VehicleModel[] dataArray = new VehicleModel[countLines()];

		FileInputStream inS = new FileInputStream("vehicles.txt");
		Scanner FileScanner = new Scanner(inS);
		for (i = 0; i < dataArray.length; i++) {
			dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
					FileScanner.nextDouble());

		}
		FileScanner.close();

		System.out.print("\nEnter model name: ");
		String userModelName = kb.next();
		for (i = 0; i <= dataArray.length - 1; i++) {
			if (dataArray[i].getModelName().toLowerCase().equals(userModelName.toLowerCase())) {
				found = true;
			}
		}
		if (found)
			System.out.println("Error: This model exists.");
		else {
			System.out.println("Enter stock quantity and rental price per day:");
			stock = kb.nextInt();
			priceD = kb.nextDouble();
			if (stock <= 0 || priceD <= 0)
				System.out.println("Error: Invalid stock quantity or rental price.");
			else {
				PrintWriter pwriter = new PrintWriter("vehicles.txt");
				VehicleModel newVehical = new VehicleModel(userModelName, stock, 0, priceD);
				VehicleModel[] updataArray = new VehicleModel[dataArray.length + 1];
				for (i = 0; i < updataArray.length - 1; i++) {
					updataArray[i] = dataArray[i];
					pwriter.println(updataArray[i].toString());
				}
				updataArray[i] = newVehical;
				pwriter.print(updataArray[updataArray.length - 1].toString());
				pwriter.close();
				System.out.println("Model successfully added . . . ");
			}

		}
		enterToContinue();
	}

	public static void choice7() throws IOException, InputMismatchException {
		int c = 0, i = 0;
		boolean found = false;
		Scanner kb = new Scanner(System.in);

		VehicleModel[] dataArray = new VehicleModel[countLines()];

		FileInputStream inS = new FileInputStream("vehicles.txt");
		Scanner FileScanner = new Scanner(inS);
		for (i = 0; i < dataArray.length; i++) {
			dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
					FileScanner.nextDouble());
		}
		FileScanner.close();

		System.out.print("\nEnter model name: ");
		String userModelName = kb.next();
		for (i = 0; i <= dataArray.length - 1; i++) {
			if (dataArray[i].getModelName().toLowerCase().equals(userModelName.toLowerCase())) {
				found = true;
				c = i;
			}
		}
		if (!found)
			System.out.println("Error: No such model.");
		else {
			if (dataArray[c].getNumRentedVehicles() != 0)
				System.out.println("Error: Rented Vehicles are out");
			else {
				PrintWriter pwriter = new PrintWriter("vehicles.txt");
				VehicleModel[] updataArray = new VehicleModel[dataArray.length - 1];
				for (i = c; i < dataArray.length - 1; i++) {
					dataArray[i] = dataArray[i + 1];
				}
				for (i = 0; i < updataArray.length; i++) {
					updataArray[i] = dataArray[i];
				}
				for (i = 0; i <= updataArray.length - 2; i++) {
					pwriter.println(updataArray[i].toString());
				}
				pwriter.print(updataArray[updataArray.length - 1].toString());
				pwriter.close();
				System.out.println("Model successfully deleted . . . ");
			}
		}
		enterToContinue();
	}
}
