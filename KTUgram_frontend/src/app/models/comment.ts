import {Time} from "@angular/common";
import {Post} from "./post";
import {User} from "./user";

export interface Comment{
  id?: number;
  content: string;
  date?: Date;
  time?: Time;
  post: Post;
  user?: User;
}
