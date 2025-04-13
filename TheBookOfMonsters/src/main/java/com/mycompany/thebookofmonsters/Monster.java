package com.mycompany.thebookofmonsters;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "monster")
@XmlAccessorType(XmlAccessType.FIELD)
public class Monster {
    private int id;
    private String name;
    private String description;
    private int dangerLevel;
    private String habitat;
    private String firstMention;
    private String immunities;
    private String activity;
    private String height;
    private String weight;
    private String recipe;
    private int time;
    private String efficiency;
    private String vulnerability;

    public String getVulnerability() {
         if (vulnerability!=null){ return vulnerability ;}
        else{ return "-";}
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

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
    
    public String getActivity() {return activity;}
    public void setActivity(String activity) {this.activity = activity;}

    public String getHeight(){
        if (height!=null){ return height ;}
        else{ return "-";}
    }
    public void setHeight(String height) {this.height = height;}

    public String getWeight() { 
        if (weight!=null){return weight ;}
        else{ return "-";}
    }
    public void setWeight(String weight) {this.weight = weight;}

    public String getRecipe() {return recipe;}
    public void setRecipe(String recipe) {this.recipe = recipe;}

    public int getTime() { return time;}
    public void setTime(int time) {this.time = time;}

    public String getEfficiency() {return efficiency;}
    public void setEfficiency(String efficiency) {this.efficiency = efficiency;}
}