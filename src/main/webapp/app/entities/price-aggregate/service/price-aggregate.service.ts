import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPriceAggregate, NewPriceAggregate } from '../price-aggregate.model';

export type PartialUpdatePriceAggregate = Partial<IPriceAggregate> & Pick<IPriceAggregate, 'id'>;

export type EntityResponseType = HttpResponse<IPriceAggregate>;
export type EntityArrayResponseType = HttpResponse<IPriceAggregate[]>;

@Injectable({ providedIn: 'root' })
export class PriceAggregateService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/price-aggregates');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(priceAggregate: NewPriceAggregate): Observable<EntityResponseType> {
    return this.http.post<IPriceAggregate>(this.resourceUrl, priceAggregate, { observe: 'response' });
  }

  update(priceAggregate: IPriceAggregate): Observable<EntityResponseType> {
    return this.http.put<IPriceAggregate>(`${this.resourceUrl}/${this.getPriceAggregateIdentifier(priceAggregate)}`, priceAggregate, {
      observe: 'response',
    });
  }

  partialUpdate(priceAggregate: PartialUpdatePriceAggregate): Observable<EntityResponseType> {
    return this.http.patch<IPriceAggregate>(`${this.resourceUrl}/${this.getPriceAggregateIdentifier(priceAggregate)}`, priceAggregate, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPriceAggregate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPriceAggregate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPriceAggregateIdentifier(priceAggregate: Pick<IPriceAggregate, 'id'>): number {
    return priceAggregate.id;
  }

  comparePriceAggregate(o1: Pick<IPriceAggregate, 'id'> | null, o2: Pick<IPriceAggregate, 'id'> | null): boolean {
    return o1 && o2 ? this.getPriceAggregateIdentifier(o1) === this.getPriceAggregateIdentifier(o2) : o1 === o2;
  }

  addPriceAggregateToCollectionIfMissing<Type extends Pick<IPriceAggregate, 'id'>>(
    priceAggregateCollection: Type[],
    ...priceAggregatesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const priceAggregates: Type[] = priceAggregatesToCheck.filter(isPresent);
    if (priceAggregates.length > 0) {
      const priceAggregateCollectionIdentifiers = priceAggregateCollection.map(
        priceAggregateItem => this.getPriceAggregateIdentifier(priceAggregateItem)!
      );
      const priceAggregatesToAdd = priceAggregates.filter(priceAggregateItem => {
        const priceAggregateIdentifier = this.getPriceAggregateIdentifier(priceAggregateItem);
        if (priceAggregateCollectionIdentifiers.includes(priceAggregateIdentifier)) {
          return false;
        }
        priceAggregateCollectionIdentifiers.push(priceAggregateIdentifier);
        return true;
      });
      return [...priceAggregatesToAdd, ...priceAggregateCollection];
    }
    return priceAggregateCollection;
  }
}
