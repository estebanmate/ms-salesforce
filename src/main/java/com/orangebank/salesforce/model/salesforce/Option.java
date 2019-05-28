package com.orangebank.salesforce.model.salesforce;

import java.io.Serializable;

import lombok.Data;

@Data
public class Option implements Serializable {
  private static final long serialVersionUID = 1L;

  private String label;
  private String value;
}
