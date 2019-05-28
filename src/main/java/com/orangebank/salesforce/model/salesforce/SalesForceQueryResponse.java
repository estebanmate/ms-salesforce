package com.orangebank.salesforce.model.salesforce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data 
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesForceQueryResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer totalSize;
  private Boolean done;
  private List<T> records;

}
