import {Inject, Injectable, InjectionToken} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonService} from './CommonService';
import {RegistrationDAO} from '../interface/RegitrationDAO';
import {User} from '../../model/User';
import {Observable} from 'rxjs';

export const REGISTRATION_URL_TOKEN = new InjectionToken<string>('url');

@Injectable({
  providedIn: 'root'
})
export class RegistrationService extends CommonService<User> implements RegistrationDAO {

  constructor(
    @Inject(REGISTRATION_URL_TOKEN) private baseUrl,
    private http: HttpClient
  ) {
    super(baseUrl, http);
  }

  checkEmailExist(email: string): Observable<any> {
    return this.http.get(this.baseUrl + '/check/' + email);
  }
}
