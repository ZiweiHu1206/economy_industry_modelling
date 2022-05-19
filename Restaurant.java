
import java.util.List;
import java.util.LinkedList;

public class Restaurant extends FoodPlace {

	private Staff cook;
	private Server server;

	public Restaurant(String name, double fixedCosts, Owner owner, Staff cook, Server server) {
		super(name,fixedCosts,owner);
		this.cook = cook;
		this.server = server;
	}

	public Staff getCook() {
		return cook;
	}
	
	public Server getServer() {
		return server;
	}

	@Override
	public String toString() {
		return "Name of restaurant: " + this.getName() +
				"\n" + "Owner: " + this.getOwner() +
				"\n" + "Cook: " + cook +
				"\n" + "Server: " + server;
	}

	@Override
	public void workShift(int hours) {
		double hourlyWageCook = this.getCook().workHours(hours);
		double hourlyWageServer = this.getServer().workHours(hours);
		double hourlyWage = hourlyWageCook + hourlyWageServer;
		
		double currentSalaryExpenses = this.getOwner().getSalaryExpenses();
		double newSalaryExpenses = currentSalaryExpenses + hourlyWage;
		this.getOwner().setSalaryExpenses(newSalaryExpenses);
	}

	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {
		List<IncomeTaxPayer> list = new LinkedList<IncomeTaxPayer>();
		list.add(this.getCook());
		list.add(this.getServer());
		list.add(this.getOwner());
		return list;
	}

	@Override
	public void distributeIncomeAndSalesTax(Check check) {
		double currentIncomeOwner = this.getOwner().getIncome();
		double newIncomeOwner = currentIncomeOwner + check.getMenuPrice();
		this.getOwner().setIncome(newIncomeOwner);
		
		double currentIncomeCook = this.getCook().getIncome();
		double newIncomeCook = currentIncomeCook + (check.getTip() * 0.2);
		this.getCook().setIncome(newIncomeCook);
		
		double currentIncomeServer = this.getServer().getIncome();
		double newIncomeServer = currentIncomeServer + (check.getTip() * 0.8);
		this.getServer().setIncome(newIncomeServer);
		
		double currentSalesTax = this.getTotalSalesTax();
		double newSalesTax = currentSalesTax + check.getSalesTax();
		this.setTotalSalesTax(newSalesTax);
	}

	@Override
	public double getTipPercentage() {
		double tipPercentage = this.getServer().getTargetTipPct();
		return tipPercentage;
	}

}
