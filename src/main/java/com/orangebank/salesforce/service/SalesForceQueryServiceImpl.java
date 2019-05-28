package com.orangebank.salesforce.service;

import io.micronaut.context.annotation.Value;
import io.reactivex.Flowable;

import javax.inject.Singleton;

import com.orangebank.salesforce.client.salesforce.TicketListSalesForceClient;
import com.orangebank.salesforce.model.Ticket;

@Singleton
public class SalesForceQueryServiceImpl implements SalesForceQueryService {

  protected final TicketListSalesForceClient ticketListSalesForceClient;

  @Value("${salesforce.tickets-querybyclient}")
  private String ticketsQueryByClient;

  /**
   * Constructor.
   * @param ticketListSalesForceClient Injectec ticketListSalesForceClient
   */
  public SalesForceQueryServiceImpl(TicketListSalesForceClient ticketListSalesForceClient) {
    this.ticketListSalesForceClient = ticketListSalesForceClient;
  }

  @Override
  public Flowable<Ticket> tickets(String clientNumber) {
    return Flowable.fromIterable(
        ticketListSalesForceClient.tickets(
          String.format(ticketsQueryByClient, clientNumber)
        ).getRecords()
    );
  }

}