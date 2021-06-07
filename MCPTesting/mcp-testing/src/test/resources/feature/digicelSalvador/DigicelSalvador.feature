@executeThis
Feature: Pruebas de integracion con Digicel Salvador
  
  Scenario: Alta suscripto - exito
    Given un usuario con el telefono '541134890851'
	When envia un sms con la palabra 'ALTA' al '12312'
    Then el resultado debe ser '200'
   # And numero de telefono '541134890851' debe estar dado de alta en el servicio 'ClubWisdom'
   # And se debe recibir el mensaje 'El alta se realizo correctamente'

  Scenario: Envio mensaje a telco
    Given un usuario con el telefono '50377726413'
    When envia un sms con la siguiente data '5437', "testOpticomElSalvador", "SMS", '5'
    Then el estatus debe ser "INSUFFICIENT_FUNDS"

#  http://services.newcomwi.com/mt/http/run?
#  username=opticom
#  &password=OpticP8SH123
#  &sender=5437
#  &recipient=50377726413
#  &messageType=SMS
#  &message=testOpticomElSalvador
#  &charge=5

#  Scenario: Alta suscripto - ya dado de alta
#    Given un usuario con el telefono '541134890851'
#	When envia un sms con la palabra 'ALTA' al numero '7676'
#    Then se espera el mensaje 'el suscripto ya estaba dado de alta'
#
#  Scenario: Alta suscripto - error en la suscripcion
#   	Given un usuario con el telefono '54111111111'
#	When envia un sms con la palabra 'ALTA' al numero '7676'
#    Then numero de telefono '541134890851' no debe estar dado de alta en el servicio 'ClubWisdom'
#    And se debe recibir el mensaje 'no se pudo realizar el alta'
#
#  Scenario: Cobro - exito
#    Given un suscripto dado de alta con el telefono '541134890851'
#	When se realiza una solicitud de cobro
#    Then el suscripto con el numero de telefono '541134890851' debe registrar un cobro para el servicio 'ClubWisdom'
#     And se debe recibir el mensaje 'El cobro se realizo correctamente'
#
#  Scenario: Cobro - no se encuentra suscripto
#     Given un suscripto que no esta dado de alta con el telefono '54111111111'
#	When se realiza una solicitud de cobro
#    Then se debe recibir el mensaje 'No se pudo registrar el cobro porque el numero de telefono no esta dado de alta'
#
#  Scenario: Cobro - error de cobro
#    Given un suscripto dado de alta con el telefono '541134890851'
#	When se realiza una solicitud de cobro
#    Then numero de telefono '541134890851' no debe registrar un cobro para el servicio 'ClubWisdom'
#     And se debe recibir el mensaje 'No se pudo realizar el cobro'
#
#
#  Scenario: Baja suscripto - exito
#    Given un suscripto dado de alta con el telefono '541134890851'
#	When envia un sms con la palabra 'BAJA' al numero '7676'
#    Then numero de telefono '541134890851' debe estar dado de baja en el servicio 'ClubWisdom'
#     And se debe recibir el mensaje 'El suscripto fue dado de baja exitosamente'
#
#  Scenario: Baja suscripto - no esta suscripto al servicio
#    Given un suscripto no dado de alta con el telefono '54111111111'
#	When envia un sms con la palabra 'BAJA' al numero '7676'
#    Then debe recibir el mensaje 'el suscripto no se encontraba dado de alta al servicio'
#
#  Scenario: Baja suscripto - error en la baja
#    Given un suscripto dado de alta con el telefono '541134890851'
#	When envia un sms con la palabra 'BAJA' al numero '7676'
#    Then numero de telefono '541134890851' no debe estar dado de baja en el servicio 'ClubWisdom'
#    	And se debe recibir el mensaje 'no se pudo realizar la baja'
  
    


    
    
    
    

    

