import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from  '@angular/common/http';
import { Injectable } from  '@angular/core'
import { Observable } from 'rxjs';
import { HttpService } from './service';

@Injectable({
  providedIn:  'root'
  })

@Component({
  selector: 'app-main-component',
  templateUrl: './main-component.component.html',
  styleUrls: ['./main-component.component.css']
})
export class MainComponentComponent { 
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
 
  categories: any[]=[];

  constructor(private httpservice: HttpService) { }
  
  ngOnInit() {
    this.httpservice.getData().subscribe((data) => {   
      this.categories=data;
     });   
  }
} 