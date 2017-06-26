package by.pzh.jedi.activity.delegate.sub.publication.artifact;

import by.pzh.jedi.activity.gateway.MMRSGateway;
import by.pzh.jedi.domain.PublicationArtifact;
import feign.Response;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationArtifactMMRServiceDelegate implements JavaDelegate {

  private final Logger LOGGER = LoggerFactory.getLogger(PublicationArtifactMMRServiceDelegate.class);


  @Override
  public void execute(DelegateExecution execution) {
    LOGGER.debug("Calling publication artifact MMR service");

    PublicationArtifact artifact = execution.getVariable("artifact", PublicationArtifact.class);

    Response response = MMRSGateway.connect("http://localhost:8002")
        .callMmrs(artifact);

    LOGGER.debug(response.toString());

  }
}
