CREATE OR REPLACE PROCEDURE receives_person(nId          NUMBER,
                                            cName        VARCHAR2, 
											cUsername    VARCHAR2, 
											cEmail       VARCHAR2, 
											cPhone	     VARCHAR2, 
											cWebsite     VARCHAR2,
											cStreet      VARCHAR2,
											cSuite       VARCHAR2,
											cCity        VARCHAR2,
											cZipcode     VARCHAR2,
											cLat         VARCHAR2,
											cLng         VARCHAR2,
											cCompanyName VARCHAR2,
											cCatchprase  VARCHAR2,
											cBs          VARCHAR2) IS
											 
  nIdAddress NUMBER;
  nIdCompany NUMBER;  
BEGIN  
  BEGIN 
    INSERT INTO person (IdPerson, cname, username, email, phone, website)
         VALUES (nId, cName, cUsername, cEmail, cPhone, cWebsite);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN 
      raise_application_error(-20100,'Error al insertar en tabla "Person", codigo: '||SQLCODE||' ERROR: '||SQLERRM);
  END;
  
  SELECT seq_address.NEXTVAL 
     INTO nIdAddress 
     FROM dual;
  
  BEGIN 
    INSERT INTO address_person (IdAddress, IdPerson, street, suite, city, Zipcode, lat, lng)
         VALUES (nIdAddress, nId, cStreet, cSuite, cCity, cZipcode, cLat, cLng);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN 
      raise_application_error(-20100,'Error al insertar en tabla "address_person", codigo: '||SQLCODE||' ERROR: '||SQLERRM);
  END;

  SELECT seq_company.NEXTVAL 
     INTO nIdCompany 
     FROM dual;

  BEGIN 
    INSERT INTO company_person (IdCompany, IdPerson, cName, Catchprase, Bs)
         VALUES (nIdCompany, nId, cCompanyName, cCatchprase, cBs);
	COMMIT;
  EXCEPTION
    WHEN OTHERS THEN 
      raise_application_error(-20100,'Error al insertar en tabla "company_person", codigo: '||SQLCODE||' ERROR: '||SQLERRM);
  END;  
END;