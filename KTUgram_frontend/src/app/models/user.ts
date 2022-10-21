import {Person} from "./person";

export interface User{
  id?: number;
  about?: string;
  profice_pic: string;
  status: boolean;
  person: Person;
}
