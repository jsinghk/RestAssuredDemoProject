package com.raexample.testcases;

import com.raexample.BaseTestCase;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BeerTest extends BaseTestCase {

    @Test(priority = 1)
    public void getDetailsOfAllBeers() {
        keyword.
                beerApi().getAllBeers();
    }

    @Test(priority = 2)
    public void getBeersBrewedBeforeDate() {
        Map<String, String> map = new HashMap<>();
        map.put("brewed_before", "10/2008");
        keyword.
                beerApi().getBeersWithCondition(map);
    }

    @Test(priority = 3)
    public void getBeersWithGreaterThanAbv() {
        Map<String, String> map = new HashMap<>();
        map.put("abv_gt", "6");
        keyword.
                beerApi().getBeersWithCondition(map);
    }

    @Test(priority = 4)
    public void validatePagination() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "2");
        map.put("per_page", "5");
        keyword.
                beerApi().getBeersWithCondition(map);
    }
}
