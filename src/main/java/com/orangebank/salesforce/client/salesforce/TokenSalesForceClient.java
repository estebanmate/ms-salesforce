package com.orangebank.salesforce.client.salesforce;

import com.orangebank.salesforce.model.salesforce.TokenSalesForce;
import com.orangebank.salesforce.repository.redis.RedisKeyGenerator;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;

@Client("${token.url}")
@Retryable(delay = "200ms")
@CacheConfig("salesforcetoken")
public interface TokenSalesForceClient {

  @Post("/${token.endpoint}?"
      + "grant_type=${token.grant-type}"
      + "&client_id=${token.client-id}"
      + "&client_secret=${token.client-secret}"
      + "&username=${token.username}"
      + "&password=${token.password}")
  @Cacheable(keyGenerator = RedisKeyGenerator.class)
  public TokenSalesForce getToken();

}
