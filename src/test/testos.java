/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author alcidesticlla
 */
public class testos {
    public static void main(String[] args) {
        char ch=System.getProperty("os.name").contains("Mac")?'/':'\\';
        String osName = System.getProperty("os.name");
        System.out.println(osName + " "+ ch);
    }
}
