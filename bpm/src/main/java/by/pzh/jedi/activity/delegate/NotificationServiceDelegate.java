package by.pzh.jedi.activity.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationServiceDelegate implements JavaDelegate {
	private final Logger LOGGER = LoggerFactory.getLogger(PIPSDelegate.class);

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.debug("Calling notification service");
	}
}
