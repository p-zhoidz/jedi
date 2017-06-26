package by.pzh.jedi.activity;

import by.pzh.jedi.activity.flow.Flow;
import by.pzh.jedi.domain.Bundle;
import by.pzh.jedi.domain.PublicationArtifact;
import by.pzh.jedi.domain.ScholarlyItem;
import java.util.Arrays;
import java.util.Collections;

public class App {

  public static void main(String... args) {
    Bundle bundle = new Bundle().setActive(true)
        .setPublicationArtifacts(
            Arrays.asList(new PublicationArtifact(), new PublicationArtifact(), new PublicationArtifact()))
        .setScholarlyItems(Collections.singletonList(new ScholarlyItem()));

    Flow.init(bundle);
  }

}
