# Getting Started

# Design Documentation:
The Tetris Simulator serves as a barebones engine to read in a static file of data with presumed correctness and parse it according to the rules of the game. If you are only looking for instructions to run the application, please skip to the end of the file.

## Tech Stack and Design
### Tech Stack
This project was implemented with Spring Batch for a variety of reasons:
* Game input was fixed and provided before-hand via input file, which was an initial clue to explore a data streaming/batch solution
* The game appears deceptively small in its input, but when I began considering edge cases I quickly realized that the potential number of input lines is only bounded by the size of the file. As an example, there could be 1 million lines in the input file with the text "Q0,Q2,Q4,Q6,Q8" which would never surpass the constrained height of 100 (always 0). This particular test was accounted for by me as an edge case in my testing strategy. 
* In the event of a large input file, a RESTful API would be significantly less performant than a batch job
* Having a POST operation for "/play-game" or a similar resource was not granular enough to qualify as a microservice. If I were to have exposed that endpoint, I would have made it a middleware endpoint that performed orchestration under the hood to a series of microservices(i.e. "play-game/place-piece/Q ). This seemed to me like clear over-solutioning of the problem for a week long deadline that still had performance deficiencies to a batch framework.
* For automated testing I chose Cucumber as it integrates well with Spring and serves as an ideal framwork for System Testing to improve confidence in the solution

### Design
* Used a chunk design instead of tasklets to make more efficient writes to the output file. Using the chunk style, the job will read/process n times before doing a write, where n is the chunk size chosen.
* Currently no data store is utilized and the board is maintained as a static global variable to maintain state across the application
* Created a generic Tetronimo object that was extended by each individual piece to provide encapsulation within my Class design. Furthermore, functionality was abstracted through a Factory that was used by the reader and a generic service interface leveraged in the processor.
* Used BufferedReader instead of FileReader/ByteItemReader due to the fact that the other readers execute parsing logic of input data at runtime which offers BufferedReader a performance advantage that shows as input file lengths scale.

# Testing
## Unit Tests
* The project is extensively covered with unit testing
* Every class, >90% line coverage

## Integration Tests
* The processor was the best example of integration testing that was available
* Would test the functionality of multiple piece types/insertion patterns under a single test

## System Tests
* Cucumber tests still in-progress and are a tech debt item, but infrastructure for it is almost complete
* Manual command-line testing occurred in place of this while development has still been in-progress to ensure system testing occurs
* Got one cucumber test to work that I was able to hack with different input files/my debugger to still gain value out of the work I began to do

## Technical Debt/Lessons Learned
* Make cucumber testing suite fully maintainable and extensible. I was able to get a test to work when I manually swapped out file names for input/output, but with more time I would have worked through getting the application context correctly injected before runtime which I believe would have required a small amount of refactoring to my code
* Make the getHeight() algorithm more runtime-efficient. It executed in O(n) runtime which is not great, and I reverted this logic back from a different approach I had been using that ran in O(1) time but had a bug discovered during testing so I optimized where time allowed me to
* Integrate JMeter testing or something similar to explore runtime performance with different chunk sizes. This was something I hoped to do originally but was a lower priority than what I did successfully implement
* Implement a POST API for reporting of these results 
* Add score of a user's input to output or separate functionality 


# Getting Started
# To run
* Ensure your workspace is configured to work with this tech stack: JDK11, Maven 
* Perform a maven "mvn clean install" either from the command line or in IntelliJ/eclipse
* Enter the target directory via the command line, and run the below command to execute the batch job:
    java -jar -Dinput.fileName=<YOUR_INPUT_FILE_PATH_HERE> -Doutput.fileName=<YOUR_OUTPUT_FILE_HERE> tetris-hamiel-0.0.1-SNAPSHOT.jar
