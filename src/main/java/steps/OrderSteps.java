package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class OrderSteps {

    private Response response;
    private String baseUrl;

    @Given("la URL del servicio de órdenes")
    public void laUrlDelServicioDeOrdenes() {
        baseUrl = "https://petstore.swagger.io/v2/store/order";
    }

    @When("realizo una solicitud para obtener la orden con ID {string}")
    public void realizoUnaSolicitudParaObtenerLaOrdenConID(String orderId) {
        response = RestAssured.get(baseUrl + "/" + orderId);
    }

    @Then("la respuesta debería tener el código HTTP {int}")
    public void laRespuestaDeberiaTenerElCodigoHTTP(int statusCode) {
        response.then().statusCode(statusCode);
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("la respuesta debería contener la información de la orden")
    public void laRespuestaDeberiaContenerLaInformacionDeLaOrden() {
        response.then().assertThat()
                .body("id", equalTo(2))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }




}
