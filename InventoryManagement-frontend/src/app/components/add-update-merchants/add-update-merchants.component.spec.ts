import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUpdateMerchantsComponent } from './add-update-merchants.component';

describe('AddUpdateMerchantsComponent', () => {
  let component: AddUpdateMerchantsComponent;
  let fixture: ComponentFixture<AddUpdateMerchantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUpdateMerchantsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddUpdateMerchantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
