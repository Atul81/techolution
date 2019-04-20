package techosolution.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "expense_type", schema = "tecolution", catalog = "")
public class ExpenseTypeEntity {
    private Integer typeId;
    private String typeDesc;
    private Date ctTime;

    @Id
    @Column(name = "type_id", nullable = false)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "type_desc", nullable = true, length = 50)
    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Basic
    @Column(name = "ct_time", nullable = true)
    public Date getCtTime() {
        return ctTime;
    }

    public void setCtTime(Date ctTime) {
        this.ctTime = ctTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseTypeEntity that = (ExpenseTypeEntity) o;
        return Objects.equals(typeId, that.typeId) &&
                Objects.equals(typeDesc, that.typeDesc) &&
                Objects.equals(ctTime, that.ctTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeDesc, ctTime);
    }
}
