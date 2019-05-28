package com.orangebank.salesforce.model.salesforce.composite;

import lombok.Data;

/**
 * BodyCompositeResponse.
 */
@Data
public class BodyCompositeResponse<T> {

  private T body;
  private Integer httpStatusCode;
  
}