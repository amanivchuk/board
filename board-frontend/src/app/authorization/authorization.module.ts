import {AuthorizationLayoutComponent} from './shared/components/authorization-layout/authorization-layout.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {SharedModule} from '../main/shared/shared.module';
import {RegistrationDialogComponent} from './dialog/registration-dialog/registration-dialog.component';
import {HeaderComponent} from './views/header/header.component';
import {FooterComponent} from './views/footer/footer.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {AboutDialogComponent} from './dialog/about-dialog/about-dialog.component';

@NgModule({
  declarations: [
    AuthorizationLayoutComponent,
    LoginPageComponent,
    RegistrationDialogComponent,
    HeaderComponent,
    FooterComponent,
    AboutDialogComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    RouterModule.forChild([
      {
        path: '', component: AuthorizationLayoutComponent, children: [
          {path: '', redirectTo: '/authorization/login', pathMatch: 'full'},
          {path: 'login', component: LoginPageComponent}
        ]
      }
    ]),
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule
  ],
  exports: [RouterModule],
  providers: []
})
export class AuthorizationModule {

}
