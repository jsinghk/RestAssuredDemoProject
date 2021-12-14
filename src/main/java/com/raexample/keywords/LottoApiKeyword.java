package com.raexample.keywords;

import com.raexample.pojo.lottoapi.Lotto;
import com.raexample.utils.Storage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static com.raexample.utils.PropertiesLoader.getProperty;
import static io.restassured.RestAssured.*;

public class LottoApiKeyword {

    private final String BASE_URI = getProperty("lotto.base.uri");
    private final String BASE_PATH = getProperty("lotto.base.path");
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private RequestSpecification reqSpec;
    private Lotto lottoResponse;
    private Storage storageInstance = null;

    public LottoApiKeyword() {
        baseURI = BASE_URI;
        basePath = BASE_PATH;
        log.info("Setting request url : " + BASE_URI + BASE_PATH);
        if(storageInstance == null){
            storageInstance = Storage.getInstance();
        }
        reqSpec = given().header("Content-Type", "application/json");

    }

    public void getLottoDetails() {
        Response response = given().accept(ContentType.JSON).spec(reqSpec).
                when().get().
                then().statusCode(200).extract().response();

        lottoResponse = response.getBody().as(Lotto.class);
        storageInstance.rememberTheValue("lottoResponse",lottoResponse);
    }
}
