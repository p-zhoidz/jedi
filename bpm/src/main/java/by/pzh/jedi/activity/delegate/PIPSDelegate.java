package by.pzh.jedi.activity.delegate;

import by.pzh.jedi.activity.gateway.PIPSGateway;
import by.pzh.jedi.domain.Bundle;
import by.pzh.jedi.domain.Result;
import feign.Response;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class PIPSDelegate implements JavaDelegate {

  private final Logger LOGGER = LoggerFactory.getLogger(PIPSDelegate.class);

  public void execute(DelegateExecution execution) {
    LOGGER.info("CALLING PIPS Delegate");

    Bundle bundle = execution.getVariable("bundle", Bundle.class);
    Response response = PIPSGateway.connect("http://localhost:8000").callPips(bundle);
    Result<Bundle> result = new Result<Bundle>()
        .setTarget(bundle)
        .setOk(HttpStatus.valueOf(response.status()).is2xxSuccessful());

    execution.setVariableLocal("result", result);
  }

}
