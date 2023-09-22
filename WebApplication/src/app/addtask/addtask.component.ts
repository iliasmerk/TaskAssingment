import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';  
 
export class taskcategory {
  id: number =0;
  name: string = '';
  description: string='';
  tasks: any[] = []; 
}

@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})

export class AddtaskComponent { 
 @Input('category') category: taskcategory | undefined ;  
 date:Date | undefined;

 isShowDivIf = false; 

 toggleDisplayDivIf() { 
   this.isShowDivIf = !this.isShowDivIf; 
 }

 constructor(private http: HttpClient){    
   
 }
 
 ngOnInit(){

 }

  public save(name:String,description:String,timestamp:String){   
      const url = 'http://localhost:8080/tasks/';  
      const headers = {  'Content-Type': 'application/json' };
      const body ={"name":name,"description":description,"deadline":timestamp,"categoryId":this.category?.id}; 
      this.date=new Date(timestamp.toString());
      if (isNaN(this.date.getTime())){
        alert(this.date);
      }else{
        this.http.post<any>(url, body, { headers }).subscribe(data => { 
        }); 
     //   window.location.reload();
      }
      
  }
}
