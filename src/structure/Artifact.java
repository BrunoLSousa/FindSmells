/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author bruno
 */
public abstract class Artifact {
    
    private Integer id;
    private String name;
    private Project project;
    private String source;
    private String pack;
    
    public Artifact(Integer id, String name, Project project, String source, String pack){
        this.id = id;
        this.name = name;
        this.project = project;
        this.source = source;
        this.pack = pack;
    }
    
    public Artifact(String name, Project project, String source, String pack){
        this.name = name;
        this.project = project;
        this.source = source;
        this.pack = pack;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Project getProject(){
        return this.project;
    }
    
    public String getSource(){
        return this.source;
    }
    
    public String getPack(){
        return this.pack;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public abstract void updateValueMetric(String metric, Double value);
    
//    public abstract void register(Artifact artifact);
//    
//    public abstract void update(Artifact artifact);
//    
//    public abstract Artifact select(String name, String );
//    
//    public abstract Artifact selectAll();
    
}