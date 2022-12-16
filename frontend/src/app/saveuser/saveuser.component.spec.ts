import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveuserComponent } from './saveuser.component';

describe('SaveuserComponent', () => {
  let component: SaveuserComponent;
  let fixture: ComponentFixture<SaveuserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveuserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaveuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
