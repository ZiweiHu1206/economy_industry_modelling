
public class Staff extends IncomeTaxPayer {

	private int salaryPerHour;
	final private int incomeTaxPercentage = 25;

	public Staff(String name, boolean isCook) {
		super(name);
		if (isCook) {
			this.salaryPerHour = 20;
		}else {
			this.salaryPerHour = 10;
		}
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public int getIncomeTaxPercentage() {
		return incomeTaxPercentage;
	}

	public double workHours(int numHours) {
		double moneyEarned = salaryPerHour * numHours;
		double currentIncome = this.getIncome();
		double newIncome = moneyEarned + currentIncome;
		this.setIncome(newIncome);   //modified the income
		return this.getIncome();
	}

	@Override
	public double calculateIncomeTax() {
		double income = this.getIncome();
		double incomeTax = income * (this.getIncomeTaxPercentage() * 0.01);
		return incomeTax;
	}

}
