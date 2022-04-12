package data;

import data.components.HardDisk;
import data.components.Motherboard;
import data.components.RAM;
import data.components.VideoCard;

import java.util.List;
import java.util.UUID;

public class Computer {
    private String id;
    private Motherboard motherboard;
    private List<HardDisk> hardDisk;
    private List<VideoCard> videoCard;
    private List<RAM> ram;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<HardDisk> getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(List<HardDisk> hardDisk) {
        this.hardDisk = hardDisk;
    }

    public List<VideoCard> getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(List<VideoCard> videoCard) {
        this.videoCard = videoCard;
    }

    public List<RAM> getRam() {
        return ram;
    }

    public void setRam(List<RAM> ram) {
        this.ram = ram;
    }

    public Computer(Motherboard motherboard, List<HardDisk> hardDisk, List<VideoCard> videoCard, List<RAM> ram) {
        this.id = UUID.randomUUID().toString();
        this.motherboard = motherboard;
        this.hardDisk = hardDisk;
        this.videoCard = videoCard;
        this.ram = ram;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }


}
