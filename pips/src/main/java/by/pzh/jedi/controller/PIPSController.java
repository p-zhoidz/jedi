package by.pzh.jedi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "pips")
public class PIPSController {

  @PostMapping(value = "/test")
  public ResponseEntity<Void> handle(@RequestBody Object payload) {
    return ResponseEntity.noContent().build();
  }

}
