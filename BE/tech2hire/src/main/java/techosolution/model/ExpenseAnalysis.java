package techosolution.model;

import java.util.List;

public class ExpenseAnalysis {

   private List<Double> shopping;

    private List<Double> food;
    private List<Double> dining;
    private List<Double> travel;

    public Double getShoppingTotal() {
        return shoppingTotal;
    }

    public void setShoppingTotal(Double shoppingTotal) {
        this.shoppingTotal = shoppingTotal;
    }

    public Double getFoodTotal() {
        return foodTotal;
    }

    public void setFoodTotal(Double foodTotal) {
        this.foodTotal = foodTotal;
    }

    public Double getDiningTotal() {
        return diningTotal;
    }

    public void setDiningTotal(Double diningTotal) {
        this.diningTotal = diningTotal;
    }

    public Double getTravelTotal() {
        return travelTotal;
    }

    public void setTravelTotal(Double travelTotal) {
        this.travelTotal = travelTotal;
    }

    public Double getEntTotal() {
        return entTotal;
    }

    public void setEntTotal(Double entTotal) {
        this.entTotal = entTotal;
    }

    public Double getMiscTotal() {
        return miscTotal;
    }

    public void setMiscTotal(Double miscTotal) {
        this.miscTotal = miscTotal;
    }

    public Double getShoppingRatio() {
        return shoppingRatio;
    }

    public void setShoppingRatio(Double shoppingRatio) {
        this.shoppingRatio = shoppingRatio;
    }

    public Double getFoodRatio() {
        return foodRatio;
    }

    public void setFoodRatio(Double foodRatio) {
        this.foodRatio = foodRatio;
    }

    public Double getDiningRatio() {
        return diningRatio;
    }

    public void setDiningRatio(Double diningRatio) {
        this.diningRatio = diningRatio;
    }

    public Double getTravelRatio() {
        return travelRatio;
    }

    public void setTravelRatio(Double travelRatio) {
        this.travelRatio = travelRatio;
    }

    public Double getEntRatio() {
        return entRatio;
    }

    public void setEntRatio(Double entRatio) {
        this.entRatio = entRatio;
    }

    public Double getMiscRatio() {
        return miscRatio;
    }

    public void setMiscRatio(Double miscRatio) {
        this.miscRatio = miscRatio;
    }

    private Double shoppingTotal;
    private Double foodTotal;
    private Double diningTotal;
    private Double travelTotal;
    private Double entTotal;
    private Double miscTotal;

    private Double shoppingRatio;
    private Double foodRatio;
    private Double diningRatio;
    private Double travelRatio;
    private Double entRatio;
    private Double miscRatio;
    public List<Double> getShopping() {
        return shopping;
    }

    public void setShopping(List<Double> shopping) {
        this.shopping = shopping;
    }

    public List<Double> getFood() {
        return food;
    }

    public void setFood(List<Double> food) {
        this.food = food;
    }

    public List<Double> getDining() {
        return dining;
    }

    public void setDining(List<Double> dining) {
        this.dining = dining;
    }

    public List<Double> getTravel() {
        return travel;
    }

    public void setTravel(List<Double> travel) {
        this.travel = travel;
    }

    public List<Double> getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(List<Double> entertainment) {
        this.entertainment = entertainment;
    }

    public List<Double> getMisc() {
        return misc;
    }

    public void setMisc(List<Double> misc) {
        this.misc = misc;
    }

    private List<Double> entertainment;
    private List<Double> misc;
}
