package json;

import java.util.*;

public class SimpleToken {
    private String content;
    private Map<String, JSONValue> data = new HashMap<>();

    public SimpleToken(String content) {
        this.content = content;
    }

    public void parse() {
        String[] array = content.split("(?<=\"),(?=\")");
        for (String kv : array) {
            String[] entry = kv.split("(?<=\"):(?=\")");
            if (entry.length > 1) {
                if(entry[1].matches("^\\[.*\\]$")){
                    JSONValue<List<String>> value = new JSONValue<>();
                    entry[1] = entry[1].replaceAll("\\[|\\]", "");
                    String[] listArray  = entry[1].split(",");
                    List<String> jsondata = Arrays.asList(listArray);
                    value.setValue(jsondata);
                    data.put(entry[0], value);
                }else{
                    JSONValue<String> jsonValue = new JSONValue<>();
                    jsonValue.setValue(entry[1]);
                    data.put(entry[0], jsonValue);
                }
            }
        }
    }

    public String toString(){
        return data.toString();
    }

    public Map<String, JSONValue> getData() {
        return data;
    }

    public String toJSONString(){
        return toString().replaceAll("=", ":");
    }

    public <T> T getValue(String key){
        JSONValue value = data.get(key);
        if(value.getValue() != null){
            return (T) value.getValue();
        }
        return null;
    }
}

