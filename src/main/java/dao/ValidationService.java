package dao;

import entity.Employee;

public class ValidationService {
    public static boolean validateEmployeeData(Employee employee) {
        // Check if any of the required fields are empty or null
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            return false;
        }
        if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
            return false;
        }
        if (employee.getDateOfBirth() == null) {
            return false;
        }
        if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            return false;
        }
        // Add more validation checks as needed
        
        // If all checks pass, return true
        return true;
    }

    // Other validation methods can be added here for different entities and operations
}
