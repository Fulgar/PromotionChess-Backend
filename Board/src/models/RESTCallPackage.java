package models;

import java.io.Serializable;

// Container Class for the JSON data received from POST request to server
public class RESTCallPackage implements Serializable
{

    private String fenString;
    private Character aiColor;
    private Integer depth;
    private String orientation;

    public RESTCallPackage() {

    }

    public RESTCallPackage(String fenString, Character aiColor, Integer depth, String orientation) {
        this.fenString = fenString;
        this.aiColor = aiColor;
        this.depth = depth;
        this.orientation = orientation;
    }

    public String getFenString() {
        return fenString;
    }

    public void setFenString(String fenString) {
        this.fenString = fenString;
    }

    public Character getAiColor() {
        return aiColor;
    }

    public void setAiColor(Character aiColor) {
        this.aiColor = aiColor;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String toString() {
        String str = "fenString: " + getFenString() + "\n";
        str += "AI Color: " + getAiColor() + "\n";
        str += "Depth: " + getDepth() + "\n";
        str += "Orientation: " + getOrientation();
        return str;
    }
}
