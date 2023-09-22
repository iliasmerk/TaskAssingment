import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TaskCategoryComponent } from './task-category/task-category.component';
import { TaskComponent } from './task/task.component';
import { HttpClientModule } from '@angular/common/http';
import { MainComponentComponent } from './main-component/main-component.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddtaskComponent } from './addtask/addtask.component'; 

@NgModule({
  declarations: [
    AppComponent, 
    TaskCategoryComponent,
    TaskComponent,
    MainComponentComponent,
    AddtaskComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
