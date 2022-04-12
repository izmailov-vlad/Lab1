package data.storage;

import com.google.gson.Gson;
import data.Computer;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class StorageImpl implements Storage {
    private final Gson gson = new Gson();
    Preferences prefs = Preferences.userRoot().node("storage");

    @Override
    public void save(Computer computer) {
        String computerJson = gson.toJson(computer);
        prefs.put(computer.getId(), computerJson);
    }

    @Override
    public void deleteComputer(String id) {
        prefs.remove(id);
    }

    @Override
    public List<Computer> getComputers() {
        ArrayList<Computer> computers = new ArrayList<>();
        try {
            final String[] keys = prefs.keys();
            for (String key : keys) {
                String jsonComputer = prefs.get(key, "");
                Computer computer = gson.fromJson(jsonComputer, Computer.class);
                computers.add(computer);
            }

        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        return computers;
    }
}
