import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AboutDialogComponent} from '../../../authorization/dialog/about-dialog/about-dialog.component';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  year: Date;
  site = '';
  siteName = 'Bulletin Board';

  constructor(private dialog: MatDialog) {
  }

  ngOnInit() {
    this.year = new Date();
  }

  openAboutDialog() {
    this.dialog.open(AboutDialogComponent, {
      autoFocus: false,
      data: {
        dialogTitle: 'About',
        message: 'Bulletin Board Test Project'
      },
      width: '400px'
    });
  }

}
