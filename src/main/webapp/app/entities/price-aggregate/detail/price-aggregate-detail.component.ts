import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPriceAggregate } from '../price-aggregate.model';

@Component({
  selector: 'jhi-price-aggregate-detail',
  templateUrl: './price-aggregate-detail.component.html',
})
export class PriceAggregateDetailComponent implements OnInit {
  priceAggregate: IPriceAggregate | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ priceAggregate }) => {
      this.priceAggregate = priceAggregate;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
