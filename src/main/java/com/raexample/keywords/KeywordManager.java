package com.raexample.keywords;

public class KeywordManager {

    public static KeywordManager instance;
    public BeerApiKeyword beerApiKeyword;
    public LottoApiKeyword lottoApiKeyword;

    public static KeywordManager getInstance() {
        if (instance == null) {
            instance = new KeywordManager();
        }
        return instance;
    }

    public BeerApiKeyword beerApi() {
        return new BeerApiKeyword();
    }

    public LottoApiKeyword lottoApi() {
        return new LottoApiKeyword();
    }
}
