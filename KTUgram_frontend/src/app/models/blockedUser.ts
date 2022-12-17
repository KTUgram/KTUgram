import {Time} from "@angular/common";
import {User} from "./user";

export interface BlockedUser{
  id?: number;
  blockerUser?: User;
  blockedUser?: User;
  date?: Date;
  time?: Time;
}
