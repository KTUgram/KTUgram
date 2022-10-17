import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {RegisterPageComponent} from "./pages/register-page/register-page.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {NewsfeedComponent} from "./pages/newsfeed/newsfeed.component";
import {FollowingComponent} from "./pages/following/following.component";
import {ChatComponent} from "./pages/chat/chat.component";

const routes: Routes = [
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegisterPageComponent},
  {
    path: 'main',
    component: MainPageComponent,
    children: [
      {path: '', redirectTo: 'newsfeed', pathMatch: 'full'},
      {path: 'newsfeed', component: NewsfeedComponent},
      {path: 'following', component: FollowingComponent},
      {path: 'chat', component: ChatComponent}
    ]
  },
  {path: '**', redirectTo: "main"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload', relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
