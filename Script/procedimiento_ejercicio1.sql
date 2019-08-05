CREATE OR REPLACE PROCEDURE recibe_operacion(nNumA      NUMBER, 
											 nNumB      NUMBER, 
											 cOperacion VARCHAR2, 
											 nResultado NUMBER) IS
  nIdOperacion NUMBER;  
BEGIN

   SELECT seq_operacion.NEXTVAL 
     INTO nIdOperacion 
     FROM dual;
  
  BEGIN 
    INSERT INTO result_calc (idoperacion, numeroa, numerob, operacion, resultado)
         VALUES (nIdOperacion, nNumA, nNumB, cOperacion, nResultado);
  EXCEPTION
    WHEN OTHERS THEN 
      raise_application_error(-20100,'Error al insertar en tabla "result_calc", codigo: '||SQLCODE||' ERROR: '||SQLERRM);
  END;
END;