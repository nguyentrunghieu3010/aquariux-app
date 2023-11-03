import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'price-aggregate',
        data: { pageTitle: 'PriceAggregates' },
        loadChildren: () => import('./price-aggregate/price-aggregate.module').then(m => m.PriceAggregateModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
