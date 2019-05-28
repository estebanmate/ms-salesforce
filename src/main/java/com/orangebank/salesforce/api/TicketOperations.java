package com.orangebank.salesforce.api;

import com.orangebank.salesforce.model.Ticket;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface TicketOperations {

  @Get("/{id}")
  public Single<Ticket> ticket(String id);

  @Get("/")
  public Flowable<Ticket> tickets(@Header("X-Customer-ID") String clientNumber);

}
