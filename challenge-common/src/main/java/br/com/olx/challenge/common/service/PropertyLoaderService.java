package br.com.olx.challenge.common.service;

import br.com.olx.challenge.common.exception.PropertyLoaderException;
import br.com.olx.challenge.common.model.Property;
import br.com.olx.challenge.common.portal.AbstractPortal;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyLoaderService {

    private final Map<String, AbstractPortal> availablePortal;
    private static final String TEMP_FILE_NAME = "temp-file.json";

    @Value("${properties.filePath}")
    private String propertiesFilePath;

    public void loadProperties() {
        List<Property> listProperty = getListOfObjectsFromFile(propertiesFilePath);
        availablePortal.entrySet().forEach(portal -> portal.getValue().filterPropertiesForPortal(listProperty));
        cleanTempFile();
    }

    private List<Property> getListOfObjectsFromFile(String filePath) {
        try {
            JsonReader reader = new JsonReader(new FileReader(getFile(filePath)));
            return Arrays.asList(new Gson().fromJson(reader, Property[].class));
        } catch (Exception e) {
            throw new PropertyLoaderException("LOADING_FILE_ERROR");
        }
    }

    private File getFile(String filePath) throws IOException {
        File file = new File(TEMP_FILE_NAME);
        FileUtils.copyURLToFile(new URL(filePath), file);
        return file;
    }

    private void cleanTempFile() {
        new File(TEMP_FILE_NAME).delete();
    }

}
