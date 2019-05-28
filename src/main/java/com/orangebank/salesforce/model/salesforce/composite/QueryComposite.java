package com.orangebank.salesforce.model.salesforce.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * QueryComposite.
 */
@Data
public class QueryComposite {
  protected String method;
  protected String url;
  protected String referenceId;
  @JsonIgnore
  protected Integer order;

  public QueryComposite() {
    
  }

  /**
   * Copy constructor.
   * @param q QueryComposite to copy
   */
  public QueryComposite(QueryComposite q) {
    this.method = q.method;
    this.url = q.url;
    this.referenceId = q.referenceId;
    this.order = q.order;
  }
}