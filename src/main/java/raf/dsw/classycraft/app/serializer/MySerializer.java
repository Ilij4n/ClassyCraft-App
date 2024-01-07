package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Project;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MySerializer {
    private final ObjectMapper mapper = new ObjectMapper();

    public MySerializer(){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Point2D.class, new Point2DDeserializer());
        mapper.registerModule(module);
    }

    public Project loadProject(File file) {
        try{
            System.out.println(file);
            return mapper.readValue(file, Project.class);

        } catch (IOException e) {
            System.out.println("error");
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
