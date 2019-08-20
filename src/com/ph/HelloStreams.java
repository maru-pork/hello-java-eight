package com.ph;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HelloStreams {
	
	public static void main(String[] args) {

		List<String> words = Arrays.asList("biogesic", "barangay", "some", "cap", "alcohol", "drug", "mercury");
		
		// stream
		long count = words.stream().filter(w->w.length()>5).count();
		System.out.println(count);
		//parallel stream
		count = words.parallelStream().filter(w->w.length()>5).count();
		System.out.println(count);
		
		/**
		 * 2.2 Stream Creation
		 */
		// Array to Stream
		Stream<String> streamFrArr = Stream.of("biogesic", "barangay", "some", "cap", "alcohol", "drug", "mercury");
		streamFrArr.forEach(System.out::print);
		// Make stream from a part of array
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		IntStream streamPart = Arrays.stream(arr);
		streamPart.forEach(System.out::print); // 12345678910
		streamPart = Arrays.stream(arr, 0, 5);
		streamPart.forEach(System.out::print); // 12345
		// Empty stream
		Stream<String> empty = Stream.empty();
		
		// Infinite streams
		Stream<String> echos = Stream.generate(()-> "Echo");
		//echos.forEach(System.out::print);
		// infinite random number
		Stream<Double> random = Stream.generate(Math::random);
		// random.forEach(System.out::print);
		// infinite sequence
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
		// integers.forEach(System.out::print);
		
		// Pattern.compile("").splitAsStream(contents);
		
		/**
		 * 2.3 The filter, map and flatMap Methods
		 */
		words.stream()
			.filter(w-> w.length() > 5)
			.forEach(System.out::println);
		words.stream()
			.map(String::toUpperCase)
			.forEach(System.out::println);
		words.stream()
			.map(s -> s.charAt(0))
			.forEach(System.out::println);
		Stream<Stream<Character>> map = words.stream().map(w->{
			List<Character> ret = new ArrayList<>();
			for(char c: w.toCharArray()) ret.add(c);
			return ret.stream();
		});
		map.forEach(System.out::println);
		Stream<Character> flatmap = words.stream().flatMap(w->{
			List<Character> ret = new ArrayList<>();
			for(char c: w.toCharArray()) ret.add(c);
			return ret.stream();
		});
		flatmap.forEach(System.out::print);
		
		/**
		 * 2.4 Extracting Substreams and Combining Streams
		 */
		// limit
		System.out.println();
		Stream
			.generate(Math::random)
			.limit(50)
			.forEach(System.out::print);
		System.out.println();
		Stream
			.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE))
			.limit(50)
			.forEach(System.out::print); // 012345678910111213141516171819202122232425262728293031323334353637383940414243444546474849
		System.out.println();
		// skip
		Stream
			.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE))
			.skip(50)
			.limit(50)
			.forEach(System.out::print); // 5051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899
		System.out.println();
		// concat
		Stream<String> combined = Stream.concat(
				Stream.of("Hello Word", "Hello Philippines"), 
				Stream.of("Hi Word", "Hi Philippines"));
		combined.forEach(System.out::printf); // Hello WordHello PhilippinesHi WordHi Philippines
		System.out.println();
		// peek
		Object[] powers = words.stream()
			.peek(w -> System.out.println("Fetching " + w))
			.limit(2)
			.toArray();
		
		/**
		 * 2.5 Stateful Transformations
		 */
		Stream.of("mary", "mary", "merry", "maru")
			.distinct()
			.forEach(System.out::print);
		System.out.println();
		Stream.of("d", "a", "c", "b")
			.sorted()
			.forEach(System.out::print);
		System.out.println();
		Stream.of("gently", "go", "wherever", "down")
			// .sorted(Comparator.reverseOrder()) // wherever go gently down
			// .sorted(Comparator.naturalOrder()) // down gently go wherever
			.sorted(Comparator.comparing(String::length).reversed()) // wherever gently down go
			.forEach(System.out::print);
		System.out.println();
		
		/**
		 * 2.6 Simple Reductions - Terminal Operations
		 */
		Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
		System.out.println(largest.isPresent() + " " + largest.get());
		Optional<String> first = words.stream().filter(w->w.startsWith("b")).findFirst();
		System.out.println(first.isPresent() + " " + first.get());
		Optional<String> any = words.stream().filter(w->w.startsWith("b")).findAny();
		System.out.println(any.isPresent() + " " + any.get());
		boolean anyMatch = words.stream().parallel().anyMatch(w->w.startsWith("b"));
		System.out.println(anyMatch);
		
		/**
		 * 2.7 The Optional Type
		 */
		String ret = largest.isPresent() ? largest.get().toLowerCase() : null; // some
		System.out.println(ret);

		/**
		 * 2.7.1 consume optional values
		 */
		largest.ifPresent(l -> System.out.println(l)); // some

		List<String> results = new ArrayList<String>();
		largest.ifPresent(l -> results.add(l));
		System.out.println(results); // [some]

		largest.ifPresent(results::add);
		System.out.println(results); // [some, some]

		Optional<Boolean> added = largest.map(results::add);
		System.out.println(added); // Optional[true]
		System.out.println(results); // [some, some, some]

		// default value
		String result = largest.orElse("default");
		// invoke code to compute for default
		result = largest.orElseGet(()->System.getProperty("some.property"));
		// throw another exception if no value
		result = largest.orElseThrow(NoSuchElementException::new);
		
		/**
		 * 2.7.2 create optional values
		 */
		Optional<String> optional = Optional.empty();
		System.out.println(optional); // Optional.empty
		optional = Optional.of("some");
		System.out.println(optional); // Optional[some]
		optional = Optional.ofNullable(null);
		System.out.println(optional); // Optional.empty
		
		/**
		 * 2.7.3 Composing optional value from functions with flatMap
		 */
		
		
		/**
		 * 2.8 Reduction Operations
		 */
		
		/**
		 * 2.9 Collection Result
		 */
		String[] resultArray = words.stream().toArray(String[]::new);
		HashSet<String> resultSet = words.stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
		
		Set<String> set = words.stream().collect(Collectors.toSet());
		List<String> list = words.stream().collect(Collectors.toList());
		TreeSet<String> treeSet = words.stream().collect(Collectors.toCollection(TreeSet::new));		
		String resultString = words.stream().collect(Collectors.joining());
		System.out.println(resultString); //biogesicbarangaysomecapalcoholdrugmercury
		
		IntSummaryStatistics summary = words.stream().collect(Collectors.summarizingInt(String::length));
		System.out.println("Average Word Length: " + summary.getAverage());
		System.out.println("Max Word Length: " + summary.getMax());
		
		/**
		 * 2.10 Collecting into Maps
		 */
		List<Person> persons = Arrays.asList(new Person(1L, "Mary"), new Person(2L, "Mary Ann"));
		Map<Long, String> idToName = persons.stream().collect(Collectors.toMap(Person::getId, Person::getName));
		Map<Long, Person> idToPerson = persons.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
		idToPerson = persons.stream().collect(
			Collectors.toMap(
				Person::getId, 
				Function.identity(), 
				(existingVal, newVal) -> {throw new IllegalStateException();},
				TreeMap::new));
		
		Map<String, String> languageNames = Arrays.stream(Locale.getAvailableLocales())
				.collect(Collectors.toMap(
					l->l.toString(),
					Locale::getLanguage,
					(e, n) -> e));
		System.out.println(languageNames);
		
		/**
		 * 2.11 Grouping and Partitioning
		 */
		
		// group by property
		Map<String, List<Locale>> countryToLocales = Arrays.stream(Locale.getAvailableLocales())
				.collect(Collectors.groupingBy(Locale::getCountry));
		List<Locale> swissLocales = countryToLocales.get("CH");
		System.out.println(swissLocales); // [fr_CH, de_CH, it_CH]
		
		// group by boolean property
		Map<Boolean, List<Locale>> englishAndNonEn = Arrays.stream(Locale.getAvailableLocales())
					.collect(Collectors.partitioningBy(l -> "en".equalsIgnoreCase(l.getLanguage())));
		List<Locale> englishLocales = englishAndNonEn.get(true);
		System.out.println(englishLocales); // [en_US, en_SG, en_MT, en, en_PH, en_NZ, en_ZA, en_AU, en_IE, en_CA, en_IN, en_GB]
		
		// group by property to Set
		Map<String, Set<Locale>> countryToLocalesSet = Arrays.stream(Locale.getAvailableLocales())
				.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
		List<Locale> swissLocalesSet = countryToLocales.get("CH");
		System.out.println(swissLocalesSet); // [fr_CH, de_CH, it_CH]
		
		// group and count
		Map<String, Long> countryToLocalesCount = Arrays.stream(Locale.getAvailableLocales())
				.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
		System.out.println(countryToLocalesCount);

		/**
		 * 2.12 Primitive Type Streams
		 */
		IntStream intStream = IntStream.of(1,2,3,4,5,6);
		Stream<Integer> stream = intStream.boxed();

		/**
		 * 2.13 Parallel Streams
		 */
		

		/**
		 * 2.4 Functional Interfaces
		 */

	}

}

class Person {
	private Long id;
	private String name;
	public Person(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
	return "id=" + id + ";name=" + name;
	}
}
