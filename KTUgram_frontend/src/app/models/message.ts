import {Time} from "@angular/common";
import {User} from "./user";

export interface Message{
  id?: number;
  content: string;
  date?: Date;
  time?: Time;
  read_date?: Date;
  read_time?: Time;
  state?: number;
  receiver_user: User;
  writer_user?: User;
}
