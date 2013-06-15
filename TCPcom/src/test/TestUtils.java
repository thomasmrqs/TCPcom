/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connexion.Utils;

/**
 *
 * @author greg
 */
public class TestUtils {
    public static void main(String[] args){
        String t = "1";
        System.out.println(t.getBytes());
        System.out.println(Utils.complement(t));
    }
}
