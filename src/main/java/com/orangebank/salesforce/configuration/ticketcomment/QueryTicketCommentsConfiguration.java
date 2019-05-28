package com.orangebank.salesforce.configuration.ticketcomment;

import com.orangebank.salesforce.model.salesforce.composite.QueryComposite;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

@EachProperty("salesforce.composite-ticket-comments.queries")
public class QueryTicketCommentsConfiguration extends QueryComposite {
  private final String queryName;

  public QueryTicketCommentsConfiguration(@Parameter String queryName) {
    this.queryName = queryName;
  }

  public String getQueryName() {
    return this.queryName;
  }
}