package by.pzh.jedi.activity.gateway;

import by.pzh.jedi.domain.PublicationArtifact;
import feign.Headers;
import feign.RequestLine;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CSGateway {

  static CSGateway connect(String url) {
    return ServiceFactory.factory(CSGateway.class, url, cause -> new CSGateway() {
      @Override
      public Response callCs(PublicationArtifact artifact) {
        return fallback();
      }

      private Response fallback() {
        System.out.println(cause);
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
      }
    });
  }


  @RequestLine("POST /cs/test")
  @Headers("Content-Type: application/json")
  Response callCs(PublicationArtifact artifact);

}
