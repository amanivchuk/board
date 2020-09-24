import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MainLayoutComponent} from './shared/component/main-layout/main-layout.component';
import {AuthorizationGuard} from '../authorization/services/authorization.guard';
import {HeaderComponent} from './views/header/header.component';
import {FooterComponent} from './views/footer/footer.component';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {BoardComponent} from './views/board/board.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatInputModule} from '@angular/material/input';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {AddBoardDialogComponent} from './dialog/add-board-dialog/add-board-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {EditProfileDialogComponent} from './dialog/edit-profile-dialog/edit-profile-dialog.component';
import {MatMenuModule} from '@angular/material/menu';
import {EditEmailDialogComponent} from './dialog/edit-email-dialog/edit-email-dialog.component';
import {ConfirmPasswordDialogComponent} from './dialog/confirm-password-dialog/confirm-password-dialog.component';
import {EditPasswordDialogComponent} from './dialog/edit-password-dialog/edit-password-dialog.component';
import {MatCardModule} from '@angular/material/card';
import {EditBoardDialogComponent} from './dialog/edit-board-dialog/edit-board-dialog.component';
import {ShowBoardDialogComponent} from './dialog/show-board-dialog/show-board-dialog.component';
import {ConfirmDialogComponent} from './dialog/confirm-dialog/confirm-dialog.component';
import {BoardDatePipe} from './pipe/board-date.pipe';
import {SharedModule} from './shared/shared.module';

@NgModule({
  declarations: [
    MainLayoutComponent,
    HeaderComponent,
    FooterComponent,
    BoardComponent,
    AddBoardDialogComponent,
    EditProfileDialogComponent,
    EditEmailDialogComponent,
    ConfirmPasswordDialogComponent,
    EditPasswordDialogComponent,
    EditBoardDialogComponent,
    ShowBoardDialogComponent,
    ConfirmDialogComponent,
    BoardDatePipe
  ],
  imports: [
    RouterModule.forChild([
      {path: '', component: MainLayoutComponent, canActivate: [AuthorizationGuard]}
    ]),
    CommonModule,
    FormsModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatCardModule,
    SharedModule,
  ],
  exports: [RouterModule],
  providers: [],
  entryComponents: []
})
export class MainModule {

}
