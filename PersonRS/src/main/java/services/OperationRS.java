
package services;


import datos.Connectionn;
import domain.PersonDTO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.List;

public class OperationRS
{
    Statement stmt;
    ResultSet rset;
    CallableStatement cstmt;
    Connection con;

    public void guardarPersona(PersonDTO persona)
            throws SQLException
    {
        try {
            this.con = Connectionn.getConnection();
            this.stmt = null;
            this.rset = null;
            this.cstmt = null;

            stmt = con.createStatement();
            cstmt = con.prepareCall("{call receives_person(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, persona.getId());
            cstmt.setString(2, persona.getName());
            cstmt.setString(3, persona.getUsername());
            cstmt.setString(4, persona.getEmail());
            cstmt.setString(5, persona.getAddress().getStreet());
            cstmt.setString(6, persona.getAddress().getSuite());
            cstmt.setString(7, persona.getAddress().getCity());
            cstmt.setString(8,persona.getAddress().getZipcode());
            cstmt.setString(9,persona.getAddress().getGeo().getLat());
            cstmt.setString(10,persona.getAddress().getGeo().getLng());
            cstmt.setString(11,persona.getPhone());
            cstmt.setString(12,persona.getWebsite());
            cstmt.setString(13,persona.getCompany().getName());
            cstmt.setString(14,persona.getCompany().getCatchPhrase());
            cstmt.setString(15,persona.getCompany().getBs());
            cstmt.execute();
            cstmt.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




    private static void imprimirPersonas(List<PersonDTO> personas){
        for(PersonDTO person: personas){
            System.out.println("Persona:" + person);
        }
    }
    public void savePerson()
    {
        final String URL_BASE = "https://jsonplaceholder.typicode.com";
        Client client;
        WebTarget webTarget;
        List<PersonDTO> personas;
        Invocation.Builder invocationBuilder;
        Response response;
        client = ClientBuilder.newClient();

        webTarget = client.target(URL_BASE).path("/users");
        //Leer todas las personas (metodo get con readEntity de tipo List<>)
        personas = webTarget.request(MediaType.APPLICATION_JSON).get(Response .class).readEntity(new GenericType<List<PersonDTO>>(){});
        System.out.println("\nPersonas recuperadas:");

        try{
           for(PersonDTO person: personas){
               guardarPersona(person);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
        imprimirPersonas(personas);
    }

}
