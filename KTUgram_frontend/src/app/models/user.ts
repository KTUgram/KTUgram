import {Person} from "./person";

export interface User{
  id?: number;
  about?: string;
  profile_pic?: string;
  status: number;
  person: Person;
}
