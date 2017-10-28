import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Street.Component.TsComponent } from './street.component.ts.component';

describe('Street.Component.TsComponent', () => {
  let component: Street.Component.TsComponent;
  let fixture: ComponentFixture<Street.Component.TsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Street.Component.TsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Street.Component.TsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
