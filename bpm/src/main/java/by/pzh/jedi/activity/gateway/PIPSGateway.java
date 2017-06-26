package by.pzh.jedi.activity.gateway;

import feign.Headers;
import feign.RequestLine;
import feign.Response;
import org.springframework.http.HttpStatus;

public interface PIPSGateway {

  static PIPSGateway connect(String url) {
    return ServiceFactory.factory(PIPSGateway.class, url, cause -> new PIPSGateway() {
      @Override
      public Response callPips(Object object) {
        return fallback();
      }

      private Response fallback() {
        System.out.println(cause);
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
      }
    });
  }

  @RequestLine("POST /pips/test")
  @Headers("Content-Type: application/json")
  Response callPips(Object obj);

}
