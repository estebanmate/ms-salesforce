package com.orangebank.salesforce.model.salesforce.composite;

import java.util.List;

import lombok.Data;

/**
 * CompositeResponse.
 */
@Data
public class CompositeResponse {

  private List<BodyCompositeResponse<?>> compositeResponse;
  
}