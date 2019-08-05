
package services;

import calculatorws.servicio.CalculatorSoap;
import calculatorws.servicio.Calculator;
import domain.OperationDTO;
import datos.Connectionn;
import java.sql.*;

public class CalculatorOperations
{
    Statement stmt;
    ResultSet rset;
    CallableStatement cstmt;
    Connection con;

    public void conexion(int num1, int num2, String operation, int resultado)
            throws SQLException
    {
        try {
            this.con = Connectionn.getConnection();
            this.stmt = null;
            this.rset = null;
            this.cstmt = null;

            stmt = con.createStatement();
            cstmt = con.prepareCall("{call recibe_operacion(?,?,?,?)}");
            cstmt.setInt(1,num1);
            cstmt.setInt(2,num2);
            cstmt.setString(3,operation);
            cstmt.setInt(4,resultado);
            cstmt.execute();
            cstmt.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void saveAdd(int num1, int num2, String operation)
    { 
       CalculatorSoap serviceAdd = new Calculator().getCalculatorSoap();
       int resultado = serviceAdd.add(num1, num2);   
       OperationDTO add = new OperationDTO(num1, num2, resultado);
       try{
           conexion(num1,num2,operation,resultado);
       }catch (SQLException e){
           e.printStackTrace();
       }
       System.out.println("El resultado de la suma es: "+add.getResultado());
    }
    
    public void safeSubtract(int num1, int num2, String operation)
    { 
       CalculatorSoap serviceSubtract = new Calculator().getCalculatorSoap();
       int resultado = serviceSubtract.subtract(num1, num2);
       OperationDTO subtract = new OperationDTO(num1, num2, resultado);
        try{
            conexion(num1,num2,operation,resultado);
        }catch (SQLException e){
            e.printStackTrace();
        }
       System.out.println("El resultado de la resta es: "+subtract.getResultado());
    }
    
    public void safeMultiply(int num1, int num2, String operation)
    { 
       CalculatorSoap serviceMultiply = new Calculator().getCalculatorSoap();
       int resultado = serviceMultiply.multiply(num1, num2);
       OperationDTO multiply = new OperationDTO(num1, num2, resultado);
        try{
            conexion(num1,num2,operation,resultado);
        }catch (SQLException e){
            e.printStackTrace();
        }
       System.out.println("El resultado de la multiplicacion es: "+multiply.getResultado());
    }
    
    public void safeDivide(int num1, int num2, String operation)
    { 
       CalculatorSoap serviceDivide = new Calculator().getCalculatorSoap();
       int resultado = serviceDivide.divide(num1, num2);
       OperationDTO divide = new OperationDTO(num1, num2, resultado);
        try{
            conexion(num1,num2,operation,resultado);
        }catch (SQLException e){
            e.printStackTrace();
        }
       System.out.println("El resultado de la divida es: "+divide.getResultado());
    }
}
