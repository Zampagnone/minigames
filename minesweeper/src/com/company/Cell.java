package com.company;

public class Cell {
    private String type;
    private boolean isCovered;
    private boolean isChecked;
    private int closeBombs;

    public Cell(){
        type = "Empty";
        isCovered = true;
        isChecked = false;
    }

    public String getType() {
        return type;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public void setCloseBombs(int closeBombs) {
        this.closeBombs = closeBombs;
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String display(){
        if(!isCovered){
            switch(type){
                case "Bomb":
                    return "B";
                case "Empty":
                    return " ";
                case "Number":
                    return "" + closeBombs;
            }
        }else{
            return ".";
        }
        return "0";
    }
}
