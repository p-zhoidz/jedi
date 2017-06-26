package by.pzh.jedi.activity.flow;

import by.pzh.jedi.domain.Bundle;
import java.util.Collections;
import java.util.Map;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flow {

  private static final Logger LOGGER = LoggerFactory.getLogger(Flow.class);
  private static final String FLOW_SCHEMA = "flow/flow.bpmn20.xml";

  public static void init(Bundle bundle) {
    LOGGER.debug("Initializing flow");

    ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
        .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
        .setJdbcUsername("sa")
        .setJdbcPassword("")
        .setJdbcDriver("org.h2.Driver")
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    ProcessEngine processEngine = cfg.buildProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();
    repositoryService.createDeployment()
        .addClasspathResource(FLOW_SCHEMA).deploy();

    Map<String, Object> vars = Collections.singletonMap("bundle", bundle);

    LOGGER.debug("Starting flow");
    processEngine.getRuntimeService()
        .startProcessInstanceByKey("jedi", vars);
  }

}
