package utils;

import com.google.gson.Gson;

public class ResponseData {
    private String location;

    private String message;

    public ResponseData(String location, String message) {
        this.location = location;
        this.message = message;
    }

    public ResponseData(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
