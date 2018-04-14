
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
	public void setStockQuantity (int stockQuantity) throws IllegalArgumentException{
		if(stockQuantity<0) throw new IllegalArgumentException("Invalid stock quantity");
		else this.stockQuantity=stockQuantity;
	}
	public void setRentalPricePerDay(double rentalPricePerDay) throws IllegalArgumentException{
		if(rentalPricePerDay<0) throw new IllegalArgumentException("Invalid rental price per day.");
		else this.rentalPricePerDay=rentalPricePerDay;
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
		line = String.format("%-20s%30s%40s%40s%n%n",modelName , stockQuantity , numRentedVehicles , rentalPricePerDay);
		return line;
	}
	public boolean equals(Object  obj) {
		boolean check = false;
		return check;
	}
}
