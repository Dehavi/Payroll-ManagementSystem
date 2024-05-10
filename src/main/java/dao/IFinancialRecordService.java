package dao;


import entity.FinancialRecord;
import java.time.LocalDate;
import java.util.List;

public interface IFinancialRecordService {
    void addFinancialRecord(int employeeId, String description, double amount, String recordType);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate);
}
