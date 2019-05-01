import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './app-service.component';
import { User } from './userData.component';
import { ExpenseReportComponent } from './expense-report/expense-report.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

  constructor(private router: Router, private apiService: ApiService) { }

  @ViewChild(ExpenseReportComponent) expenseReport;
  public title: string = 'to tech2hire';
  public userList = [];
  public expensesList = [];
  public userSelected = false;
  public noExpenseLimit = false;
  public selectedUser: number;
  public expenseLimit: any;
  public userDetails: User = new User();
  public savedExpenseLimit: number;
  public valueStart: Date = new Date();
  public valueEnd: Date = new Date();
  public defaultItem: { text: string, value: number } = { text: "Select item", value: null };
  public expenseType: number;
  public inputParam: boolean;
  public negativeValue: boolean;
  public tempAmount: number;
  public startedAnalysis: boolean = false;
  public series: any[];
  public categories: number[];
  public categorysize: number;
  public graphicalData = {
    shopping: [],
    travel: [],
    enter: [],
    misc: [],
    dining: [],
    food: []
  };
  public typePriority = [];
  public spendRatioObject = {
    shopping: null,
    travel: null,
    enter: null,
    misc: null,
    dining: null,
    food: null
  };
  public spendTotalObject = {
    shopping: null,
    travel: null,
    enter: null,
    misc: null,
    dining: null,
    food: null
  };
  public analysisObj = {
    maxAmount: null,
    maxRatio: null,
    maxAmountCategory: '',
    maxRatioCategory: '',
    crossedSchemas: []
  }
  public dataHolding: any;
  public dateSelection = false;
  public compareSpends = false;
  public dateValidation = false;
  public generateAlgorithm: boolean;
  public initSetLimit: number;
  public nearLimit: boolean;
  public dataExchange: string = 'Initial';
  public eventChanger: string = 'Default';

  ngAfterViewInit() {
    // this.dataExchange = this.expenseReport.message;
  }
  ngOnInit(): void {
    this.fetchUserDetails();
    this.fetchExpense();
  }

  dataEventHandler(event) {
    this.dataExchange = event;
  }

  public fetchUserDetails() {
    this.apiService.getUsers().subscribe(data => {
      // console.log(JSON.stringify(data));
      data = data.responseBody;
      this.userList = [];
      data.forEach(user => {
        if (user.delFlg === 'N')
          this.userList.push(
            { text: user.userName, value: user.userId, limit: user.expenseLimit, month: user.userMonth, initLimit: user.expenseInit }
          );
      });
    });
  }

  public userChange(dataItem) {
    this.userSelected = false;
    this.noExpenseLimit = false;
    this.startedAnalysis = false;
    console.log(dataItem);
    for (let i of this.userList) {
      if (i.value == dataItem) {
        this.selectedUser = i.value;
        this.initSetLimit = i.initLimit;
        this.title = i.text + '!!! Spend in ' + i.month + ' ' + 'month';
        if (i.limit === '0' || i.limit.toString() === '0') {
          this.userSelected = false;
          this.noExpenseLimit = true;
          break;
        } else {
          this.savedExpenseLimit = i.limit;
        }
      }
    }
    if (!this.noExpenseLimit) {
      this.userSelected = true;
      console.log(this.expenseLimit + ' ' + this.selectedUser + " " + this.expenseType);
      this.fetchUserDetails();
      this.fetchGraphicalData();
    }
  }

  public submitLimit() {
    console.log(this.expenseLimit);
    this.userDetails.expenseAmount = this.expenseLimit;
    this.userDetails.identifier = this.selectedUser;
    this.apiService.updateLimit(this.userDetails).subscribe(data => {
      console.log(JSON.stringify(data));
      this.expenseLimit = undefined;
    });
    this.userSelected = true;
    this.noExpenseLimit = false;
    this.savedExpenseLimit = this.expenseLimit;
    this.fetchExpense();
  }

  public fetchExpense() {
    this.apiService.getExpenses().subscribe(data => {
      data = data.responseBody;
      data.forEach(expenses => {
        this.expensesList.push({
          text: expenses.typeDesc, value: expenses.typeId
        });
        this.typePriority.push({ type: expenses.typeId, priority: expenses.typePrior, desc: expenses.typeDesc });
      });
    })
  }

  public expenseChange(dataItem) {
    this.expenseType = dataItem;
  }

  public submitExpenditure() {
    this.inputParam = false;
    this.negativeValue = false;
    this.nearLimit = false;
    let tempComp: number = 0;
    tempComp = 0.1 * this.initSetLimit;
    console.log('Checking for equal operator' + tempComp + this.savedExpenseLimit);
    if (this.expenseType === null || this.expenseType === undefined || this.expenseLimit === null || this.expenseLimit === undefined) {
      this.inputParam = true;
    } else {
      this.tempAmount = this.savedExpenseLimit - this.expenseLimit;
      if (this.tempAmount < 0) {
        this.negativeValue = true;
        this.tempAmount = -(this.tempAmount);
      } else {
        this.savedExpenseLimit = this.tempAmount;
        if (tempComp >= this.savedExpenseLimit) {
          this.nearLimit = true;
        }
      }
      this.userDetails.identifier = this.selectedUser;
      this.userDetails.expensetype = this.expenseType;
      this.userDetails.expenseAmount = this.expenseLimit;
      this.userDetails.expenseDate = this.valueStart;
      // console.log(JSON.stringify(this.userDetails));
      if (!this.negativeValue) {
        this.apiService.updateExpenses(this.userDetails).subscribe(data => {
          //  console.log(data);
          this.eventChanger = 'Event Changed';
        }, error => {
          alert(JSON.stringify(error));
        })
        this.userDetails.expenseAmount = this.savedExpenseLimit;
        this.apiService.updateLimit(this.userDetails).subscribe(data => {
          // console.log(JSON.stringify(data));
          this.expenseLimit = null;
        });
      }
      this.eventChanger = 'Initial';
    }
  }

  public fetchGraphicalData() {
    this.apiService.getGraphData(this.selectedUser).subscribe(data => {
      console.log(JSON.stringify(data));
      data = data.responseBody;
      this.graphicalData.shopping = data.shopping;
      this.graphicalData.travel = data.travel;
      this.graphicalData.dining = data.dining;
      this.graphicalData.misc = data.misc;
      this.graphicalData.food = data.food;
      this.graphicalData.enter = data.entertainment;
    }, error => {
      alert(JSON.stringify(error));
    });
  }
  public getAnalysis() {
    this.categories = this.fillArrayWithNumbers(31);
    this.refreshGraph();
    this.startedAnalysis = true;
    this.valueStart = new Date();
  }

  public refreshGraph() {
    this.series = [{
      name: "Shopping",
      data: this.graphicalData.shopping
    }, {
      name: "Food",
      data: this.graphicalData.food
    }, {
      name: "Dining",
      data: this.graphicalData.dining
    }, {
      name: "Travel",
      data: this.graphicalData.travel
    }, {
      name: "Entertainment",
      data: this.graphicalData.enter
    }, {
      name: "Miscellaneous",
      data: this.graphicalData.misc
    }];
  }

  public removeAnalysis() {
    this.startedAnalysis = false;
    this.userSelected = false;
    this.noExpenseLimit = false;
    this.valueStart = new Date();
    this.compareSpends = false;
    if (this.generateAlgorithm) {
      this.startedAnalysis = true;
    }
    this.generateAlgorithm = false;
    this.userChange(this.selectedUser);
  }

  public getByDates() {
    this.dateSelection = true;
  }

  public compareSpendings() {
    this.compareSpends = true;
  }


  public onDateChange() {
    this.dateValidation = false;
    if (this.valueStart < this.valueEnd) {
      let diffc = this.valueEnd.getTime() - this.valueStart.getTime();
      let days = Math.round(Math.abs(diffc / (1000 * 60 * 60 * 24)));
      this.categorysize = days;
      this.userDetails.identifier = this.selectedUser;
      this.userDetails.expenseDate = this.valueStart;
      this.userDetails.expenseEndDate = this.valueEnd;
      this.apiService.getDatesGraphicalData(this.userDetails).subscribe(data => {
        data = data.responseBody;
        this.dataHolding = data;
      }, error => {
        alert(JSON.stringify(error));
      });
    }
  }

  public datesDataPopulation() {
    this.dateValidation = false;
    if (this.valueEnd === null || this.valueStart === null || this.valueEnd === undefined || this.valueStart === undefined) {
      this.dateValidation = true;
      return;
    } else if (this.valueStart > this.valueEnd) {
      this.dateValidation = true;
    } else {
      this.graphicalData.shopping = this.dataHolding.shopping;
      if (this.graphicalData.shopping.length < this.categorysize) {
        this.categorysize = this.graphicalData.shopping.length;
      }
      this.graphicalData.travel = this.dataHolding.travel;
      if (this.categorysize < this.graphicalData.travel.length) {
        this.categorysize = this.graphicalData.travel.length
      }
      this.graphicalData.dining = this.dataHolding.dining;
      if (this.categorysize < this.graphicalData.dining.length) {
        this.categorysize = this.graphicalData.dining.length
      }
      this.graphicalData.misc = this.dataHolding.misc;
      if (this.categorysize < this.graphicalData.misc.length) {
        this.categorysize = this.graphicalData.misc.length
      }
      this.graphicalData.food = this.dataHolding.food;
      if (this.categorysize < this.graphicalData.food.length) {
        this.categorysize = this.graphicalData.food.length
      }
      this.graphicalData.enter = this.dataHolding.entertainment;
      if (this.categorysize < this.graphicalData.enter.length) {
        this.categorysize = this.graphicalData.enter.length
      }
      this.categories = this.fillArrayWithNumbers(this.categorysize);
      this.refreshGraph();
    }
  }

  public fillArrayWithNumbers(n) {
    let arr = Array.apply(null, Array(n));
    return arr.map(function (x, i) { return i });
  }

  public generateAlgorithmReport() {
    this.generateAlgorithm = true;
    this.startedAnalysis = false;
    this.userDetails.identifier = this.selectedUser;
    this.userDetails.expenseAmount = this.savedExpenseLimit;
    this.apiService.getAssessmentData(this.userDetails).subscribe(data => {
      data = data.responseBody;
      let temp: number = -1;
      let tempMax: number = -1;
      let tempName: string;
      let tempMaxName: string;
      this.spendRatioObject.shopping = data.shoppingRatio;
      for (let i of this.typePriority) {
        if (i.type === 1) {
          if (this.spendRatioObject.shopping > i.priority) {
            this.analysisObj.crossedSchemas.push({ name: i.desc });
          }
        }
      }
      if (temp < this.spendRatioObject.shopping) {
        temp = this.spendRatioObject.shopping;
        tempName = 'Shopping';
      }
      this.spendTotalObject.shopping = data.shoppingTotal;
      if (tempMax < this.spendTotalObject.shopping) {
        tempMax = this.spendTotalObject.shopping;
        tempMaxName = 'Shopping';
      }
      this.spendRatioObject.travel = data.travelRatio;
      for (let i of this.typePriority) {
        if (i.type === 4) {
          if (this.spendRatioObject.travel > i.priority) {
            this.analysisObj.crossedSchemas.push({ name: i.desc });
          }
        }
      }
      if (temp < this.spendRatioObject.travel) {
        temp = this.spendRatioObject.travel;
        tempName = 'Travel';
      }
      this.spendTotalObject.travel = data.travelTotal;
      if (tempMax < this.spendTotalObject.travel) {
        tempMax = this.spendTotalObject.travel;
        tempMaxName = 'Travel';
      }
      this.spendRatioObject.dining = data.diningRatio;
      for (let i of this.typePriority) {
        if (i.type === 3) {
          if (this.spendRatioObject.dining > i.priority) {
            this.analysisObj.crossedSchemas.push({ name: i.desc });
          }
        }
      }
      if (temp < this.spendRatioObject.dining) {
        temp = this.spendRatioObject.dining;
        tempName = 'Dining';
      }
      this.spendTotalObject.dining = data.diningTotal;
      if (tempMax < this.spendTotalObject.dining) {
        tempMax = this.spendTotalObject.dining;
        tempMaxName = 'Dining';
      }
      this.spendRatioObject.misc = data.miscRatio;
      for (let i of this.typePriority) {
        if (i.type === 6) {
          if (this.spendRatioObject.misc > i.priority) {
            this.analysisObj.crossedSchemas.push({ name: i.desc });
          }
        }
      }
      if (temp < this.spendRatioObject.misc) {
        temp = this.spendRatioObject.misc;
        tempName = 'Miscellaneous';
      }
      this.spendTotalObject.misc = data.miscTotal;
      if (tempMax < this.spendTotalObject.misc) {
        tempMax = this.spendTotalObject.misc;
        tempMaxName = 'Miscellaneous';
      }
      this.spendRatioObject.enter = data.entRatio;
      for (let i of this.typePriority) {
        if (i.type === 5) {
          if (this.spendRatioObject.enter > i.priority) {
            this.analysisObj.crossedSchemas.push({ name: i.desc });
          }
        }
      }
      if (temp < this.spendRatioObject.enter) {
        temp = this.spendRatioObject.enter;
        tempName = 'Entertainment';
      }
      this.spendTotalObject.enter = data.entTotal;
      if (tempMax < this.spendTotalObject.enter) {
        tempMax = this.spendTotalObject.enter;
        tempMaxName = 'Entertainment';
      }
      this.spendRatioObject.food = data.foodRatio;
      for (let i of this.typePriority) {
        if (i.type === 2) {
          if (this.spendRatioObject.food > i.priority) {
            this.analysisObj.crossedSchemas.push({ name: i.desc });
          }
        }
      }
      if (temp < this.spendRatioObject.food) {
        temp = this.spendRatioObject.food;
        tempName = 'Food';
      }
      this.spendTotalObject.food = data.foodTotal;
      if (tempMax < this.spendTotalObject.food) {
        tempMax = this.spendTotalObject.food;
        tempMaxName = 'Food';
      }
      this.analysisObj.maxAmount = tempMax;
      this.analysisObj.maxAmountCategory = tempMaxName;
      this.analysisObj.maxRatio = temp;
      this.analysisObj.maxRatioCategory = tempName;
      console.log(JSON.stringify(data));
    })
  }
}
