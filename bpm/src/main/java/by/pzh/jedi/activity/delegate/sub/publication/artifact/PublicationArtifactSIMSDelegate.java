package by.pzh.jedi.activity.delegate.sub.publication.artifact;

import by.pzh.jedi.activity.gateway.MMRSGateway;
import by.pzh.jedi.domain.PublicationArtifact;
import feign.Response;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationArtifactSIMSDelegate implements JavaDelegate {
	private final Logger LOGGER = LoggerFactory.getLogger(PublicationArtifactSIMSDelegate.class);

	@Override
	public void execute(DelegateExecution execution) {
		LOGGER.debug("Calling publication artifact SIM service");

		PublicationArtifact artifact = execution.getVariable("artifact", PublicationArtifact.class);
		Response response = MMRSGateway.connect("http://localhost:8003")
				.callMmrs(artifact);
		LOGGER.debug(response.toString());

	}
}
