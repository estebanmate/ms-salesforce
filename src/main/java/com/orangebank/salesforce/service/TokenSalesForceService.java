package com.orangebank.salesforce.service;

import io.micronaut.cache.annotation.CacheInvalidate;

import javax.inject.Singleton;

import com.orangebank.salesforce.client.salesforce.TokenSalesForceClient;

@Singleton
public class TokenSalesForceService {
  protected final TokenSalesForceClient tokenSalesForceClient;

  public TokenSalesForceService(TokenSalesForceClient tokenSalesForceClient) {
    this.tokenSalesForceClient = tokenSalesForceClient;
  }

  /**
   * Get a SalesForce token.
   * @return SalesForce token.
   */
  public String getToken() {
    return tokenSalesForceClient.getToken().getAccessToken();
  }

  @CacheInvalidate(
      cacheNames = {"salesforcetoken"},
      all = true
  )
  public String removeToken() {
    return "ok";
  }

}