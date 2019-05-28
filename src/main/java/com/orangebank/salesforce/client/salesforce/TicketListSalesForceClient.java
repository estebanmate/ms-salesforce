package com.orangebank.salesforce.client.salesforce;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;

import javax.validation.constraints.NotBlank;

import com.orangebank.salesforce.model.Ticket;
import com.orangebank.salesforce.model.salesforce.SalesForceQueryResponse;

@Client("${salesforce.api-url}/${salesforce.api-version}")
@Retryable(delay = "200ms")
public interface TicketListSalesForceClient {

  @Get("/${salesforce.query-endpoint}/")
  public SalesForceQueryResponse<Ticket> tickets(@NotBlank @QueryValue String q);

}
