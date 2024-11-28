import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeupdateComponent } from './incomeupdate.component';

describe('IncomeupdateComponent', () => {
  let component: IncomeupdateComponent;
  let fixture: ComponentFixture<IncomeupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IncomeupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
