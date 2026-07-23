package elocindev.necronomicon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class FileUtils {
    public static void setPrettyPrint(Path path) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String jsonString = sb.toString();

        JsonElement jsonElement;
        try {
            jsonElement = JsonParser.parseString(jsonString);
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON in file: " + path.toAbsolutePath());
            e.printStackTrace();
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(jsonElement);

        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            fileWriter.write(prettyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
