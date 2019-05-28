package com.orangebank.salesforce.client.salesforce;

import com.orangebank.salesforce.model.salesforce.composite.CompositeRequest;
import com.orangebank.salesforce.model.salesforce.composite.CompositeResponse;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Post;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;


@Client("${salesforce.api-url}/${salesforce.api-version}/${salesforce.composite-endpoint}")
@Retryable(delay = "200ms")
public interface CompositeSalesForceClient {

  @Post
  @Consumes(MediaType.APPLICATION_JSON)
  public Single<CompositeResponse> composite(@Body CompositeRequest compositeRequest);

}
