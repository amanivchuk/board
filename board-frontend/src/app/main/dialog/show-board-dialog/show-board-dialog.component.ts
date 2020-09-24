import {Component, Inject, OnInit} from '@angular/core';
import {Board} from '../../model/Board';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {DialogAction, DialogResult} from '../../object/DialogResult';
import {EditBoardDialogComponent} from '../edit-board-dialog/edit-board-dialog.component';
import {BoardEditDto} from '../../data/dto/BoardEditDto';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-show-board-dialog',
  templateUrl: './show-board-dialog.component.html',
  styleUrls: ['./show-board-dialog.component.css']
})
export class ShowBoardDialogComponent implements OnInit {

  userId: number;
  board: Board;
  dialogTitle: string;

  constructor(
    private dialogRef: MatDialogRef<ShowBoardDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [Board, number, string],
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.board = this.data[0];
    this.userId = this.data[1];
    this.dialogTitle = this.data[2];
  }

  editBoard() {
    const dialogRef = this.dialog.open(EditBoardDialogComponent, {
      data: [this.board, 'Редактирование объявления'],
      width: '800px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        const editedBoard = result.obj as Board;
        const boardEditDto = new BoardEditDto(editedBoard.id, editedBoard.title, editedBoard.text, editedBoard.dateAdd);

        this.dialogRef.close(new DialogResult(DialogAction.SAVE, boardEditDto));
      }
    });
  }

  delete() {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: '500px',
      data: {
        dialogTitle: 'Подтвердить действие',
        message: `Вы действительно хотите удалить?`
      },
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!(result)) { // если просто закрыли окно, ничего не нажав
        return;
      }

      if (result.action === DialogAction.OK) {
        this.dialogRef.close(new DialogResult(DialogAction.DELETE)); //нажали удалить
      }
    });
  }

  cancel(): void {
    this.dialogRef.close(null);
  }

  checkPermission(): boolean {
    if (this.userId === this.board.user.id) {
      return true;
    } else {
      return false;
    }
  }
}
