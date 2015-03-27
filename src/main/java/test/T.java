package test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T {

    @Test
    public void test() {
        List<Integer> integers = Arrays.asList(5, 6, 7);
        Optional<Integer> reduce = integers.stream().reduce((x, y) -> x + y);
        Assert.assertTrue(reduce.get() == 19);

        List of = Stream.of(1, 2, 3).collect(Collectors.toList());

    }
}
