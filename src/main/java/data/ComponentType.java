package data;

import java.util.Arrays;

public enum ComponentType {
    Motherboard(1),
    HardDisk(2),
    VideoCard(3),
    RAM(4);
    private int serial;

    ComponentType(int serial) {
        this.serial = serial;
    }

    public static ComponentType findByValue(int value){
        for(ComponentType v : values()){
            if( v.serial == value){
                return v;
            }
        }
        return null;
    }

    public int getSerial() {
        return serial;
    }
}
