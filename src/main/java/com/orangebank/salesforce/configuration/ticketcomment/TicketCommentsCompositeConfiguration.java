package com.orangebank.salesforce.configuration.ticketcomment;

import java.util.Arrays;

import javax.inject.Singleton;

import com.orangebank.salesforce.model.salesforce.composite.CompositeRequest;

/**
 * TicketCommentsCompositeConfiguration.
 */
@Singleton
public class TicketCommentsCompositeConfiguration extends CompositeRequest {

  public TicketCommentsCompositeConfiguration(QueryTicketCommentsBean... queries) {
    this.compositeRequest = Arrays.asList(queries);
  }
  
}