package techosolution.model;

import java.io.Serializable;
import java.util.Date;

public class ExpenseMange implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double expenseAmount;
    private Integer identifier;
    private Date expenseDate;
    private Integer expensetype;
    private Date expenseEndDate;
    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Integer getExpensetype() {
        return expensetype;
    }

    public void setExpensetype(Integer expensetype) {
        this.expensetype = expensetype;
    }

    public Date getExpenseEndDate() {
        return expenseEndDate;
    }

    public void setExpenseEndDate(Date expenseEndDate) {
        this.expenseEndDate = expenseEndDate;
    }
}
