import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPriceAggregate } from '../price-aggregate.model';
import { PriceAggregateService } from '../service/price-aggregate.service';

@Injectable({ providedIn: 'root' })
export class PriceAggregateRoutingResolveService implements Resolve<IPriceAggregate | null> {
  constructor(protected service: PriceAggregateService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPriceAggregate | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((priceAggregate: HttpResponse<IPriceAggregate>) => {
          if (priceAggregate.body) {
            return of(priceAggregate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
