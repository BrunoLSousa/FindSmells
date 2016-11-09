/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metrics.MetricMethod;
import metrics.MetricPackage;
import metrics.MetricType;

/**
 *
 * @author bruno
 */
public class Evaluator {

    private Map<EnumOperator, Operator> grammar;
    private List<EnumOperator> expression;
    private int amountOpenParenthesis;
    private int amountCloseParenthesis;

    public Evaluator() {
        createGrammar();
        this.expression = new ArrayList<>();
        this.amountOpenParenthesis = 0;
        this.amountCloseParenthesis = 0;
    }

    private void createGrammar() {
        this.grammar = new HashMap<>();
        createExpression();
        createOpenParenthesis();
        createCloseParenthesis();
        createLogicOperator();
    }

    private void createExpression() {
        Operator operator = new Operator();
        operator.addChild(EnumOperator.CLOSE_PARENTHESIS);
        operator.addChild(EnumOperator.LOGIC_OPERATOR);
        this.grammar.put(EnumOperator.EXPRESSION, operator);
    }

    private void createOpenParenthesis() {
        Operator operator = new Operator();
        operator.addChild(EnumOperator.OPEN_PARENTHESIS);
        operator.addChild(EnumOperator.EXPRESSION);
        this.grammar.put(EnumOperator.OPEN_PARENTHESIS, operator);
    }

    private void createCloseParenthesis() {
        Operator operator = new Operator();
        operator.addChild(EnumOperator.CLOSE_PARENTHESIS);
        operator.addChild(EnumOperator.LOGIC_OPERATOR);
        this.grammar.put(EnumOperator.CLOSE_PARENTHESIS, operator);
    }

    private void createLogicOperator() {
        Operator operator = new Operator();
        operator.addChild(EnumOperator.OPEN_PARENTHESIS);
        operator.addChild(EnumOperator.EXPRESSION);
        this.grammar.put(EnumOperator.LOGIC_OPERATOR, operator);
    }

    public boolean evaluate(String expression) {
        if (convert(expression)) {
            return validate();
        }
        return false;
    }

    private boolean convert(String expression) {
        String[] separate = expression.split(" ");
        for (String part : separate) {
            if (part.contains("<=") || part.contains(">=") || part.contains("<") || part.contains(">") || part.contains("=")) {
                if (!convertExpression(part)) {
                    return false;
                } else {
                    this.expression.add(EnumOperator.EXPRESSION);
                }
            } else if (part.equals("(")) {
                this.expression.add(EnumOperator.OPEN_PARENTHESIS);
            } else if (part.equals(")")) {
                this.expression.add(EnumOperator.CLOSE_PARENTHESIS);
            } else if (part.equals("AND") || part.equals("OR")) {
                this.expression.add(EnumOperator.LOGIC_OPERATOR);
            }else {
                return false;
            }
        }
        return true;
    }

    private boolean convertExpression(String expression) {
        try {
            String[] separate = null;// = expression.split(" ");
            if (expression.contains("<=")) {
                separate = expression.split("<=");
            } else if (expression.contains(">=")) {
                separate = expression.split(">=");
            } else if (expression.contains("<")) {
                separate = expression.split("<");
            } else if (expression.contains(">")) {
                separate = expression.split(">");
            } else if (expression.contains("=")) {
                separate = expression.split("=");
            }else {
                return false;
            }
            return ((MetricMethod.contains(separate[0]) || MetricPackage.contains(separate[0]) || MetricType.contains(separate[0])) && separate[1].equals("VALUE"));
        } catch (Exception e) {
            System.err.println("Error in the creation of expression!");
            return false;
        }
    }

    private boolean validate() {
        if (this.expression.get(0).equals(EnumOperator.OPEN_PARENTHESIS) || this.expression.get(0).equals(EnumOperator.EXPRESSION)) {
            for (int i = 0; i < (this.expression.size() - 1); i++) {
                if (!this.grammar.get(this.expression.get(i)).validate(this.expression.get(i + 1))) {
                    return false;
                }
                verifyParethesis(this.expression.get(i));
            }
            verifyParethesis(this.expression.get(this.expression.size() - 1));
            return (this.amountOpenParenthesis == this.amountCloseParenthesis);
        } else {
            return false;
        }
    }

    private void verifyParethesis(EnumOperator operator) {
        if(operator.equals(EnumOperator.OPEN_PARENTHESIS)) {
            this.amountOpenParenthesis = (this.amountOpenParenthesis >= this.amountCloseParenthesis) ? this.amountOpenParenthesis + 1 : 1000;
        }else if(operator.equals(EnumOperator.CLOSE_PARENTHESIS)) {
            this.amountCloseParenthesis = (this.amountCloseParenthesis <= this.amountOpenParenthesis) ? this.amountCloseParenthesis + 1 : -1000;
        }
    }

}
