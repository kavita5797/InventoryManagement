import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUpdateProductReceivingStockComponent } from './add-update-product-receiving-stock.component';

describe('AddUpdateProductReceivingStockComponent', () => {
  let component: AddUpdateProductReceivingStockComponent;
  let fixture: ComponentFixture<AddUpdateProductReceivingStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUpdateProductReceivingStockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddUpdateProductReceivingStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
