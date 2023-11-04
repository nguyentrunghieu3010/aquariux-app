import { SourceType } from 'app/entities/enumerations/source-type.model';

import { IPriceAggregate, NewPriceAggregate } from './price-aggregate.model';

export const sampleWithRequiredData: IPriceAggregate = {
  id: 70692,
};

export const sampleWithPartialData: IPriceAggregate = {
  id: 77631,
  symbol: 'capability Handcrafted aggregate',
  askQty: 49244,
  bidSize: 36767,
  sourceType: SourceType['BINANCE'],
};

export const sampleWithFullData: IPriceAggregate = {
  id: 61450,
  symbolCrypto: 'Brand',
  symbol: 'blue',
  bidPrice: 59166,
  bidQty: 82782,
  askPrice: 24315,
  askQty: 72315,
  bid: 53258,
  bidSize: 15378,
  ask: 86186,
  askSize: 86947,
  sourceType: SourceType['BINANCE'],
};

export const sampleWithNewData: NewPriceAggregate = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
