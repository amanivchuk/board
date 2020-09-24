import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Board} from '../../model/Board';
import {DialogAction, DialogResult} from '../../object/DialogResult';
import {BoardCreateDto} from '../../data/dto/BoardCreateDto';

@Component({
  selector: 'app-add-board-dialog',
  templateUrl: './add-board-dialog.component.html',
  styleUrls: ['./add-board-dialog.component.css']
})
export class AddBoardDialogComponent implements OnInit {

  board: BoardCreateDto;
  dialogTitle: string;

  newPicture: FormData;
  newTitle: string = '';
  newText: string = '';

  selectedFile: File;

  tmpMessage: string;
  tmpImageURL: any;
  imagePath;

  constructor(
    private dialogRef: MatDialogRef<AddBoardDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: [Board, string],
  ) {
  }

  ngOnInit(): void {
    this.board = this.data[0];
    this.dialogTitle = this.data[1];

  }


  confirm(): void {

    const uploadAvatarData = new FormData();
    // uploadAvatarData.append('imageFile', this.selectedFile, this.selectedFile.name);

    this.board.title = this.newTitle;
    this.board.text = this.newText;
    this.board.dateAdd = new Date();

    this.dialogRef.close(new DialogResult(DialogAction.SAVE, this.board, uploadAvatarData));
  }

  cancel(): void {
    this.dialogRef.close(null);
  }

  validInputData(): boolean {
    if (this.newTitle.length === 0) {
      return true;
    }
    if (this.newText.length === 0) {
      return true;
    }

    return false;
  }

  fileSelected(files) {
    // this.selectedFile = <File>event.target.files[0];
    if (files.length === 0) {
      return;
    }

    let mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.tmpMessage = 'Только фотографии';
      return;
    }

    this.selectedFile = files[0];

    let reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      this.tmpImageURL = reader.result;
    };
  }
}
