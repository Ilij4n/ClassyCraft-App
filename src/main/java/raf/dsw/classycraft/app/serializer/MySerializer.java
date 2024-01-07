package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MySerializer {
    private final ObjectMapper mapper = new ObjectMapper();

    public MySerializer(){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public Project loadProject(File file) {
        try{
            System.out.println(file);
            return (Project) mapper.readValue(file, ClassyNode.class);
        } catch (IOException e) {
            System.out.println("greska alooo");
            e.printStackTrace();
            return null;
        }
    }

    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            mapper.writeValue(writer,project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
