import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {UserUpdatePasswordDto} from '../../data/dto/UserUpdatePasswordDto';
import {DialogAction, DialogResult} from '../../object/DialogResult';

@Component({
  selector: 'app-edit-password-dialog',
  templateUrl: './edit-password-dialog.component.html',
  styleUrls: ['./edit-password-dialog.component.css']
})
export class EditPasswordDialogComponent implements OnInit {

  user: UserUpdatePasswordDto;
  dialogTitle: string;

  newPassword: string = '';
  confirmNewPassword: string = '';

  constructor(
    private dialogRef: MatDialogRef<EditPasswordDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [UserUpdatePasswordDto, string],
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.user = this.data[0];
    this.dialogTitle = this.data[1];
  }


  confirm() {
    this.user.password = this.newPassword;
    this.dialogRef.close(new DialogResult(DialogAction.SAVE, this.user));
  }

  validInputData(): boolean {
    if (this.newPassword.length === 0) {
      return true;
    }
    if (this.confirmNewPassword.length === 0) {
      return true;
    }
    if (this.newPassword !== this.confirmNewPassword) {
      return true;
    }
    return false;
  }

  cancel(): void {
    this.dialogRef.close(null);
  }
}
