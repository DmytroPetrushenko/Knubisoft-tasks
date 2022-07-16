package com.knubisoft.base.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTasksImpl implements ListTasks {
    @Override
    public List<String> addElements(String... elements) {
        return List.of(elements);
    }

    @Override
    public List<String> getElementsByIndexes(List<String> elements, int[] indexes) {
        if (indexes == null) {
            throw new IllegalArgumentException();
        }
        for (int i : indexes) {
            if(elements.size() > i && i > 0){
                elements.add(elements.get(i));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return elements;
    }

    @Override
    public ArrayList<String> addElementsByIndexes(ArrayList<String> elements, int[] indexes) {
        if (indexes == null) {
            throw new IllegalArgumentException();
        }
        for (int i : indexes) {
            if (elements.size() > i && i > 0) {
                elements.add(i, elements.get(i));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return elements;
    }

    @Override
    public LinkedList<String> setElementsByIndexes(LinkedList<String> elements, int[] indexes) {
        if (indexes == null) {
            throw new IllegalArgumentException();
        }
        for (int i : indexes) {
            if (elements.size() > i || i > 0) {
                elements.set(i, elements.get(i));
            } else {
                throw new IllegalArgumentException();
            }
        }

        return elements;
    }

    @Override
    public int getListSize(List<String> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public List<Long> merge(List<Integer> first, List<Long> second, List<String> third) {
        if (third.contains(null) || second.contains(null) || first.contains(null)) {
            throw new NullPointerException();
        }
        List<Long> firstLong = first.stream()
                .map(Integer::longValue)
                .collect(Collectors.toList());
        List<Long> thirdLong = third.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return Stream.of(firstLong, second, thirdLong)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public int findMaxValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        return Stream.of(first, second, third)
                .flatMap(Collection::stream)
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }

    @Override
    public int findMinValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        return Stream.of(first, second, third)
                .flatMap(Collection::stream)
                .min(Comparator.naturalOrder())
                .orElseThrow();
    }

    @Override
    public int multiplyMax2Elements(List<Integer> first, List<Integer> second, List<Integer> third) {
        return Stream.of(first, second, third)
                .flatMap(Collection::stream)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((x, y) -> x * y)
                .orElseThrow();
    }

    @Override
    public List<String> removeNulls(List<String> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> flatMapWithoutNulls(List<List<Integer>> list) {
        if (list == null) {
            throw new NoSuchElementException();
        }
        return list.stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> cloneIds(List<Integer> originalIds) {
        if (originalIds == null) {
            throw new NoSuchElementException();
        }
        return originalIds.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> shuffle(List<String> originalStrings) {
        int size = originalStrings.size();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int itemFirst = random.nextInt(size);
            int itemSecond = random.nextInt(size);
            String valueFirst = originalStrings.get(itemFirst);
            String valueSecond = originalStrings.get(itemSecond);
            originalStrings.set(itemFirst, valueSecond);
            originalStrings.set(itemSecond, valueFirst);
        }
        return originalStrings;
    }

    @Override
    public String getLastElement(LinkedList<String> list) {
        if (list == null) {
            throw new NoSuchElementException();
        }
        if (list.isEmpty()) {
            return "";
        }
        return list.get(list.size() - 1);
    }

    @Override
    public List<String> compareElements(LinkedList<String> originalCollection, LinkedList<String> additionalCollection) {
        if (Objects.isNull(originalCollection) || Objects.isNull(additionalCollection)) {
            throw new IllegalArgumentException();
        }
        List<String> result = new LinkedList<>();
        for (String element: originalCollection) {
            if (additionalCollection.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
