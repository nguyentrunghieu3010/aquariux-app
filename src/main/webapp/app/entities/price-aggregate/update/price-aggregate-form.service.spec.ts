import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../price-aggregate.test-samples';

import { PriceAggregateFormService } from './price-aggregate-form.service';

describe('PriceAggregate Form Service', () => {
  let service: PriceAggregateFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriceAggregateFormService);
  });

  describe('Service methods', () => {
    describe('createPriceAggregateFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPriceAggregateFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            symbolCrypto: expect.any(Object),
            symbol: expect.any(Object),
            bidPrice: expect.any(Object),
            bidQty: expect.any(Object),
            askPrice: expect.any(Object),
            askQty: expect.any(Object),
            bid: expect.any(Object),
            bidSize: expect.any(Object),
            ask: expect.any(Object),
            askSize: expect.any(Object),
            sourceType: expect.any(Object),
          })
        );
      });

      it('passing IPriceAggregate should create a new form with FormGroup', () => {
        const formGroup = service.createPriceAggregateFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            symbolCrypto: expect.any(Object),
            symbol: expect.any(Object),
            bidPrice: expect.any(Object),
            bidQty: expect.any(Object),
            askPrice: expect.any(Object),
            askQty: expect.any(Object),
            bid: expect.any(Object),
            bidSize: expect.any(Object),
            ask: expect.any(Object),
            askSize: expect.any(Object),
            sourceType: expect.any(Object),
          })
        );
      });
    });

    describe('getPriceAggregate', () => {
      it('should return NewPriceAggregate for default PriceAggregate initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPriceAggregateFormGroup(sampleWithNewData);

        const priceAggregate = service.getPriceAggregate(formGroup) as any;

        expect(priceAggregate).toMatchObject(sampleWithNewData);
      });

      it('should return NewPriceAggregate for empty PriceAggregate initial value', () => {
        const formGroup = service.createPriceAggregateFormGroup();

        const priceAggregate = service.getPriceAggregate(formGroup) as any;

        expect(priceAggregate).toMatchObject({});
      });

      it('should return IPriceAggregate', () => {
        const formGroup = service.createPriceAggregateFormGroup(sampleWithRequiredData);

        const priceAggregate = service.getPriceAggregate(formGroup) as any;

        expect(priceAggregate).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPriceAggregate should not enable id FormControl', () => {
        const formGroup = service.createPriceAggregateFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPriceAggregate should disable id FormControl', () => {
        const formGroup = service.createPriceAggregateFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
