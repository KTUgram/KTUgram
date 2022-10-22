import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {RegisterPageComponent} from "./pages/register-page/register-page.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {NewsfeedComponent} from "./pages/newsfeed/newsfeed.component";
import {FollowingComponent} from "./pages/following/following.component";
import {ChatComponent} from "./pages/chat/chat.component";
import {NgxPermissionsGuard} from "ngx-permissions";
import {AdminGuardsGuard} from "./guards/admin-guards.guard";
import {UsersComponent} from "./pages/admin/users/users.component";

const routes: Routes = [
  {path: 'login', component: LoginPageComponent, canActivate: [NgxPermissionsGuard],
    data: {
      permissions: {
        login: 'GUEST'
      }
    }},
  {path: 'register', component: RegisterPageComponent, canActivate: [NgxPermissionsGuard],
    data: {
      permissions: {
        only: 'GUEST'
      }
    }},
  {
    path: 'main',
    component: MainPageComponent,
    children: [
      {path: '', canActivate: [AdminGuardsGuard], pathMatch: 'full', component: MainPageComponent},
      {path: 'newsfeed', component: NewsfeedComponent, canActivate: [NgxPermissionsGuard],
        data: {
          permissions: {
            only: ['USER', 'GUEST', 'ADMIN']
          }
        }},
      {path: 'following', component: FollowingComponent, canActivate: [NgxPermissionsGuard],
      data: {
        permissions: {
          only: ['USER', 'GUEST']
        }
      }},
      {path: 'chat', component: ChatComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: ['USER', 'GUEST']
        }
      }},
      {path: 'users', component: UsersComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: 'ADMIN'
        }
      }}
    ]
  },
  {path: '**', redirectTo: "main"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload', relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
