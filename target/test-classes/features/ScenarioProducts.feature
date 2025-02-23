Feature: End to End simulation Test

# Scenario: As a user I can add new data
# Given A list of products are available
# When I add a new product to the etalase
# Then The product is available

# ex: scenario outline
#login
#user deleted, New user -> user gagal login
#guna outline, step memuat beberapa test data
#User deleted : joko, 123
#New user : "", ""
Scenario Outline: As a user I can add new data with some data
# Given A list of products are available
When I add a new "<payload>" to the etalase
Then The product is available

Examples:
    | payload   |
    | addItem  |
    | addItem2  |