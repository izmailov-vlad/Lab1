package data.components;

import data.ComponentType;

public class Component {
    protected String name;
    protected ComponentType componentType;
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Component && ((Component) obj).name.equals(this.name));
    }
}
