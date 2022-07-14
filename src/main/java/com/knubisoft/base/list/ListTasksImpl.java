package com.knubisoft.base.list;

import java.util.*;
import java.util.stream.Collectors;

public class ListTasksImpl implements ListTasks {
    @Override
    public List<String> addElements(String... elements) {
        return List.of(elements);
    }

    @Override
    public List<String> getElementsByIndexes(List<String> elements, int[] indexes) {
        List<String> result;
        if (indexes == null) {
            throw new IllegalArgumentException();
        }
        result = new ArrayList<>(elements);
        for (int i : indexes) {
            if(result.size() > i && i > 0){
                result.add(result.get(i));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    @Override
    public ArrayList<String> addElementsByIndexes(ArrayList<String> elements, int[] indexes) {
        if (indexes == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> newArrayList = new ArrayList<>(elements);
        for (int i : indexes) {
            if (newArrayList.size() > i && i > 0) {
                newArrayList.add(i, newArrayList.get(i));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return newArrayList;
    }

    @Override
    public LinkedList<String> setElementsByIndexes(LinkedList<String> elements, int[] indexes) {
        if (indexes == null) {
            throw new IllegalArgumentException();
        }
        LinkedList<String> result = new LinkedList<>(elements);
        for (int i : indexes) {
            if (result.size() > i || i > 0) {
                result.set(i, elements.get(i));
            } else {
                throw new IllegalArgumentException();
            }
        }

        return result;
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
        List<Long> result = new LinkedList<>();
        if (third.contains(null) || second.contains(null) || first.contains(null)) {
            throw new NullPointerException();
        }
        List<Long> firstLong = first.stream().map(Integer::longValue).collect(Collectors.toList());
        List<Long> thirdLong = third.stream().map(Long::parseLong).collect(Collectors.toList());
        result.addAll(firstLong);
        result.addAll(second);
        result.addAll(thirdLong);
        return result;
    }


    @Override
    public int findMaxValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        List<Integer> listUnion = new ArrayList<>(first);
        listUnion.addAll(second);
        listUnion.addAll(third);
        Integer maxValue = listUnion.get(0);
        for (int i = 1; i < listUnion.size() - 1; i++) {
            Integer currentValue = listUnion.get(i);
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
        }
        return maxValue;
    }

    @Override
    public int findMinValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        List<Integer> listUnion = new ArrayList<>(first);
        listUnion.addAll(second);
        listUnion.addAll(third);
        Integer minValue = listUnion.get(0);
        for (int i = 1; i < listUnion.size() - 1; i++) {
            Integer currentValue = listUnion.get(i);
            if (currentValue < minValue) {
                minValue = currentValue;
            }
        }
        return minValue;
    }

    @Override
    public int multiplyMax2Elements(List<Integer> first, List<Integer> second, List<Integer> third) {
        List<Integer> listUnion = new ArrayList<>(first);
        listUnion.addAll(second);
        listUnion.addAll(third);
        int size = listUnion.size();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Integer firstValue = listUnion.get(j);
                Integer secondValue = listUnion.get(j + 1);
                if ( firstValue > secondValue) {
                    listUnion.set(j, secondValue);
                    listUnion.set(j + 1, firstValue);
                }
            }
        }
        return listUnion.get(size - 1) * listUnion.get(size - 2);
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
