import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './userData.component';

@Injectable()
export class ApiService {

    constructor(public http: HttpClient) { }
    baseUrlExpense: string = 'http://localhost:8080/expenses/';
    baseUrlMisc: string = 'http://localhost:8080/misc/';

    checkingApi(): Observable<any>{
        return this.http.get(this.baseUrlExpense +'test');
    }
    getUsers(): Observable<any> {
        return this.http.get(this.baseUrlMisc+ 'users');
    }

    updateLimit(user: User): Observable<any> {
        return this.http.post(this.baseUrlMisc+ 'updateExpense', user);
    }

    getExpenses(): Observable<any> {
        return this.http.get(this.baseUrlMisc + 'getExpenses');
    }

    updateExpenses(user: User): Observable<any> {
        return this.http.post(this.baseUrlExpense + 'updateExpenses', user);
    } 
}