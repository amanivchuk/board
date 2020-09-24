import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService<T> {

  readonly url: string;

  constructor(
    @Inject('string') url: string,
    public httpClient: HttpClient
  ) {
    this.url = url;
  }

  add(t: T): Observable<T> {
    return this.httpClient.post<T>(this.url, t);
  }

  delete(id: number): Observable<T> {
    return this.httpClient.delete<T>(this.url + '/' + id);
  }

  get(id: number): Observable<T> {
    return this.httpClient.get<T>(this.url + '/' + id);
  }

  findAll(): Observable<T[]> {
    return this.httpClient.get<T[]>(this.url);
  }

  update(t: T): Observable<T> {
    return this.httpClient.put<T>(this.url, t);
  }

}
