package techosolution.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_mng", schema = "tecolution", catalog = "")
public class UserMngEntity {
    private Integer userId;
    private Double expenseLimit;
    private String userName;
    private Date ctDate;

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "expense_limit", nullable = false, precision = 0)
    public Double getExpenseLimit() {
        return expenseLimit;
    }

    public void setExpenseLimit(Double expenseLimit) {
        this.expenseLimit = expenseLimit;
    }


    @Basic
    @Column(name = "user_name", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "ct_date", nullable = false)
    public Date getCtDate() {
        return ctDate;
    }

    public void setCtDate(Date ctDate) {
        this.ctDate = ctDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMngEntity that = (UserMngEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(expenseLimit, that.expenseLimit) &&
                Objects.equals(ctDate, that.ctDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, expenseLimit, ctDate);
    }
}
