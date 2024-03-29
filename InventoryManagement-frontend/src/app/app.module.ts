import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductComponent } from './components/product/product.component';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { AddUpdateProductComponent } from './components/add-update-product/add-update-product.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { AddUpdateUserComponent } from './components/add-update-user/add-update-user.component';
import { LoginComponent } from './components/login/login.component';
import { ProductCategoryListComponent } from './components/product-category-list/product-category-list.component';
import { UserService } from './service/UserService.service';
import { HttpClientModule } from '@angular/common/http';
import { ProductService } from './service/ProductService.service';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { SignupComponent } from './components/signup/signup.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProductReceivingStockComponent } from './components/product-receiving-stock/product-receiving-stock.component';
import { MerchantsListComponent } from './components/merchants-list/merchants-list.component';
import { AddUpdateMerchantsComponent } from './components/add-update-merchants/add-update-merchants.component';
import { MerchantService } from './service/MerchantService.service';
import { ProductCategoryService } from './service/ProductCategoryService.service';
import { AddUpdateProductReceivingStockComponent } from './components/add-update-product-receiving-stock/add-update-product-receiving-stock.component';
import { PaymentComponent } from './components/payment/payment.component';
import { AddPaymentComponent } from './components/add-payment/add-payment.component';
@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    AddUpdateProductComponent,
    NavigationComponent,
    UserListComponent,
    AddUpdateUserComponent,
    LoginComponent,
    ProductCategoryListComponent,
    SignupComponent,
    DashboardComponent,
    ProductReceivingStockComponent,
    MerchantsListComponent,
    AddUpdateMerchantsComponent,
    AddUpdateProductReceivingStockComponent,
    PaymentComponent,
    AddPaymentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatIconModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [UserService, ProductService , MerchantService , ProductCategoryService],
  bootstrap: [AppComponent],
})
export class AppModule {}
