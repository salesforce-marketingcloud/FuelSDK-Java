/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.ExtractDescription;
import java.util.List;

@SoapObject(internalType = ExtractDescription.class)
public class ETExtractDescription extends ETSoapObject{

//    @ExternalName("")
//    private String customerKey;
    @ExternalName("id")
    @InternalName("objectID")
    private String id = null;    

    @ExternalName("name")
    private String name;
//    @ExternalName("configurationPage")
//    protected String configurationPage;
//    @ExternalName("packageKey")
//    protected String packageKey;
    
    @ExternalName("parameters")
    private ExtractDescription.Parameters parameters;

    /**
     * @return the parameters
     */
    public ExtractDescription.Parameters getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(ExtractDescription.Parameters parameters) {
        this.parameters = parameters;
    }
    
    public static void main( String[] args ){
        try {
            System.out.println("hello world");
            ETClient client = new ETClient("fuelsdk.properties");
            
            ETResponse<ETExtractDescription> response = client.retrieve(ETExtractDescription.class);
            //System.out.println("resp="+ response.toString());  
            
            //List<ETResult<ETExtractDescription>> result = response.getResults();// client.retrieve(ETExtractDescription.class);
            for(ETResult<ETExtractDescription> r : response.getResults()){
                System.out.print("ID="+ r.getObject().getId());  
                System.out.println(", Name="+ r.getObject().getName());  
            }
            
        } catch (ETSdkException ex) {
            ex.printStackTrace();
        }
    }    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
