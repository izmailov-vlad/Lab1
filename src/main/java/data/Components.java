package data;

import data.components.*;
import data.storage.Storage;

import java.util.*;

public final class Components {
    final private Map<ComponentType, List<Component>> components;

    public Components() {
        components = new HashMap<>();
        final ArrayList<Component> motherBoards = new ArrayList<>();
        final ArrayList<Component> videoCards = new ArrayList<>();
        final ArrayList<Component> rams = new ArrayList<>();
        final ArrayList<Component> hardDisks = new ArrayList<>();

        motherBoards.add(new Motherboard("GIGABYTE H410M H V3", ComponentType.Motherboard));
        motherBoards.add(new Motherboard("MSI MAG X570S TOMAHAWK MAX WIFI", ComponentType.Motherboard));
        motherBoards.add(new Motherboard("ASUS ROG ZENITH II EXTREME ALPHA", ComponentType.Motherboard));

        components.put(ComponentType.Motherboard , motherBoards);


        hardDisks.add(new HardDisk("Seagate BarraCuda", ComponentType.HardDisk));
        hardDisks.add(new HardDisk("Toshiba X300 Performance [HDWR11AEZSTA]", ComponentType.HardDisk));
        hardDisks.add(new HardDisk("Synology HAT5300 [HAT5300-16T]", ComponentType.HardDisk));

        components.put(ComponentType.HardDisk , hardDisks);

        rams.add(new RAM("Kingston FURY Beast Black", ComponentType.RAM));
        rams.add(new RAM("A-Data XPG Lancer", ComponentType.RAM));
        rams.add(new RAM("Samsung [M393AAG40M32-CAE]", ComponentType.RAM));


        components.put(ComponentType.RAM , rams);

        videoCards.add(new VideoCard("PowerColor AMD Radeon RX 550 Red Dragon LP", ComponentType.VideoCard));
        videoCards.add(new VideoCard("Powercolor AMD Radeon RX 6600 XT Fighter [AXRX 6600XT 8GBD6-3DH]", ComponentType.VideoCard));
        videoCards.add(new VideoCard("PowerColor Red Dragon AMD Radeon RX 6800 XT [AXRX 6800XT 16GBD6-3DHR/OC]", ComponentType.VideoCard));

        components.put(ComponentType.VideoCard , videoCards);
    }

    public List<Component> getComponentsByKey(ComponentType key) {
        return components.get(key);
    }
}

