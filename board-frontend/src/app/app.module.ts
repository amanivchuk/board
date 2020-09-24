import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import {SharedModule} from './main/shared/shared.module';
import {AuthorizationInterceptor} from './main/shared/authorization.interceptor';
import {REGISTRATION_URL_TOKEN} from './authorization/data/impl/RegistrationService';
import {BOARD_URL_TOKEN} from './main/data/impl/BoardService';
import {USER_URL_TOKEN} from './main/data/impl/UserService';
import {AlertService} from './main/service/alert.service';


registerLocaleData(localeRu);

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SharedModule,

  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthorizationInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthorizationInterceptor, multi: true},
    {provide: REGISTRATION_URL_TOKEN, useValue: 'http://localhost:8082/users'},
    {provide: BOARD_URL_TOKEN, useValue: 'http://localhost:8082/board'},
    {provide: USER_URL_TOKEN, useValue: 'http://localhost:8082/users'},
    AlertService
  ],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
