package com.raexample.keywords;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import static com.raexample.utils.PropertiesLoader.getProperty;
import static io.restassured.RestAssured.*;

public class BeerApiKeyword extends BaseApiKeyword {

    private final String BASE_URI = getProperty("punk.base.uri");
    private final String BASE_PATH = getProperty("punk.base.path");
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public BeerApiKeyword() {
        baseURI = BASE_URI;
        basePath = BASE_PATH;
        log.info("Setting request url : " + BASE_URI + BASE_PATH);
    }

    public void getAllBeers() {
        Response response = given().accept(ContentType.JSON).
                when().get().
                then().statusCode(200).extract().response();
        log.info("Writing the Response to Output File");
        jsonResponseFileWriterInstance.writeJsonToFile(response, className + "-GetAllBeers");
        storageInstance.rememberTheResponse(response);

    }

    public void getBeersWithCondition(Map<String, ?> queryParamsMap) {
        Response response = given().accept(ContentType.JSON).queryParams(queryParamsMap).
                when().get().
                then().statusCode(200).extract().response();
        jsonResponseFileWriterInstance.writeJsonToFile(response, className + "-GetBeerWithCondition");
        storageInstance.rememberTheResponse(response);
    }
}
