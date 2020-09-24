export class UserUpdateEmailDto {
  oldEmail: string;
  newEmail: string;
  password: string;


  constructor(oldEmail: string, newEmail: string, password: string) {
    this.oldEmail = oldEmail;
    this.newEmail = newEmail;
    this.password = password;
  }
}
