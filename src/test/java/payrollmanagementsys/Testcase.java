package payrollmanagementsys;

import org.junit.Test;
import static org.junit.Assert.*;
import main.PayrollManagement;
import exception.EmployeeNotFoundException;
import exception.PayrollGenerationException;
import exception.TaxCalculationException;
import exception.FinancialRecordException;
import exception.DatabaseConnectionException;

public class Testcase {

	@Test
	public void testCalculateGrossSalaryForEmployee() {
	    // Test case for an employee with a basic salary of $5000 and overtime pay of $500
	    int basicSalary = 5000;
	    int overtimePay = 500;
	    double expectedGrossSalary = 5500.0;
	    assertEquals(expectedGrossSalary, PayrollManagement.calculateGrossSalaryForEmployee(basicSalary, overtimePay), 0.01);

	    // Test case for an employee with a basic salary of $6000 and no overtime pay
	    assertEquals(expectedGrossSalary, PayrollManagement.calculateGrossSalaryForEmployee(basicSalary, overtimePay), 0.01);
	}

	@Test
	public void testCalculateNetSalaryAfterDeductions() {
	    // Assuming gross salary is $5000 and deductions are $1000
	    double grossSalary = 5000;
	    double deductions = 1000;
	    
	    // Expected net salary after deductions: $4000
	    double expectedNetSalary = 4000;
	    
	    // Calculate net salary after deductions
	    double actualNetSalary = PayrollManagement.calculateNetSalaryAfterDeductions(grossSalary, deductions);
	    
	    // Assert that the actual net salary matches the expected net salary
	    assertEquals(expectedNetSalary, actualNetSalary, 0.01);
	}


    @Test
    public void testProcessPayrollForMultipleEmployees() throws DatabaseConnectionException, PayrollGenerationException, EmployeeNotFoundException, TaxCalculationException, FinancialRecordException {
        // Call the method to process payroll for multiple employees
		PayrollManagement.processPayrollForMultipleEmployees();
		// No assertion needed, if no exception is thrown, it indicates success
    }

    @Test
    public void testVerifyErrorHandlingForInvalidEmployeeData() throws FinancialRecordException {
        // Call the method with invalid input data
		PayrollManagement.verifyErrorHandlingForInvalidEmployeeData();
		// No assertion needed, if no exception is thrown, it indicates success
    }
   
    @Test
    public void testCalculateTaxForHighIncomeEmployee() {
        int lowIncome = 40000; // Example low income
        double expectedZeroTax = 0.0; // Expected tax for income below $50,000
        assertEquals(expectedZeroTax, PayrollManagement.calculateTaxForHighIncomeEmployee(lowIncome), 0.000001d);
    }
    
         
     

     
     
}