import {User} from '../../authorization/model/User';

export class Board {
  id: number;
  title: string;
  picture: any;
  text: string;
  dateAdd: Date;
  user?: User;


  constructor(id: number, title: string, picture: any, text: string, dateAdd: Date, user?: User) {
    this.id = id;
    this.title = title;
    this.picture = picture;
    this.text = text;
    this.dateAdd = dateAdd;
    this.user = user;
  }
}
