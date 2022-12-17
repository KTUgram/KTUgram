import {Time} from "@angular/common";
import {Comment} from "./comment";
import {User} from "./user";

export interface CommentReport{
  id?: number;
  reason: number;
  date?: Date;
  time?: Time;
  comment: Comment;
  user?: User;
  reasonComment?: String;
}
