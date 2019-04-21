package techosolution.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techosolution.model.ExpenseAnalysis;
import techosolution.model.ExpenseMange;
import techosolution.model.Response;
import techosolution.service.ExpenseService;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/expenses")
@Api(value = "expenseManagement", description = "Spend in a Day")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    private static final Logger log = Logger.getLogger("ExpenseController");

    @PostMapping("/updateExpenses")
    public Boolean expenses(@RequestBody ExpenseMange expenseMange) {
        boolean b = expenseService.updateExpenses(expenseMange);
        return b;
    }

    @PostMapping("/getAnalysisData/{userID}")
    public ResponseEntity<Response<ExpenseAnalysis>> getAnalysisData(@PathVariable Integer userID) throws Exception {
        log.info("Inside Data Analysis Part");
        ExpenseAnalysis expenseAnalysis = expenseService.getExpenseAnalysis(userID);
        if(null != expenseAnalysis){
            return getResponseResponseEntity(expenseAnalysis);
        } else
            throw new Exception("Error in fetching analysis data");
    }

    @PostMapping("/getAnalysisBwDates")
    public ResponseEntity<Response<ExpenseAnalysis>> getAnalysisBetweenDateData(@RequestBody ExpenseMange expenseMange) throws Exception {
        log.info("Inside Data Analysis Part for two dates");
        ExpenseAnalysis expenseAnalysis = expenseService.getDateAnalysis(expenseMange);
        if(null != expenseAnalysis){
            return getResponseResponseEntity(expenseAnalysis);
        } else
            throw new Exception("Error in fetching analysis data for dates");
    }

    @PostMapping("/getAssessmentReport")
    public ResponseEntity<Response<ExpenseAnalysis>> generateAssessmentReport(@RequestBody ExpenseMange expenseMange) throws Exception {
        log.info("Inside Assessment Generation");
        ExpenseAnalysis expenseAnalysis = expenseService.generateAssessment(expenseMange);
        if(null != expenseAnalysis){
            return getResponseResponseEntity(expenseAnalysis);
        } else
            throw new Exception("Error in fetching data reports");
    }

    private ResponseEntity<Response<ExpenseAnalysis>> getResponseResponseEntity(ExpenseAnalysis expenseAnalysis) {
        Response<ExpenseAnalysis> expenseAnalysisResponse = new Response<>();
        expenseAnalysisResponse.setHeaderModel(new MiscController().headerModel());
        expenseAnalysisResponse.setResponse(expenseAnalysis);
        return new ResponseEntity<>(expenseAnalysisResponse, HttpStatus.OK);
    }
}
