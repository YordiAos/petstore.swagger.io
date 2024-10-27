package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class EliminarOrdenSteps {

    private Response response;
    private String baseUrl;

    @Given("la URL del servicio para eliminar órdenes")
    public void laUrlDelServicioParaEliminarOrdenes() {
        baseUrl = "https://petstore.swagger.io/v2/store/order";
    }

    @When("elimino la orden con ID {string}")
    public void eliminoLaOrdenConID(String orderId) {
        response = RestAssured.delete(baseUrl + "/" + orderId);
    }

    @Then("la respuesta debería tener el código HTTP eliminar {int}")
    public void laRespuestaDeberiaTenerElCodigoHTTPEliminar(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("no debería existir la orden con ID {string}")
    public void noDeberiaExistirLaOrdenConID(String orderId) {
        Response getResponse = RestAssured.get(baseUrl + "/" + orderId);
        getResponse.then().statusCode(404);
    }
    @Given("existe una orden con ID {string}")
    public void existeUnaOrdenConID(String orderId) {
        RestAssured.given()
                .contentType("application/json")
                .body("{\"id\": " + orderId + ", \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2024-10-26T15:00:04.641Z\", \"status\": \"placed\", \"complete\": true}")
                .post(baseUrl);
    }

}
