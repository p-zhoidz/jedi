package by.pzh.jedi.activity.delegate.sub.publication.instance;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationInstanceDelegate implements JavaDelegate {

  private final Logger LOGGER = LoggerFactory.getLogger(PublicationInstanceDelegate.class);

  @Override
  public void execute(DelegateExecution execution) {
    LOGGER.debug("Calling publication instance delegate");
  }
}
