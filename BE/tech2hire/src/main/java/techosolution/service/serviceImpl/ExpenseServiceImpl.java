package techosolution.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techosolution.entity.ExpenseMngEntity;
import techosolution.model.ExpenseAnalysis;
import techosolution.model.ExpenseMange;
import techosolution.repository.ExpenseMngRepo;
import techosolution.service.ExpenseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseMngRepo expenseMngRepo;

    @Override
    public boolean updateExpenses(ExpenseMange expenseMange) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            Date db = formatter.parse(formatter.format(expenseMange.getExpenseDate()));
            ExpenseMngEntity expenseMngEntity = new ExpenseMngEntity();
            expenseMngEntity.setExpenseType(expenseMange.getExpensetype());
            expenseMngEntity.setUserId(expenseMange.getIdentifier());
            expenseMngEntity.setExpenseAmount(expenseMange.getExpenseAmount());
            expenseMngEntity.setExpenseDate(expenseMange.getExpenseDate());
            expenseMngRepo.save(expenseMngEntity);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ExpenseAnalysis getExpenseAnalysis(Integer userId) {
        List<Object[]> objects = expenseMngRepo.analysisData(userId);
        return populateModel(objects, 31);
    }

    @Override
    public ExpenseAnalysis getDateAnalysis(ExpenseMange expenseMange) {
        // long difference =  (expenseMange.getExpenseDate().getTime()-expenseMange.getExpenseEndDate().getTime())/86400000;
        // long abs = Math.abs(difference);
        List<Object[]> objects =
                expenseMngRepo.getAnalysisDate(expenseMange.getExpenseDate(), expenseMange.getExpenseEndDate(), expenseMange.getIdentifier());
        return populateModel(objects, 0);
    }

    @Override
    public ExpenseAnalysis generateAssessment(ExpenseMange expenseMange) {
        List<Object[]> getDateSequencedData = expenseMngRepo.getAssementData(expenseMange.getIdentifier());
        ExpenseAnalysis expenseAnalysis = populateModel(getDateSequencedData, 0);
        Double expenseAmount = expenseMange.getExpenseAmount();
        Double shoppingRatio = expenseAnalysis.getShoppingTotal()/expenseAmount;
        Double foodRatio = expenseAnalysis.getFoodTotal()/expenseAmount;
        Double diningRatio =  expenseAnalysis.getDiningTotal()/expenseAmount;
        Double travelRatio = expenseAnalysis.getTravelTotal()/expenseAmount;
        Double entRatio = expenseAnalysis.getEntTotal()/expenseAmount;
        Double miscRatio = expenseAnalysis.getMiscTotal()/expenseAmount;
        expenseAnalysis.setShoppingRatio(shoppingRatio);
        expenseAnalysis.setFoodRatio(foodRatio);
        expenseAnalysis.setDiningRatio(diningRatio);
        expenseAnalysis.setTravelRatio(travelRatio);
        expenseAnalysis.setEntRatio(entRatio);
        expenseAnalysis.setMiscRatio(miscRatio);
        return expenseAnalysis;
    }


    private ArrayList<Double> getSizing(Integer size) {
        ArrayList<Double> pillars = new ArrayList<>();
        for (int i = 0; i < size; i++)
            pillars.add(i, 0.00);
        return pillars;
    }

    private ExpenseAnalysis populateModel(List<Object[]> metaData, Integer size) {
        ExpenseAnalysis expenseAnalysis = new ExpenseAnalysis();
        List<Double> shopping = getSizing(size);
        List<Double> food = getSizing(size);
        List<Double> dining = getSizing(size);
        List<Double> travel = getSizing(size);
        List<Double> entertainment = getSizing(size);
        List<Double> misc = getSizing(size);
        Double shoppingTotal = 0.00;
        Double foodTotal = 0.00;
        Double diningTotal = 0.00;
        Double travelTotal  = 0.00;
        Double entTotal = 0.00;
        Double miscTotal = 0.00;


        for (Object[] obj : metaData) {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) obj[1]);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            day--;
            if ((Integer) obj[0] == 1) {
                if (!size.equals(0))
                    shopping.set(day, (Double) obj[2]);
                else {
                    shoppingTotal += (Double) obj[2];
                    shopping.add((Double) obj[2]);
                }
            } else if ((Integer) obj[0] == 2) {
                if (!size.equals(0))
                    food.set(day, (Double) obj[2]);
                else {
                    foodTotal+= (Double) obj[2];
                    food.add((Double) obj[2]);
                }
            } else if ((Integer) obj[0] == 3) {
                if (!size.equals(0))
                    dining.set(day, (Double) obj[2]);
                else {
                    diningTotal+= (Double) obj[2];
                    dining.add((Double) obj[2]);
                }
            } else if ((Integer) obj[0] == 4) {
                if (!size.equals(0))
                    travel.set(day, (Double) obj[2]);
                else {
                    travelTotal+= (Double) obj[2];
                    travel.add((Double) obj[2]);
                }
            } else if ((Integer) obj[0] == 5) {
                if (!size.equals(0))
                    entertainment.set(day, (Double) obj[2]);
                else {
                    entTotal+= (Double) obj[2];
                    entertainment.add((Double) obj[2]);
                }
            } else if ((Integer) obj[0] == 6) {
                if (!size.equals(0))
                    misc.set(day, (Double) obj[2]);
                else {
                    miscTotal+= (Double) obj[2];
                    misc.add((Double) obj[2]);
                }
            }
        }
        expenseAnalysis.setEntTotal(entTotal);
        expenseAnalysis.setMiscTotal(miscTotal);
        expenseAnalysis.setFoodTotal(foodTotal);
        expenseAnalysis.setDiningTotal(diningTotal);
        expenseAnalysis.setShoppingTotal(shoppingTotal);
        expenseAnalysis.setTravelTotal(travelTotal);
        expenseAnalysis.setDining(dining);
        expenseAnalysis.setEntertainment(entertainment);
        expenseAnalysis.setFood(food);
        expenseAnalysis.setMisc(misc);
        expenseAnalysis.setShopping(shopping);
        expenseAnalysis.setTravel(travel);
        return expenseAnalysis;
    }
}
