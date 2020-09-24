export class UserUpdateDto {
  firstName: string;
  lastName: any;


  constructor(firstName: string, lastName: any) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
