package com.home_project.programmer.s.cheat.sheet.model.hint;

public abstract class Hints {
    private String questName;
    private String description;

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
