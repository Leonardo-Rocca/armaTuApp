package com.lrocca.examplecardiag;

import com.lrocca.examplecardiag.Step;

import java.util.ArrayList;

/**
 * Created by lrocca on 21/07/2017.
 */
public class Solution {
    private ArrayList<Step> steps;
    private String name;
    private Integer priority = -1;
    private long id =-1;

    public Solution(String name, ArrayList<Step> steps) {
        this.setSteps(steps);
        this.setName(name);
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
