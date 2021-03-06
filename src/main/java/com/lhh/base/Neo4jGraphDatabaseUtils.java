package com.lhh.base;

import com.sun.jersey.api.client.ClientResponse;  
import com.sun.jersey.api.client.WebResource;  
import com.sun.jersey.api.client.Client;  
import java.net.URI;  
import javax.ws.rs.core.MediaType; 


public class Neo4jGraphDatabaseUtils {
	
	/*private WebResource getWebResource(){
		
	}*/
	

    public static void main(String args[]){  
    	Neo4jGraphDatabaseUtils lr = new Neo4jGraphDatabaseUtils();  
        URI firstNode = lr.createNode();  
        lr.addProperty( firstNode, "name", "Joe Strummer" );  
        /*URI secondNode = lr.createNode();  
        lr.addProperty( secondNode, "band", "The Clash" );  */
  
    }  
  
  
    public URI createNode(){  
       String SERVER_ROOT_URI = "http://61.152.154.22:7474/db/data/";  
       final String nodeEntryPointUri = SERVER_ROOT_URI + "node";  
       WebResource resource = Client.create().resource(nodeEntryPointUri);  
  
       ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )  
               .type(MediaType.APPLICATION_JSON)  
               .entity("{}")  
               .post(ClientResponse.class);  
       final URI location = response.getLocation();  
       System.out.println( String.format("POST to [%s], status code [%d], location header [%s]",   nodeEntryPointUri, response.getStatus(), location.toString() ) );  
       response.close();  
       return location;  
  
   }  
  
    public void addProperty(URI nodeUri,String propertyName, String  propertyValue){  
        String propertyUri = nodeUri.toString() + "/properties/" + propertyName;  
        WebResource resource = Client.create()  .resource( propertyUri );  
        ClientResponse response = resource.accept( MediaType.APPLICATION_JSON )  
                .type( MediaType.APPLICATION_JSON )  
                .entity( "\"" + propertyValue + "\"" )  
                .put( ClientResponse.class );  
        System.out.println( String.format( "PUT to [%s], status code [%d]",  
                propertyUri, response.getStatus() ) );  
        response.close();  
  
    }  
}
