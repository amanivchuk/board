import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {AuthorizationService} from '../../authorization/services/authorization.service';
import {Router} from '@angular/router';
import {catchError, tap} from 'rxjs/operators';
import {SpinnerService} from '../service/spinner.service';

@Injectable({providedIn: 'root'})
export class AuthorizationInterceptor implements HttpInterceptor {

  constructor(
    private auth: AuthorizationService,
    private router: Router,
    private spinnerService: SpinnerService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinnerService.show(); // показать спиннер

    if (this.auth.isAuthenticated()) {
      req = req.clone({
        setHeaders: {
          Authorization: this.auth.token
        }
      });
    }

    return next
      .handle(req)
      .pipe(
        tap((event: HttpEvent<any>) => {
          // console.log('Intercept');
          if (event instanceof HttpResponse) { //пришел ответ - значит запрос завершен
            this.spinnerService.hide(); //когда запрос выполнился - убрать спиннер
          }
        }, (error) => {
          this.spinnerService.hide(); //если возникла ошибка - убрать спиннер
        }),
        catchError((error: HttpErrorResponse) => {
          // console.log('[Interceptor Error]', error);
          if (error.status === 401) {
            this.auth.logout();
            this.router.navigate(['/authorization', 'login'], {
              queryParams: {
                authFailed: true
              }
            });
          }
          return throwError(error);
        })
      );
  }

}
