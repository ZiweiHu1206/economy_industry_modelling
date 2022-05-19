
public class Owner extends IncomeTaxPayer {

	final private int incomeTaxPct = 10;
	private double salaryExpenses;

	private FoodPlace foodPlace;

    public Owner(String name) {
		super(name);
	}

	public int getIncomeTaxPct() {
		return incomeTaxPct;
	}

	public double getSalaryExpenses() {
		return salaryExpenses;
	}

	public void setSalaryExpenses(double salaryExpenses) {
		this.salaryExpenses = salaryExpenses;
	}

	public void setFoodPlace(FoodPlace foodPlace) {
		this.foodPlace = foodPlace;
	}
	
	public FoodPlace getFoodPlace() {
		return foodPlace;
	}
	@Override
	public double calculateIncomeTax() {
		if (this.getIncome() >= (this.getSalaryExpenses() + this.getFoodPlace().getFixedCosts())) {
			double salaryExpense = this.getSalaryExpenses();
			double fixedCost = this.getFoodPlace().getFixedCosts();
			double income = this.getIncome();
			double difference = income - salaryExpense - fixedCost;
			double incomeTax = difference * (this.getIncomeTaxPct() * 0.01);
			return incomeTax;
		}else {
			return 0;
		}
	}
}
