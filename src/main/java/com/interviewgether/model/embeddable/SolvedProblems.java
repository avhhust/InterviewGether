package com.interviewgether.model.embeddable;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class SolvedProblems {

    private int numOfEasy;
    private int numOfMedium;
    private int numOfHard;

    public SolvedProblems() {}

    public int getNumOfEasy() {
        return numOfEasy;
    }

    public void setNumOfEasy(int numOfEasy) {
        this.numOfEasy = numOfEasy;
    }

    public int getNumOfMedium() {
        return numOfMedium;
    }

    public void setNumOfMedium(int numOfMedium) {
        this.numOfMedium = numOfMedium;
    }

    public int getNumOfHard() {
        return numOfHard;
    }

    public void setNumOfHard(int numOfHard) {
        this.numOfHard = numOfHard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolvedProblems that = (SolvedProblems) o;
        return numOfEasy == that.numOfEasy && numOfMedium == that.numOfMedium && numOfHard == that.numOfHard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfEasy, numOfMedium, numOfHard);
    }

    @Override
    public String toString() {
        return "SolvedProblems{" +
                "numOfEasy=" + numOfEasy +
                ", numOfMedium=" + numOfMedium +
                ", numOfHard=" + numOfHard +
                '}';
    }
}
