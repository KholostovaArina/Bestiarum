package com.mycompany.thebookofmonsters;

public class Monster {
    private String name;
    private String description;
    private int dangerLevel;
    private String habitat;
    private String firstMention;
    private String immunities;
    private int height;
    private int weight;
    private String recipe;
    private  int time;
    private String efficiency;

    public Monster(String name, String description, int dangerLevel, String habitat, 
                  String firstMention, String immunities, int height, int weight,
                  String recipe, int time, String efficiency) {
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.habitat = habitat;
        this.firstMention = firstMention;
        this.immunities = immunities;
        this.height = height;
        this.weight=weight;
        this.recipe = recipe;
        this.efficiency = efficiency;

    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public int getDangerLevel() {return dangerLevel;}
    public void setDangerLevel(int dangerLevel) {this.dangerLevel = dangerLevel;}

    public String getHabitat() {return habitat;}
    public void setHabitat(String habitat) {this.habitat = habitat;}

    public String getFirstMention() { return firstMention;}
    public void setFirstMention(String firstMention) {this.firstMention = firstMention; }

    public String getImmunities() {return immunities;}
    public void setImmunities(String immunities) {this.immunities = immunities;}

    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    public int getWeight() { return weight;}
    public void setWeight(int weight) {this.weight = weight;}

    public String getRecipe() {return recipe;}
    public void setRecipe(String recipe) {this.recipe = recipe;}

    public int getTime() { return time;}
    public void setTime(int time) {this.time = time;}

    public String getEfficiency() {return efficiency;}
    public void setEfficiency(String efficiency) {this.efficiency = efficiency;}
    
}
