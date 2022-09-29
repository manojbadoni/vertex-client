import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class EmployeeClient {

    public static void main(String args[]) {
        System.out.println("abc");


        Vertx vertx = Vertx.vertx();
        WebClient webClient = WebClient.create(vertx);

        //post
        webClient.post(9090,"localhost","/addEmployee").sendJson(new Employee(4,"mb","IT",45000),response->{
            if(response.succeeded()) {
                HttpResponse<Buffer> httpResponse = response.result();
                System.out.println("Post Response: " + httpResponse.bodyAsString());
            }
            else {
                System.out.println("Error: " + response.cause().getMessage());

            }
        });

        //Get

        webClient.get(9090,"localhost","/getEmployees").send(response->{
            if(response.succeeded()) {
                HttpResponse<Buffer> httpResponse = response.result();
                System.out.println("get Response: " + httpResponse.bodyAsString());
            }
            else {
                System.out.println("Error: " + response.cause().getMessage());

            }
        });

        //Get with filter

        webClient.get(9090,"localhost","/getEmployee/:name").addQueryParam("name", "mb").send(response->{
            if(response.succeeded()) {
                HttpResponse<Buffer> httpResponse = response.result();
                System.out.println("get Response filter: " + httpResponse.bodyAsString());
            }
            else {
                System.out.println("Error: " + response.cause().getMessage());

            }
        });
    }
}
