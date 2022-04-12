package data;

import data.components.*;
import utils.BuildResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputerBuilderImpl implements ComputerBuilder {

    private Map<ComponentType, ArrayList<Component>> components = new HashMap<>();

    @Override
    public void reset() {
        components = null;
    }

    @Override
    public void setComponent(Component component) {
        components.computeIfAbsent(component.getComponentType(), value -> new ArrayList<>());
        components.get(component.getComponentType()).add(component);
    }

    @Override
    public List<ArrayList<Component>> getCurrentBuild() {
        return components.values().stream().toList();
    }

    @Override
    public BuildResult<Computer> getResult() {
        Motherboard motherboard = null;
        List<RAM> rams = null;
        List<HardDisk> hardDisks = null;
        List<VideoCard> videoCards = null;
        if (components.get(ComponentType.Motherboard) != null) {
            motherboard = (Motherboard) components.get(ComponentType.Motherboard).get(0);
        }
        if (components.get(ComponentType.RAM) != null) {
            rams = components.get(ComponentType.RAM).stream().map(component -> (RAM) component).toList();
        }
        if (components.get(ComponentType.HardDisk) != null) {
            hardDisks = components.get(ComponentType.HardDisk).stream().map(component -> (HardDisk) component).toList();
        }
        if (components.get(ComponentType.VideoCard) != null) {
            videoCards = components.get(ComponentType.VideoCard).stream().map(component -> (VideoCard) component).toList();
        }
        if(videoCards == null || motherboard == null || rams == null || hardDisks == null) {
            return new BuildResult<Computer>().failure(new Computer(motherboard, hardDisks, videoCards, rams));
        }

        return new BuildResult<Computer>().success(new Computer(motherboard, hardDisks, videoCards, rams));
    }
}
