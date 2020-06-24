import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ToDoList';
     /* An empty array that is responsible 
       to add a division */
       public items = []; 
       oldItems=[];
       public newTask; 
     
     
       /* When input is empty, it will 
          not create a new division */
       public addToList() { 
           if (this.newTask == '') { 
           } 
           else { 
               this.items.push(this.newTask); 
               this.newTask = ''; 
           } 
       } 

       public deleteTask(index) { 
         console.log(index)
         console.log(this.items)
           this.items.splice(index, 1); 
       } 
       public completeTask(index) { 
        console.log(index)
        console.log(this.items)
      } 
}
