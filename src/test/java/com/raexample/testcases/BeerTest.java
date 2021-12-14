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
        map.put("first_brewed", "10/2008");
        keyword.
                beerApi().getBeersWithCondition(map);
    }
}
