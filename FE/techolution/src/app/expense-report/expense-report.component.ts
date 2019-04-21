import { Component, OnInit, AfterViewInit, ViewChild, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../app-service.component';
import { User } from '../userData.component';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-expense-report',
  templateUrl: './expense-report.component.html',
  styleUrls: ['./expense-report.component.css']
})
export class ExpenseReportComponent{
 
  constructor(private route: Router, private apiService: ApiService) { }

}
