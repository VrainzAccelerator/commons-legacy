@executeThis
Feature: Pruebas de pegadas a Frontera2
  
  Scenario: Pegada de alta_suscripto a Frontera2 exitosa
    Given the frontera2 message "<order><auth><usuario>TMS</usuario><password>TMS123</password></auth><service><provision>mcpgateway</provision><operacion>alta_suscripto</operacion></service><options/><parameters><palabra>TRIVIASF4RSUSCRIPCION</palabra><ani>541134890851</ani><mt>7979</mt><telco>ar.personal_it</telco></parameters></order>"     
	When I make the post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200
    	And frontera2 return message contains one of
    		|Se dio de alta el suscripto exitosamente|
    		|El suscripto ya se encuentra dado de alta|
    

  Scenario Outline: Pegada exitosa de baja_suscripto a Frontera2 con suscripciones a palabra padre 
    Given the suscriptions with this data <ani>, <palabra>, <usuario>, <pass>, <mt>, <telco>      
	When I make the post to this frontera2 url "http://192.168.10.6/service.php" with <usuario>, <pass>, <servicio>, <ani>, <mt>, <telco> 
    Then frontera2 must return response code 200
        And frontera2 return message contains "Se dio de baja el suscripto exitosamente"

Examples:            		
    | usuario | pass         | servicio                  | palabra |ani         | mt     | telco          |
    | TMS     | TMS123       | PersonalKids              | ALTA    | 1112345678 | 5437   | ar.personal_it |   
    | TMS     | TMS123       | PersonalWisdom            | ALTA    | 1112345678 | 99913  | ar.personal_it |
    | TFL     | TFL123       | DesafioBasquetPersonal    | BASQUET | 1112345678 | 7676   | ar.personal_it |
    | TFL     | TFL123       | DesafioPersonalFutbol     | FUTBOL  | 1112345678 | 7676   | ar.personal_it |
    | TFL     | TFL123       | DesafioPersonalRugby      | RUGBY   | 1112345678 | 7676   | ar.personal_it |
    | TFL     | TFL123       | SPreMatchGame             | PMG     | 1112345678 | 7676   | ar.personal_it |
    | TFL     | TFL123       | SPreMatchGame             | SATPMG  | 1112345678 | 7676   | ar.personal_it |    
    | FUN4RIDE|FUN4RIDE123   | PersonalTrivias           | ALTA    | 1112345678 | 7979   | ar.personal_it |    

    
  Scenario: Pegada de billing a Frontera2 exitosa
    Given the frontera2 message "<order><auth><usuario>TMS</usuario><password>TMS123</password></auth><service><provision>mcpgateway</provision><operacion>billing</operacion></service><options/><parameters><servicio>PersonalTrivias</servicio><ani>541134890851</ani><billing>429</billing></parameters></order>"     
	When I make the post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200
    	And frontera2 return message contains one of
    		|La operacion se realizo correctamente|
    		
    
  Scenario: Pegada de alta_suscripto_web a Frontera2 exitosa
    Given the frontera2 message "<order><auth><usuario>Fun4Ride</usuario><password>Fun4Ride123</password></auth><service><provision>mcpgateway</provision><operacion>alta_suscripto_web</operacion></service><options/><parameters><palabra>TRIVIASF4RSUSCRIPCION</palabra><ani>541134890851</ani><mt>7979</mt><telco>ar.personal_it</telco><pin>TMS123</pin></parameters></order>"     
	When I make the post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200
    	And frontera2 return message contains one of
    		|Se dio de alta el suscripto exitosamente|   
    		 
  Scenario: Pegada de enviar_pin a Frontera2 exitosa
    Given the frontera2 message "<order><auth><usuario>Fun4Ride</usuario><password>Fun4Ride123</password></auth><service><provision>mcpgateway</provision><operacion>enviar_pin</operacion></service><options/><parameters><palabra>TRIVIASF4RSUSCRIPCION</palabra><ani>541134890851</ani><mt>7979</mt><telco>ar.personal_it</telco></parameters></order>"     
	When I make the post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200        
    	And frontera2 return message contains one of
    		|Se ha enviado el PIN al usuario|   
    		
  Scenario: Pegada de indicar_suscripcion a Frontera2 exitosa
    Given the frontera2 message "<order><auth><usuario>TMS</usuario><password>TMS123</password></auth><service><provision>mcpgateway</provision><operacion>indicar_suscripcion</operacion></service><options/><parameters><ani>541134890851</ani><telco>ar.personal_it</telco><mt>7979</mt><palabra>TRIVIASF4RSUSCRIPCION</palabra></parameters></order>"     
	When I make the post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200    
    	And frontera2 return message contains one of
    		|true|   
    		|false|
      
  Scenario: Pegada de alta_suscripto_wap a Frontera2 exitosa
    Given the frontera2 message "<order><auth><usuario>Fun4Ride</usuario><password>Fun4Ride123</password></auth><service><provision>mcpgateway</provision><operacion>alta_suscripto_wap</operacion></service><options/><parameters><palabra>TRIVIASF4RSUSCRIPCION</palabra><ani>541134890851</ani><mt>7979</mt><telco>ar.personal_it</telco><urlOk>www.google.com/search?q=ok</urlOk><urlCancel>www.google.com/search?q=cancel</urlCancel><urlError>www.google.com/search?q=error</urlError><urlUnsusc>www.google.com/search?q=unsubscribe</urlUnsusc></parameters></order>"     
	When I make the post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200    
    	And frontera2 return message contains one of
    		|Se dio de alta el suscripto exitosamente|
    		|El suscripto ya se encuentra dado de alta|   

  Scenario: Pegada de generar_url a Frontera2 exitosa - JSON
    Given the frontera2 message "{  "parameters":{  "mt": "7979", "urlOk": "http://www.google.com/", "palabra": "TRIVIASF4RSUSCRIPCION", "telco": "ar.personal_it", "urlError": "http://www.facebook.com", "urlCancel": "", "ani": "541134890851", "urlUnsusc": "" }, "service":{  "provision":"mcpgateway", "operacion":"generar_url" }, "auth":{  "password":"Fun4Ride123", "usuario":"Fun4Ride" }, "options":{  } }"     
	When I make the json post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200
    	And frontera2 return message contains one of
    		|Se encriptaron los parametros exitosamente|
    
  Scenario: Pegada de enviar_sms a Frontera2 exitosa - JSON
    Given the frontera2 message "{  "parameters":{  "mt": "7676", "telco": "ar.personal_it", "ani": "541134890851", "transID": "", "mensaje": "Te diste de baja exitosamente" }, "service":{  "provision":"mcpgateway", "operacion":"enviar_sms" }, "auth":{  "password":"TMS123", "usuario":"TMS" }, "options":{  } }"     
	When I make the json post to this frontera2 url "http://192.168.10.6/service.php"
    Then frontera2 must return response code 200      
    	And frontera2 return message contains one of
    		|Se inserto el SMS exitosamente|

    
    
    
    

    

