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
    UsersComponent
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
    MatMenuModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BackEndUrlInterceptor, multi: true },
    AdminGuardsGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
