package com.orangebank.salesforce.service;

import io.micronaut.http.HttpStatus;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;

import com.orangebank.salesforce.client.salesforce.CompositeSalesForceClient;
import com.orangebank.salesforce.client.salesforce.TicketSalesForceClient;
import com.orangebank.salesforce.exception.TicketNotFoundException;
import com.orangebank.salesforce.model.Ticket;
import com.orangebank.salesforce.model.salesforce.Comment;
import com.orangebank.salesforce.model.salesforce.Option;
import com.orangebank.salesforce.model.salesforce.SalesForceQueryResponse;
import com.orangebank.salesforce.model.salesforce.composite.TicketCommentsCompositeFactory;
import com.orangebank.salesforce.util.ObjectValueConversor;

@Singleton
public class TicketService {

  protected final TicketSalesForceClient ticketSalesForceClient;
  protected final SalesForceQueryService salesForceQueryService;
  protected final CompositeSalesForceClient compositeSalesForceClient;
  protected final TicketCommentsCompositeFactory ticketCommentsCompositeFactory;
  
  /**
   * Constructor.
   * @param ticketSalesForceClient Injected ticketSalesForceClient
   * @param salesForceQueryService Injected salesForceQueryService
   * @param compositeSalesForceClient Injected compositeSalesForceClient
   * @param ticketCommentsCompositeFactory Injected ticketCommentsCompositeFactory
   */
  public TicketService(TicketSalesForceClient ticketSalesForceClient,
      SalesForceQueryService salesForceQueryService,
      CompositeSalesForceClient compositeSalesForceClient,
      TicketCommentsCompositeFactory ticketCommentsCompositeFactory) {
    this.ticketSalesForceClient = ticketSalesForceClient;
    this.salesForceQueryService = salesForceQueryService;
    this.compositeSalesForceClient = compositeSalesForceClient;
    this.ticketCommentsCompositeFactory = ticketCommentsCompositeFactory;
  }
  
  /**
   * Get all the Ticket of the Client with the clientNumber.
   * @param clientNumber of the Client.
   * @return a List of Tickets.
   */
  public Flowable<Ticket> tickets(String clientNumber) {
    return salesForceQueryService.tickets(clientNumber)
        .map(ticket -> {
          ticket.setStatusOption(getStatusObject(ticket.getStatus()));
          return ticket;
        });
  }

  /**
   * Get a {@link Ticket} and the {@link Comment} fo the ticket.
   * @param id id of the {@link Ticket}
   * @return the ticket
   */
  public Single<Ticket> ticket(String id) {
    return 
        compositeSalesForceClient.composite(
          ticketCommentsCompositeFactory.getCompositeByTicket(id)
        )
        .map(res -> {
          if (!res.getCompositeResponse().get(0).getHttpStatusCode().equals(
              HttpStatus.OK.getCode())) {
            throw new TicketNotFoundException();
          }
          Ticket ticket = ObjectValueConversor.convertValueToObject(
              res.getCompositeResponse().get(0).getBody(),
              Ticket.class
          );

          ticket.setStatusOption(getStatusObject(ticket.getStatus()));

          if (ObjectValueConversor.convertValueToObject(
                res.getCompositeResponse().get(1).getBody(),
                SalesForceQueryResponse.class
              ).getTotalSize() > 0) {
            ticket.setComments(
                ObjectValueConversor.convertValueToList(
                  ObjectValueConversor.convertValueToObject(
                    res.getCompositeResponse().get(2).getBody(),
                    SalesForceQueryResponse.class
                  ).getRecords(),
                  Comment.class
                )
            );
          }
          return ticket;
        });
  }

  private Option getStatusObject(String statusLabel) {
    return ticketSalesForceClient.metadata().getFields().stream()
      .filter(field -> "Status".equalsIgnoreCase(field.getName()))
      .findFirst()
      .get()
      .getOptions().stream()
      .filter(option -> statusLabel.equals(option.getLabel()))
      .findFirst()
      .orElse(null);
  }

}