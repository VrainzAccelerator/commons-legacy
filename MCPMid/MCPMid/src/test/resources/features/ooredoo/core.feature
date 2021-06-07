@executeThis
Feature: Procesamiento de eventos de Ooredoo

	Scenario: Alta sucripto Ooredoo via wap ok
		Given the message "{ani:231144446666, servicio:1001, fecha:'2016-06-08 12:51:23'}"     
		When I receive the post to this url "http://localhost:8080/MCPMid/consents" 
		Then response code 200 must be returned
    		and description must be "ok"

	Scenario: Alta sucripto Ooredoo via wap error mensaje con formato erroneo
    	Given the message "{ani:231144446666 servicio:1001, fecha:'2016-06-08 12:51:23'"     
		When I receive the post to this url "http://localhost:8080/MCPMid/consents"
    	Then response code 500 must be returned
 			and description must be "Mensaje malformado"
 	
 	Scenario: Alta sucripto Ooredoo via wap error servicio externo no disponible
    	Given the message "{ani:231144446666, servicio:1001, fecha:'2016-06-08 12:51:23'}"     
		When I receive the post to this url "http://localhost:8080/MCPMid/consents"
    	Then response code 500 must be returned
    		and description must be "El servidor externo no responde"
 	 	
 	Scenario: Alta sucripto Ooredoo via wap error mensaje con datos erroneos
    	Given the message "{ani:231144446666, servicio:1001, fecha:'2016-06-08 12:51:23'}"     
		When I receive the post to this url "http://localhost:8080/MCPMid/consents"
    	Then response code 500 must be returned
 			and description must be "Los datos son incorrectos"
