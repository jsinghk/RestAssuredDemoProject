package com.raexample.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.raexample.utils.JsonSchemaLoader.getJsonSchema;

public class JsonSchemaValidator {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static JsonSchemaValidator instance = null;

    public static boolean validateJsonSchema(Response response, String jsonSchema) {
        if (instance == null) {
            instance = new JsonSchemaValidator();
        }
        return instance.validateSchema(response, jsonSchema);
    }

    public boolean validateSchema(Response response, String jsonSchemaName) {

        JsonSchema jsonSchema = getJsonSchema(jsonSchemaName);
        JsonNode responseJson = response.body().as(JsonNode.class);
        Set<ValidationMessage> validationResult = jsonSchema.validate(responseJson);
        if (validationResult.isEmpty()) {
            log.info("Response schema validation successful");
        } else {
            log.error("Response schema has following errors : ");
            validationResult.forEach(vr -> log.error(vr.getMessage()));
            return false;
        }
        return true;
    }
}
