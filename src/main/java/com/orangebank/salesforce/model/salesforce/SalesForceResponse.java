package com.orangebank.salesforce.model.salesforce;

import java.util.List;

import lombok.Data;

@Data 
public class SalesForceResponse {

  private String id;

  private List<String> errors;

  private boolean success;

}
