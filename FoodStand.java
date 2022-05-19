
import java.util.List;
import java.util.LinkedList;

public class FoodStand extends FoodPlace {

    public FoodStand(String name, double fixedCosts, WorkingOwner owner) {
        super(name,fixedCosts,owner);
        
    }

    @Override
    public String toString() {
        return "Name of FoodStand: " + this.getName() +
                "\n" + "Owner: " + this.getOwner();
    }

    @Override
    public void workShift(int hours) {
        
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {
        List<IncomeTaxPayer> list = new LinkedList<IncomeTaxPayer>();
        list.add(this.getOwner());
        return list;
    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {
        double currentIncomeOwner = this.getOwner().getIncome();
        double newIncomeOwner = currentIncomeOwner + check.getMenuPrice() + check.getTip();
        this.getOwner().setIncome(newIncomeOwner);
        
        double currentSalesTax = this.getTotalSalesTax();
        double newSalesTax = currentSalesTax + check.getSalesTax();
        this.setTotalSalesTax(newSalesTax);
    }

    @Override
    public double getTipPercentage() {
        WorkingOwner owner = (WorkingOwner) this.getOwner();
        double tipPercentage = owner.getTargetTipPct();
        return tipPercentage;
    }
}
