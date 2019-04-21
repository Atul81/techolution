package techosolution.repository;

import org.springframework.data.jpa.repository.Query;
import techosolution.config.Const;
import techosolution.entity.ExpenseMngEntity;

import java.util.Date;
import java.util.List;

public interface ExpenseMngRepo extends MyRepo<ExpenseMngEntity, Integer> {

    @Query(value = Const.DBQueries.getAnalysisData, nativeQuery = true)
    List<Object[]> analysisData(Integer userId);

    @Query(value = Const.DBQueries.getAnalysisDataRange,  nativeQuery = true)
    List<Object[]> getAnalysisDate(Date one, Date two, Integer identifier);

    @Query(value = Const.DBQueries.getDateSequencedData,  nativeQuery = true)
    List<Object[]> getAssementData(Integer identifier);
}
