package techosolution.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techosolution.entity.ExpenseMngEntity;
import techosolution.model.ExpenseMange;
import techosolution.repository.ExpenseMngRepo;
import techosolution.service.ExpenseService;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseMngRepo expenseMngRepo;

    @Override
    public boolean updateExpenses(ExpenseMange expenseMange) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            Date db = formatter.parse(formatter.format(expenseMange.getExpenseDate()));
            ExpenseMngEntity expenseMngEntity = new ExpenseMngEntity();
            expenseMngEntity.setExpenseType(expenseMange.getExpensetype());
            expenseMngEntity.setUserId(expenseMange.getIdentifier());
            expenseMngEntity.setExpenseAmount(expenseMange.getExpenseAmount());
            expenseMngEntity.setExpenseDate(expenseMange.getExpenseDate());
            expenseMngRepo.save(expenseMngEntity);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
