import java.util.*;
import java.io.*;
public class VMD {
public static void main(String[] args) throws IOException{
			Scanner kb=new Scanner(System.in);
			
			//FileInputStream input = new FileInputStream("vehicles.txt");
			//Scanner inputscan = new Scanner(input);
			
			//boolean mainMenuComplete=false;
			int mainMenuChoice=0;
			
			while(mainMenuChoice != 8) {
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
				
				case 4: choice4(); break;
				
				case 5: break;
				
				case 6: break;
				
				case 7: break;
				
				case 8: break;
				
				default:System.out.print("Error: Wrong choice.\n"); break;
				}
				
				} catch (InputMismatchException e) {System.out.println(e);}
				  catch (IllegalArgumentException e) {System.out.println(e);}
				if (mainMenuChoice!=8) {
					enterToContinue();
				}
			}//end of loop
		}
		
		public static void enterToContinue() {
			Scanner kb=new Scanner(System.in);
			System.out.print("\nPress Enter key to continue . . .");
			kb.nextLine();
			//kb.nextLine();
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
		
		public static void choice3() throws IOException, InputMismatchException{
			String line, modelName;
			FileInputStream input = new FileInputStream("vehicles.txt");
			Scanner inputscan = new Scanner(input);
			Scanner kb = new Scanner(System.in);
			int stockQuantity, numRentedVehicles;
			double rentalPricePerDay;
			boolean subMenuComplete=false, found=false;
			int subMenuChoice=0;
			int newStockQuantity;
			double newRentalPricePerDay;
			
			
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
					VehicleModel vehicle1=new VehicleModel(modelName, stockQuantity, numRentedVehicles, rentalPricePerDay);
					
					while(!subMenuComplete) {
						System.out.print("1. Update stock quantity.\n"
									   + "2. Update rental price per day.\n"
									   + "\n"
									   + "Enter your choice: ");
						subMenuChoice=kb.nextInt();
						
						switch(subMenuChoice) {
						case 1: System.out.printf("Available stock for %s is %d vehicles, and %d rented vehicles.%n", modelName, stockQuantity, numRentedVehicles);
								System.out.printf("Enter new stock Quantity: ");
								newStockQuantity=kb.nextInt();
								vehicle1.setStockQuantity(newStockQuantity); 
								System.out.println("Vehicle file has been updated . . .\n"
												 + "Model "+modelName+" information updated.\n");
								subMenuComplete=true; 
								break;
						
						case 2: System.out.printf("Current rental price per day for %s is %.2f Saudi Riyals%n", modelName, rentalPricePerDay);
								System.out.printf("Enter new rental price per day [SAR]: ");
								newRentalPricePerDay=kb.nextDouble();
								vehicle1.setRentalPricePerDay(newRentalPricePerDay);
								System.out.println("Vehicle file has been updated . . .\n"
										 	+ "Model "+modelName+" information updated.\n"); 
								subMenuComplete=true; 
								break;
						
						default:System.out.print("Error: Wrong choice.\n"); break;
						}
					}
				}
			}

			
			if (!found) {
				System.out.print("Error: No such model.\n");
			}
		}
		
		public static void choice4() throws IOException{
			
		}
		
	}
