import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditChefComponent } from './edit-chef.component';

describe('EditChefComponent', () => {
  let component: EditChefComponent;
  let fixture: ComponentFixture<EditChefComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditChefComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditChefComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
