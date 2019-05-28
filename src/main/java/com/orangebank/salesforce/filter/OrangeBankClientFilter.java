package com.orangebank.salesforce.filter;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.context.ServerRequestContext;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;

import java.util.Optional;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orangebank.salesforce.util.SalesforceUtils;

@Filter("/**")
public class OrangeBankClientFilter implements HttpClientFilter {

    private static final Logger LOG = LoggerFactory.getLogger(OrangeBankClientFilter.class);

    /**
     * @param targetRequest The target request.
     * @param chain         The filter chain.
     * @return The publisher of the response.
     */
    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> targetRequest, ClientFilterChain chain) {
        return doFilter(targetRequest, chain, ServerRequestContext.currentRequest());
    }

    /**
     *
     * @param targetRequest  The target request of this HttpClientFilter.
     * @param chain          The filter chain.
     * @param currentRequest The original request which triggered during its
     *                       execution the invocation of this HttpClientFilter.
     * @return The publisher of the response.
     */
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> targetRequest, ClientFilterChain chain,
            Optional<HttpRequest<Object>> currentRequest) {

        targetRequest.headers(SalesforceUtils
                .getSalesforceHeaders(currentRequest.isPresent() ? currentRequest.get() : targetRequest));

        LOG.info("Sending request to " + targetRequest.toString(), SalesforceUtils.getLogInfo(targetRequest));

        return chain.proceed(targetRequest);
    }

}