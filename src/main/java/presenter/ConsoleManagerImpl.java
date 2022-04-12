package presenter;

import data.*;
import data.components.Component;
import data.storage.Storage;
import data.storage.StorageImpl;
import utils.BuildResult;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleManagerImpl implements ConsoleManager {
    final private Components components = new Components();
    final private Storage storage = new StorageImpl();

    final private Scanner in = new Scanner(System.in);
    private String action;
    final private ComputerBuilder computerBuilder = new ComputerBuilderImpl();

    private void showAvailableComponents() {
        final List<Component> motherboards = components.getComponentsByKey(ComponentType.Motherboard);
        final List<Component> videoCards = components.getComponentsByKey(ComponentType.VideoCard);
        final List<Component> rams = components.getComponentsByKey(ComponentType.RAM);
        final List<Component> hardDrives = components.getComponentsByKey(ComponentType.HardDisk);

        System.out.println("motherboards: ");

        motherboards.forEach((motherboard) -> {
            System.out.println("              " + motherboard.getName());
        });

        System.out.println("hard disks: ");

        hardDrives.forEach((hardDrive) -> {
            System.out.println("              " + hardDrive.getName());
        });

        System.out.println("video cards: ");

        videoCards.forEach((videoCard) -> {
            System.out.println("              " + videoCard.getName());
        });

        System.out.println("rams: ");

        rams.forEach((ram) -> {
            System.out.println("              " + ram.getName());
        });
    }

    ;

    private void showActionsDocumentation() {
        System.out.println("----------------------------------");
        System.out.println(Constants.Commands.delete + " - " + Constants.deleteSavedComputer);
        System.out.println(Constants.Commands.save + " - " + Constants.saveComputer);
        System.out.println(Constants.Commands.showCurrent + " - " + Constants.saveComputer);
        System.out.println(Constants.Commands.add + " " + Constants.addComponent);
        System.out.println(Constants.Commands.exit + " - " + Constants.exit);
        System.out.println(Constants.Commands.showPrevious + " - " + Constants.showPreviousComputers);
        System.out.println("----------------------------------");
    }

    private List<Component> showAvailableProductsOfSelector(ComponentType componentType) {
        final List<Component> componentsOfProduct = components.getComponentsByKey(componentType);
        for (int i = 0; i < componentsOfProduct.size(); i++) {
            System.out.println(i + ": " + componentsOfProduct.get(i).getName());
        }
        return componentsOfProduct;
    }

    private void actionRequest() {
        System.out.print("action: ");
    }

    private void indexRequest() {
        System.out.print("index: ");
    }

    private void showComponentSelector() {
        System.out.println("1. Motherboard");
        System.out.println("2. Hard Disk");
        System.out.println("3. Video Card");
        System.out.println("4. RAM");
    }

    private void showSavedComputers(List<Computer> computers) {
        List<Component> hardDisks = null;
        List<Component> rams = null;
        List<Component> videoCards = null;

        for (int i = 0; i < computers.size(); i++) {

            System.out.println(i + ":");
            System.out.println("  " + computers.get(i).getId());

            if (computers.get(i).getHardDisk() != null) {
                hardDisks = computers.get(i).getHardDisk().stream().map(hardDisk -> (Component) hardDisk).toList();
            }
            if (computers.get(i).getRam() != null) {
                rams = computers.get(i).getRam().stream().map(ram -> (Component) ram).toList();
            }
            if (computers.get(i).getVideoCard() != null) {
                videoCards = computers.get(i).getVideoCard().stream().map(videoCard -> (Component) videoCard).toList();
            }
            if (computers.get(i).getMotherboard() != null) {
                System.out.println("  " + computers.get(i).getMotherboard().getName());
            }

            showComponents(hardDisks);
            showComponents(rams);
            showComponents(videoCards);
        }
    }

    private void selectComponent(ComponentType componentType) {
        final List<Component> availableProductsOfSelector = showAvailableProductsOfSelector(componentType);
        indexRequest();
        action = in.next();
        final int indexOfComponent = Integer.parseInt(action);
        computerBuilder.setComponent(availableProductsOfSelector.get(indexOfComponent));
    }

    private void showComponents(List<Component> components) {
        if (components == null) return;
        components.forEach(componentByType -> System.out.print("  " + componentByType.getName() + "; "));
        System.out.println();
    }

    @Override
    public void run() {
        showAvailableComponents();
        showActionsDocumentation();
        System.out.println("Новый компьютер");
        actionRequest();
        action = in.next();

        while (!action.equals(Actions.EXIT.getValue())) {

            if (action.equals(Constants.Commands.add)) {
                try {
                    showComponentSelector();
                    action = in.next();
                    int actionInt = Integer.parseInt(action);
                    selectComponent(ComponentType.findByValue(actionInt));
                } catch (Exception exception) {
                    System.out.println("Wrong index");
                }

            }
            if (action.equals(Constants.Commands.showCurrent)) {
                final List<ArrayList<Component>> currentBuild = computerBuilder.getCurrentBuild();
                currentBuild.forEach(this::showComponents);
            }
            if (action.equals(Constants.Commands.delete)) {
                final List<Computer> computers = storage.getComputers();
                System.out.println("Saved computers");
                showSavedComputers(computers);
                indexRequest();
                action = in.next();
                int computerIndex = Integer.parseInt(action);
                storage.deleteComputer(computers.get(computerIndex).getId());
            }
            if (action.equals(Constants.Commands.save)) {
                final BuildResult<Computer> buildedComputer = computerBuilder.getResult();
                if (buildedComputer.isSuccess) {
                    storage.save(computerBuilder.getResult().getData());
                    computerBuilder.reset();
                } else {
                    System.out.println("Not finished build");
                }

            }
            if (action.equals(Constants.Commands.showPrevious)) {
                final List<Computer> computers = storage.getComputers();
                System.out.println("Saved computers");
                showSavedComputers(computers);
            }
            actionRequest();
            action = in.next();
        }
    }


}

enum Actions {
    CREATE_COMPUTER("create"), SAVE_COMPUTER("save"), EXIT("exit"), SHOW_PREV("show_prev");
    private final String action;

    Actions(String action) {
        this.action = action;
    }

    public String getValue() {
        return action;
    }
}

