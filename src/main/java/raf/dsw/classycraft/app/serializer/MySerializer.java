package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            //e.printStackTrace();
            return null;
        }
    }

    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            mapper.writeValue(writer,project);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    public void saveDiagram(Diagram diagram) {
        String pathString = "src\\main\\resources\\sabloni\\"+diagram.getName()+".json";
        Path path = Paths.get(pathString);
        if (Files.exists(path)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Dijagram pod tim imenom vec postoji, sablon ce biti overwriteovan", MessageType.INFO);
           // return;
        }
        try (FileWriter writer = new FileWriter(pathString)) {
            mapper.writeValue(writer,diagram);
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Sablon sacuvan u resursima",MessageType.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Diagram loadDiagram(File file) {
        try{
            System.out.println(file);
            return mapper.readValue(file, Diagram.class);

        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
    }
}
