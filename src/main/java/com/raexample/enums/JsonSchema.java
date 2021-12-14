package com.raexample.enums;

public enum JsonSchema {

    BEER_SCHEMA("beer-schema");

    private String schemaName;
    public String getSchemaName() {
        return schemaName;
    }

    private JsonSchema(String schemaName) {
        this.schemaName=schemaName;
    }

}
