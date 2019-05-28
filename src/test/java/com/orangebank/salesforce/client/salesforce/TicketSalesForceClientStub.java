package com.orangebank.salesforce.client.salesforce;

import com.orangebank.salesforce.client.salesforce.TicketSalesForceClient;
import com.orangebank.salesforce.model.Ticket;
import com.orangebank.salesforce.model.salesforce.Metadata;
import com.orangebank.salesforce.service.JsonFileReader;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Single;

@Primary
public class TicketSalesForceClientStub implements TicketSalesForceClient {

  /**
   * Get a ticket.
   * @param id id of the ticket.
   * @return ticket.
   */
  public Single<Ticket> ticket(String id) {
    Ticket ticket = new Ticket();
    ticket.setId(id);
    return Single.just(ticket);
  }

  /**
   * Get ticket metadata.
   * @return metadata.
   */
  public Metadata metadata() {
    return JsonFileReader.<Metadata>stringToData(
        Metadata.class,
        "src/test/resources/metadata.json"
    );
  }

}
