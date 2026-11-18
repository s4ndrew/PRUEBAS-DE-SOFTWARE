package com.pruebas.api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

class CursoApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    @DisplayName("Escenario 1: Consultar todos los cursos")
    void testConsultarTodosLosCursos() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/cursos")
                .then()
                .statusCode(200)
                .body("$", is(not(empty())));
    }

    @Test
    @DisplayName("Escenario 2: Consultar el curso con id 1")
    void testConsultarCursoPorId1() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/cursos/1")
                .then()
                .statusCode(200)
                .body("nombre", equalTo("Pruebas de Software"))
                .body("precio", greaterThan(0.0f));
    }
}