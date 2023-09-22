import { Component, Input, OnInit } from '@angular/core'; 
 

export class taskcategory {
  id: number =0;
  name: string = '';
  description: string='';
  tasks: any[] = []; 
}

@Component({
  selector: 'app-task-category',
  templateUrl: './task-category.component.html',
  styleUrls: ['./task-category.component.css']
})
export class TaskCategoryComponent implements OnInit {  
  @Input('category') category: taskcategory | undefined ;
  constructor() { 
   }
  ngOnInit(): void { 
  }
}
