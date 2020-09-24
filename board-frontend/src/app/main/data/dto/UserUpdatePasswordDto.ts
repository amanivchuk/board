export class UserUpdatePasswordDto {
  password: string;


  constructor(password: string) {
    this.password = password;
  }
}
