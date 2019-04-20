package techosolution.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "expense_mng", schema = "tecolution", catalog = "")
public class ExpenseMngEntity {
    private Integer expenseId;
    private Integer expenseType;
    private Date expenseDate;
    private Integer userId;
    private Double expenseAmount;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", nullable = false)
    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    @Basic
    @Column(name = "expense_type", nullable = false)
    public Integer getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(Integer expenseType) {
        this.expenseType = expenseType;
    }

    @Basic
    @Column(name = "expense_amount", nullable = false, precision = 0)
    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    @Basic
    @Column(name = "expense_date", nullable = false)
    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseMngEntity that = (ExpenseMngEntity) o;
        return Objects.equals(expenseId, that.expenseId) &&
                Objects.equals(expenseType, that.expenseType) &&
                Objects.equals(expenseDate, that.expenseDate) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseId, expenseType, expenseDate, userId);
    }
}
