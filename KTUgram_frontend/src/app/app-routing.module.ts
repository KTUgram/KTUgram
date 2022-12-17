import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {RegisterPageComponent} from "./pages/register-page/register-page.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {NewsfeedComponent} from "./pages/newsfeed/newsfeed.component";
import {FollowingComponent} from "./pages/following/following.component";
import {ChatPageComponent} from "./pages/chat/chat.component";
import {NgxPermissionsGuard} from "ngx-permissions";
import {AdminGuardsGuard} from "./guards/admin-guards.guard";
import {UsersComponent} from "./pages/admin/users/users.component";
import {RegisterConfirmPageComponent} from "./pages/register-confirm-page/register-confirm-page.component";
import {ProfilePageComponent} from "./pages/profile-page/profile-page.component";
import {UserProfilePageComponent} from "./pages/user-profile-page/user-profile-page.component";
import {CommentsPageComponent} from "./pages/admin/comments-page/comments-page.component";
import {UserCommentsPageComponent} from "./pages/admin/user-comments-page/user-comments-page.component";
import {UserPostsPageComponent} from "./pages/admin/user-posts-page/user-posts-page.component";
import { CommentReportsPageComponent } from './pages/admin/comments-page/reports/commentReports-page.component';

const routes: Routes = [
  {path: 'login', component: LoginPageComponent, canActivate: [NgxPermissionsGuard],
    data: {
      permissions: {
        login: 'GUEST'
      }
    }},
  {path: 'confirm', component: RegisterConfirmPageComponent, canActivate: [NgxPermissionsGuard],
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
      {path: 'chat', component: ChatPageComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: ['USER', 'GUEST']
        }
      }},
      {path: 'user-profile', component: UserProfilePageComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: ['USER']
        }
      }},
      {path: 'profile/:id', component: ProfilePageComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: ['USER', 'GUEST', 'ADMIN']
        }
      }},
      {path: 'users', component: UsersComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: 'ADMIN'
        }
      }},
      {path: 'comments', component: CommentsPageComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: 'ADMIN'
        }
      }},
      {path: 'comments/:id', component: CommentsPageComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: 'ADMIN'
        }
      }},
      {path: 'comments/reports/:id', component: CommentReportsPageComponent, canActivate: [NgxPermissionsGuard],
      data:{
        permissions: {
          only: 'ADMIN'
        }
      }},
      {path: 'posts/:id', component: UserPostsPageComponent, canActivate: [NgxPermissionsGuard],
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
