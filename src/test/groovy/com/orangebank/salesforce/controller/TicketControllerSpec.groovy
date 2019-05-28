package com.orangebank.salesforce.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.core.io.socket.SocketUtils
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.*

import static io.micronaut.http.HttpRequest.*

import spock.lang.*

class TicketControllerSpec extends Specification {

  @Shared @AutoCleanup EmbeddedServer embeddedServer =
	  ApplicationContext.run(EmbeddedServer, ["redis.uri":"redis://localhost:${SocketUtils.findAvailableTcpPort()}"])

  @Shared @AutoCleanup RxHttpClient client = RxHttpClient.create(embeddedServer.URL)  

}