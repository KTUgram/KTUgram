import {Time} from "@angular/common";
import {User} from "./user";

export interface Post{
  id?: number;
  date: Date;
  time: string;
  about: string;
  content: string;
  location: string;
  title: string;
  state: string;
  user: User;
}
