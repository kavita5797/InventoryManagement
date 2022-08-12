import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUpdateMerchantsComponent } from './components/add-update-merchants/add-update-merchants.component';
import { AddUpdateProductReceivingStockComponent } from './components/add-update-product-receiving-stock/add-update-product-receiving-stock.component';
import { AddUpdateProductComponent } from './components/add-update-product/add-update-product.component';
import { AddUpdateUserComponent } from './components/add-update-user/add-update-user.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { MerchantsListComponent } from './components/merchants-list/merchants-list.component';
import { ProductCategoryListComponent } from './components/product-category-list/product-category-list.component';
import { ProductReceivingStockComponent } from './components/product-receiving-stock/product-receiving-stock.component';
import { ProductComponent } from './components/product/product.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserListComponent } from './components/user-list/user-list.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'user-list', component: UserListComponent },
  { path: 'merchant-list', component: MerchantsListComponent },
  { path: 'add-update-merchant', component: AddUpdateMerchantsComponent },
  { path: 'add-update-merchant/:id', component: AddUpdateMerchantsComponent },
  { path: 'add-update-user', component: AddUpdateUserComponent },
  { path: 'add-update-user/:id', component: AddUpdateUserComponent },
  { path: 'product-list', component: ProductComponent },
  { path: 'product-list/:id', component: ProductComponent },
  { path: 'add-update-product', component: AddUpdateProductComponent },
  { path: 'add-update-product/:id', component: AddUpdateProductComponent },
  { path: 'product-category-list', component: ProductCategoryListComponent },
  { path: 'add-update-product-receiving-stock', component: AddUpdateProductReceivingStockComponent },
  { path: 'product-stock', component: ProductReceivingStockComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
