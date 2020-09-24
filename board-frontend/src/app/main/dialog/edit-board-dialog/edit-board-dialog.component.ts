import {Component, Inject, OnInit} from '@angular/core';
import {Board} from '../../model/Board';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DialogAction, DialogResult} from '../../object/DialogResult';

@Component({
  selector: 'app-edit-board-dialog',
  templateUrl: './edit-board-dialog.component.html',
  styleUrls: ['./edit-board-dialog.component.css']
})
export class EditBoardDialogComponent implements OnInit {

  board: Board;
  dialogTitle: string;


  constructor(
    private dialogRef: MatDialogRef<EditBoardDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [Board, string],
  ) {
  }

  ngOnInit(): void {
    this.board = this.data[0];
    this.dialogTitle = this.data[1];
  }

  confirm() {
    this.board.dateAdd = new Date();
    this.dialogRef.close(new DialogResult(DialogAction.SAVE, this.board));
  }

  cancel() {
    this.dialogRef.close(null);
  }

  validInputData(): boolean {
    if (this.board.title.length === 0) {
      return true;
    }
    if (this.board.text.length === 0) {
      return true;
    }
    return false;
  }
}
