import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BoardSearchValues} from '../../data/SearchObjects';
import {Board} from '../../model/Board';
import {MatTableDataSource} from '@angular/material/table';
import {PageEvent} from '@angular/material/paginator';
import {DialogAction} from '../../object/DialogResult';
import {AddBoardDialogComponent} from '../../dialog/add-board-dialog/add-board-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {BoardCreateDto} from '../../data/dto/BoardCreateDto';
import {ShowBoardDialogComponent} from '../../dialog/show-board-dialog/show-board-dialog.component';
import {BoardEditDto} from '../../data/dto/BoardEditDto';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  @Input()
  totalBoardFounded: number;
  @Input()
  showSearch: boolean; //показать/скрыть поиск
  @Input()
  userId: number;

  @Output()
  paging = new EventEmitter<PageEvent>(); // переход по страницам данных
  @Output()
  searchAction = new EventEmitter<BoardSearchValues>();
  @Output()
  toggleSearch = new EventEmitter<boolean>(); //показать/скрыть поиск
  @Output()
  addBoard = new EventEmitter<{ boardCreateDto: BoardCreateDto, formData: FormData }>();
  @Output()
  editBoard = new EventEmitter<BoardEditDto>();
  @Output()
  deleteBoard = new EventEmitter<Board>();

  readonly defaultSortColumn = 'dateAdd';
  readonly defaultSortDirection = 'desc';

  boardSearchValues: BoardSearchValues;
  boards: Board[];
  changed = false;
  filterTitle: string;
  dataSource: MatTableDataSource<Board> = new MatTableDataSource<Board>();
  filterSortColumn: string;
  filterSortDirection: string;
  sortIconName: string;

  //название иконки из коллекции
  readonly iconNameDown = 'arrow_downward';
  readonly iconNameUp = 'arrow_upward';
  // цвета
  readonly colorWhite = '#fff';

  displayedColumns: string[] = ['id', 'picture', 'title', 'text', 'dateAdd', 'author', 'operations'];

  constructor(
    private dialog: MatDialog
  ) {
  }

  @Input('boardSearchValues')
  set setBoardSearchValues(boardSearchValues: BoardSearchValues) {
    this.boardSearchValues = boardSearchValues;
    this.initSearchValues(); //записать в локальные переменные
    this.initSortDirectionIcon(); //показать правильную иконку (убывание, возрастание)
  }

  @Input('boards')
  set setEmployees(boards: Board[]) {
    this.boards = boards;
    this.fillTable(); // передать данные таблице для отображения сотрудников
  }

  ngOnInit(): void {
  }

  fillTable() {
    if (!this.dataSource) {
      return;
    }
    // console.log(this.boards);
    this.dataSource.data = this.boards;
  }

  pageChanged(pageEvent: PageEvent) {
    this.paging.emit(pageEvent);
  }

  initSearch() {
    this.boardSearchValues.title = this.filterTitle;
    this.boardSearchValues.sortColumn = this.filterSortColumn;
    this.boardSearchValues.sortDirection = this.filterSortDirection;

    this.searchAction.emit(this.boardSearchValues);

    this.changed = false; // сбрасываем флаг изменения
  }

  checkFilterChanged() {
    this.changed = false;

    // поочередно проверяем все фильтры (текущее введенное значение с последним сохраненным)
    if (this.boardSearchValues.title !== this.filterTitle) {
      this.changed = true;
    }

    if (this.boardSearchValues.sortColumn !== this.filterSortColumn) {
      this.changed = true;
    }

    if (this.boardSearchValues.sortDirection !== this.filterSortDirection) {
      this.changed = true;
    }

    return this.changed;
  }

  initSortDirectionIcon() {
    if (this.filterSortDirection === 'desc') {
      this.sortIconName = this.iconNameDown;
    } else {
      this.sortIconName = this.iconNameUp;
    }
  }

  changedSortDirection() {
    if (this.filterSortDirection === 'asc') {
      this.filterSortDirection = 'desc';
    } else {
      this.filterSortDirection = 'asc';
    }
    this.initSortDirectionIcon();
  }

  //обновить локальные переменные поиска
  initSearchValues() {

    if (!this.boardSearchValues) {
      return;
    }

    this.filterTitle = this.boardSearchValues.title;
    this.filterSortColumn = this.boardSearchValues.sortColumn;
    this.filterSortDirection = this.boardSearchValues.sortDirection;
  }

  //обновить локальные переменные поиска
  clearSearchValues() {
    this.filterTitle = '';
    this.filterSortColumn = this.defaultSortColumn;
    this.filterSortDirection = this.defaultSortDirection;
  }

  onToggleSearch() {
    this.toggleSearch.emit(!this.showSearch);
  }


  openAddAdvertDialog() {
    const dialogRef = this.dialog.open(AddBoardDialogComponent, {
      data: [new BoardCreateDto('', '', null),
        'Добавление нового объявления'],
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        const boardCreateDto: BoardCreateDto = result.obj as BoardCreateDto;
        const formData: FormData = result.formData as FormData;
        this.addBoard.emit({boardCreateDto, formData});
      }
    });
  }

  openAdvertContent(board: Board) {
    const dialogRef = this.dialog.open(ShowBoardDialogComponent, {
      data: [board, this.userId, 'Просмотр объявления'],
      width: '800px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (!(result)) {
        return;
      }

      if (result.action === DialogAction.SAVE) {
        const boardEditDto = result.obj as BoardEditDto;
        this.editBoard.emit(boardEditDto);
      }
      if (result.action === DialogAction.DELETE) {
        this.deleteBoard.emit(board);
      }
    });
  }
}
