import { SourceType } from 'app/entities/enumerations/source-type.model';

export interface IPriceAggregate {
  id: number;
  symbolCrypto?: string | null;
  bidPrice?: number | null;
  bidQty?: number | null;
  askPrice?: number | null;
  askQty?: number | null;
  sourceType?: SourceType | null;
}

export type NewPriceAggregate = Omit<IPriceAggregate, 'id'> & { id: null };
