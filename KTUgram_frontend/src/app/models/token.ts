export interface Token{
  loggedInUserName: string;
  loggedInUserId: string;
  isAdmin: boolean;
  rights: string[];
}
