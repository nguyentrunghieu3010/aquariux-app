import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPriceAggregate } from '../price-aggregate.model';
import { PriceAggregateService } from '../service/price-aggregate.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './price-aggregate-delete-dialog.component.html',
})
export class PriceAggregateDeleteDialogComponent {
  priceAggregate?: IPriceAggregate;

  constructor(protected priceAggregateService: PriceAggregateService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.priceAggregateService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
