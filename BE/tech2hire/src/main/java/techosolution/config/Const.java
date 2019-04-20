package techosolution.config;

import techosolution.model.HeaderModel;

import java.util.Date;

public class Const {

    public interface DBQueries {
        String updateExpenseQuery = "update user_mng um set um.expense_limit = ?1 where um.user_id = ?2";

        String getByIDQuery =
                "";
    }

    public interface KeyWords {
    }

    public interface Status {
        String SUCCESS = "0000";
        String ERROR = "9999";
        String FAILURE = "0010";
        String ACCESS_DENOED_ERROR_CODE = "9000";
        String ACCESS_DENOED_ERRROR_MSG = "User does not have proper access";
        String UNDEFINED = "9990";

    }
}
