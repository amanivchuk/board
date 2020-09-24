import {CommonDAO} from './CommonDAO';
import {Observable} from 'rxjs';
import {BoardSearchValues} from '../SearchObjects';
import {Board} from '../../model/Board';

export interface BoardDAO extends CommonDAO<Board> {

  findAdvert(boardSearchValues: BoardSearchValues): Observable<any>;

}
