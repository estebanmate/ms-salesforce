package com.orangebank.salesforce.api.dao.impl;

import fr.brouillard.oss.jgitver.GitVersionCalculator;
import io.reactivex.Single;

import java.io.File;

import javax.inject.Singleton;

import com.orangebank.salesforce.api.dao.VersionDao;

@Singleton
public class VersionDaoImpl implements VersionDao {

  @Override
  public Single<String> readGitProperties() {
    File workDir = new File(System.getProperty("user.dir"));
    try (GitVersionCalculator jgitver = GitVersionCalculator.location(workDir)) {
      System.out.println(jgitver.getVersion());
      return Single.just(jgitver.getVersion());
    } catch (Exception e) {
      e.printStackTrace();
      return Single.error(e);
    }

  }
}
