package techosolution.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import techosolution.config.Const;
import techosolution.entity.UserMngEntity;

import javax.transaction.Transactional;

public interface UserMngRepo extends MyRepo<UserMngEntity, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = Const.DBQueries.updateExpenseQuery, nativeQuery = true)
    int updateExpenses(Double amount, Integer iden);


}
