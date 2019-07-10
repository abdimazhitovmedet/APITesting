@UiTest
Feature:Post in UI Api


  Scenario:First POST request scenario
    Given user is authorized to create post
    When user create post with title and content and status
      | title                  | content                               | status    |
      | "post with java code1" | "posting the contetnt with java code" | "publish" |
      | "post with java code2" | "posting the contetnt with java code" | "publish" |
      | "post with java code3" | "posting the contetnt with java code" | "publish" |
      | "post with java code4" | "posting the contetnt with java code" | "publish" |
      | "post with java code5" | "posting the contetnt with java code" | "publish" |
    Then gets 201 status code


  Scenario:Post via API and verify in UI
    Given user is authorized to create post
    When user create post with title and content and status
      | title                  | content                               | status    |
      | "post with java code1" | "posting the contetnt with java code" | "publish" |
      | "post with java code2" | "posting the contetnt with java code" | "publish" |
      | "post with java code3" | "posting the contetnt with java code" | "publish" |
      | "post with java code4" | "posting the contetnt with java code" | "publish" |
      | "post with java code5" | "posting the contetnt with java code" | "publish" |
    Then user verifies the post  in UI





  #Given server is up
#When user is on word press web site "https://www.medet.dev.cc/wp-admin/edit.php"
#And user press add new button
#And user gives title and text
#  Then user verifies post created
#Given user authorized in word press with credentials: <"admin"> , "admin" site "https://www.medet.dev.cc/wp-admin/edit.php"
#When

#  @scenarioOutline2
#  Scenario Outline: Verifying each section of etsy webPage
#    When User click on "<section>"
#    Then User verifies that "<title>"
#    Examples:
#      | section               | title                         |
#      | Jewelry & Accessories | Jewelry & Accessories \| Etsy |
#      | Clothing & Shoes      | Clothing & Shoes \| Etsy      |
#      | Home & Living         | Home & Living \| Etsy         |
#      | Wedding & Party       | Wedding & Party \| Etsy       |
#      | Toys & Entertainment  | Toys & Entertainment \| Etsy  |