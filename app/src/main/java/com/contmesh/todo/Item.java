package com.contmesh.todo;

public class Item {
    private String text;
    private Boolean isChecked;
    private long id;

    public Item(long id, String text, Boolean isChecked) {
        this.id = id;
        this.text = text;
        this.isChecked = isChecked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
