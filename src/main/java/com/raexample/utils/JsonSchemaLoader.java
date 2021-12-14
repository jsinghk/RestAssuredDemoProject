package com.raexample.utils;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;


public class JsonSchemaLoader {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static JsonSchemaLoader instance = null;
    private static JsonSchema jsonSchemaObject = null;


    public static JsonSchema getJsonSchema(String jsonSchema) {
        if (instance == null) {
            instance = new JsonSchemaLoader();
        }
        try {
            jsonSchemaObject = instance.loadJsonSchema("json/schema/" + jsonSchema + ".json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonSchemaObject;
    }

    public JsonSchema loadJsonSchema(String jsonSchema) throws IOException {
        log.info("Reading json schema : " + jsonSchema);
        InputStream schemaStream = JsonSchemaLoader.class.getClassLoader().getResourceAsStream(jsonSchema);
        if (schemaStream == null) {
            throw new IOException("Unable to open stream for resouce " + jsonSchema);
        } else {
            JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4).getSchema(schemaStream);
            log.info(schema.toString());
            schemaStream.close();
            return schema;
        }
    }
}
