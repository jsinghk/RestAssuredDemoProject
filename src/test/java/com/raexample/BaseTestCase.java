package com.raexample;

import com.raexample.keywords.KeywordManager;
import com.raexample.utils.Storage;

public abstract class BaseTestCase {

    protected KeywordManager keyword;
    protected Storage storage;

    protected BaseTestCase() {
        keyword = KeywordManager.getInstance();
        storage = Storage.getInstance();
    }
}
