package domain_model;

public class Superhero {

    private String name;
    private String realName;
    private String superPower;
    private int yearCreated;
    private Boolean isHuman;
    private double strength;

    public Superhero(String name, String realName, String superPower,
                     int yearCreated, Boolean isHuman, double strength) {
        this.name = name;
        this.realName = realName;
        this.superPower = superPower;
        this.yearCreated = yearCreated;
        this.isHuman = isHuman;
        this.strength = strength;
    }

    public String getName(){
        return name;
    }
    public void setName(String newName){
        this.name = newName;
    }

    public String getRealName(){
        return realName;
    }
    public void setRealName(String newRealName){
        this.realName = newRealName;
    }

    public String getSuperpower(){
        return superPower;
    }
    public void setSuperPower(String newSuperPower){
        this.superPower = newSuperPower;
    }

    public int getYearCreated(){
        return yearCreated;
    }
    public void setYearCreated(int newYearCreated){
        this.yearCreated = newYearCreated;
    }

    public Boolean getIsHuman(){
        return isHuman;
    }
    public void setIsHuman(boolean newIsHuman){
        this.isHuman = newIsHuman;
    }

    public double getStrength(){
        return strength;
    }
    public void setStrength(double newStrength){
        this.strength = newStrength;
    }

    public String toString(){
        return "name: " + name + "\n" +
                "real name: " + realName + "\n" +
                "superpower: " + superPower + "\n" +
                "year created: " + yearCreated + "\n" +
                "is human: " + isHuman + "\n" +
                "strength: " + strength;
    }
}
