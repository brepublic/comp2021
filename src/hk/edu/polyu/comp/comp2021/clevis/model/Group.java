package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of shapes.
 */
public class Group implements Shape {
    private String name;
    private List<String> componentNames;
    
    /**
     * Makes a new group.
     * @param name the group's name
     * @param componentNames the names of the shapes to put in the group
     */
    public Group(String name, List<String> componentNames) {
        this.name = name;
        this.componentNames = new ArrayList<>(componentNames);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "group";
    }
    
    /**
     * Gets the list of shape names in this group.
     * @return the list of shape names
     */
    public List<String> getComponentNames() {
        return new ArrayList<>(componentNames);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("group ").append(name);
        for (String componentName : componentNames) {
            sb.append(" ").append(componentName);
        }
        return sb.toString();
    }
}

