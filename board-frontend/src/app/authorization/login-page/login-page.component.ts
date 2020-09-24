import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserAuthorizationJWT} from '../interfaces';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {AuthorizationService} from '../services/authorization.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;
  message: string;

  constructor(
    private router: Router,
    public authService: AuthorizationService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params: Params) => {
      if (params['loginAgain']) {
        this.message = 'Пожалуйста ввойдите в систему';
      } else if (params['authFailed']) {
        this.message = 'Сессия устарела. Ввойдите в систему заново';
      }
    });

    this.form = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.minLength(2)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(4)])
    });
  }

  submit() {
    if (this.form.invalid) {
      return;
    }

    const userAuthorization: UserAuthorizationJWT = {
      email: this.form.value.email,
      password: this.form.value.password,
    };

    this.authService.login(userAuthorization).subscribe(() => {
      this.form.reset();
      this.router.navigate(['/main']);
    });
  }
}
