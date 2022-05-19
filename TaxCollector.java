import java.util.ArrayList;
import java.util.List;

public class TaxCollector {

	private List<FoodPlace> foodPlaces = new ArrayList<>();

	private double incomeTaxCollected;
	private double salesTaxCollected;

	public TaxCollector(List<FoodPlace> foodPlaces) {
		this.foodPlaces = foodPlaces;
	}

	public List<FoodPlace> getFoodPlaces() {
		return foodPlaces;
	}

	public double getIncomeTaxCollected() {
		return incomeTaxCollected;
	}

	public double getSalesTaxCollected() {
		return salesTaxCollected;
	}

	public void collectTax() {
		double incomeTax = 0;
		double salesTax = 0;
		
		for (FoodPlace foodPlace : this.getFoodPlaces()) {
			salesTax = salesTax + foodPlace.getTotalSalesTax();
			for (IncomeTaxPayer incomeTaxPayer : foodPlace.getIncomeTaxPayers()) {
				incomeTax = incomeTax + incomeTaxPayer.calculateIncomeTax();
			}
			
		}
		this.incomeTaxCollected = incomeTax;
		this.salesTaxCollected = salesTax;
	}
	
	public String toString() {
		return "TaxCollector: income tax collected: " + incomeTaxCollected + ", sales tax collected: " + salesTaxCollected;
	}
	
}
