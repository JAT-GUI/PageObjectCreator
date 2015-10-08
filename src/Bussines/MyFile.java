/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template MyFile, choose Tools | Templates
 * and open the template in the editor.
 */

package Bussines;

/**
 *
 * @author Marco Antonio
 */
public interface MyFile {
    


    
    String getType() ;

    void setType(String type);

    public String getName() ;

    public void setName(String name); 
    public String getHeader();

    public void setHeader(String header);
    public String getBody();

    public void setBody(String body) ;
    
    String build_line(String name_property,String target, String type, boolean key, boolean button);
    

    public String getFooter() ;

    

    public void setFooter(String footer) ;

    
    
    public String absoluteName();

    
    
}
