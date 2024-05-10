package dao;
import entity.Tax;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxServiceImpl implements ITaxService {
    private Map<Integer, List<Tax>> taxesByEmployee;
    private Map<Integer, Tax> taxesById;

    public TaxServiceImpl() {
        this.taxesByEmployee = new HashMap<>();
        this.taxesById = new HashMap<>();
    }

    @Override
    public void calculateTax(int employeeId, int taxYear) {
        // For demonstration purposes, we'll print a message
        System.out.println("Placeholder implementation for calculating tax for Employee ID: " + employeeId +
                " for the tax year: " + taxYear);
    }

    @Override
    public Tax getTaxById(int taxId) {
        return taxesById.get(taxId);
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        return taxesByEmployee.getOrDefault(employeeId, new ArrayList<>());
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxesForYear = new ArrayList<>();
        for (List<Tax> taxes : taxesByEmployee.values()) {
            for (Tax tax : taxes) {
                if (tax.getTaxYear() == taxYear) {
                    taxesForYear.add(tax);
                }
            }
        }
        return taxesForYear;
    }
}
