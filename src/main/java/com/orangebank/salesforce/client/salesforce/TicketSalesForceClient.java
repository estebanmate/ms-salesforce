package com.orangebank.salesforce.client.salesforce;

import com.orangebank.salesforce.model.Ticket;
import com.orangebank.salesforce.model.salesforce.Metadata;
import com.orangebank.salesforce.repository.redis.RedisKeyGenerator;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;

@Client("${salesforce.api-url}/${salesforce.api-version}/${salesforce.ticket-endpoint}")
@Retryable(delay = "200ms")
@CacheConfig("salesforcemetadata")
public interface TicketSalesForceClient {
  
  @Get("/{id}")
  public Single<Ticket> ticket(String id);

  @Get("/${salesforce.metadata}")
  @Cacheable(keyGenerator = RedisKeyGenerator.class)
  public Metadata metadata();

}
