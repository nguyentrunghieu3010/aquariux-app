import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPriceAggregate, NewPriceAggregate } from '../price-aggregate.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPriceAggregate for edit and NewPriceAggregateFormGroupInput for create.
 */
type PriceAggregateFormGroupInput = IPriceAggregate | PartialWithRequiredKeyOf<NewPriceAggregate>;

type PriceAggregateFormDefaults = Pick<NewPriceAggregate, 'id'>;

type PriceAggregateFormGroupContent = {
  id: FormControl<IPriceAggregate['id'] | NewPriceAggregate['id']>;
  symbolCrypto: FormControl<IPriceAggregate['symbolCrypto']>;
  symbol: FormControl<IPriceAggregate['symbol']>;
  bidPrice: FormControl<IPriceAggregate['bidPrice']>;
  bidQty: FormControl<IPriceAggregate['bidQty']>;
  askPrice: FormControl<IPriceAggregate['askPrice']>;
  askQty: FormControl<IPriceAggregate['askQty']>;
  bid: FormControl<IPriceAggregate['bid']>;
  bidSize: FormControl<IPriceAggregate['bidSize']>;
  ask: FormControl<IPriceAggregate['ask']>;
  askSize: FormControl<IPriceAggregate['askSize']>;
  sourceType: FormControl<IPriceAggregate['sourceType']>;
};

export type PriceAggregateFormGroup = FormGroup<PriceAggregateFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PriceAggregateFormService {
  createPriceAggregateFormGroup(priceAggregate: PriceAggregateFormGroupInput = { id: null }): PriceAggregateFormGroup {
    const priceAggregateRawValue = {
      ...this.getFormDefaults(),
      ...priceAggregate,
    };
    return new FormGroup<PriceAggregateFormGroupContent>({
      id: new FormControl(
        { value: priceAggregateRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      symbolCrypto: new FormControl(priceAggregateRawValue.symbolCrypto),
      symbol: new FormControl(priceAggregateRawValue.symbol),
      bidPrice: new FormControl(priceAggregateRawValue.bidPrice),
      bidQty: new FormControl(priceAggregateRawValue.bidQty),
      askPrice: new FormControl(priceAggregateRawValue.askPrice),
      askQty: new FormControl(priceAggregateRawValue.askQty),
      bid: new FormControl(priceAggregateRawValue.bid),
      bidSize: new FormControl(priceAggregateRawValue.bidSize),
      ask: new FormControl(priceAggregateRawValue.ask),
      askSize: new FormControl(priceAggregateRawValue.askSize),
      sourceType: new FormControl(priceAggregateRawValue.sourceType),
    });
  }

  getPriceAggregate(form: PriceAggregateFormGroup): IPriceAggregate | NewPriceAggregate {
    return form.getRawValue() as IPriceAggregate | NewPriceAggregate;
  }

  resetForm(form: PriceAggregateFormGroup, priceAggregate: PriceAggregateFormGroupInput): void {
    const priceAggregateRawValue = { ...this.getFormDefaults(), ...priceAggregate };
    form.reset(
      {
        ...priceAggregateRawValue,
        id: { value: priceAggregateRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PriceAggregateFormDefaults {
    return {
      id: null,
    };
  }
}
