import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EuAccountComponent } from './eu-account.component';

describe('EuAccountComponent', () => {
  let component: EuAccountComponent;
  let fixture: ComponentFixture<EuAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EuAccountComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EuAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
