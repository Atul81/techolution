package techosolution.config;

import techosolution.model.HeaderModel;

import java.util.Date;

public class Const {

    public interface DBQueries {
        String updateExpenseQuery = "update user_mng um set um.expense_limit = ?1 where um.user_id = ?2";

        String getAnalysisData = "select em.expense_type, em.expense_date, sum(em.expense_amount) as expense_amount from expense_mng em join user_mng um on em.user_id = um.user_id where (em.user_id = ?1 and um.delFlg = 'N') group by em.expense_date order by em.expense_type ;";

        String getAnalysisDataRange = "select em.expense_type, em.expense_date, sum(em.expense_amount) from expense_mng em where (em.user_id = ?3 and em.expense_date BETWEEN ?1 and ?2) group by em.expense_date order by em.expense_date ;";

        String getDateSequencedData = "select em.expense_type, em.expense_date, sum(em.expense_amount) from expense_mng em where em.user_id = ?1 group by em.expense_date order by em.expense_date;";
    }

    public interface KeyWords {
    }

    public interface Status {
        String SUCCESS = "0000";
        String ERROR = "9999";
        String FAILURE = "0010";
        String UNDEFINED = "9990";

    }
}
