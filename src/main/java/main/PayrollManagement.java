package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.FinancialRecord;
import entity.Payroll;
import entity.Tax;

public class PayrollManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        try {
           
            String connectionString = "jdbc:mysql://localhost:3306/payrollmanagementsystem";
            String username = "root";
            String password = "VijiViji1515";
            connection = DriverManager.getConnection(connectionString, username, password);

            while (true) {
                System.out.println("*********MENU**********");
                
                System.out.println("1. Add Employee");
                
                
                
                System.out.println("2. Add Payroll");
                System.out.println("3. Generate Payroll");              
                System.out.println("4. Generate payroll by id");
                System.out.println("5. Generate payroll by employee");
                System.out.println("6. Generate payroll for period");
                
                System.out.println("7. Calculate Tax by employeeid and taxyear"); 
                System.out.println("8. Generate tax by ID");
                System.out.println("9. Generate tax by Employeeid");
                System.out.println("10.Generate tax by Year");
                
                System.out.println("11. Add Financial Record");
                System.out.println("12. Generate FinancialRecord By Id");
                System.out.println("13. Get FinancialRecords For Employee");
                System.out.println("14. Get Financial Records For Date");                
                System.out.println("15.Update Employee");
                System.out.println("16. Remove Employee"); 
                System.out.println("17. Get All Employees");
                
                System.out.println("18.Get All Employees by ID ");
                
                System.out.println("19. EXIT ");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addEmployee(connection, scanner);
                        break;
                        
                    case 2:
                    	addPayroll(connection,scanner);
                    	break;
                    case 3:
                        generatePayroll(connection);
                        break;
                    case 4:
                    	getPayrollById(connection,scanner);
                    	break;
                    case 5:	
                    	 getPayrollsForEmployee(connection,scanner);
                     	break;
                    case 6:
                     	getPayrollsForPeriod(connection,scanner);
                    	break;
                    	
                    case 7:
                    	calculateTax(connection,scanner);
                        break;
                    case 8:
                    	getTaxById(connection,scanner);
                    	break;
                    case 9:
                    	GetTaxesForEmployee(connection,scanner);
                    	break;                 
                    case 10:
                    	GetTaxesForYear(connection,scanner);
                    	break;
                    	
                    case 11:
                    	addFinancialRecord(connection,scanner);
                    	break;
                    case 12:
                    	getFinancialRecordById(connection,scanner);
                        break;
                    case 13:
                    	GetFinancialRecordsForEmployee(connection,scanner);
                    	break;
                    case 14:
                    	GetFinancialRecordsForDate(connection,scanner);
                    	break;      
                    case 15:
                    	UpdateEmployee(connection,scanner);
                    	break;
                    case 16:
                    	RemoveEmployee(connection,scanner);
                    	break;
                    case 17:
                    	getAllEmployees(connection);
                    	break;
                    case 18:
                    	getEmployeeById(connection,scanner);
                    	break;
                    case 19:
                        System.out.println("Exiting application...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
 
	 private static void getEmployeeById(Connection connection, Scanner scanner) {
		    System.out.print("Enter employee ID: ");
		    int employeeId = scanner.nextInt();

		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        String sql = "SELECT * FROM employee WHERE employeeid = ?";
		        preparedStatement = connection.prepareStatement(sql);
		        preparedStatement.setInt(1, employeeId);
		        resultSet = preparedStatement.executeQuery();

		        if (resultSet.next()) {
		            System.out.println("Employee Found:");
		            System.out.println("Employee ID: " + resultSet.getInt("employeeid"));
		            System.out.println("First Name: " + resultSet.getString("firstname"));
		            System.out.println("Last Name: " + resultSet.getString("lastname"));
		            System.out.println("Date of Birth: " + resultSet.getObject("dateofbirth", LocalDate.class));
		            System.out.println("Gender: " + resultSet.getString("gender"));
		            System.out.println("Email: " + resultSet.getString("email"));
		            System.out.println("Phone Number: " + resultSet.getString("phonenumber"));
		            System.out.println("Address: " + resultSet.getString("address"));
		            System.out.println("Position: " + resultSet.getString("position"));
		            System.out.println("Joining Date: " + resultSet.getObject("joiningdate", LocalDate.class));
		            System.out.println("Termination Date: " + resultSet.getObject("terminationdate", LocalDate.class));
		        } else {
		            System.out.println("Employee with ID " + employeeId + " not found.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (resultSet != null) {
		                resultSet.close();
		            }
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}


	//MENU 17

private static void getAllEmployees(Connection connection) {
	
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        String sql = "SELECT * FROM employee";
		        preparedStatement = connection.prepareStatement(sql);
		        resultSet = preparedStatement.executeQuery();

		        System.out.println("List of Employees:");
		        while (resultSet.next()) {
		            System.out.println("Employee ID: " + resultSet.getInt("employeeid"));
		            System.out.println("First Name: " + resultSet.getString("firstname"));
		            System.out.println("Last Name: " + resultSet.getString("lastname"));
		            System.out.println("Date of Birth: " + resultSet.getObject("dateofbirth", LocalDate.class));
		            System.out.println("Gender: " + resultSet.getString("gender"));
		            System.out.println("Email: " + resultSet.getString("email"));
		            System.out.println("Phone Number: " + resultSet.getString("phonenumber"));
		            System.out.println("Address: " + resultSet.getString("address"));
		            System.out.println("Position: " + resultSet.getString("position"));
		            System.out.println("Joining Date: " + resultSet.getObject("joiningdate", LocalDate.class));
		            System.out.println("Termination Date: " + resultSet.getObject("terminationdate", LocalDate.class));
		            System.out.println();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (resultSet != null) {
		                resultSet.close();
		            }
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}


	


//menu 16


private static void RemoveEmployee(Connection connection, Scanner scanner) {
    System.out.println("Enter the Employee ID of the employee you want to remove:");
    int employeeId = scanner.nextInt();
    scanner.nextLine(); 

    try {
        // Check if the employee exists
        String selectSql = "SELECT * FROM employee WHERE employeeid = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setInt(1, employeeId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Employee with ID " + employeeId + " does not exist.");
                return;
            }
        }

        String deleteSql = "DELETE FROM employee WHERE employeeid = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
            deleteStatement.setInt(1, employeeId);

            int rowsAffected = deleteStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee with ID " + employeeId + " removed successfully.");
            } else {
                System.out.println("Failed to remove employee with ID " + employeeId + ".");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




//ADD EMPLOYEE MENU 1   
    private static void addEmployee(Connection connection, Scanner scanner) throws SQLException {
    	Scanner scanner1 = new Scanner(System.in);
    	System.out.println("Enter employee details:");
    	  System.out.print("First Name: ");
          String firstName = scanner.next();
          System.out.print("Last Name: ");
          String lastName = scanner.next();
          System.out.print("Date of Birth (YYYY-MM-DD): ");
          String dob = scanner.next();
          System.out.print("Gender: ");
          String gender = scanner.next();
          System.out.print("Email: ");
          String email = scanner.next();
          System.out.print("Phone Number: ");
          String phoneNumber = scanner.next();
          System.out.print("Address: ");
          String address = scanner.next();
          System.out.println("Position: ");
          String position = scanner.next();
          System.out.print("Joining Date (YYYY-MM-DD): ");
          String joiningdate = scanner.next();
          // Add other employee details input here

          // Execute SQL query to insert employee data into the database
          String sql = "INSERT INTO Employee (employeeid,firstname, lastname ,dateofbirth, gender, email, phonenumber, address, position, joiningdate,terminationdate) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	  int employeeId = generateEmployeeId(connection);
              statement.setInt(1, employeeId);
              statement.setString(2, firstName);
              statement.setString(3, lastName);
              statement.setString(4, dob);
              statement.setString(5, gender);
              statement.setString(6, email);
              statement.setString(7, phoneNumber);
              statement.setString(8, address);
              statement.setString(9, position);
              statement.setString(10, joiningdate);
              statement.setNull(11, java.sql.Types.DATE);
              // Set other statement parameters here
              statement.executeUpdate();
              System.out.println("Employee added successfully.");
          }
    }
    
//ADD PAYROLL MENU 2
    private static void addPayroll(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter payroll details:");
        System.out.print("Payroll ID: ");
        int payrollId = scanner.nextInt();
        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Pay Period Start Date (YYYY-MM-DD): ");
        String payPeriodStartDateStr = scanner.next();
        LocalDate payPeriodStartDate = LocalDate.parse(payPeriodStartDateStr);
        System.out.print("Pay Period End Date (YYYY-MM-DD): ");
        String payPeriodEndDateStr = scanner.next();
        LocalDate payPeriodEndDate = LocalDate.parse(payPeriodEndDateStr);
        System.out.print("Basic Salary: ");
        double basicSalary = scanner.nextDouble();
        System.out.print("Overtime Pay: ");
        double overtimePay = scanner.nextDouble();
        System.out.print("Deductions: ");
        double deductions = scanner.nextDouble();
        
        // Calculate net salary
        double netSalary = basicSalary + overtimePay - deductions;

        // Execute SQL query to insert payroll data into the database
        String sql = "INSERT INTO payroll (payrollid, employeeid, payperiodstartdate, payperiodenddate, basicsalary, overtimepay, deductions, netsalary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payrollId);
            statement.setInt(2, employeeId);
            statement.setDate(3, java.sql.Date.valueOf(payPeriodStartDate));
            statement.setDate(4, java.sql.Date.valueOf(payPeriodEndDate));
            statement.setDouble(5, basicSalary);
            statement.setDouble(6, overtimePay);
            statement.setDouble(7, deductions);
            statement.setDouble(8, netSalary);
            
            statement.executeUpdate();
            System.out.println("Payroll added successfully.");
        }
    }
    
    
    //GENERATE PAYROLL -MENU 3
      private static final String PAYROLL_QUERY = "SELECT * FROM payroll";
      public static void generatePayroll(Connection connection) throws SQLException {
          try (PreparedStatement statement = connection.prepareStatement(PAYROLL_QUERY);
               ResultSet resultSet = statement.executeQuery()) {

              System.out.println("Generating Payroll:");
              System.out.println("---------------------");

              while (resultSet.next()) {
                  int payrollID = resultSet.getInt("payrollid");
                  int employeeID = resultSet.getInt("employeeid");
                  LocalDate payPeriodStartDate = resultSet.getDate("payperiodstartdate").toLocalDate();
                  LocalDate payPeriodEndDate = resultSet.getDate("payperiodenddate").toLocalDate();
                  double basicSalary = resultSet.getDouble("basicsalary");
                  double overtimePay = resultSet.getDouble("overtimepay");
                  double deductions = resultSet.getDouble("deductions");

                  // Calculate net salary
                  double netSalary = basicSalary + overtimePay - deductions;

                  // Create Payroll object
                  Payroll payroll = new Payroll(payrollID, employeeID, payPeriodStartDate, payPeriodEndDate,
                          basicSalary, overtimePay, deductions, netSalary);

                  // Print payroll details
                  System.out.println(payroll);
                  System.out.println("---------------------");
              }

              System.out.println("Payroll generation completed.");
          }
      }

    //GENERATE PAYROLL BY ID - MENU 4	
  	private static void getPayrollById(Connection connection, Scanner scanner) {
          try {
              System.out.print("Enter Payroll ID: ");
              int payrollId = scanner.nextInt();

              // Execute SQL query to retrieve payroll data by ID
              String sql = "SELECT * FROM payroll WHERE payrollid = ?";
              try (PreparedStatement statement = connection.prepareStatement(sql)) {
                  statement.setInt(1, payrollId);
                  try (ResultSet resultSet = statement.executeQuery()) {
                      if (resultSet.next()) {
                          // Map ResultSet data to Payroll object
                          Payroll payroll = mapResultSetToPayroll(resultSet);

                          // Display payroll details
                          System.out.println("Payroll Details:");
                          System.out.println(payroll);
                      } else {
                          System.out.println("No payroll found with ID: " + payrollId);
                      }
                  }
              }
          } catch (SQLException e) {
              System.out.println("Error retrieving payroll: " + e.getMessage());
          }
      }

      // Helper method to map ResultSet data to Payroll object
      private static Payroll mapResultSetToPayroll(ResultSet resultSet) throws SQLException {
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
   
      
   
    //GENERATE PAYROLLS FOR EMPLOYEE MENU-5
		private static void getPayrollsForEmployee(Connection connection, Scanner scanner) {
    	    try {
    	        System.out.print("Enter Employee ID: ");
    	        int employeeId = scanner.nextInt();

    	        // Execute SQL query to retrieve all payrolls for the specified employee
    	        String sql = "SELECT * FROM payroll WHERE employeeid = ?";
    	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
    	            statement.setInt(1, employeeId);
    	            try (ResultSet resultSet = statement.executeQuery()) {
    	                List<Payroll> employeePayrolls = new ArrayList<>();
    	                while (resultSet.next()) {
    	                    // Map each ResultSet row to a Payroll object and add to the list
    	                    Payroll payroll = mapResultSetToPayroll(resultSet);
    	                    employeePayrolls.add(payroll);
    	                }

    	                // Display payroll details for the employee
    	                if (!employeePayrolls.isEmpty()) {
    	                    System.out.println("Payrolls for Employee ID: " + employeeId);
    	                    for (Payroll payroll : employeePayrolls) {
    	                        System.out.println(payroll);
    	                    }
    	                } else {
    	                    System.out.println("No payrolls found for Employee ID: " + employeeId);
    	                }
    	            }
    	        }
    	    } catch (SQLException e) {
    	        System.out.println("Error retrieving payrolls for employee: " + e.getMessage());
    	    }
    	}

	

   private static int generateEmployeeId(Connection connection) throws SQLException {
        int employeeId = 0;
        String sql = "SELECT MAX(employeeid) FROM employee";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                employeeId = resultSet.getInt(1) + 1;
            } else {
                employeeId = 1;
            }
        }
        return employeeId;
    }


 //GENERATE PAYROLLS FOR PERIOD MENU-6  
   
   private static void getPayrollsForPeriod(Connection connection, Scanner scanner) {
       try {
           System.out.print("Enter Start Date (YYYY-MM-DD): ");
           String startDateStr = scanner.next();
           LocalDate startDate = LocalDate.parse(startDateStr);

           System.out.print("Enter End Date (YYYY-MM-DD): ");
           String endDateStr = scanner.next();
           LocalDate endDate = LocalDate.parse(endDateStr);

           // Execute SQL query to retrieve all payrolls for the specified period
           String sql = "SELECT * FROM payroll WHERE payperiodstartdate >= ? AND payperiodenddate <= ?";
           try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setDate(1, java.sql.Date.valueOf(startDate));
               statement.setDate(2, java.sql.Date.valueOf(endDate));
               try (ResultSet resultSet = statement.executeQuery()) {
                   List<Payroll> periodPayrolls = new ArrayList<>();
                   while (resultSet.next()) {
                       // Map each ResultSet row to a Payroll object and add to the list
                       Payroll payroll = mapResultSetToPayroll(resultSet);
                       periodPayrolls.add(payroll);
                   }

                   // Display payroll details for the period
                   if (!periodPayrolls.isEmpty()) {
                       System.out.println("Payrolls for Period from " + startDate + " to " + endDate);
                       for (Payroll payroll : periodPayrolls) {
                           System.out.println(payroll);
                       }
                   } else {
                       System.out.println("No payrolls found for the specified period.");
                   }
               }
           }
       } catch (SQLException e) {
           System.out.println("Error retrieving payrolls for the period: " + e.getMessage());
       }
   }

  // CALCULATE TAX MENU-7
   private static void calculateTax(Connection connection, Scanner scanner) {
	    try {
	        // Prompt the user to enter the employee ID and tax year
	        System.out.print("Enter Employee ID: ");
	        int employeeId = scanner.nextInt();
	        System.out.print("Enter Tax Year: ");
	        int taxYear = scanner.nextInt();

	        // Execute SQL query to retrieve the tax information
	        String sql = "SELECT * FROM tax WHERE employeeID = ? AND taxYear = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, employeeId);
	            statement.setInt(2, taxYear);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Retrieve tax information
	                    double taxableIncome = resultSet.getDouble("taxableIncome");
	                    double taxAmount = resultSet.getDouble("taxAmount");

	                    // Display tax details
	                    System.out.println("Tax Details:");
	                    System.out.println("Employee ID: " + employeeId);
	                    System.out.println("Tax Year: " + taxYear);
	                    System.out.println("Taxable Income: " + taxableIncome);
	                    System.out.println("Tax Amount: " + taxAmount);
	                } else {
	                    System.out.println("No tax information found for Employee ID " + employeeId + " for tax year " + taxYear);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error calculating tax: " + e.getMessage());
	    }
	}

// GENERATE TAX BY ID MENU -8
private static void getTaxById(Connection connection, Scanner scanner) {
    try {
        // Prompt the user to enter the tax ID
        System.out.print("Enter Tax ID: ");
        int taxId = scanner.nextInt();

        // Execute SQL query to retrieve the tax information
        String sql = "SELECT * FROM tax WHERE taxID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taxId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve tax information
                    int employeeId = resultSet.getInt("employeeID");
                    int taxYear = resultSet.getInt("taxYear");
                    double taxableIncome = resultSet.getDouble("taxableIncome");
                    double taxAmount = resultSet.getDouble("taxAmount");

                    // Display tax details
                    System.out.println("Tax Details:");
                    System.out.println("Tax ID: " + taxId);
                    System.out.println("Employee ID: " + employeeId);
                    System.out.println("Tax Year: " + taxYear);
                    System.out.println("Taxable Income: " + taxableIncome);
                    System.out.println("Tax Amount: " + taxAmount);
                } else {
                    System.out.println("No tax information found for Tax ID " + taxId);
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving tax information: " + e.getMessage());
    }
}

//GENERATE TAXES FOR EMPLOYEE MENU -9
private static void GetTaxesForEmployee(Connection connection, Scanner scanner) {
  try {
      // Prompt the user to enter the employee ID
      System.out.print("Enter Employee ID: ");
      int employeeId = scanner.nextInt();

      // Execute SQL query to retrieve tax information for the employee
      String sql = "SELECT * FROM tax WHERE employeeID = ?";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
          statement.setInt(1, employeeId);
          try (ResultSet resultSet = statement.executeQuery()) {
              boolean found = false;
              while (resultSet.next()) {
                  // Retrieve tax information
                  int taxId = resultSet.getInt("taxID");
                  int taxYear = resultSet.getInt("taxYear");
                  double taxableIncome = resultSet.getDouble("taxableIncome");
                  double taxAmount = resultSet.getDouble("taxAmount");

                  // Display tax details
                  System.out.println("Tax ID: " + taxId);
                  System.out.println("Tax Year: " + taxYear);
                  System.out.println("Taxable Income: " + taxableIncome);
                  System.out.println("Tax Amount: " + taxAmount);
                  System.out.println("---------------------");
                  found = true;
              }
              if (!found) {
                  System.out.println("No tax information found for Employee ID " + employeeId);
              }
          }
      }
  } catch (SQLException e) {
      System.out.println("Error retrieving tax information: " + e.getMessage());
  }
}



//Generate taxes for year MENU-10

private static void GetTaxesForYear(Connection connection, Scanner scanner) {
  try {
      // Prompt the user to enter the tax year
      System.out.print("Enter Tax Year: ");
      int taxYear = scanner.nextInt();

      // Execute SQL query to retrieve tax information for the specified year
      String sql = "SELECT * FROM tax WHERE taxYear = ?";
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
          statement.setInt(1, taxYear);
          try (ResultSet resultSet = statement.executeQuery()) {
              boolean found = false;
              while (resultSet.next()) {
                  // Retrieve tax information
                  int taxId = resultSet.getInt("taxID");
                  int employeeId = resultSet.getInt("employeeID");
                  double taxableIncome = resultSet.getDouble("taxableIncome");
                  double taxAmount = resultSet.getDouble("taxAmount");

                  // Display tax details
                  System.out.println("Tax ID: " + taxId);
                  System.out.println("Employee ID: " + employeeId);
                  System.out.println("Taxable Income: " + taxableIncome);
                  System.out.println("Tax Amount: " + taxAmount);
                  System.out.println("---------------------");
                  found = true;
              }
              if (!found) {
                  System.out.println("No tax information found for Tax Year " + taxYear);
              }
          }
      }
  } catch (SQLException e) {
      System.out.println("Error retrieving tax information: " + e.getMessage());
  }
}



	// GENERATE FINANCIAL RECORD FOR EMPLOYEE - 13
	private static void GetFinancialRecordsForEmployee(Connection connection, Scanner scanner) {
			// TODO Auto-generated method stub
		
			
		}

 

 //ADD FINANCIAL RECORD MENU - 11
    private static final String INSERT_FINANCIAL_RECORD_QUERY = "INSERT INTO financialrecord (recordid,employeeID, recordDate, description, amount, recordType) VALUES (?,?, ?, ?, ?, ?)";
    public static void addFinancialRecord(Connection connection,Scanner scanner) throws SQLException {
        // Get user input for financial record details
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter financial record details:");
        System.out.print("Record ID: ");
        int recordid = scanner.nextInt();
        System.out.print("Employee ID: ");
        int employeeID = scanner.nextInt();
        System.out.print("Record Date (yyyy-MM-dd): ");
        String recordDateStr = scanner.next();
        LocalDate recordDate = LocalDate.parse(recordDateStr);
        System.out.print("Description: ");
        String description = scanner.next();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Record Type: ");
        String recordType = scanner.next();

        // Insert financial record into the database
        try (PreparedStatement statement = connection.prepareStatement(INSERT_FINANCIAL_RECORD_QUERY)) {
        	statement.setInt(1,recordid);
            statement.setInt(2, employeeID);
            statement.setDate(3, java.sql.Date.valueOf(recordDate));
            statement.setString(4, description);
            statement.setDouble(5, amount);
            statement.setString(6, recordType);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Financial record added successfully.");
            } else {
                System.out.println("Failed to add financial record.");
            }
        }
        
    }
  
    
  

 // GET FINANCIAL RECORD BY ID MENU-12
 	private static void getFinancialRecordById(Connection connection, Scanner scanner) {
       try {
           System.out.print("Enter Record ID: ");
           int recordId = scanner.nextInt();

           // Execute SQL query to retrieve the financial record by ID
           String sql = "SELECT * FROM financialrecord WHERE recordid = ?";
           try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setInt(1, recordId);
               try (ResultSet resultSet = statement.executeQuery()) {
                   if (resultSet.next()) {
                       // Map the ResultSet row to a FinancialRecord object
                       int employeeID = resultSet.getInt("employeeID");
                       LocalDate recordDate = resultSet.getDate("recordDate").toLocalDate();
                       String description = resultSet.getString("description");
                       double amount = resultSet.getDouble("amount");
                       String recordType = resultSet.getString("recordType");

                       // Display financial record details
                       System.out.println("Financial Record Details:");
                       System.out.println("Record ID: " + recordId);
                       System.out.println("Employee ID: " + employeeID);
                       System.out.println("Record Date: " + recordDate);
                       System.out.println("Description: " + description);
                       System.out.println("Amount: " + amount);
                       System.out.println("Record Type: " + recordType);
                   } else {
                       System.out.println("No financial record found with ID: " + recordId);
                   }
               }
           }
       } catch (SQLException e) {
           System.out.println("Error retrieving financial record: " + e.getMessage());
       }
   }   
   

 // GetFinancialRecordsForDate MENU 14

 private static void GetFinancialRecordsForDate(Connection connection, Scanner scanner) {
 		// TODO Auto-generated method stub
 	System.out.print("Enter record date (yyyy-MM-dd): ");
     String recordDateStr = scanner.next();

     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;

     try {
         // SQL query to retrieve financial records for the specified date
         String sql = "SELECT * FROM financialrecord WHERE recordDate = ?";
         preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, recordDateStr);
         resultSet = preparedStatement.executeQuery();

         // Display the financial records for the specified date
         while (resultSet.next()) {
             FinancialRecord financialRecord = new FinancialRecord();
             financialRecord.setRecordID(resultSet.getInt("recordId"));
             financialRecord.setEmployeeID(resultSet.getInt("employeeId"));
             financialRecord.setDescription(resultSet.getString("description"));
             financialRecord.setAmount(resultSet.getDouble("amount"));
             financialRecord.setRecordType(resultSet.getString("recordType"));
             System.out.println("Financial Record:");
             System.out.println("Record ID: " + financialRecord.getRecordID());
             System.out.println("Employee ID: " + financialRecord.getEmployeeID());
             System.out.println("Description: " + financialRecord.getDescription());
             System.out.println("Amount: " + financialRecord.getAmount());
             System.out.println("Record Type: " + financialRecord.getRecordType());
             System.out.println("---------------------------------------");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }      
     }


//MENU 15

private static void UpdateEmployee(Connection connection, Scanner scanner) {
		// TODO Auto-generated method stub
	 System.out.println("Enter the Employee ID of the employee you want to update:");
    int employeeId = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    try {
        // Check if the employee exists
        String selectSql = "SELECT * FROM employee WHERE employeeid = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setInt(1, employeeId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Employee with ID " + employeeId + " does not exist.");
                return;
            }
        }

        // Prompt user for updated employee details
        System.out.println("Enter updated details for the employee (press Enter to skip):");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Position: ");
        String position = scanner.nextLine();
        System.out.print("Joining Date (YYYY-MM-DD): ");
        String joiningDate = scanner.nextLine();
        System.out.print("Termination Date (YYYY-MM-DD): ");
        String terminationDate = scanner.nextLine();

        // Update the employee record in the database
        String updateSql = "UPDATE employee SET firstname = ?, lastname = ?, dateofbirth = ?, gender = ?, email = ?, phonenumber = ?, address = ?, position = ?, joiningdate = ?, terminationdate = ? WHERE employeeid = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setString(1, firstName.isEmpty() ? null : firstName);
            updateStatement.setString(2, lastName.isEmpty() ? null : lastName);
            updateStatement.setString(3, dob.isEmpty() ? null : dob);
            updateStatement.setString(4, gender.isEmpty() ? null : gender);
            updateStatement.setString(5, email.isEmpty() ? null : email);
            updateStatement.setString(6, phoneNumber.isEmpty() ? null : phoneNumber);
            updateStatement.setString(7, address.isEmpty() ? null : address);
            updateStatement.setString(8, position.isEmpty() ? null : position);
            updateStatement.setString(9, joiningDate.isEmpty() ? null : joiningDate);
            updateStatement.setString(10, terminationDate.isEmpty() ? null : terminationDate);
            updateStatement.setInt(11, employeeId);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee with ID " + employeeId + " updated successfully.");
            } else {
                System.out.println("Failed to update employee with ID " + employeeId + ".");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

	}





//1
	public static void processPayrollForMultipleEmployees() {
	// TODO Auto-generated method stub
	
}
	
//2

public static void verifyErrorHandlingForInvalidEmployeeData() {
	// TODO Auto-generated method stub
	
}


//3
public static double calculateGrossSalaryForEmployee(int basicSalary, int overtimePay) {
	// TODO Auto-generated method stub
	 
	    return basicSalary + overtimePay;

}

//4

public static double calculateNetSalaryAfterDeductions(double grossSalary, double deductions) {
	// TODO Auto-generated method stub
double netSalary = grossSalary - deductions;
    
    // Ensure net salary is not negative
    if (netSalary < 0) {
        return 0; // Return 0 if net salary is negative
    }
    
    return netSalary;
}
//5

public static double calculateTaxForHighIncomeEmployee(int highIncome) {
	// TODO Auto-generated method stub
	double taxRate = 0.30; // 30% tax rate
    double threshold = 50000; // Threshold for high-income

    // If the income is above the threshold, calculate tax
    if (highIncome > threshold) {
        double taxableIncome = highIncome - threshold;
        return taxableIncome * taxRate;
    } else {
        return 0; // No tax if income is below the threshold
    }
}


 
}