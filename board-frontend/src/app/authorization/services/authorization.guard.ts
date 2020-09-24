import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthorizationService} from './authorization.service';

@Injectable({providedIn: 'root'})
export class AuthorizationGuard implements CanActivate {

  constructor(
    private authorizationService: AuthorizationService,
    private router: Router
  ) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {

    if (this.authorizationService.isAuthenticated()) {
      // console.log('authorized!');
      return true;
    } else {
      this.authorizationService.logout();
      this.router.navigate(['/authorization', 'login'], {
        queryParams: {
          loginAgain: true
        }
      });
    }
  }

}
