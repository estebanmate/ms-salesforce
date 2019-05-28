package com.orangebank.salesforce.api.dao;

import io.reactivex.Single;

public interface VersionDao {

  Single<String> readGitProperties();

}
