@smoke
Feature: Creación de órdenes
  Scenario: Crear una orden válida
    Given la URL del servicio para crear órdenes
    When creo una orden con el siguiente cuerpo:
      """
      {
        "id": 1,
        "petId": 1,
        "quantity": 1,
        "shipDate": "2024-10-26T15:00:04.641Z",
        "status": "placed",
        "complete": true
      }
      """
    Then la respuesta al crear debería tener el código HTTP 200
    And la respuesta debería contener la información de la orden creada

  Scenario: Crear una orden con datos inválidos
    Given la URL del servicio para crear órdenes
    When creo una orden con el siguiente cuerpo:
      """
      {
      "id":a,
      "petId":ab,
      "quantity": "not-a-number",
      "shipDate": "2024-10-26T15:00:04.641Z",
      "status": "invalid",
      "complete": false
      }
      """
    Then la respuesta al crear debería tener el código HTTP 400

  Scenario: Crear una orden con datos faltantes
    Given la URL del servicio para crear órdenes
    When creo una orden con el siguiente cuerpo:
      """
      {
      "id": "not-a-number",
      "petId": "not-a-number",
      "quantity": "not-a-number",
      "shipDate": "2024-10-26T15:00:04.641Z",
      "status": "invalid",
      "complete": false
      }
      """
    Then la respuesta al crear debería tener el código HTTP 500
