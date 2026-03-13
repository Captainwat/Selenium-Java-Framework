package com.captainwat.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class BookingApiTest {

    String token;

    @BeforeClass
    public void setUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType("application/json")
                .build();

        String authBody = "{ \"username\": \"admin\", \"password\": \"password123\" }";

        token = RestAssured
                .given()
                    .body(authBody)
                .when()
                    .post("/auth")
                .then()
                    .statusCode(200)
                    .extract().path("token");
    }

    private int createTestBooking() {
        Booking myData = BookingFactory.createDefaultBooking();

        return RestAssured
                .given()
                    .body(myData)
                .when()
                    .post("/booking")
                .then()
                    .statusCode(200)
                    .body("booking.firstname", equalTo(myData.firstname))
                    .body("booking.lastname", equalTo(myData.lastname))
                    .extract().path("bookingid");
    }

    private void deleteTestBooking(int bookingId) {
        RestAssured
                .given()
                    .cookie("token", token)
                .when()
                    .delete("/booking/" + bookingId)
                .then()
                    .statusCode(201);
    }

    @Test
    public void healthCheckTest() {
        RestAssured
                .given()
                .when()
                    .get("/ping")
                .then()
                    .statusCode(201);
    }

    @Test
    public void getBookingIdsTest() {
        RestAssured
                .given()
                .when()
                    .get("/booking")
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0));
    }

    @Test
    public void createBookingTest() {
    int newBookingId = createTestBooking();
    Assert.assertTrue(newBookingId > 0);
    deleteTestBooking(newBookingId);
}

    @Test
    public void updateBookingTest() {
    int newBookingId = createTestBooking();
    Booking myUpdatedData = BookingFactory.createUpdatedBooking();

    RestAssured
            .given()
                .cookie("token", token)
                .body(myUpdatedData)
            .when()
                .put("/booking/" + newBookingId)
            .then()
                .statusCode(200)
                .body("firstname", equalTo(myUpdatedData.firstname))
                .body("lastname", equalTo(myUpdatedData.lastname));

    deleteTestBooking(newBookingId);
}

    @Test
public void getOneBookingTest() {
    int newBookingId = createTestBooking();
    Booking myData = BookingFactory.createDefaultBooking();

    RestAssured
            .given()
            .when()
                .get("/booking/" + newBookingId)
            .then()
                .statusCode(200)
                .body("firstname", equalTo(myData.firstname))
                .body("lastname", equalTo(myData.lastname));

    deleteTestBooking(newBookingId);
}

    @Test
    public void getAuthTokenTest() {
        String authBody = "{ \"username\": \"admin\", \"password\": \"password123\" }";

        String testToken = RestAssured
                .given()
                    .body(authBody)
                .when()
                    .post("/auth")
                .then()
                    .statusCode(200)
                    .extract().path("token");

        Assert.assertNotNull(testToken);
        Assert.assertFalse(testToken.isEmpty());
    }

    @Test
public void deleteBookingTest() {
    int newBookingId = createTestBooking();
    deleteTestBooking(newBookingId);
}
}