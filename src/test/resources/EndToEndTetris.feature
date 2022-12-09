@EndToEnd
Feature: Testing the customer journey for an end user using the Tetris application

@Success @Provided
Scenario: As a provided user, I want to successfully run the Tetris application with valid input
Given I am a <providedInput> user with valid input
When I invoke the Tetris application
Then I expect the output to match the expected results

@Success
Scenario Outline: As a standard user, I want to successfully run the Tetris application with valid input
Given I am a <input> user with valid input
When I invoke the Tetris application
Then I expect the output to match the expected results
Examples:
| input             |
| all-shapes        |
| stacking-l-and-j  |
| stacking-z-and-s  |
| stacking-t        |
| height-100        |
| blank-input       |

@Edge
Scenario: As a Tetris Troll, I want to attempt to break the application by running the application with a million lines
Given I am a <troll> user with valid input
When I invoke the Tetris application
Then I expect the output to match the expected results

@Exception
Scenario Outline: As a Tetris Troll, I want to attempt to break the application by inputting invalid data
Given I am a <input> user with invalid input
When I invoke the Tetris application
Then I expect the following <exception> to be thrown
Examples:
| input                                | exception                |
| file-does-not-exist                  | TetrisInputException     |
| file-has-negative-start-index        | TetrisProcessorException |
| file-has-start-index-equal-to-length | TetrisProcessorException |
| file-has-invalid-characters          | TetrisProcessorException |
| file-has-invalid-shape               | TetrisProcessorException |