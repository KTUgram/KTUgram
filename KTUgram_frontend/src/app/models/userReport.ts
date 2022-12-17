import {Time} from "@angular/common";
import {User} from "./user";

export interface UserReport{
  id?: number;
  date: Date;
  time: Time;
  reasonComment: string;
  reason: number;
  reporter?: User;
  reportedUser?: User;
}
