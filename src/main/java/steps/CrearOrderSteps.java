package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class CrearOrderSteps {

    private Response response;
    private String baseUrl;
    private String requestBody;

    @Given("la URL del servicio para crear órdenes")
    public void laUrlDelServicioParaCrearOrdenes() {
        baseUrl = "https://petstore.swagger.io/v2/store/order";
    }

    @When("creo una orden con el siguiente cuerpo:")
    public void creoUnaOrdenConElSiguienteCuerpo(String body) {
        requestBody = body;
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl);
    }

    @Then("la respuesta al crear debería tener el código HTTP {int}")
    public void laRespuestaDeberiaTenerElCodigoHTTP(int statusCode) {
        response.then().statusCode(statusCode);
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("la respuesta debería contener la información de la orden creada")
    public void laRespuestaDeberiaContenerLaInformacionDeLaOrdenCreada() {
        response.then().assertThat()
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }

    @When("creo una orden con datos inválidos")
    public void creoUnaOrdenConDatosInvalidos() {
        requestBody = "{ \"id\": -1, \"petId\": -1, \"quantity\": -1, \"shipDate\": \"2024-10-26T15:00:04.641Z\", \"status\": \"invalid\", \"complete\": false }";
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl);
    }
}
