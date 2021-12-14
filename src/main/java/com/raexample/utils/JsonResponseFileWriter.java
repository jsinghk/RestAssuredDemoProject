package com.raexample.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class JsonResponseFileWriter {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static JsonResponseFileWriter instance;

    public static JsonResponseFileWriter getInstance() {
        if (instance == null) {
            instance = new JsonResponseFileWriter();
        }
        return instance;
    }

    public void writeJsonToFile(Response response, String jsonFileName) {

        try {
            Files.createDirectories(Paths.get(new File("target").getAbsolutePath() + "/json-response"));
            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().
                    getFile().replace("classes", "json-response") + jsonFileName + ".json";
            log.info("Json File Path : " + path);
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(response.asPrettyString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
