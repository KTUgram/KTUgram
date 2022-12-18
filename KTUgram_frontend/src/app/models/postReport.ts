import {Time} from "@angular/common";
import {User} from "./user";

export interface PostReport{
  id?: number;
  date?: Date;
  time?: Time;
  reasonComment: string;
  reason: number;
  reporter?: User;
  reportedPost?: User;
}