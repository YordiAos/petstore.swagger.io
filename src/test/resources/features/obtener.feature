@smoke
Feature: Order Management

  Scenario: Validar respuesta con código HTTP 200 para orden válida
    Given la URL del servicio de órdenes
    When realizo una solicitud para obtener la orden con ID "2"
    Then la respuesta debería tener el código HTTP 200
    And la respuesta debería contener la información de la orden

  Scenario: Validar respuesta con código HTTP 400 para orden no encontrada
    Given la URL del servicio de órdenes
    When realizo una solicitud para obtener la orden con ID "111"
    Then la respuesta debería tener el código HTTP 404


  Scenario: Validar respuesta con código HTTP 500 para ID inválido
    Given la URL del servicio de órdenes
    When realizo una solicitud para obtener la orden con ID "#$"
    Then la respuesta debería tener el código HTTP 405

