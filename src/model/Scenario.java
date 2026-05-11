package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private final String name;
    private final String qualityType;
    private final String mode;
    private final String description;
    private final List<Dimension> dimensions;

    public Scenario(String name, String qualityType, String mode, String description) {
        this.name = name;
        this.qualityType = qualityType;
        this.mode = mode;
        this.description = description;
        this.dimensions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getQualityType() {
        return qualityType;
    }

    public String getMode() {
        return mode;
    }

    public String getDescription() {
        return description;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }
}
