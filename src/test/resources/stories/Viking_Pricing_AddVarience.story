Viking_Pricing_AddVarience
Meta:
@poc

Narrative:
As a user login to vikings app


Scenario: Login to vikings

Given login to DJJ Vikings pricing app
When user navigates to scale pricing
When select inventory type and company from dropdown
When select branch and level from list
When scale pricing search
When user adds new price
When user enters new price details
Then Add variances for all levels
Then save price material
Then user navigates to price list
Then user select company branch and search prices from price list
Then validate material description in pricing
When user opens scrapdragon application and validates
Then user logout