@smoke
Feature: Gestión de Eliminación de Órdenes

  Scenario: Eliminar una orden válida
    Given la URL del servicio para eliminar órdenes
    And existe una orden con ID "1"
    When elimino la orden con ID "1"
    Then la respuesta debería tener el código HTTP eliminar 200
    And no debería existir la orden con ID "1"

  Scenario: Intentar eliminar una orden que no existe
    Given la URL del servicio para eliminar órdenes
    When elimino la orden con ID "999"
    Then la respuesta debería tener el código HTTP eliminar 404

  Scenario: Intentar eliminar una orden con un ID negativo
    Given la URL del servicio para eliminar órdenes
    When elimino la orden con ID "-12"
    Then la respuesta debería tener el código HTTP eliminar 404

  Scenario: Intentar eliminar una orden con un ID no numérico
    Given la URL del servicio para eliminar órdenes
    When elimino la orden con ID "#$%&a"
    Then la respuesta debería tener el código HTTP eliminar 405
