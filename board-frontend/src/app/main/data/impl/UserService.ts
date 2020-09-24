import {Inject, Injectable, InjectionToken} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CommonService} from './CommonService';
import {User} from '../../../authorization/model/User';
import {UserDAO} from '../interface/UserDAO';
import {UserUpdateDto} from '../dto/UserUpdateDto';
import {Observable} from 'rxjs';
import {UserUpdateEmailDto} from '../dto/UserUpdateEmailDto';
import {UserUpdatePasswordDto} from '../dto/UserUpdatePasswordDto';

export const USER_URL_TOKEN = new InjectionToken<string>('url');


@Injectable({
  providedIn: 'root'
})
export class UserService extends CommonService<User> implements UserDAO {

  constructor(
    @Inject(USER_URL_TOKEN) private baseUrl,
    public httpClient: HttpClient
  ) {
    super(baseUrl, httpClient);
  }


  updateUser(user: UserUpdateDto, id: number): Observable<any> {
    return this.httpClient.put(this.baseUrl + '/' + id, user);
  }

  updateEmail(userUpdateEmailDto: UserUpdateEmailDto): Observable<any> {
    return this.httpClient.put(this.baseUrl + '/email', userUpdateEmailDto);
  }

  updatePassword(userUpdatePasswordDto: UserUpdatePasswordDto, id: number): Observable<any> {
    return this.httpClient.put(this.baseUrl + '/password/' + id, userUpdatePasswordDto);
  }
}
