package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.awt.geom.Point2D;
import java.io.IOException;

public class Point2DDeserializer extends StdDeserializer<Point2D> {

    public Point2DDeserializer() {
        this(null);
    }

    public Point2DDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Point2D deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        // Navigate to the "location" node
        JsonNode locationNode = node.get("location");
        if (locationNode == null || !locationNode.isObject()) {
            throw new IOException("Invalid JSON structure for Point2D location");
        }

        // Extract "x" and "y" values from the "location" node
        double x = locationNode.get("x").asDouble();
        double y = locationNode.get("y").asDouble();

        return new Point2D.Double(x, y);
    }
}
