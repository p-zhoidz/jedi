package by.pzh.jedi.activity.delegate.sub.publication.artifact;

import by.pzh.jedi.activity.gateway.CSGateway;
import by.pzh.jedi.domain.PublicationArtifact;
import feign.Response;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class PublicationArtifactContentService implements JavaDelegate {

  private final Logger LOGGER = LoggerFactory.getLogger(PublicationArtifactContentService.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOGGER.debug("Calling publication artifact content service");

    PublicationArtifact artifact = execution.getVariable("artifact", PublicationArtifact.class);

    Response response = CSGateway.connect("http://localhost:8001")
        .callCs(artifact);

    LOGGER.debug(response.toString());
  }
}
