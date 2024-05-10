package dao;
import entity.Employee;
import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void addEmployee(Employee employeeData);
    void updateEmployee(Employee employeeData);
    void removeEmployee(int employeeId);
}
