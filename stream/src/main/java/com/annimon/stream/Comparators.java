package com.annimon.stream;

import com.annimon.stream.function.Function;

import java.util.Collections;
import java.util.Comparator;

/**
 */
public abstract class Comparators<T> implements Comparator<T> {
    /**
     * Returns a comparator with reverse order.
     *
     * @param <T> the type of the objects compared by the comparator
     * @return a comparator
     * @see Collections#reverseOrder()
     */
    public static <T extends Comparable<? super T>> Comparators<T> reverseOrder() {
        return new Comparators<T>() {
            @Override
            public int compare(T o1, T o2) {
                return Collections.reverseOrder().compare(o1, o2);
            }
        };
    }

    /**
     * Returns a comparator that reverses the order of the specified comparator.
     * If the specified comparator is {@code null}, this method is equivalent
     * to {@link #reverseOrder()}.
     *
     * @param <T> the type of the objects compared by the comparator
     * @param comparator a comparator to be reversed
     * @return a comparator
     * @throws NullPointerException if {@code comparator} is null
     * @see Collections#reverseOrder(java.util.Comparator)
     */
    public Comparators<T> reversed() {
        return new Comparators<T>() {
            @Override
            public int compare(T o1, T o2) {
                return Collections.reverseOrder().compare(o1, o2);
            }
        };
    }

    /**
     * Returns a comparator that uses a function that extracts
     * a {@link java.lang.Comparable} sort key to be compared.
     *
     * @param <T> the type of the objects compared by the comparator
     * @param <U> the type of the sort key
     * @param keyExtractor  the function that extracts the sort key
     * @return a comparator
     * @throws NullPointerException if {@code keyExtractor} is null
     */
    public static <T, U extends Comparable<? super U>> Comparators<T> comparing(
            final Function<? super T, ? extends U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return new Comparators<T>() {

            @Override
            public int compare(T t1, T t2) {
                final U u1 = keyExtractor.apply(t1);
                final U u2 = keyExtractor.apply(t2);
                return u1.compareTo(u2);
            }
        };
    }
}
