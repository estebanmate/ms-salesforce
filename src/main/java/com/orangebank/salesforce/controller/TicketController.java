package com.orangebank.salesforce.controller;

import com.orangebank.salesforce.api.TicketOperations;
import com.orangebank.salesforce.exception.TicketNotFoundException;
import com.orangebank.salesforce.model.Ticket;
import com.orangebank.salesforce.service.TicketService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Controller("/ticket")
public class TicketController implements TicketOperations {

  private final TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  /**
   * Get a ticket with id.
   *
   * @param id id of the ticket.
   * @return A ticket.
   */
  @Override
  public Single<Ticket> ticket(String id) {
    return ticketService.ticket(id);
  }

  /**
   * Get a ticket list by Client.
   *
   * @param clientNumber Number of the Client.
   * @return List Of Tickets.
   */
  @Override
  public Flowable<Ticket> tickets(String clientNumber) {
    return ticketService.tickets(clientNumber);
  }

  @Error
  public HttpResponse<String> error(HttpRequest<?> request, TicketNotFoundException e) {
    return HttpResponse.notFound();
  }

}
