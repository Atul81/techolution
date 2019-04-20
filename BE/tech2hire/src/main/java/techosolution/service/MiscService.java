package techosolution.service;

import org.springframework.stereotype.Service;
import techosolution.entity.ExpenseTypeEntity;
import techosolution.entity.UserMngEntity;
import techosolution.model.ExpenseMange;

import java.util.List;

public interface MiscService {

    List<UserMngEntity> fetchUsers();

    List<ExpenseTypeEntity> fetchAllTypeExpenses();

    boolean updateExpenseLimit(ExpenseMange expenseMange);
}
