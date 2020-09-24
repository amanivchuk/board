import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {FbAuthorizationResponseJWT, UserAuthorizationJWT} from '../interfaces';
import {Observable, Subject, throwError} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AlertService} from '../../main/service/alert.service';

@Injectable({providedIn: 'root'})
export class AuthorizationService {

  public error$: Subject<string> = new Subject<string>();

  constructor(
    private router: Router,
    private http: HttpClient,
    private alertService: AlertService
  ) {
  }

  get token(): string {
    const expDate = new Date(localStorage.getItem('token-exp'));
    if (new Date() > expDate) {
      this.logout();
      return null;
    }
    return localStorage.getItem('token');
  }

  login(userAuthorization: UserAuthorizationJWT): Observable<any> {
    return this.http.post(`http://localhost:8082/authentication/login`, userAuthorization)
      .pipe(
        tap(this.setToken),
        catchError(
          this.handleError.bind(this))
      );
  }

  logout() {
    this.setToken(null);
    this.router.navigate(['/authorization', 'login']);
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  private handleError(error: HttpErrorResponse) {

    const message = error.error.error;
    console.log(error.error.error);

    switch (message) {
      case 'Bad Request':
        this.error$.next('Невернвый логин');
        break;
      case 'Unauthorized':
        this.error$.next('Невернвый пароль');
        break;
    }

    return throwError(error);
  }

  private setToken(response: FbAuthorizationResponseJWT | null) {
    if (response) {
      localStorage.setItem('token', 'Bearer ' + response.token);
      localStorage.setItem('token-exp', response.expiration);
    } else {
      localStorage.clear();
    }
  }
}
