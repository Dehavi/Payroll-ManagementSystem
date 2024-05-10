package dao;

import entity.Payroll;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PayrollServiceImpl implements IPayrollService {
    private Map<Integer, Payroll> payrolls;

    public PayrollServiceImpl() {
        this.payrolls = new HashMap<>();
    }

 //1  
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) {
        try (Connection connection = DBConnUtil.getConnection()) {
            // TODO: Implement payroll generation logic using SQL queries

            // Example SQL query to insert payroll data into the database
            String sql = "INSERT INTO payroll (employeeid, payperiodstartdate, payperiodenddate, basicsalary, overtimepay, deductions, netsalary) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            statement.setDate(2, java.sql.Date.valueOf(startDate));
            statement.setDate(3, java.sql.Date.valueOf(endDate));
            // Set other payroll data parameters as needed
            // statement.setDouble(4, basicSalary);
            // statement.setDouble(5, overtimePay);
            //statement.setDouble(6, deductions);
            // statement.setDouble(7, netSalary);
            
            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Payroll generated successfully for Employee ID: " + employeeId +
                                   " for the period from " + startDate + " to " + endDate);
            } else {
                System.out.println("Failed to generate payroll for Employee ID: " + employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or query execution errors
        }
    }

   

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> employeePayrolls = new ArrayList<>();
        for (Payroll payroll : payrolls.values()) {
            if (payroll.getEmployeeID() == employeeId) {
                employeePayrolls.add(payroll);
            }
        }
        return employeePayrolls;
    }

 
    
	@Override
	public Payroll getPayrollById(int payrollId) {
		// TODO Auto-generated method stub
		return null;
	}

//2


	private Payroll mapResultSetToPayroll(ResultSet resultSet) throws SQLException {
	    int payrollId = resultSet.getInt("payrollid");
	    int employeeId = resultSet.getInt("employeeid");
	    LocalDate startDate = resultSet.getDate("payperiodstartdate").toLocalDate();
	    LocalDate endDate = resultSet.getDate("payperiodenddate").toLocalDate();
	    double basicSalary = resultSet.getDouble("basicsalary");
	    double overtimePay = resultSet.getDouble("overtimepay");
	    double deductions = resultSet.getDouble("deductions");
	    double netSalary = resultSet.getDouble("netsalary");
	    return new Payroll(payrollId, employeeId, startDate, endDate, basicSalary, overtimePay, deductions, netSalary);
	}


//3

	public List<Payroll> getPayrollsForEmployee(Connection connection, int employeeId) throws SQLException {
	    List<Payroll> employeePayrolls = new ArrayList<>();
	    // Execute SQL query to retrieve all payrolls for a specific employee
	    String sql = "SELECT * FROM payroll WHERE employeeid = ?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, employeeId);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                // Map each ResultSet row to a Payroll object and add to the list
	                Payroll payroll = mapResultSetToPayroll(resultSet);
	                employeePayrolls.add(payroll);
	            }
	        }
	    }
	    return employeePayrolls;
	}
//4
	public List<Payroll> getPayrollsForPeriod(Connection connection, LocalDate startDate, LocalDate endDate)
			throws SQLException {
		
    List<Payroll> periodPayrolls = new ArrayList<>();
    for (Payroll payroll : payrolls.values()) {
        LocalDate payrollStartDate = payroll.getPayPeriodStartDate();
        LocalDate payrollEndDate = payroll.getPayPeriodEndDate();
        if (payrollStartDate.isEqual(startDate) || payrollStartDate.isAfter(startDate)) {
            if (payrollEndDate.isEqual(endDate) || payrollEndDate.isBefore(endDate)) {
                periodPayrolls.add(payroll);
            }
        }
    }
    return periodPayrolls;
}

	}

