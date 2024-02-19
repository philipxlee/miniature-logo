# Collections API Lab Discussion
### Philip Lee, Arnav Nayak, Connor Johnson, Prince Ahmed
### Team 2



## In your experience using these collections, are they hard or easy to use?
* Relatively easy to use, as the data structures are common and well-documented.
* They methods you can call on the data structures are similar to other languages as well like C++.

## In your experience using these collections, do you feel mistakes are easy to avoid?
* Yes, they were pretty self-explanatory besides perhaps intializing the data structure due to the
syntax. 

## What methods are common to all collections (except Maps)?
* Just to name a few: add, contains, isEmpty, etc.

## What methods are common to all Deques?
* addFirst, addLast, getFirst, getLast, etc.

## What is the purpose of each interface implemented by LinkedList?
* List: LinkedList implements the List interface, which means it provides an ordered collection of 
elements with duplicates allowed. It maintains the insertion order of elements and allows fast 
insertion and removal operations at both ends of the list.
* Deque: LinkedList also implements the Deque interface, which stands for double-ended queue. It 
provides operations to insert, remove, and access elements from both ends of the list.
* Queue: LinkedList implements the Queue interface, which represents a collection that supports 
adding elements at one end and removing elements from the other end.
* Cloneable and Serializable: LinkedList also implements the Cloneable and Serializable interfaces 
to provide cloning and serialization capabilities, respectively.

## How many different implementations are there for a Set?
* HashSet, TreeSet, and LinkedHashSet, etc.

## What is the purpose of each superclass of PriorityQueue?
* AbstractQueue: This abstract class provides a skeletal implementation of the Queue interface. It 
handles the common functionality and provides default implementations for most of the methods 
defined in the Queue interface. Subclasses, such as PriorityQueue, only need to implement a few 
specific methods to create a complete implementation.
* AbstractCollection: The AbstractCollection class provides a partial implementation of the 
Collection interface, which PriorityQueue indirectly inherits. It provides implementations for 
several methods like size(), isEmpty(), contains(), toArray(), addAll(), and more.

## What is the purpose of the Collections utility class?
* Provides things like shuffle,sort, reverse, etc. for collections.
* Also provides things like immutable collections.


## API Characterics applied to Collections API

 * Easy to learn
   * Well documented with a FAQ

 * Encourages extension
   * Allows you to override or get add more implementations.
   
 * Leads to readable code
   * The methods are self-explanatory and the data structures are common.

 * Hard to misuse
   * Easy to read
 
 