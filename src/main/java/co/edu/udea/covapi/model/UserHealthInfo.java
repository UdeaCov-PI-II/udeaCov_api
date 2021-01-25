package co.edu.udea.covapi.model;

import java.util.List;

public class UserHealthInfo extends FirebaseModel{

    private float weight;
    private float height;
    private boolean hasHighBloodPressure;
    private boolean hasHeartDisease;
    private boolean hasHighCholesterol;
    private boolean haskidneyDisease;
    private boolean hasDiabetes;
    private boolean hasEPOC;
    private boolean hasAsthma;
    private boolean hasAlterationImmunityDisease;
    private boolean hasCancer;
    private boolean useOralSteroids;
    private boolean hasHepaticDisease;
    private boolean hasObesity;
    private boolean isSmoker;
    private boolean hasStrangeDisease;
    private boolean isPregnant;
    private boolean hasChildBirthRecently;
    private String hasPermanentDisability;
    private String bloodType;
    private boolean hasRoomates;
    private List<String> roomatesConditions;

    public UserHealthInfo() {
        // required for firestore
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isHasHighBloodPressure() {
        return hasHighBloodPressure;
    }

    public void setHasHighBloodPressure(boolean hasHighBloodPressure) {
        this.hasHighBloodPressure = hasHighBloodPressure;
    }

    public boolean isHasHeartDisease() {
        return hasHeartDisease;
    }

    public void setHasHeartDisease(boolean hasHeartDisease) {
        this.hasHeartDisease = hasHeartDisease;
    }

    public boolean isHasHighCholesterol() {
        return hasHighCholesterol;
    }

    public void setHasHighCholesterol(boolean hasHighCholesterol) {
        this.hasHighCholesterol = hasHighCholesterol;
    }

    public boolean isHaskidneyDisease() {
        return haskidneyDisease;
    }

    public void setHaskidneyDisease(boolean haskidneyDisease) {
        this.haskidneyDisease = haskidneyDisease;
    }

    public boolean isHasDiabetes() {
        return hasDiabetes;
    }

    public void setHasDiabetes(boolean hasDiabetes) {
        this.hasDiabetes = hasDiabetes;
    }

    public boolean isHasEPOC() {
        return hasEPOC;
    }

    public void setHasEPOC(boolean hasEPOC) {
        this.hasEPOC = hasEPOC;
    }

    public boolean isHasAsthma() {
        return hasAsthma;
    }

    public void setHasAsthma(boolean hasAsthma) {
        this.hasAsthma = hasAsthma;
    }

    public boolean isHasAlterationImmunityDisease() {
        return hasAlterationImmunityDisease;
    }

    public void setHasAlterationImmunityDisease(boolean hasAlterationImmunityDisease) {
        this.hasAlterationImmunityDisease = hasAlterationImmunityDisease;
    }

    public boolean isHasCancer() {
        return hasCancer;
    }

    public void setHasCancer(boolean hasCancer) {
        this.hasCancer = hasCancer;
    }

    public boolean isUseOralSteroids() {
        return useOralSteroids;
    }

    public void setUseOralSteroids(boolean useOralSteroids) {
        this.useOralSteroids = useOralSteroids;
    }

    public boolean isHasHepaticDisease() {
        return hasHepaticDisease;
    }

    public void setHasHepaticDisease(boolean hasHepaticDisease) {
        this.hasHepaticDisease = hasHepaticDisease;
    }

    public boolean isHasObesity() {
        return hasObesity;
    }

    public void setHasObesity(boolean hasObesity) {
        this.hasObesity = hasObesity;
    }

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    public boolean isHasStrangeDisease() {
        return hasStrangeDisease;
    }

    public void setHasStrangeDisease(boolean hasStrangeDisease) {
        this.hasStrangeDisease = hasStrangeDisease;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public boolean isHasChildBirthRecently() {
        return hasChildBirthRecently;
    }

    public void setHasChildBirthRecently(boolean hasChildBirthRecently) {
        this.hasChildBirthRecently = hasChildBirthRecently;
    }

    public String getHasPermanentDisability() {
        return hasPermanentDisability;
    }

    public void setHasPermanentDisability(String hasPermanentDisability) {
        this.hasPermanentDisability = hasPermanentDisability;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public boolean isHasRoomates() {
        return hasRoomates;
    }

    public void setHasRoomates(boolean hasRoomates) {
        this.hasRoomates = hasRoomates;
    }

    public List<String> getRoomatesConditions() {
        return roomatesConditions;
    }

    public void setRoomatesConditions(List<String> roomatesConditions) {
        this.roomatesConditions = roomatesConditions;
    }
}
