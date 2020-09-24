import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {DialogAction, DialogResult} from '../../object/DialogResult';
import {UserUpdateDto} from '../../data/dto/UserUpdateDto';

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrls: ['./edit-profile-dialog.component.css']
})
export class EditProfileDialogComponent implements OnInit {

  user: UserUpdateDto;
  dialogTitle: string;

  constructor(
    private dialogRef: MatDialogRef<EditProfileDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [UserUpdateDto, string],
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.user = this.data[0];
    this.dialogTitle = this.data[1];
  }

  confirm() {
    this.dialogRef.close(new DialogResult(DialogAction.SAVE, this.user));

  }

  validInputData(): boolean {
    if (this.user.firstName.length === 0) {
      return true;
    }
    if (this.user.lastName.length === 0) {
      return true;
    }
    return false;
  }

  cancel(): void {
    this.dialogRef.close(null);
  }
}
