import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PriceAggregateComponent } from './list/price-aggregate.component';
import { PriceAggregateDetailComponent } from './detail/price-aggregate-detail.component';
import { PriceAggregateUpdateComponent } from './update/price-aggregate-update.component';
import { PriceAggregateDeleteDialogComponent } from './delete/price-aggregate-delete-dialog.component';
import { PriceAggregateRoutingModule } from './route/price-aggregate-routing.module';

@NgModule({
  imports: [SharedModule, PriceAggregateRoutingModule],
  declarations: [
    PriceAggregateComponent,
    PriceAggregateDetailComponent,
    PriceAggregateUpdateComponent,
    PriceAggregateDeleteDialogComponent,
  ],
})
export class PriceAggregateModule {}
