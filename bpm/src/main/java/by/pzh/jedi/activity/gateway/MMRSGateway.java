package by.pzh.jedi.activity.gateway;


import by.pzh.jedi.domain.PublicationArtifact;
import feign.Headers;
import feign.RequestLine;
import feign.Response;
import org.springframework.http.HttpStatus;

public interface MMRSGateway {

  static MMRSGateway connect(String url) {
    return ServiceFactory.factory(MMRSGateway.class, url, cause -> new MMRSGateway() {
      @Override
      public Response callMmrs(PublicationArtifact artifact) {
        return fallback();
      }

      private Response fallback() {
        System.out.println(cause);
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
      }
    });
  }

  @RequestLine("POST /mmrs/test")
  @Headers("Content-Type: application/json")
  Response callMmrs(PublicationArtifact artifact);

}
