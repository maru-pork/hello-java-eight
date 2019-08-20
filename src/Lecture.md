**Marker Interface** - when a interface doesn't contain any method.
**Functional Interface** - one and only one abstract method.
**Lambda Expression** - syntax used to pass a block of code into a method that accepts a functional interface parameter.
**Syntax** 
``
(double percentage) -> { return salary + (salary * percentage); }
method parameters 
arrow token
method body
``

**Predicate** - a functional interface handling a very common scenario: a "test" method is invoked with a parameter, resulting in a boolean outcome
- The type is Predicate<T> where T can be any type needed in the test.
- single abstract method: boolean test(T t)

java.time = LocalDate, LocalDateTime, LocalTime, Period
-- LocalDate is immutable like String. Once LocalDate is created, you can't change it. If you want to change it, plusWeeks, minusDays.
-- No constructor for LocalDate. Static method is always used i.e. LocalDate.now.

**Arrays**
- Sorting and Searching arrays: java.util.Arrays
- length is a read-only attribute of an array i.e. letters.length

**Varargs** or Variable Arguments (...)
- pass a variable number of arguments to a method

**Collections**
- java.util [Collections (List, Set, Queue) and Maps]

Collections (interface) - List(interface), Set(interface), Queue(interface)
AbstractCollection 
- AbstractList - ArrayList, AbstractSequentialList- LinkedList
- AbstractSet - HashSet, TreeSet
- AbstractQueue - ArrayBlockingQueue

Maps (interface) - AbstractMap, Dictionary
- AbstractMap - HashMap, TreeMap
- Dictionary - HashTable

- List
- Set
- Queue - data structure with a head and tail
- ArrayBlockingQueue - FIFO
- PriorityQueue

list to array - Collections.toArray
array to list - Arrays.asList

**Iterator**
- list.iterator()
- Iterator interface has three methods: hasNext(), next(), remove()

**Generics**
**Autoboxing**
**Diamond Operator <> ** 
- List<String> stringList = new ArrayList<>();

Collections.sort(list)
Collections.binarySearch(list, "a")
Collections.reverse(list)
Collections.shuffle(list)
Collections.EMPTY_LIST

java.lang.Comparable
java.util.Comparator

**Throwable**
- when a problem occurs within a method, the method creates an object (java.lang.Throwable) and hands it off to the runtime system.
- the act of sending a Throwable object to the runtime is called "throwing".
- java.lang.Throwable - java.lang.Error, java.lang.Exception
- Error represents a serious problem that application should not try to recover from i.e. OutOfMemoryError
- Exceptions are problems that an application might want to capture, react to, or in some cases actually try to recover from

- try-catch-finally
- try-catch
- try-finally

**try-with-resources**
- syntax that allows us to omit the finally clause by automatically calling the "close" at the end of the method
- any object that implements the java.lang.autocloseable interface may be used with the syntax

**The Exception Object**
Throwable method
- getCause()
- getMessage()
- getStackTrace()
- printStackTrace()

**Runtime (Unchecked)**
- Runtime exceptions are exceptions that an application may want to capture but is not absolutely required to capture
- extends java.lang.RuntimeException i.e. StringIndexOutOfBoundsException, ClassCastException, ArithmeticException, ArrayIndexOutOfBoundsException

**Non-runtime (Checked)**
- Non-runtime exceptions (Checked Exceptions) are exceptions that an application must capture, and the compiler enforces doing so
- extends java.lang.Exception
- they must be checked for and handled (try-catch block or re-throwing the exception)
- i.e. SQLException and FileNotFoundException

**Streams**
- key abstraction in Java 8 for processing collections of values and specifying what you want to have done, leaving the scheduling of operations to the implementation
- follow "what, not how" principle
- pipeline of operations int three stages:
- 1. You create a stream.
- 2. You specify intermediate operations for transforming the initial stream into others, in one or more steps
- 3. You apply a terminal operation to produce a result. This operation forces the execution of the lazy operations that precede it. Afterwards, the stream can no longer be used.
- Collections.sort sorts a collection in place
- Stream.sorted returns a new sorted stream
- Optional<T> object is a wrapper for an object of type T or for no object.
- The key to using Optional effectively is to use a method that either consumes the correct value or produces an alternative.
- forEach method are terminal operations. You cannot use the stream again after calling them. To continue using stream, use peek instead.
- PrimitiveTypeStream- IntStream and DoubleStream
