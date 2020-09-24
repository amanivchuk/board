import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DialogAction, DialogResult} from '../../object/DialogResult';

@Component({
  selector: 'app-confirm-password-dialog',
  templateUrl: './confirm-password-dialog.component.html',
  styleUrls: ['./confirm-password-dialog.component.css']
})
export class ConfirmPasswordDialogComponent implements OnInit {

  dialogTitle: string;
  password: string;

  constructor(
    private dialogRef: MatDialogRef<ConfirmPasswordDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: { dialogTitle: string, password: string }
  ) {
    this.dialogTitle = data.dialogTitle;
    this.password = data.password;
  }

  ngOnInit(): void {
  }

  private onConfirm(): void {
    this.dialogRef.close(new DialogResult(DialogAction.OK, this.password));
  }

  private onCancel(): void {
    this.dialogRef.close(new DialogResult(DialogAction.CANCEL));
  }

}
