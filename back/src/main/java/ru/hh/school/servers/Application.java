package ru.hh.school.servers;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.servers.config.CommonConfig;
import ru.hh.school.servers.config.JerseyConfig;
import ru.hh.school.servers.config.ProdConfig;

public class Application {

  public static void main(String[] args) {
    NabApplication.builder()
      .configureJersey(JerseyConfig.class).bindToRoot()
      .build().run(ProdConfig.class, CommonConfig.class);
  }
}
