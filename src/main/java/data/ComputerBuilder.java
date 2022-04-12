package data;

import data.components.*;
import utils.BuildResult;

import java.util.ArrayList;
import java.util.List;

public interface ComputerBuilder {
    void reset();
    void setComponent(Component component);
    List<ArrayList<Component>> getCurrentBuild();
    BuildResult<Computer> getResult();

}
