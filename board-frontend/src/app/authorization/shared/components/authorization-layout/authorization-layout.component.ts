import {Component, OnInit} from '@angular/core';
import {RegistrationService} from '../../../data/impl/RegistrationService';
import {UserCreateDto} from '../../../../main/data/dto/UserCreateDto';
import {AlertService} from '../../../../main/service/alert.service';

@Component({
  selector: 'app-authorization-layout',
  templateUrl: './authorization-layout.component.html',
  styleUrls: ['./authorization-layout.component.css']
})
export class AuthorizationLayoutComponent implements OnInit {

  constructor(
    private registrationService: RegistrationService,
    private alertService: AlertService
  ) {
  }

  ngOnInit() {
  }

  createUser(userCreateDto: UserCreateDto) {
    userCreateDto.userRole = 'USER';
    this.registrationService.add(userCreateDto).subscribe(result => {
      this.alertService.success('Вы успешо зарегистрировались!');
    });
  }
}
