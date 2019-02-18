package ru.hh.school.servers;

import ru.hh.nab.starter.NabApplication;

public class Application {

  public static void main(String[] args) {
    NabApplication.builder()
      .configureJersey(JerseyConfig.class).bindToRoot()
      .build().run(CommonConfig.class);
  }
}
