Feature: Inscripción a cursos

  Scenario: Inscripción correcta
    Given que el sistema de matrícula está disponible
    When un estudiante nuevo con código "I2026901" solicita inscribirse en el curso 2
    Then el sistema responde con estado 200

  Scenario: Inscripción duplicada
    Given que el estudiante "I2024001" ya está inscrito en el curso 1
    When el estudiante "I2024001" intenta inscribirse de nuevo en el curso 1
    Then el sistema rechaza la solicitud con un código de error 4xx o 5xx