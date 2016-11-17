/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.File;

/**
 *
 * @author bruno
 */
public class Teste {

    public static void main(String[] args) {

        String path = "~/.findsmells";
        path = Teste.preProcessPath(path);
        File diretorio = new File(path);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); //mkdir() cria somente um diretório, mkdirs() cria diretórios e subdiretórios.
            System.out.println("Diretório foi criado");
        } else {
            System.out.println("Diretório já existente");
        }
        System.out.println(Teste.preProcessPath(path));

    }

    public static String preProcessPath(String path) {
        if (path.startsWith("~")) {
            String osName = System.getProperty("os.name").toLowerCase();
            String homePath = System.getProperty("user.home");
            if (osName.startsWith("windows")) {
                homePath = homePath.replace("\\", "/");
            }
            path = path.replaceFirst("^~", homePath);
        } else if (path.startsWith(".") && !path.startsWith("..")) {
            String osName = System.getProperty("os.name").toLowerCase();
            String currPath = System.getProperty("user.dir");
            if (osName.startsWith("windows")) {
                currPath = currPath.replace("\\", "/");
            }
            path = path.replaceFirst("^\\.", currPath);
        }
        return path;
    }

}
