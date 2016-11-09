/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure.dao;

import structure.DetectionStrategy;
import structure.Project;

/**
 *
 * @author bruno
 */
public interface DAOMetric extends DAO{
    
    public Object selectByObject(Object object);
    
    public Object applyDetectionStrategy(DetectionStrategy detectionStrategy, Project project);
    
    public int totalArtifacts(Project project);
    
}
