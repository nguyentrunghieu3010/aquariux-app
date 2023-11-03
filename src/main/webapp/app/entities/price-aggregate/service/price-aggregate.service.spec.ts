import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPriceAggregate } from '../price-aggregate.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../price-aggregate.test-samples';

import { PriceAggregateService } from './price-aggregate.service';

const requireRestSample: IPriceAggregate = {
  ...sampleWithRequiredData,
};

describe('PriceAggregate Service', () => {
  let service: PriceAggregateService;
  let httpMock: HttpTestingController;
  let expectedResult: IPriceAggregate | IPriceAggregate[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PriceAggregateService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a PriceAggregate', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const priceAggregate = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(priceAggregate).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a PriceAggregate', () => {
      const priceAggregate = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(priceAggregate).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a PriceAggregate', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of PriceAggregate', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a PriceAggregate', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addPriceAggregateToCollectionIfMissing', () => {
      it('should add a PriceAggregate to an empty array', () => {
        const priceAggregate: IPriceAggregate = sampleWithRequiredData;
        expectedResult = service.addPriceAggregateToCollectionIfMissing([], priceAggregate);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(priceAggregate);
      });

      it('should not add a PriceAggregate to an array that contains it', () => {
        const priceAggregate: IPriceAggregate = sampleWithRequiredData;
        const priceAggregateCollection: IPriceAggregate[] = [
          {
            ...priceAggregate,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPriceAggregateToCollectionIfMissing(priceAggregateCollection, priceAggregate);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a PriceAggregate to an array that doesn't contain it", () => {
        const priceAggregate: IPriceAggregate = sampleWithRequiredData;
        const priceAggregateCollection: IPriceAggregate[] = [sampleWithPartialData];
        expectedResult = service.addPriceAggregateToCollectionIfMissing(priceAggregateCollection, priceAggregate);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(priceAggregate);
      });

      it('should add only unique PriceAggregate to an array', () => {
        const priceAggregateArray: IPriceAggregate[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const priceAggregateCollection: IPriceAggregate[] = [sampleWithRequiredData];
        expectedResult = service.addPriceAggregateToCollectionIfMissing(priceAggregateCollection, ...priceAggregateArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const priceAggregate: IPriceAggregate = sampleWithRequiredData;
        const priceAggregate2: IPriceAggregate = sampleWithPartialData;
        expectedResult = service.addPriceAggregateToCollectionIfMissing([], priceAggregate, priceAggregate2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(priceAggregate);
        expect(expectedResult).toContain(priceAggregate2);
      });

      it('should accept null and undefined values', () => {
        const priceAggregate: IPriceAggregate = sampleWithRequiredData;
        expectedResult = service.addPriceAggregateToCollectionIfMissing([], null, priceAggregate, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(priceAggregate);
      });

      it('should return initial array if no PriceAggregate is added', () => {
        const priceAggregateCollection: IPriceAggregate[] = [sampleWithRequiredData];
        expectedResult = service.addPriceAggregateToCollectionIfMissing(priceAggregateCollection, undefined, null);
        expect(expectedResult).toEqual(priceAggregateCollection);
      });
    });

    describe('comparePriceAggregate', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePriceAggregate(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.comparePriceAggregate(entity1, entity2);
        const compareResult2 = service.comparePriceAggregate(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.comparePriceAggregate(entity1, entity2);
        const compareResult2 = service.comparePriceAggregate(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.comparePriceAggregate(entity1, entity2);
        const compareResult2 = service.comparePriceAggregate(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
