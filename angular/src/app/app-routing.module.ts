import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewsApiComponent } from './Modules/news-api/news-api.component';
import { SignupComponent } from './Modules/signup/signup.component';
import { LoginComponent } from './Modules/login/login.component';
import { AuthGuardGuard } from './auth-guard.guard';
import { GetUserComponent } from './Modules/get-user/get-user.component';
import { SearchHistoryComponent } from './Modules/search-history/search-history.component';
import { UserNavComponent } from './user-nav/user-nav.component';

const routes: Routes = [
{
  path:'Signup',
  component:SignupComponent
},
{
  path:'Login',
  component: LoginComponent
},
{
  path:'GetUser',
  component: GetUserComponent,
  canActivate : [AuthGuardGuard]
},

{
  path: 'Usernav',
  component:UserNavComponent,
  canActivate:[AuthGuardGuard],
     children:[
     {
       path:'SearchHistory',
      component: SearchHistoryComponent
      },
     {
       path:'News',
       component: NewsApiComponent,
      }
]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
