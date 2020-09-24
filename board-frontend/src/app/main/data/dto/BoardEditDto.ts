export class BoardEditDto {
  id: number;
  title: string;
  text: string;
  dateAdd: Date;


  constructor(id: number, title: string, text: string, dateAdd: Date) {
    this.id = id;
    this.title = title;
    this.text = text;
    this.dateAdd = dateAdd;
  }
}
