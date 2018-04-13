import java.util.*;
import java.io.*;
public class VehicleModelDriver {
	public static void main(String[] args) throws IOException{
		Scanner kb=new Scanner(System.in);
		
		//FileInputStream input=new FileInputStream("vehicles.txt");
		//Scanner inputscan=new Scanner(input);
		
		boolean mainMenuComplete=false;
		int mainMenuChoice=0;
		
		while(!mainMenuComplete) {
			try {
			System.out.print("1. Display Information for all models.\n"
						   + "2. Display Information for a particular model.\n"
						   + "3. Update stock info for a particular model.\n"
						   + "4. Rent vehicle(s) to a customer.\n"
						   + "5. Get vehicle(s) from a customer.\n"
						   + "6. Add New Model.\n"
						   + "7. Delete Model.\n"
						   + "8. Exit.\n"
						   + "\n"
						   + "Please select your choice: ");
			
			mainMenuChoice=kb.nextInt();
			switch(mainMenuChoice) {
			case 1: choice1(); break;
			
			case 2: choice2(); break;
			
			case 3: choice3(); break;
			
			case 4: break;
			
			case 5: break;
			
			case 6: break;
			
			case 7: break;
			
			default: break;
			}
			
			} catch (InputMismatchException e) {System.out.println(e);}
			if (mainMenuChoice==8) {
				mainMenuComplete=true;
			}
			
			else {
				System.out.print("\nPress Enter key to continue . . .");
				kb.nextLine();
				kb.nextLine();
			}
		}//end of loop
	}
	
	public static void choice1() throws IOException{
		FileInputStream input=new FileInputStream("vehicles.txt");
		Scanner inputscan=new Scanner(input);
		
		String modelName;
		int stockQuantity, numRentedVehicles;
		double rentalPricePerDay;
		System.out.printf("%-20s%30s%40s%40s%n%n", "Model name", "Stock Quantity", "Number of rented vehicles", "Rental Price Per Day (SAR)");
		while(inputscan.hasNextLine()) {
			modelName=inputscan.next();
			stockQuantity=inputscan.nextInt();
			numRentedVehicles=inputscan.nextInt();
			rentalPricePerDay=inputscan.nextDouble();
			
			
			System.out.printf("%-20s%30d%40d%40.2f%n", modelName, stockQuantity, numRentedVehicles, rentalPricePerDay);
		}
	}

	public static void choice2() throws IOException{
		String line, modelName;
		FileInputStream input=new FileInputStream("vehicles.txt");
		Scanner inputscan=new Scanner(input);
		Scanner kb=new Scanner(System.in);
		int stockQuantity, numRentedVehicles;
		double rentalPricePerDay;
		boolean found=false;
		
		
		System.out.print("\nEnter model name: ");
		String userModelName=kb.next();
		
		while(inputscan.hasNextLine()) {
			line=inputscan.nextLine();
			if(line.toLowerCase().indexOf(userModelName.toLowerCase())!=-1) {
				Scanner linescan=new Scanner(line);
				found=true;
				modelName=linescan.next();
				stockQuantity=linescan.nextInt();
				numRentedVehicles=linescan.nextInt();
				rentalPricePerDay=linescan.nextDouble();
				//linescan.close();
				System.out.printf("%-20s%30s%40s%40s%n%n", "Model name", "Stock Quantity", "Number of rented vehicles", "Rental Price Per Day (SAR)");

				System.out.printf("%-20s%30d%40d%40.2f%n", modelName, stockQuantity, numRentedVehicles, rentalPricePerDay);
			}
		}//loop end

		//inputscan.close();
		//kb.close();
		
		if (!found) {
			System.out.print("Error: No such model.\n");
		}
		
	}
	
	public static void choice3() throws IOException{
		
	}
	
}
