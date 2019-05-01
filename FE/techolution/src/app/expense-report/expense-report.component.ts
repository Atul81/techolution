import { Component, OnInit, AfterViewInit, ViewChild, Input, Output, EventEmitter, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../app-service.component';
import { User } from '../userData.component';

@Component({
  selector: 'app-expense-report',
  templateUrl: './expense-report.component.html',
  styleUrls: ['./expense-report.component.css']
})
export class ExpenseReportComponent implements OnChanges {

  constructor(private route: Router, private apiService: ApiService) { }

  public user: User;
  @Input() set userDetails(value: User){
    this.user = value;
    this.onClick();
  }

  @Input() changeEvent: string;

  @Output() dataEvent = new EventEmitter<string>();

  message: string;

  onClick() {
    console.log(JSON.stringify(this.user));
    this.dataShared();
  }

  dataShared() {
    this.message ='Checking for data exchange';
    this.dataEvent.emit(this.message);
  }

  ngOnChanges(changes: SimpleChanges) {
    // this.onClick();
    const changeLog: SimpleChange = changes.name;
    console.log(changes + ' ' + changeLog);
    console.log(JSON.stringify(this.user) + ' ' + changes);
  }
}
