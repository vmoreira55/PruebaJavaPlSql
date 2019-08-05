package testPersonRS;


import services.OperationRS;

public class ServicePersonRsTest {
    
    public static void main(String[] args) {
        OperationRS operationRS = new OperationRS();
        operationRS.savePerson();
    }
}