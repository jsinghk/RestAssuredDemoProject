package com.raexample.testcases;

import com.raexample.BaseTestCase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.raexample.enums.JsonSchema.BEER_SCHEMA;
import static com.raexample.utils.JsonSchemaValidator.validateJsonSchema;

public class BeerTest extends BaseTestCase {

    @Test(priority = 1)
    public void getDetailsOfAllBeers() {
        keyword.
                beerApi().getAllBeers();
        boolean validationResult = validateJsonSchema(((Response) storage.whatIsTheObject("response")),
                BEER_SCHEMA.getSchemaName());
        Assert.assertTrue(validationResult);
    }

    @Test(priority = 2)
    public void getBeersBrewedBeforeDate() {
        Map<String, String> map = new HashMap<>();
        map.put("brewed_before", "10/2008");
        keyword.
                beerApi().getBeersWithCondition(map);
        boolean validationResult = validateJsonSchema(((Response) storage.whatIsTheObject("response")),
                BEER_SCHEMA.getSchemaName());
        Assert.assertTrue(validationResult);
    }

    @Test(priority = 3)
    public void getBeersWithGreaterThanAbv() {
        Map<String, String> map = new HashMap<>();
        map.put("abv_gt", "6");
        keyword.
                beerApi().getBeersWithCondition(map);
        boolean validationResult = validateJsonSchema(((Response) storage.whatIsTheObject("response")),
                BEER_SCHEMA.getSchemaName());
        Assert.assertTrue(validationResult);
    }

    @Test(priority = 4)
    public void validatePagination() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "2");
        map.put("per_page", "5");
        keyword.
                beerApi().getBeersWithCondition(map);
        boolean validationResult = validateJsonSchema(((Response) storage.whatIsTheObject("response")),
                BEER_SCHEMA.getSchemaName());
        Assert.assertTrue(validationResult);
    }
}
