Feature: Add object a List

# Scenario Non-outline
Scenario: As a user I can add new data
    Given A list of item are available
    When I add item to list
    Then The item is available

# Scenario Outline

Scenario Outline: As a user I can add new data
    Given A list of item are available
    When I add item to list "<payload>"
    Then The item is available

    Examples:
    |payload    |
    |addItem    |
    |addItem2   |