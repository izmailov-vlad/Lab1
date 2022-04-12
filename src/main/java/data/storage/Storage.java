package data.storage;

import data.Computer;

import java.util.List;

public interface Storage {
    void save(Computer computer);
    void deleteComputer(String id);
    List<Computer> getComputers();
}
