package com.pruebas.bdd.steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InscripcionStepDefinitions {

    private Response response;

    @Given("que el sistema de matrícula está disponible")
    public void sistemaDisponible() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Given("que el estudiante {string} ya está inscrito en el curso {int}")
    public void estudianteYaInscrito(String codigoEstudiante, Integer cursoId) {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @When("un estudiante nuevo con código {string} solicita inscribirse en el curso {int}")
    public void inscribirEstudianteNuevo(String codigoEstudiante, Integer cursoId) {
        String payload = String.format(
                "{\"codigoEstudiante\": \"%s\", \"cursoId\": %d}",
                codigoEstudiante, cursoId
        );

        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api/inscripciones");
    }

    @When("el estudiante {string} intenta inscribirse de nuevo en el curso {int}")
    public void inscribirEstudianteDuplicado(String codigoEstudiante, Integer cursoId) {
        String payload = String.format(
                "{\"codigoEstudiante\": \"%s\", \"cursoId\": %d}",
                codigoEstudiante, cursoId
        );

        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api/inscripciones");
    }

    @Then("el sistema responde con estado {int}")
    public void verificarRespuestaExitosa(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("el sistema rechaza la solicitud con un código de error 4xx o 5xx")
    public void verificarCodigoError() {
        response.then().statusCode(allOf(greaterThanOrEqualTo(400), lessThan(600)));
    }
}