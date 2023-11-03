import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PriceAggregateDetailComponent } from './price-aggregate-detail.component';

describe('PriceAggregate Management Detail Component', () => {
  let comp: PriceAggregateDetailComponent;
  let fixture: ComponentFixture<PriceAggregateDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PriceAggregateDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ priceAggregate: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(PriceAggregateDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(PriceAggregateDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load priceAggregate on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.priceAggregate).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
