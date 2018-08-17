package com;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ApiFactory {

    public static ApiKey getKey(String exName) {
        List<String> fileContents = null;
        try {
            fileContents = FileUtils.readLines(new File("/Users/handong/Documents/blk.txt"));
        } catch (IOException e) {
            return new ApiKey();
        }
        ApiKey apiKey = new ApiKey();
        for (String con : fileContents) {
            String key = con.split(":")[0].trim();
            String val = con.split(":")[1].trim();
            if (key.startsWith(exName) && key.endsWith("_S")) {
                apiKey.setSecret(val);
            }
            if (key.startsWith(exName) && key.endsWith("_K")) {
                apiKey.setApiKey(val);
            }
        }

        return apiKey;
    }
}
