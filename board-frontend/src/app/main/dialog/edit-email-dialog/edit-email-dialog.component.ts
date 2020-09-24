import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {UserUpdateEmailDto} from '../../data/dto/UserUpdateEmailDto';
import {DialogAction, DialogResult} from '../../object/DialogResult';
import {ConfirmPasswordDialogComponent} from '../confirm-password-dialog/confirm-password-dialog.component';

@Component({
  selector: 'app-edit-email-dialog',
  templateUrl: './edit-email-dialog.component.html',
  styleUrls: ['./edit-email-dialog.component.css']
})
export class EditEmailDialogComponent implements OnInit {

  user: UserUpdateEmailDto;
  dialogTitle: string;

  constructor(
    private dialogRef: MatDialogRef<EditEmailDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [UserUpdateEmailDto, string],
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.user = this.data[0];
    this.dialogTitle = this.data[1];
  }

  confirm() {
    const dialogRef = this.dialog.open(ConfirmPasswordDialogComponent, {
      maxWidth: '500px',
      data: {
        dialogTitle: 'Введите пароль для проверки',
        password: ''
      },
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!(result)) { // если просто закрыли окно, ничего не нажав
        return;
      }

      if (result.action === DialogAction.OK) {
        const password = result.obj as string;
        console.log('password = ' + password);
        this.user.password = password;
        // this.dialogRef.close(new DialogResult(DialogAction.OK));
        this.dialogRef.close(new DialogResult(DialogAction.SAVE, this.user));
      }
    });
  }

  validInputData(): boolean {
    if (this.user.newEmail.length === 0) {
      return true;
    }
    if (this.user.oldEmail.length === 0) {
      return true;
    }
    return false;
  }

  cancel(): void {
    this.dialogRef.close(null);
  }

}
