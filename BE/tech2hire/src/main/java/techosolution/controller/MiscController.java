package techosolution.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techosolution.config.Const;
import techosolution.entity.ExpenseTypeEntity;
import techosolution.entity.UserMngEntity;
import techosolution.model.ExpenseMange;
import techosolution.model.HeaderModel;
import techosolution.model.MiscParam;
import techosolution.model.Response;
import techosolution.service.MiscService;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/misc")
@Api(value = "miscdescription", description = "Static and Misc Data")
public class MiscController {

    private static final Logger log = Logger.getLogger("MiscController");


    @Autowired
    private MiscService miscService;

    public HeaderModel headerModel() {
        HeaderModel headerModel = new HeaderModel();
        headerModel.setAppName("Full Stack Development");
        headerModel.setLanguage("English");
        headerModel.setResponseDateTime(new Date().toString());
        headerModel.setStatus(Const.Status.SUCCESS);
        headerModel.setUserId("94252");
        headerModel.setUserAction("Request Mapping Operation");
        return headerModel;
    }

    @ApiOperation(value = "fetchUsers")
    @GetMapping("/users")
    public ResponseEntity<Response<List<UserMngEntity>>> getAllUsers() {
    List<UserMngEntity> users = miscService.fetchUsers();
    Response<List<UserMngEntity>> response = new Response<>();
    response.setResponse(users);
    if(null != response.getResponse())
        response.setHeaderModel(headerModel());
    return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateExpense")
    public Boolean updateExpenseLimit(@RequestBody ExpenseMange expenseMange) {
        return miscService.updateExpenseLimit(expenseMange);
    }

    @GetMapping("/getExpenses")
    public ResponseEntity<Response<List<ExpenseTypeEntity>>> getAllExpenses() {
        List<ExpenseTypeEntity> expenseTypeEntities = miscService.fetchAllTypeExpenses();
        Response<List<ExpenseTypeEntity>> responseExpenses = new Response<>();
        responseExpenses.setResponse(expenseTypeEntities);
        if(null != responseExpenses.getResponse())
            responseExpenses.setHeaderModel(headerModel());
        return new ResponseEntity<>(responseExpenses, HttpStatus.OK);
    }


}
