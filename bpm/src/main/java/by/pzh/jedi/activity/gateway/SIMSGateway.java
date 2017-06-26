package by.pzh.jedi.activity.gateway;


import by.pzh.jedi.domain.PublicationArtifact;
import feign.Headers;
import feign.RequestLine;
import feign.Response;
import org.springframework.http.HttpStatus;

public interface SIMSGateway {

  static SIMSGateway connect(String url) {
    return ServiceFactory.factory(SIMSGateway.class, url, cause -> new SIMSGateway() {
      @Override
      public Response callSims(PublicationArtifact artifact) {
        return fallback();
      }

      private Response fallback() {
        System.out.println(cause);
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
      }
    });
  }

  @RequestLine("POST /sims/test")
  @Headers("Content-Type: application/json")
  Response callSims(PublicationArtifact artifact);

}
