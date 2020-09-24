import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {RegistrationDialogComponent} from '../../dialog/registration-dialog/registration-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {User} from '../../model/User';
import {DialogAction} from '../../object/DialogResult';
import {UserCreateDto} from '../../../main/data/dto/UserCreateDto';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output()
  createUser = new EventEmitter<User>();

  constructor(
    private dialog: MatDialog,
  ) {
  }

  ngOnInit() {
  }

  registration() {
    const dialogRef = this.dialog.open(RegistrationDialogComponent, {
      data: [new UserCreateDto('', '', '', '', 'USER'),
        'Создание нового пользователя'],
      width: '800px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        this.createUser.emit(result.obj as UserCreateDto);
      }

    });
  }
}
