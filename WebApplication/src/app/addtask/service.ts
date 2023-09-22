// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable, catchError } from 'rxjs';
 
// @Injectable({
//   providedIn: 'root'
// })
// export class HttpService {

//   constructor(private http: HttpClient) { }

//   getData(): Observable<any> {
//     return this.http.get('http://localhost:8080/task/');
//   }
 
//   addHero(hero: Hero): Observable<Hero> {
//     return this.http.post<Hero>(this.heroesUrl, hero, httpOptions)
//       .pipe(
//         catchError(this.handleError('addHero', hero))
//       );
//   }
// }