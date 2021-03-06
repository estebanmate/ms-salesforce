package com.orangebank.salesforce.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(SwaggerConfig.PREFIX)
public class SwaggerConfig {

  public static final String PREFIX = "application.api.swagger";

  private String version;
  private String layout;
  private boolean deepLinking;
  private List<UriConfig> urls;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getLayout() {
    return layout;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public boolean isDeepLinking() {
    return deepLinking;
  }

  public void setDeepLinking(boolean deepLinking) {
    this.deepLinking = deepLinking;
  }

  public List<UriConfig> getUrls() {
    return urls;
  }

  public void setUrls(List<UriConfig> urls) {
    this.urls = urls;
  }

  @ConfigurationProperties(UriConfig.PREFIX)
  public static class UriConfig {

    static final String PREFIX = "urls";

    private String name;
    private String url;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }

}