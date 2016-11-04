/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure.dao;

/**
 *
 * @author bruno
 */
public interface DAO {
    
    public void register(Object object);
    
    public void update(Object object);
    
    public Object selectAll();
    
    public Object selectById(Integer id);
    
}
