import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUpdateProductComponent } from './components/add-update-product/add-update-product.component';
import { AddUpdateUserComponent } from './components/add-update-user/add-update-user.component';
import { LoginComponent } from './components/login/login.component';
import { ProductCategoryListComponent } from './components/product-category-list/product-category-list.component';
import { ProductComponent } from './components/product/product.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserListComponent } from './components/user-list/user-list.component';

const routes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'user-list', component: UserListComponent },
  { path: 'add-update-user', component: AddUpdateUserComponent },
  { path: 'add-update-user/:id', component: AddUpdateUserComponent },
  { path: 'product-list', component: ProductComponent },
  { path: 'product-list/:id', component: ProductComponent },
  { path: 'add-update-product', component: AddUpdateProductComponent },
  { path: 'product-category-list', component: ProductCategoryListComponent },
  { path: 'login', component: LoginComponent },
  {path:'signup' , component: SignupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
