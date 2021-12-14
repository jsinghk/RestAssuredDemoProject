package com.raexample.keywords;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static com.raexample.utils.JsonSchemaLoader.getJsonSchema;
import static com.raexample.utils.PropertiesLoader.getProperty;
import static io.restassured.RestAssured.*;

public class BeerApiKeyword {

    private final String BASE_URI = getProperty("punk.base.uri");
    private final String BASE_PATH = getProperty("punk.base.path");
    private final String BEER_SCHEMA = "beer-schema";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private JsonSchema beerSchema;

    public BeerApiKeyword() {
        baseURI = BASE_URI;
        basePath = BASE_PATH;
        log.info("Setting request url : " + BASE_URI + BASE_PATH);
        beerSchema = getJsonSchema(BEER_SCHEMA);
    }

    public void getAllBeers() {
        Response response = given().accept(ContentType.JSON).
                when().get().
                then().statusCode(200).extract().response();
        log.info("Response :\n" + response.asPrettyString());

        JsonNode responseJson = response.body().as(JsonNode.class);
        Set<ValidationMessage> validationResult = beerSchema.validate(responseJson);
        if (validationResult.isEmpty()) {
            log.info("Response schema validation successful");
        } else {
            log.error("Response schema has following errors : ");
            validationResult.forEach(vr -> log.error(vr.getMessage()));
            Assert.fail("Schema has validation error");
        }
    }

    public void getBeersWithCondition(Map<String, ?> queryParamsMap) {
        Response response = given().accept(ContentType.JSON).queryParams(queryParamsMap).
                when().get().
                then().statusCode(200).extract().response();
        log.info("Response :\n" + response.asPrettyString());

        JsonNode responseJson = response.body().as(JsonNode.class);
        Set<ValidationMessage> validationResult = beerSchema.validate(responseJson);
        if (validationResult.isEmpty()) {
            log.info("Response schema validation successful");
        } else {
            log.error("Response schema has following errors : ");
            validationResult.forEach(vr -> log.error(vr.getMessage()));
            Assert.fail("Schema has validation error");
        }
    }
}
