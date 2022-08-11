import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductReceivingStockComponent } from './product-receiving-stock.component';

describe('ProductReceivingStockComponent', () => {
  let component: ProductReceivingStockComponent;
  let fixture: ComponentFixture<ProductReceivingStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductReceivingStockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductReceivingStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
