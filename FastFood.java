
import java.util.ArrayList;
import java.util.List;

public class FastFood extends FoodPlace {

    private List<Staff> staff = new ArrayList<>();

    public FastFood(String name, double fixedCosts, Owner owner, List<Staff> staff) {
        super(name,fixedCosts,owner);
        
        List<Staff> copyStaff = new ArrayList<Staff>();
        for (Staff eachStaff : staff) {
        	copyStaff.add(eachStaff);
        }
        this.staff = copyStaff;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name of FastFood: " + this.getName() +
                "\n" + "Owner: " + this.getOwner());
        int index = 1;
        for (Staff staff: staff) {
            builder.append("\n" + "Staff " + index++ + " : " + staff );
        }
        return builder.toString();
    }

    @Override
    public void workShift(int hours) {
        double salaryExpenses = 0;
        for (Staff staff : this.getStaff()) {
        	double hourlyWage = staff.workHours(hours);
        	salaryExpenses += hourlyWage;
        }
        
        double currentSalaryExpenses = this.getOwner().getSalaryExpenses();
		double newSalaryExpenses = currentSalaryExpenses + salaryExpenses;
		this.getOwner().setSalaryExpenses(newSalaryExpenses);
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {
        List<IncomeTaxPayer> list = new ArrayList<IncomeTaxPayer>();
        list.add(this.getOwner());
        for (Staff staff: this.getStaff()) {
        	list.add(staff);
        }
        return list;
    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {
    	double currentIncomeOwner = this.getOwner().getIncome();
		double newIncomeOwner = currentIncomeOwner + check.getMenuPrice();
		this.getOwner().setIncome(newIncomeOwner);
		
		double tip = check.getTip();
		int numStaff = this.getStaff().size();
		double tipEachStaff = tip / numStaff;
		for (Staff eachStaff: this.getStaff()) {
			double currentIncomeStaff = eachStaff.getIncome();
			double newIncomeStaff = currentIncomeStaff + (tipEachStaff);
			eachStaff.setIncome(newIncomeStaff);
		}
		
		double currentSalesTax = this.getTotalSalesTax();
        double newSalesTax = currentSalesTax + check.getSalesTax();
        this.setTotalSalesTax(newSalesTax);
    }

    @Override
    public double getTipPercentage() {
        return 0;
    }
}
