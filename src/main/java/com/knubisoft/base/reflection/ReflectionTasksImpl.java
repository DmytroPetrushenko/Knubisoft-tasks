package com.knubisoft.base.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.reflections.Reflections;
import static org.reflections.scanners.Scanners.SubTypes;

public class ReflectionTasksImpl implements ReflectionTasks {

    @Override
    public Object createNewInstanceForClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }
        String[] fields = {"tableName", "schemaName", "version"};
        Class[] params = Arrays.stream(fields).map(String::getClass).toArray(Class[]::new);
        try {
            Constructor<?> constructor = cls.getConstructor(params);
            return constructor.newInstance(fields);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                 | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Class<? extends T> findImplementationForInterface(Class<T> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }
        Set<Class<? extends T>> subTypesOf = new Reflections("com.knubisoft.base.reflection.model", SubTypes)
                .getSubTypesOf(cls);
        if (subTypesOf.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return new ArrayList<>(subTypesOf).get(0);
    }

    @Override
    public Map<String, Object> findAllFieldsForClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }
        Map<String, Object> result = new HashMap<>();

        Class<?> superclass = cls.getSuperclass();
        Field[] fieldsSuper = superclass == null ? null : superclass.getDeclaredFields();
        Field[] fieldsAll = Stream.of(fieldsSuper, cls.getDeclaredFields())
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .toArray(Field[]::new);
        if (fieldsAll.length == 0) {
            return result;
        }
        Object instance = createNewInstanceForClass(cls);
        return Arrays.stream(fieldsAll)
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toMap(Field::getName, field -> {
                    try {
                        return Optional.ofNullable(field.get(instance));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }));
    }

    @Override
    public int countPrivateMethodsInClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }
        Class<?> superclass = cls.getSuperclass();
        Method[] methodsSuperMethods;
        if (superclass == null || Object.class.isAssignableFrom(superclass)) {
            methodsSuperMethods = null;
        } else {
            methodsSuperMethods = superclass.getDeclaredMethods();
        }
        return (int) Stream.of(methodsSuperMethods, cls.getDeclaredMethods())
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .map(Method::toString)
                .filter(e -> e.matches("\\bprivate.+"))
                .count();
    }

    @Override
    public boolean isMethodHasAnnotation(Method method, Class<?> annotationUnderMethod) {
        String name = method.getName();
        long count = Arrays.stream(annotationUnderMethod.getDeclaredMethods())
                .filter(method1 -> method1.getAnnotations().length > 0
                        && method1.getName().equals(name))
                .count();
        return count > 0;
    }

    @Override
    public Object evaluateMethodByName(Class<?> cls, String name) {
        if (name == null) {
            throw new NoSuchElementException();
        }
        try {
            Object instance = cls.getConstructor().newInstance();
            Method method = cls.getMethod(name);
            return method.invoke(instance);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException
                 | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object evaluateMethodWithArgsByName(Object obj, String name, Object... args) {
        if (obj == null || name == null || args == null) {
            throw new IllegalArgumentException();
        }
        try {
            Class<?> aClass = obj.getClass();
            Class<?>[] classes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
            Method method = aClass.getMethod(name, classes);
            return method.invoke(obj, args);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object changePrivateFieldValue(Object instance, String name, Object newValue) {
        if (instance == null || name == null || newValue == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        try {
            Object newInstance = instance.getClass().getConstructor().newInstance();
            Field declaredField = newInstance.getClass().getDeclaredField(name);
            declaredField.setAccessible(true);
            declaredField.set(newInstance, newValue);
            return newInstance;
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
