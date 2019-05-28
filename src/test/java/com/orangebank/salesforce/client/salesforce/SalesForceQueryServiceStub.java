package com.orangebank.salesforce.client.salesforce;

import com.orangebank.salesforce.model.Ticket;
import com.orangebank.salesforce.model.salesforce.SalesForceQueryResponse;
import com.orangebank.salesforce.service.JsonFileReader;
import com.orangebank.salesforce.service.SalesForceQueryService;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Flowable;

@Primary
public class SalesForceQueryServiceStub implements SalesForceQueryService {

  private SalesForceQueryResponse<Ticket> ticketListResponse;

  /**
   * Constructor.
   */
  public SalesForceQueryServiceStub() {

    ticketListResponse = new SalesForceQueryResponse<>();
    ticketListResponse.setRecords(JsonFileReader.<Ticket>stringToDataList(
        Ticket.class,
        "src/test/resources/tickets.json"
    ));
  }

  @Override
  public Flowable<Ticket> tickets(String clientNumber) {
    return Flowable.fromIterable(ticketListResponse.getRecords());
  }

}
