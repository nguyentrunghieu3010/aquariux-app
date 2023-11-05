import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PriceAggregateComponent } from '../list/price-aggregate.component';
import { PriceAggregateDetailComponent } from '../detail/price-aggregate-detail.component';
import { PriceAggregateUpdateComponent } from '../update/price-aggregate-update.component';
import { PriceAggregateRoutingResolveService } from './price-aggregate-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const priceAggregateRoute: Routes = [
  {
    path: '',
    component: PriceAggregateComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PriceAggregateDetailComponent,
    resolve: {
      priceAggregate: PriceAggregateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PriceAggregateUpdateComponent,
    resolve: {
      priceAggregate: PriceAggregateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PriceAggregateUpdateComponent,
    resolve: {
      priceAggregate: PriceAggregateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(priceAggregateRoute)],
  exports: [RouterModule],
})
export class PriceAggregateRoutingModule {}
