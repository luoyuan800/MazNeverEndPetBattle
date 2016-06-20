package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleToken {
    private String content;
    private Map<String, JSONValue> data = new HashMap<>();

    public SimpleToken(String content) {
        this.content = content;
    }

    public SimpleToken() {

    }

    public void parse() {
        String[] array = content.split(",(?=\")");
        for (String kv : array) {
            String[] entry = kv.split("(?<=\"):");
            if (entry.length > 1) {
                if (entry[1].matches("^\\[.*\\]$")) {
                    JSONValue<List<JSONValue<String>>> value = new JSONValue<>();
                    entry[1] = entry[1].replaceAll("\\[|\\]", "");
                    String[] listArray = entry[1].split(",");
                    List<JSONValue<String>> jsondata = new ArrayList<>();
                    for (String s : listArray) {
                        JSONValue<String> value1 = new JSONValue<>();
                        value1.setValue(s.replaceAll("\"", ""));
                        jsondata.add(value1);
                    }
                    value.setValue(jsondata);
                    data.put(entry[0].replaceAll("\"", ""), value);
                } else {
                    JSONValue<String> jsonValue = new JSONValue<>();
                    jsonValue.setValue(entry[1].replaceAll("\"", ""));
                    data.put(entry[0].replaceAll("\"", ""), jsonValue);
                }
            }
        }
    }

    public String toString() {
        return data.toString();
    }

    public Map<String, JSONValue> getData() {
        return data;
    }

    public String toJSONString() {
        StringBuilder builder = new StringBuilder("{");
        int i = 0;
        for (Map.Entry<String, JSONValue> entry : data.entrySet()) {
            builder.append("\"").append(entry.getKey()).append("\"").append(":").append(entry.getValue().toString());
            if (i < data.size() - 1) {
                builder.append(",");
            }
            i++;
        }
        builder.append("}");
        return builder.toString();
    }

    public <T> T getValue(String key) {
        JSONValue value = data.get(key);
        if (value != null && value.getValue() != null) {
            return (T) value.getValue();
        }
        return null;
    }

    public <T> void setValue(String key, T value) {
        JSONValue<T> jsonValue = new JSONValue<>();
        jsonValue.setValue(value);
        this.data.put(key, jsonValue);
    }
}

