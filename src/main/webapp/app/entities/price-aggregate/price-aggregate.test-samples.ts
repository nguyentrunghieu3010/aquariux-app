import { SourceType } from 'app/entities/enumerations/source-type.model';

import { IPriceAggregate, NewPriceAggregate } from './price-aggregate.model';

export const sampleWithRequiredData: IPriceAggregate = {
  id: 70692,
};

export const sampleWithPartialData: IPriceAggregate = {
  id: 3647,
  bidPrice: 62222,
  sourceType: SourceType['BINANCE'],
};

export const sampleWithFullData: IPriceAggregate = {
  id: 36693,
  symbolCrypto: 'input Gloves',
  bidPrice: 66900,
  bidQty: 12542,
  askPrice: 38191,
  askQty: 20440,
  sourceType: SourceType['HUOBI'],
};

export const sampleWithNewData: NewPriceAggregate = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
