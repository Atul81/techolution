import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExpenseReportComponent } from './expense-report/expense-report.component';
import { ApiService } from './app-service.component';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { InputsModule } from '@progress/kendo-angular-inputs';
import { DropDownsModule } from '@progress/kendo-angular-dropdowns';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { IntlModule } from '@progress/kendo-angular-intl';
import { DateInputsModule } from '@progress/kendo-angular-dateinputs';
import { ChartsModule } from '@progress/kendo-angular-charts';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { PDFExportModule } from '@progress/kendo-angular-pdf-export';
import 'hammerjs';

@NgModule({
  declarations: [
    AppComponent,
    ExpenseReportComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    InputsModule,
    BrowserAnimationsModule,
    HttpClientJsonpModule,
    DropDownsModule,
    FormsModule,
    IntlModule, 
    DateInputsModule,
    ChartsModule,
    ButtonsModule,
    PDFExportModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
