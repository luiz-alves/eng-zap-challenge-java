package br.com.olx.challenge.common.service;

import br.com.olx.challenge.common.exception.PropertyLoaderException;
import br.com.olx.challenge.common.model.Property;
import br.com.olx.challenge.common.portal.AbstractPortal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyLoaderService {

    private final Map<String, AbstractPortal> availablePortal;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${properties.filePath}")
    private String propertiesFilePath;

    @Value("${properties.isAbsolutePath}")
    private Boolean isAbsolutePath;

    public void loadProperties(){
        List<Property> listProperty = getListOfObjectsFromFile(propertiesFilePath);
        availablePortal.entrySet().forEach(portal -> portal.getValue().filterPropertiesForPortal(listProperty));
    }

    private List<Property> getListOfObjectsFromFile(String filePath){
        try{
            JsonReader reader = new JsonReader(new FileReader(getFile(filePath)));
            return Arrays.asList(new Gson().fromJson(reader, Property[].class));
        } catch (Exception e){
            throw new PropertyLoaderException("LOADING_FILE_ERROR");
        }
    }

    public File getFile(String filePath) throws URISyntaxException {
        if(isAbsolutePath){
            return new File(filePath);
        }
        return new File(getClass().getClassLoader().getResource(filePath).toURI());
    }
}
