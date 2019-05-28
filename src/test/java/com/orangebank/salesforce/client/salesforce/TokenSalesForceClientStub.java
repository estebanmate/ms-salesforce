package com.orangebank.salesforce.client.salesforce;

import com.orangebank.salesforce.client.salesforce.TokenSalesForceClient;
import com.orangebank.salesforce.model.salesforce.TokenSalesForce;

import io.micronaut.context.annotation.Primary;

@Primary
public class TokenSalesForceClientStub implements TokenSalesForceClient {

  /**
   * Get a token.
   * @return token.
   */
  public TokenSalesForce getToken() {
    TokenSalesForce token = new TokenSalesForce();
    token.setAccessToken("token");
    return token;
  }
}
