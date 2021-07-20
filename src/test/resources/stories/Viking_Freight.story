Viking_freight
Meta:
@test1

Narrative:
As a user login to freight app


Scenario: Login to vikings freight application

Given login to DJJ Vikings freight app
When user landed to recycling freight
When user add first stop
When user add second stop
Then user add movement charge for yard to supplier
Then user add charges to supplier
Then user add stop charge for yard to supplier
Then user save lane
Then user logout