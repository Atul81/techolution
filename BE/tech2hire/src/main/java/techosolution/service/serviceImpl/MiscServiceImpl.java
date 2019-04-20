package techosolution.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techosolution.entity.ExpenseTypeEntity;
import techosolution.entity.UserMngEntity;
import techosolution.model.ExpenseMange;
import techosolution.repository.ExpenseType;
import techosolution.repository.UserMngRepo;
import techosolution.service.MiscService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MiscServiceImpl implements MiscService {

    private static final Logger log = Logger.getLogger("MiscServiceImpl");

    @Autowired
    private UserMngRepo userMngRepo;

    @Autowired
    private ExpenseType expenseTypeRepo;

    @Override
    public List<UserMngEntity> fetchUsers() {
        List<UserMngEntity> allUsers = userMngRepo.findAll();
        if (allUsers.size() > 0)
            return allUsers;
        else return null;
    }

    @Override
    public List<ExpenseTypeEntity> fetchAllTypeExpenses() {
        List<ExpenseTypeEntity> allExpenses = expenseTypeRepo.findAll();
        if (allExpenses.size() > 0)
            return allExpenses;
        else return null;
    }

    @Override
    public boolean updateExpenseLimit(ExpenseMange expenseMange) {
        Integer i = userMngRepo.updateExpenses(expenseMange.getExpenseAmount(), expenseMange.getIdentifier());
        log.info(i.toString());
        return false;
    }
}
