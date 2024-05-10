package dao;
import entity.Tax;
import java.util.List;

public interface ITaxService {
    void calculateTax(int employeeId, int taxYear);
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}
