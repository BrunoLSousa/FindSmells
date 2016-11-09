/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Cycle {

    private Integer id;
    private String name;
    private Project project;
    private Integer nodes;
    private Integer diameter;
    private List<String> pack;

    public Cycle(String name, Project project, Integer nodes, Integer diameter) {
        this.name = name;
        this.project = project;
        this.nodes = nodes;
        this.diameter = diameter;
        this.pack = new ArrayList<>();
    }

    public void addDataCycle(String pack) {
        this.pack.add(pack);
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Project getProject(){
        return this.project;
    }
    
    public Integer getNodes(){
        return this.nodes;
    }
    
    public Integer getDiameter(){
        return this.diameter;
    }

}
