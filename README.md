# INFO6205_04
## N_Queens Problem using Genetic Algorithm
### Problem Statement
The N-Queens problem is a classic problem that is used to determine the position of queens that can be spread on the chess board such that no two queens can kill each other. The problem can be solved in many possible ways including backtracking, branch and bound, etc.   
Here in our project we try to solve N-Queens using Gentic algorithm .The general running time of N queens problem is O(n!) and hence we have tried to reduce this using our genetic algorithm approach.

### Genetic Algorithm

Genetic Algorithm are non-deterministic algorithm that follows a natural order to determine the best possible solution of a problem. 
In our program the genetic algorithm will be used in order to determine the conflicts between any two queens on the chess board and will assign the fitness of the pattern according to the conflict.  
The fittest patterns along with their offsprings will pass on to the next generation and conflicts will be calculated accordingly.
To eliminate any possibility of biased result, we have added mutations in our code so that a few mutated patterns are added after fixed intervals. This would ensure that the results that we get are not biased to the parents that are generated.


## References:  
**Genetic Algorithm:**  
[Genetic Algorithm Introdcution](https://towardsdatascience.com/introduction-to-genetic-algorithms-including-example-code-e396e98d8bf3)  
[Creating a Genetic Algorithm Project](http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3)  
[Tutorial on Genetic Algorithm](https://www.tutorialspoint.com/genetic_algorithms/index.htm)  

**N queens Algorithm:**  
[Understanding N queens Using Backtracking Algorithm](https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/)  
[Improving N queens using Branch and Bound Method](https://www.geeksforgeeks.org/n-queen-problem-using-branch-and-bound/)  
[Java Code for N queens Algorithm Using Genetic Algorithm](http://mnemstudio.org/ai/ga/nqueens_java_ex1.txt)  

