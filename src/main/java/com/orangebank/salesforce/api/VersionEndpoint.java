package com.orangebank.salesforce.api;

import com.orangebank.salesforce.api.dao.VersionDao;

import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;
import io.reactivex.Single;

@Endpoint(id = "version",
        prefix = "custom",
        defaultSensitive = false)
public class VersionEndpoint {

  private final VersionDao versionDao;

  public VersionEndpoint(VersionDao versionDao) {
    this.versionDao = versionDao;
  }

  @Read
  Single<String> getVersion() {
    return versionDao.readGitProperties();
  }
}