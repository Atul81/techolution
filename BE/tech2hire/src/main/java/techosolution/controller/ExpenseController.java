package techosolution.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techosolution.model.ExpenseMange;
import techosolution.service.ExpenseService;

@RestController
@RequestMapping(value = "/expenses")
@Api(value = "expenseManagement", description = "Spend in a Day")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/updateExpenses")
    public Boolean expenses(@RequestBody ExpenseMange expenseMange) {
        boolean b = expenseService.updateExpenses(expenseMange);
        return b;
    }
}
