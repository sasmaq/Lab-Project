import java.io.*;
import java.util.*;

public class VM {
	private String modelName;
	private int stockQuantity,numRentedVehicles;
	private double rentalPricePerDay;
	
		public VehicleModel(String modelName, int stockQuantity, int numRentedVehicles, double rentalPricePerDay) throws IllegalArgumentException{
			if (stockQuantity<0 || stockQuantity<0 || rentalPricePerDay<0) throw new IllegalArgumentException();
			this.modelName = modelName;
			this.stockQuantity = stockQuantity;
			this.numRentedVehicles = numRentedVehicles;
			this.rentalPricePerDay = rentalPricePerDay;
		}
	
	
	public String getModelName() {
		return modelName;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public int getNumRentedVehicles() {
		return numRentedVehicles;
	}
	public double getRentalPricePerDay() {
		return rentalPricePerDay;
	}
	
	public void setStockQuantity (int stockQuantity) throws IllegalArgumentException, IOException{
		if(stockQuantity<0) throw new IllegalArgumentException("Invalid stock quantity");
		else {
			this.stockQuantity=stockQuantity;
			
			File input=new File("vehicles.txt");
			Scanner inputscan=new Scanner(input);
			
			int size=0;
			while(inputscan.hasNextLine()) {
				inputscan.nextLine();
				size++;
				}
			
			input=new File("vehicles.txt");
			inputscan=new Scanner(input);
			
			String[] line=new String[size];
			for(int i=0; i<size; i++) {
				line[i]=inputscan.nextLine();
				if(line[i].toLowerCase().indexOf(modelName.toLowerCase())!=-1) {
					line[i]=toString();
					}
				}
			inputscan.close();
			File output=new File("vehicles.txt");
			output.createNewFile();
			PrintWriter pwriter=new PrintWriter(output);
			
			for(int k = 0; k <= line.length - 2; k++)
			       pwriter.println(line[k]); 
			     
			       pwriter.print(line[line.length - 1]);
			       
			       pwriter.close();
			       inputscan.close();
			       input.delete();
			       output.renameTo(input);
			}
	}
	public void setRentalPricePerDay(double rentalPricePerDay) throws IllegalArgumentException, IOException{
		if(rentalPricePerDay<0) throw new IllegalArgumentException("Invalid rental price per day.");
		else {
			this.rentalPricePerDay=rentalPricePerDay;
			
			File input=new File("vehicles.txt");
			Scanner inputscan=new Scanner(input);
			
			int size=0;
			while(inputscan.hasNextLine()) {
				inputscan.nextLine();
				size++;
				}
			
			input=new File("vehicles.txt");
			inputscan=new Scanner(input);
			
			String[] line=new String[size];
			for(int i=0; i<size; i++) {
				line[i]=inputscan.nextLine();
				if(line[i].toLowerCase().indexOf(modelName.toLowerCase())!=-1) {
					line[i]=toString();
					}
				}
			inputscan.close();
			File output=new File("vehicles.txt");
			output.createNewFile();
			PrintWriter pwriter=new PrintWriter(output);
			
			for(int k = 0; k <= line.length - 2; k++)
			       pwriter.println(line[k]); 
			     
			       pwriter.print(line[line.length - 1]);
			       
			       pwriter.close();
			       inputscan.close();
			       input.delete();
			       output.renameTo(input);
		}
	}
	public double rentVehicleModel(int numVehicles, int numDays) throws IllegalArgumentException{
		if(numVehicles<0 || numDays<0) throw new IllegalArgumentException();
		else {
		double cost;
		cost = numVehicles*(rentalPricePerDay*numDays);
		return cost;
		}
	}
	public void getVehiclesFromCustomer(int numVehicles) throws IllegalArgumentException{
		if(numVehicles<0) throw new IllegalArgumentException();
		else {
		stockQuantity = stockQuantity + numVehicles;
		numRentedVehicles = numRentedVehicles - numVehicles;
		}
	}
	
	public String toString() {
		String line;
		line = String.format("%-19s\t\t    %36d\t\t %11d\t\t\t\t %5.2f",modelName , stockQuantity , numRentedVehicles , rentalPricePerDay);
		return line;
	}
	public boolean equals(Object  obj) {
		boolean check = false;
		return check;
	}
}
