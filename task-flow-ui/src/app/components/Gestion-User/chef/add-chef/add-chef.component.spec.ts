import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddChefComponent } from './add-chef.component';

describe('AddChefComponent', () => {
  let component: AddChefComponent;
  let fixture: ComponentFixture<AddChefComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddChefComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddChefComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
