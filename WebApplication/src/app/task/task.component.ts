import { formatDate } from '@angular/common';
import { Component, Input } from '@angular/core';


export class task {
  id: number =0;
  name: string = '';
  description: string='';
  deadline:string=''; 
}

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
 
export class TaskComponent {
  @Input('task') task: task | undefined ;
  date: Date | undefined;
  showdate:String="";
  constructor() { 
   }
  ngOnInit(): void { 
    
    if (this.task?.deadline !==undefined){ 
      this.showdate=formatDate(new Date(this.task?.deadline), 'dd-MM-yyyy' ,'en-US');
    }

  }
}
