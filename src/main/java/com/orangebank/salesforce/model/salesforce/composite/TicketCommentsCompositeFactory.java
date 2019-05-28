package com.orangebank.salesforce.model.salesforce.composite;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.orangebank.salesforce.configuration.ticketcomment.TicketCommentsCompositeConfiguration;

@Factory
public class TicketCommentsCompositeFactory {
  
  private TicketCommentsCompositeConfiguration compositeTicketCommentsConfiguration;

  public TicketCommentsCompositeFactory(
      TicketCommentsCompositeConfiguration compositeTicketCommentsConfiguration) {
    this.compositeTicketCommentsConfiguration = compositeTicketCommentsConfiguration;
  }

  @PostConstruct
  private void orderQueries() {
    this.compositeTicketCommentsConfiguration.getCompositeRequest().sort(
        (q1, q2) -> q1.getOrder().compareTo(q2.getOrder())
    );
  }

  /** 
   * Get an object to obatin the commens of a ticket through a composite request.
   * @param id id of the {@link com.orangebank.salesforce.model.Ticket}
   * @return {@link CompositeRequest} composite request
  */
  @Bean
  @Singleton
  public CompositeRequest getCompositeByTicket(String id) {
    CompositeRequest compositeRequest = 
        new CompositeRequest();

    List<QueryComposite> list = new ArrayList<>(
        compositeTicketCommentsConfiguration.getCompositeRequest()
    );

    list.set(
        0, new QueryComposite(compositeTicketCommentsConfiguration.getCompositeRequest().get(0))
    );

    list.get(0).setUrl(
        String.format(
          list.get(0).getUrl(),
          id
        )
    );

    compositeRequest.setCompositeRequest(list);

    return compositeRequest;
  }

}