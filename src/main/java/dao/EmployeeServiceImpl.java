package dao;
import entity.Employee;
import util.DBConnUtil;
 import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "SELECT * FROM employee WHERE employeeid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeid"));
                employee.setFirstName(resultSet.getString("firstname"));
                employee.setLastName(resultSet.getString("lastname"));
                employee.setDateOfBirth(resultSet.getObject("dateofbirth", LocalDate.class));
                employee.setGender(resultSet.getString("gender"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhoneNumber(resultSet.getString("phonenumber"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPosition(resultSet.getString("position"));
                employee.setJoiningDate(resultSet.getObject("joiningdate", LocalDate.class));
                employee.setTerminationDate(resultSet.getObject("terminationdate", LocalDate.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeid"));
                employee.setFirstName(resultSet.getString("firstname"));
                employee.setLastName(resultSet.getString("lastname"));
                employee.setDateOfBirth(resultSet.getObject("dateofbirth", LocalDate.class));
                employee.setGender(resultSet.getString("gender"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhoneNumber(resultSet.getString("phonenumber"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPosition(resultSet.getString("position"));
                employee.setJoiningDate(resultSet.getObject("joiningdate", LocalDate.class));
                employee.setTerminationDate(resultSet.getObject("terminationdate", LocalDate.class));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }

        return employees;
    }

    @Override
    public void addEmployee(Employee employeeData) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "INSERT INTO employee (employeeid, firstname, lastname, dateofbirth, gender, email, phonenumber, address, position, joiningdate, terminationdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeData.getEmployeeID());
            preparedStatement.setString(2, employeeData.getFirstName());
            preparedStatement.setString(3, employeeData.getLastName());
            preparedStatement.setObject(4, employeeData.getDateOfBirth());
            preparedStatement.setString(5, employeeData.getGender());
            preparedStatement.setString(6, employeeData.getEmail());
            preparedStatement.setString(7, employeeData.getPhoneNumber());
            preparedStatement.setString(8, employeeData.getAddress());
            preparedStatement.setString(9, employeeData.getPosition());
            preparedStatement.setObject(10, employeeData.getJoiningDate());
            preparedStatement.setObject(11, employeeData.getTerminationDate());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }
    }

    @Override
    public void updateEmployee(Employee employeeData) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "UPDATE employee SET firstname = ?, lastname = ?, dateofbirth = ?, gender = ?, email = ?, phonenumber = ?, address = ?, position = ?, joiningdate = ?, terminationdate = ? WHERE employeeid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeData.getFirstName());
            preparedStatement.setString(2, employeeData.getLastName());
            preparedStatement.setObject(3, employeeData.getDateOfBirth());
            preparedStatement.setString(4, employeeData.getGender());
            preparedStatement.setString(5, employeeData.getEmail());
            preparedStatement.setString(6, employeeData.getPhoneNumber());
            preparedStatement.setString(7, employeeData.getAddress());
            preparedStatement.setString(8, employeeData.getPosition());
            preparedStatement.setObject(9, employeeData.getJoiningDate());
            preparedStatement.setObject(10, employeeData.getTerminationDate());
            preparedStatement.setInt(11, employeeData.getEmployeeID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }
    }

    @Override
    public void removeEmployee(int employeeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "DELETE FROM employee WHERE employeeid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }
    }


    // Other methods: addEmployee, updateEmployee, removeEmployee
}