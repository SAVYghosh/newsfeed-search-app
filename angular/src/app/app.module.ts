import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewsApiComponent } from './Modules/news-api/news-api.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NewsApiService } from './Services/news-api.service';
import { SignupComponent } from './Modules/signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './Modules/login/login.component';
import { LoginService } from './Services/login.service';
import { SignupService } from './Services/signup.service';
import { UserNavComponent } from './user-nav/user-nav.component';
import { AdminNavComponent } from './admin-nav/admin-nav.component';
import { GetUserComponent } from './Modules/get-user/get-user.component';
import { GetUserService } from './Services/get-user.service';
import { HttpConfigInterceptor } from './HttpConfigInterceptor';
import { SearchHistoryComponent } from './Modules/search-history/search-history.component';

// export function tokenGetter(){
//   return localStorage.getItem('Token')
// }
@NgModule({
  declarations: [
    AppComponent,
    NewsApiComponent,
    SignupComponent,
    LoginComponent,
    UserNavComponent,
    AdminNavComponent,
    GetUserComponent,
    SearchHistoryComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
   
  ],
  providers: [NewsApiService, LoginService, SignupService,GetUserService,
    {provide:HTTP_INTERCEPTORS,useClass:HttpConfigInterceptor,multi:true}],

  bootstrap: [AppComponent]
})
export class AppModule { }
