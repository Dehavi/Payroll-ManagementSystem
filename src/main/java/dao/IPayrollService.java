
package dao;

import entity.Payroll;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IPayrollService {
    void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate);
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    
   // Payroll getPayrollById(Connection connection, int payrollId) throws SQLException;

    List<Payroll> getPayrollsForEmployee(Connection connection, int employeeId) throws SQLException;

    List<Payroll> getPayrollsForPeriod(Connection connection, LocalDate startDate, LocalDate endDate) throws SQLException;

   
}

