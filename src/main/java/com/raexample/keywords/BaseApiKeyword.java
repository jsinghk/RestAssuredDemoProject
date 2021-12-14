package com.raexample.keywords;

import com.raexample.utils.JsonResponseFileWriter;
import com.raexample.utils.Storage;

public abstract class BaseApiKeyword {

    protected Storage storageInstance;
    protected JsonResponseFileWriter jsonResponseFileWriterInstance;
    protected String className = this.getClass().getSimpleName();

    protected BaseApiKeyword() {
        if (storageInstance == null) {
            storageInstance = Storage.getInstance();
        }
        jsonResponseFileWriterInstance = JsonResponseFileWriter.getInstance();
    }
}
