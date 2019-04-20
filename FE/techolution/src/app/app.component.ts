import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './app-service.component';
import { User } from './userData.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private router: Router, private apiService: ApiService) { }

  public title: string = '';
  public userList = [];
  public expensesList = [];
  public userSelected = false;
  public noExpenseLimit = false;
  public selectedUser: number;
  public expenseLimit: any;
  public userDetails: User = new User();
  public savedExpenseLimit: number;
  public valueStart: Date = new Date();
  public defaultItem: { text: string, value: number } = { text: "Select item", value: null };
  public expenseType: number;
  public inputParam: boolean;
  public negativeValue: boolean;
  public tempAmount: number;
  ngOnInit(): void {
    this.fetchUserDetails();
  }

  public fetchUserDetails() {
    this.apiService.getUsers().subscribe(data => {
      // console.log(JSON.stringify(data));
      data = data.responseBody;
      data.forEach(user => {
        this.userList.push(
          { text: user.userName, value: user.userId, limit: user.expenseLimit }
        );
      });
    });
  }

  public userChange(dataItem) {
    console.log(dataItem);
    for (let i of this.userList) {
      if (i.value == dataItem) {
        this.selectedUser = i.value;
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
      console.log(this.expenseLimit + ' ' + this.selectedUser + " " + this.expenseType)
      this.fetchExpense();
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
    this.fetchUserDetails();
    this.fetchExpense();
  }

  public fetchExpense() {
    this.apiService.getExpenses().subscribe(data => {
      data = data.responseBody;
      data.forEach(expenses => {
        this.expensesList.push({
          text: expenses.typeDesc, value: expenses.typeId
        });
      });
    })
  }

  public expenseChange(dataItem) {
    this.expenseType = dataItem;
  }

  public submitExpenditure() {
    this.inputParam = false;
    this.negativeValue = false;
    if (this.expenseType === null || this.expenseType === undefined || this.expenseLimit === null || this.expenseLimit === undefined) {
      this.inputParam = true;
    } else {
      this.tempAmount = this.savedExpenseLimit - this.expenseLimit;
      if (this.tempAmount < 0) {
        this.negativeValue = true;
      } else {
        this.savedExpenseLimit = this.tempAmount;
      }
      this.userDetails.identifier = this.selectedUser;
      this.userDetails.expensetype = this.expenseType;
      this.userDetails.expenseAmount = this.expenseLimit;
      this.userDetails.expenseDate = this.valueStart;
      console.log(JSON.stringify(this.userDetails));
      if (!this.negativeValue) {
        this.apiService.updateExpenses(this.userDetails).subscribe(data => {
          console.log(data);

        }, error => {
          alert(JSON.stringify(error));
        })
      }
    }
  }
}
