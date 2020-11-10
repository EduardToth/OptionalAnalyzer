package utilities;

import java.util.List;
import java.util.Arrays;

public class Libraries {

	public static final List<String> badGenericTypesForOptional = Arrays.asList("java.lang.Integer",
			"java.lang.Double",
			"java.lang.Long",
			"java.lang.Float");
	

	public static final List<String> collectionTypes = Arrays.asList("java.util.Stack",
			"java.util.Vector",
			"java.util.LinkedList",
			"java.util.ArrayList",
			"java.util.List",
			"java.util.LinkedHashMap",
			"java.util.HashSet",
			"java.util.Set",
			"java.util.Collection",
			"java.util.Queue",
			"java.util.Deque",
			"java.util.ArrayDeque",
			"java.util.ProrityQueue",
			"java.util.TreeSet",
			"java.util.SortedSet",
			"java.util.EnumMap",
			"java.util.HashMap",
			"java.util.AbstractMap",
			"java.util.Map",
			"java.util.HashTable",
			"java.util.SortedMap",
			"java.util.Navigable",
			"java.util.TreeMap",
			"java.util.Iterable");

	public static final List<String> optionalTypes = Arrays.asList("java.util.OptionalDouble",
			"java.util.OptionalLong",
			"java.util.Optional",
			"java.util.OptionalInt");

	public static final List<String> specialOptionalTypes = Arrays.asList("java.util.OptionalLong",
			"java.util.OptionalInt",
			"java.Util.OptionalDouble");
}
