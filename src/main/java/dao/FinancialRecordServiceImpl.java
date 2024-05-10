package dao;
import entity.FinancialRecord;
import util.DBConnUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordServiceImpl implements IFinancialRecordService {

    @Override
    public void addFinancialRecord(int employeeId, String description, double amount, String recordType) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "INSERT INTO financialrecord (employeeid, recorddate, description, amount, recordtype) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setObject(2, LocalDate.now());
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, amount);
            preparedStatement.setString(5, recordType);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        FinancialRecord financialRecord = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "SELECT * FROM financialrecord WHERE recordid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, recordId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                financialRecord = new FinancialRecord();
                financialRecord.setRecordID(resultSet.getInt("recordid"));
                financialRecord.setEmployeeID(resultSet.getInt("employeeid"));
                financialRecord.setRecordDate(resultSet.getObject("recorddate", LocalDate.class));
                financialRecord.setDescription(resultSet.getString("description"));
                financialRecord.setAmount(resultSet.getDouble("amount"));
                financialRecord.setRecordType(resultSet.getString("recordtype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }

        return financialRecord;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "SELECT * FROM financialrecord WHERE employeeid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FinancialRecord financialRecord = new FinancialRecord();
                financialRecord.setRecordID(resultSet.getInt("recordid"));
                financialRecord.setEmployeeID(resultSet.getInt("employeeid"));
                financialRecord.setRecordDate(resultSet.getObject("recorddate", LocalDate.class));
                financialRecord.setDescription(resultSet.getString("description"));
                financialRecord.setAmount(resultSet.getDouble("amount"));
                financialRecord.setRecordType(resultSet.getString("recordtype"));
                financialRecords.add(financialRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }

        return financialRecords;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnUtil.getConnection();
            String sql = "SELECT * FROM financialrecord WHERE recorddate = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, recordDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FinancialRecord financialRecord = new FinancialRecord();
                financialRecord.setRecordID(resultSet.getInt("recordid"));
                financialRecord.setEmployeeID(resultSet.getInt("employeeid"));
                financialRecord.setRecordDate(resultSet.getObject("recorddate", LocalDate.class));
                financialRecord.setDescription(resultSet.getString("description"));
                financialRecord.setAmount(resultSet.getDouble("amount"));
                financialRecord.setRecordType(resultSet.getString("recordtype"));
                financialRecords.add(financialRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnUtil.closeConnection();
        }

        return financialRecords;
    }
}
