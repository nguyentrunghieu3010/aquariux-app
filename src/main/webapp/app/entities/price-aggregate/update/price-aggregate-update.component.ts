import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PriceAggregateFormService, PriceAggregateFormGroup } from './price-aggregate-form.service';
import { IPriceAggregate } from '../price-aggregate.model';
import { PriceAggregateService } from '../service/price-aggregate.service';
import { SourceType } from 'app/entities/enumerations/source-type.model';

@Component({
  selector: 'jhi-price-aggregate-update',
  templateUrl: './price-aggregate-update.component.html',
})
export class PriceAggregateUpdateComponent implements OnInit {
  isSaving = false;
  priceAggregate: IPriceAggregate | null = null;
  sourceTypeValues = Object.keys(SourceType);

  editForm: PriceAggregateFormGroup = this.priceAggregateFormService.createPriceAggregateFormGroup();

  constructor(
    protected priceAggregateService: PriceAggregateService,
    protected priceAggregateFormService: PriceAggregateFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ priceAggregate }) => {
      this.priceAggregate = priceAggregate;
      if (priceAggregate) {
        this.updateForm(priceAggregate);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const priceAggregate = this.priceAggregateFormService.getPriceAggregate(this.editForm);
    if (priceAggregate.id !== null) {
      this.subscribeToSaveResponse(this.priceAggregateService.update(priceAggregate));
    } else {
      this.subscribeToSaveResponse(this.priceAggregateService.create(priceAggregate));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPriceAggregate>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(priceAggregate: IPriceAggregate): void {
    this.priceAggregate = priceAggregate;
    this.priceAggregateFormService.resetForm(this.editForm, priceAggregate);
  }
}
