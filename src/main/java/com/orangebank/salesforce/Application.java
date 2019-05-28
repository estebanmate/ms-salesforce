package com.orangebank.salesforce;

import com.orangebank.salesforce.Application;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;

@OpenAPIDefinition(
    servers = {
      @Server(
        description = "Deployed microservice",
        url = "{Server}",
        variables = {
          @ServerVariable(
            name = "Server",
            defaultValue = "/"
          )
        }
      ), @Server(
        description = "Local microservice",
        url = "{Local}",
        variables = {
          @ServerVariable(
            name = "Local",
            defaultValue = "/"
          )
        }
      )
    },
    info = 
      @Info(
        title = "MS Salesforce Calls Filter",
        version = "0.1",
        description = "MS for filter implement to Salesforce API calls"
      )
)
public class Application {
  public static void main(String[] args) {
    Micronaut.run(Application.class);
  }
}