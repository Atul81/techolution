package techosolution.service;

import techosolution.model.ExpenseAnalysis;
import techosolution.model.ExpenseMange;

public interface ExpenseService {

    boolean updateExpenses(ExpenseMange expenseMange);

    ExpenseAnalysis getExpenseAnalysis(Integer userId);

    ExpenseAnalysis getDateAnalysis(ExpenseMange expenseMange);

    ExpenseAnalysis generateAssessment(ExpenseMange expenseMange);
}
