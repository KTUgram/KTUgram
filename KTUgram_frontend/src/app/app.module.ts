import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { LoginCardComponent } from './components/login-card/login-card.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { RegisterCardComponent } from './components/register-card/register-card.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BackEndUrlInterceptor} from "./services/backend-url.interceptor";
import {RouterModule} from "@angular/router";
import { MainPageComponent } from './pages/main-page/main-page.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import { NewsfeedComponent } from './pages/newsfeed/newsfeed.component';
import { FollowingComponent } from './pages/following/following.component';
import { ChatComponent } from './pages/chat/chat.component';
import { UsersComponent } from './pages/admin/users/users.component';
import {NgxPermissionsModule, NgxPermissionsRestrictStubModule} from "ngx-permissions";
import {AdminGuardsGuard} from "./guards/admin-guards.guard";
import {MatMenuModule} from "@angular/material/menu";
import { PostComponent } from './components/post/post.component';
import {ScrollingModule} from "@angular/cdk/scrolling";
import { RegisterConfirmPageComponent } from './pages/register-confirm-page/register-confirm-page.component';
import { CommentsPageComponent } from './pages/admin/comments-page/comments-page.component';
import { UserCommentsPageComponent } from './pages/admin/user-comments-page/user-comments-page.component';
import { UserPostsPageComponent } from './pages/admin/user-posts-page/user-posts-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { UserProfilePageComponent } from './pages/user-profile-page/user-profile-page.component';
import { UsersTableComponent } from './components/admin/users-table/users-table.component';
import {MatTableModule} from "@angular/material/table";
import {MatRippleModule} from "@angular/material/core";
import { AddPostComponent } from './components/add-post/add-post.component';
import {MatDialogModule} from "@angular/material/dialog";
import { UploadImgComponent } from './components/upload-img/upload-img.component';
import { DragNDropDirective } from './directives/dragNDrop/drag-ndrop.directive';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    LoginCardComponent,
    RegisterPageComponent,
    RegisterCardComponent,
    MainPageComponent,
    NewsfeedComponent,
    FollowingComponent,
    ChatComponent,
    UsersComponent,
    PostComponent,
    RegisterConfirmPageComponent,
    CommentsPageComponent,
    UserCommentsPageComponent,
    UserPostsPageComponent,
    ProfilePageComponent,
    UserProfilePageComponent,
    UsersTableComponent,
    AddPostComponent,
    UploadImgComponent,
    DragNDropDirective
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        HttpClientModule,
        MatSidenavModule,
        MatToolbarModule,
        MatIconModule,
        NgxPermissionsModule.forRoot(),
        NgxPermissionsRestrictStubModule,
        MatMenuModule,
        ScrollingModule,
        MatTableModule,
        MatRippleModule,
        MatDialogModule
    ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BackEndUrlInterceptor, multi: true },
    AdminGuardsGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
