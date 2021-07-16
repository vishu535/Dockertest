Viking_CRM_CreateSupplier
Meta:
@Pricing

Narrative:
As a user login to vikings app

Scenario: Login to vikings

Given login to DJJ Vikings CRM application
When User navigates to recycling CRM section
When User selects accounts view activity
When user selects all accounts from view section
When Create new Supplier account from supplier home page
Then Edit supplier account header
Then Enter main details in supplier
Then Enter address information
Then Check address information
When Navigate to account summary account header
Then create new primary contact
Then User save primary contact account
When convert to supplier
Then user approve supplier and copy supplier id
Then user logout