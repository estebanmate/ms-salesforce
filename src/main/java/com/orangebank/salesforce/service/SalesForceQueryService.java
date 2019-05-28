package com.orangebank.salesforce.service;

import com.orangebank.salesforce.model.Ticket;

import io.reactivex.Flowable;

public interface SalesForceQueryService {

  public Flowable<Ticket> tickets(String clientNumber);

}