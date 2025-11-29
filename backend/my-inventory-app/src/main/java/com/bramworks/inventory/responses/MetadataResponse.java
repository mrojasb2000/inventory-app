package com.bramworks.inventory.responses;

import java.util.ArrayList;
import java.util.HashMap;

public class MetadataResponse {

    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String type, String code, String data) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("code", code);
        map.put("date", data);
        this.metadata.add(map);
    }
}
