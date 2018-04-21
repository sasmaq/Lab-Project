import java.util.*;
import java.io.*;

public class VehicleModelDriver {
	public static void main(String[] args) throws IOException {
		int mainMenuChoice = 0;

		while (mainMenuChoice != 8) {
			try {
				Scanner kb = new Scanner(System.in);
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
		Scanner kb = new Scanner(System.in);
		System.out.print("\nPress Enter key to continue . . .\n");
		kb.nextLine();

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

	public static void choice1() throws IOException, InputMismatchException {
		FileInputStream input = new FileInputStream("vehicles.txt");
		Scanner inputscan = new Scanner(input);

		String modelName;
		int stockQuantity, numRentedVehicles;
		double rentalPricePerDay;
		System.out.printf("%-20s%30s%40s%40s%n%n", "Model name", "Stock Quantity", "Number of rented vehicles",
				"Rental Price Per Day (SAR)");
		while (inputscan.hasNextLine()) {

			modelName = inputscan.next();
			stockQuantity = inputscan.nextInt();
			numRentedVehicles = inputscan.nextInt();
			rentalPricePerDay = inputscan.nextDouble();

			System.out.printf("%-20s%30d%40d%40.2f%n", modelName, stockQuantity, numRentedVehicles, rentalPricePerDay);

		}
		inputscan.close();
		enterToContinue();
	}

	public static void choice2() throws IOException, InputMismatchException {
		String line;
		FileInputStream input = new FileInputStream("vehicles.txt");
		Scanner inputscan = new Scanner(input);
		Scanner kb = new Scanner(System.in);
		boolean found = false;

		System.out.print("\nEnter model name: ");
		String userModelName = kb.next();

		while (inputscan.hasNextLine()) {
			line = inputscan.nextLine();
			if (line.toLowerCase().indexOf(userModelName.toLowerCase()) != -1) {
				Scanner linescan = new Scanner(line);
				found = true;
				VehicleModel VehicleInfo = new VehicleModel(linescan.next(), linescan.nextInt(), linescan.nextInt(),
						linescan.nextDouble());
				System.out.printf("%-20s%30s%40s%40s%n%n", "Model name", "Stock Quantity", "Number of rented vehicles",
						"Rental Price Per Day (SAR)");
				System.out.print(VehicleInfo.toString() + "\n");
				linescan.close();
			}
		} // loop end

		inputscan.close();
		// kb.close();

		if (!found) {
			System.out.print("Error: No such model.\n");
		}
		enterToContinue();
	}

	public static void choice3() throws IOException, InputMismatchException {
		boolean found = false;
		int c = 0, i = 0;
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
			c++;
		}

		if (!found)
			System.out.println("Error: No such model.");
		else {

			VehicleModel[] dataArray = new VehicleModel[c];
			FileScannerC.close();

			FileInputStream inS = new FileInputStream("vehicles.txt");
			Scanner FileScanner = new Scanner(inS);
			for (i = 0; i < dataArray.length; i++) {
				dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
						FileScanner.nextDouble());
			}
			FileScanner.close();

			for (i = 0; i < dataArray.length; i++) {
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

		int c = 0, i = 0;
		int vehiclesNum, days;
		double total = 0, subTotal;
		String tInfo = String.format("%-20s%10s%20s%30s%20s%n", "model", "Quantity", "Number of days",
				"Rental price per day(SAR)", "Subtotal(SAR)");
		boolean done = false, found = false;
		char YN;
		FileOutputStream outB = new FileOutputStream("bills.txt");
		PrintWriter pwriter = new PrintWriter(outB);
		pwriter.print(tInfo);
		FileInputStream inSC = new FileInputStream("vehicles.txt");
		Scanner FileScannerC = new Scanner(inSC);
		Scanner kb = new Scanner(System.in);

		while (FileScannerC.hasNextLine()) {
			FileScannerC.nextLine();
			c++;
		} // loop End
		FileScannerC.close();
		VehicleModel[] dataArray = new VehicleModel[c];

		FileInputStream inS = new FileInputStream("vehicles.txt");
		Scanner FileScanner = new Scanner(inS);
		for (i = 0; i < dataArray.length; i++) {
			dataArray[i] = new VehicleModel(FileScanner.next(), FileScanner.nextInt(), FileScanner.nextInt(),
					FileScanner.nextDouble());
		}
		FileScanner.close();

		while (!done) {
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

				System.out.printf("Available stock for %s is %d vehicles, and %d rented vehicles.%n",
						dataArray[c].getModelName(), dataArray[c].getStockQuantity(),
						dataArray[c].getNumRentedVehicles());
				System.out.print("Enter the required number of vehicles: ");
				vehiclesNum = kb.nextInt();
				System.out.print("Enter the required number of days [1 - 31]: ");
				days = kb.nextInt();
				subTotal = dataArray[c].rentVehicleModel(vehiclesNum, days);
				tInfo = tInfo + String.format("%n%-20s%10d%20d%30.2f%20.2f", dataArray[c].getModelName(), vehiclesNum,
						days, dataArray[c].getRentalPricePerDay(), subTotal);
				pwriter.printf("%n%-20s%10d%20d%30.2f%20.2f", dataArray[c].getModelName(), vehiclesNum, days,
						dataArray[c].getRentalPricePerDay(), subTotal);
				total = total + subTotal;

				updetar(dataArray);
				System.out.printf("Model %s information updated.%n%n", dataArray[c].getModelName());
			}
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
			} else
				;
		} // loop End

		FileScanner.close();
		outB.close();
		enterToContinue();
	}

	public static void choice5() throws IOException, InputMismatchException {
		int c = 0, i = 0;
		int returnedVehicles;
		boolean found = false;

		FileInputStream inSC = new FileInputStream("vehicles.txt");
		Scanner FileScannerC = new Scanner(inSC);
		Scanner kb = new Scanner(System.in);

		while (FileScannerC.hasNextLine()) {
			FileScannerC.nextLine();
			c++;
		} // loop End
		FileScannerC.close();
		VehicleModel[] dataArray = new VehicleModel[c];

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
			if (dataArray[c].getNumRentedVehicles() == 0) {
				System.out.println("Error: No vehcles rented for this model.");
			} else {
				System.out.printf("Current number of rented vehicles for %s is %d%n", dataArray[c].getModelName(),
						dataArray[c].getNumRentedVehicles());
				System.out.print("Enter number of vehicles returned: ");
				returnedVehicles = kb.nextInt();
				dataArray[c].getVehiclesFromCustomer(returnedVehicles);

				updetar(dataArray);
				System.out.printf("Model %s information updated.%n%n", dataArray[c].getModelName());
			}
		}
		enterToContinue();
	}

	public static void choice6() throws IOException, InputMismatchException {
		int c = 0, i = 0;
		int stock;
		double priceD;
		boolean found = false;

		FileInputStream inSC = new FileInputStream("vehicles.txt");
		Scanner FileScannerC = new Scanner(inSC);
		Scanner kb = new Scanner(System.in);

		while (FileScannerC.hasNextLine()) {
			FileScannerC.nextLine();
			c++;
		} // loop End
		FileScannerC.close();
		VehicleModel[] dataArray = new VehicleModel[c];

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

		FileInputStream inSC = new FileInputStream("vehicles.txt");
		Scanner FileScannerC = new Scanner(inSC);
		Scanner kb = new Scanner(System.in);

		while (FileScannerC.hasNextLine()) {
			FileScannerC.nextLine();
			c++;
		} // loop End
		FileScannerC.close();
		VehicleModel[] dataArray = new VehicleModel[c];

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
