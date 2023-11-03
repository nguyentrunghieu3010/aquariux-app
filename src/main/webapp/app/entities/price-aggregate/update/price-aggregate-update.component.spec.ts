import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PriceAggregateFormService } from './price-aggregate-form.service';
import { PriceAggregateService } from '../service/price-aggregate.service';
import { IPriceAggregate } from '../price-aggregate.model';

import { PriceAggregateUpdateComponent } from './price-aggregate-update.component';

describe('PriceAggregate Management Update Component', () => {
  let comp: PriceAggregateUpdateComponent;
  let fixture: ComponentFixture<PriceAggregateUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let priceAggregateFormService: PriceAggregateFormService;
  let priceAggregateService: PriceAggregateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PriceAggregateUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(PriceAggregateUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PriceAggregateUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    priceAggregateFormService = TestBed.inject(PriceAggregateFormService);
    priceAggregateService = TestBed.inject(PriceAggregateService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const priceAggregate: IPriceAggregate = { id: 456 };

      activatedRoute.data = of({ priceAggregate });
      comp.ngOnInit();

      expect(comp.priceAggregate).toEqual(priceAggregate);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPriceAggregate>>();
      const priceAggregate = { id: 123 };
      jest.spyOn(priceAggregateFormService, 'getPriceAggregate').mockReturnValue(priceAggregate);
      jest.spyOn(priceAggregateService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ priceAggregate });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: priceAggregate }));
      saveSubject.complete();

      // THEN
      expect(priceAggregateFormService.getPriceAggregate).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(priceAggregateService.update).toHaveBeenCalledWith(expect.objectContaining(priceAggregate));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPriceAggregate>>();
      const priceAggregate = { id: 123 };
      jest.spyOn(priceAggregateFormService, 'getPriceAggregate').mockReturnValue({ id: null });
      jest.spyOn(priceAggregateService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ priceAggregate: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: priceAggregate }));
      saveSubject.complete();

      // THEN
      expect(priceAggregateFormService.getPriceAggregate).toHaveBeenCalled();
      expect(priceAggregateService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPriceAggregate>>();
      const priceAggregate = { id: 123 };
      jest.spyOn(priceAggregateService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ priceAggregate });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(priceAggregateService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
