package com.orangebank.salesforce.model.salesforce.composite;

import java.util.List;
import lombok.Data;

/**
 * CompositeRequest.
 */
@Data
public class CompositeRequest {

  protected List<? extends QueryComposite> compositeRequest;
  
}