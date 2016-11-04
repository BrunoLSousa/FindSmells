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
public class DetectionStrategy {
    
    private Integer id;
    private String name;
    private String expression;

    public DetectionStrategy(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public DetectionStrategy(Integer id, String name, String expression) {
        this.id = id;
        this.name = name;
        this.expression = expression;
    }
    
    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getExpression() {
        return this.expression;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
