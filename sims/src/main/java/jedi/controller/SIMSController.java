package jedi.controller;

import by.pzh.jedi.domain.PublicationArtifact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "sims")
public class SIMSController {

  @PostMapping(value = "/test")
  public ResponseEntity<Void> handle(@RequestBody PublicationArtifact payload) {
    return ResponseEntity.noContent().build();
  }

}
