package com.orangebank.salesforce.filter;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orangebank.salesforce.util.SalesforceUtils;

@Filter("/**")
public class OrangeBankServerFilter implements HttpServerFilter {

  private static final Logger LOG = LoggerFactory.getLogger(OrangeBankServerFilter.class);

  /**
   *
   * @param targetRequest The target request.
   * @param chain         The filter chain.
   * @return The publisher of the response.
   */
  @Override
  public Publisher<MutableHttpResponse<?>> doFilter(
      HttpRequest<?> targetRequest, ServerFilterChain chain) {
    if (!targetRequest.toString().contains("health")) {
      LOG.info("Receiving request " + targetRequest.toString(),
          SalesforceUtils.getLogInfo(targetRequest));
    }
    return chain.proceed(targetRequest);
  }

}