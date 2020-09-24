export class BoardCreateDto {
  title: string;
  text: string;
  dateAdd: Date;


  constructor(title: string, text: string, dateAdd: Date) {
    this.title = title;
    this.text = text;
    this.dateAdd = dateAdd;
  }
}
