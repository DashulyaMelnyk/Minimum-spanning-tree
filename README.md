# Minimum-spanning-tree :heart_eyes_cat:
The implementation of Kruskal's algorithm for the connected weighted undirected graph. Minimum weight spanning tree is a subgraph in a form of a tree that contains all the vertices of the initial graph with the minimun total weitgh of its edges. 

## Usages
* *Building a connected network.* The standard application is to a problem like phone network design. You have a business with several offices; you want to lease phone lines to connect them up with each other, and the phone company charges different amounts of money to connect different pairs of cities. 
* *Clustering.* If you want to cluster a bunch of points into k clusters, then one approach is to compute a minimum spanning tree and then drop the k-1 most expensive edges of the MST. This separates the MST into a forest with k connected components; each component is a cluster. (I confess I’m not very clear on whether anyone uses this clustering method in practice, and if so, what domains it is useful in.)
* *Traveling salesman problem.* There’s a straightforward way to use the MST to get a 2-approximation to the optimal solution to the traveling salesman problem, and the Christofides’ heuristic uses MSTs to get a 1.5-approximation. (One could reasonably question how real-world this is, though, as there are other approximation algorithms for the traveling salesman problem that will typically do even better in practice.)



## Kruskal's algorithm pseudocode:

función Kruskal(G)  
 T ← Ø

 foreach v en V[G] do  
 New set C(v) <- {v}  
 New MinHeap Q with G edges ordered by weight.  
 // n is total number of vertices  
 while !Q.empty() do  
 (u,v) ← Q.removeMin()  
 // To prevent cycles in T: add (u,v) if u and v are in  
 //different sets  
 // C(u) returns the set where u belongs.  
 if C(u) != C(v) do  
 Add edge (u,v) to T  
 Merge C(v) y C(u)  
 return T  

## Autors

* **Daria Melnyk** - *Initial work* - [DashulyaMelnyk](https://github.com/DashulyaMelnyk)




