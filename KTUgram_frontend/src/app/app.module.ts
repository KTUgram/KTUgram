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
import { UsersComponent } from './pages/admin/users/users.component';
import {NgxPermissionsModule, NgxPermissionsRestrictStubModule} from "ngx-permissions";
import {AdminGuardsGuard} from "./guards/admin-guards.guard";
import {MatMenuModule} from "@angular/material/menu";
import { PostComponent } from './components/post/post.component';
import {ScrollingModule} from "@angular/cdk/scrolling";
import { RegisterConfirmPageComponent } from './pages/register-confirm-page/register-confirm-page.component';
import { CommentsPageComponent } from './pages/admin/comments-page/comments-page.component';
import { CommentReportsPageComponent } from './pages/admin/comments-page/reports/commentReports-page.component';
import { UserCommentsPageComponent } from './pages/admin/user-comments-page/user-comments-page.component';
import { UserPostsPageComponent } from './pages/admin/user-posts-page/user-posts-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { UserProfilePageComponent } from './pages/user-profile-page/user-profile-page.component';
import { UsersTableComponent } from './components/admin/users-table/users-table.component';
import {MatTableModule} from "@angular/material/table";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import { AddPostComponent } from './components/add-post/add-post.component';
import {MatDialogModule} from "@angular/material/dialog";
import { UploadImgComponent } from './components/upload-img/upload-img.component';
import { DragNDropDirective } from './directives/dragNDrop/drag-ndrop.directive';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CommentsDialogComponent } from './components/comments-dialog/comments-dialog.component';
import { EditCommentDialogComponent } from './components/edit-comment-dialog/edit-comment-dialog.component';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { ReportDialogComponent } from './components/report-dialog/report-dialog.component';
import { MessageComponent } from './components/message/message.component';
import { ChatComponent } from './components/chat/chat.component';
import {ChatPageComponent} from "./pages/chat/chat.component";
import { MessageDialogComponent } from './components/message-dialog/message-dialog.component';
import { EditMessageDialogComponent } from './components/edit-message-dialog/edit-message-dialog.component';
import { DeleteMessageDialogComponent } from './components/delete-message-dialog/delete-message-dialog.component';
import { ProfilePictureComponent } from './components/profile-picture/profile-picture.component';
import {MatStepperModule} from "@angular/material/stepper";
import {ReactiveFormsModule} from "@angular/forms";
import {MatDatepickerModule} from "@angular/material/datepicker";
import { SearchMessageDialogComponent } from './components/search-message-dialog/search-message-dialog.component';
import { FoundMessageDialogComponent } from './components/found-message-dialog/found-message-dialog.component';
import { UserCardComponent } from './components/user-card/user-card.component';
import { EditProfileDialogComponent } from './components/edit-profile-dialog/edit-profile-dialog.component';
import { BlockUserMessageDialogComponent } from './components/block-user-message-dialog/block-user-message-dialog.component';
import { BlockedUsersDialogComponent } from './components/blocked-users-dialog/blocked-users-dialog.component';

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
    ChatPageComponent,
    MessageDialogComponent,
    MessageComponent,
    RegisterConfirmPageComponent,
    CommentsPageComponent,
    CommentReportsPageComponent,
    UserCommentsPageComponent,
    UserPostsPageComponent,
    ProfilePageComponent,
    UserProfilePageComponent,
    UsersTableComponent,
    AddPostComponent,
    UploadImgComponent,
    DragNDropDirective,
    CommentsDialogComponent,
    EditCommentDialogComponent,
    DeleteDialogComponent,
    ReportDialogComponent,
    EditMessageDialogComponent,
    DeleteMessageDialogComponent,
    ProfilePictureComponent,
    SearchMessageDialogComponent,
    FoundMessageDialogComponent,
    UserCardComponent,
    EditProfileDialogComponent,
    BlockUserMessageDialogComponent,
    BlockedUsersDialogComponent
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
    MatDialogModule,
    MatSnackBarModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BackEndUrlInterceptor, multi: true },
    AdminGuardsGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
