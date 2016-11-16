/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

/**
 *
 * @author bruno
 */
public class Teste {

    public static void main(String[] args) {
//        String text = "asd6sd7asd7ds";
//        //Se quiser separador decimal tb
//        if (text.matches("^[0-9]*[.]{0,1}[0-9]*$")) {
//            
//        }
//        String numero = "sd6.57dsf765sdaf756";
//        numero = numero.replaceAll("[0-9]*[.]{0,1}[0-9]+", "");
//        numero = numero.replaceAll("^[0-9[.]]+[0-9]+", "");
//        System.out.println(numero);

//        String qualquerCoisa = " kknd_ 1Felipe _MMA12 1354 50.100";
        String expression = "(nsc <= 1 AND dit <= 2 AND nof > 3)";
//
//        Matcher matcher = Pattern.compile("[0-9]+[.]{0,1}[0-9]+").matcher(qualquerCoisa);
//        Matcher matcher = Pattern.compile("[0-9]+(\\.[0-9]+)?").matcher(qualquerCoisa);
//////        matcher.find();
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }
        
        String validator = "0987654321";
        String exp = expression;
        exp = exp.replaceAll("[0-9]+", "VALUE");
        exp = exp.replaceFirst("VALUE+", "300");
        exp = exp.replaceFirst("VALUE+", "200");
        exp = exp.replaceFirst("VALUE+", "100");
//        for (int index = 0; index < (split.length - 1); index++) {
//            if (!this.fieldsMetrics[index].getText().isEmpty()) {
//                if (validateNumber(this.fieldsMetrics[index].getText())) {
//                    exp = exp.replaceFirst("VALUE", this.fieldsMetrics[index].getText());
//                } else {
//                    JOptionPane.showMessageDialog(this, "The field of metric " + this.metrics[index].toString() + " has a value invalid. is empty. Insert a value valid!", "Attention", JOptionPane.WARNING_MESSAGE, null);
//                    this.fieldsMetrics[index].grabFocus();
//                    return false;
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "The field of metric " + this.metrics[index].toString() + " is empty. Insert a value in this field!", "Attention", JOptionPane.WARNING_MESSAGE, null);
//                this.fieldsMetrics[index].grabFocus();
//                return false;
//            }
//        }
        expression = exp;

    }

}
