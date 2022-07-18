package com.knubisoft.base.reflection;

import com.knubisoft.base.reflection.model.EntryModel;
import com.knubisoft.base.reflection.model.InheritedEntryModel;
import com.knubisoft.base.reflection.model.TestModel;
import com.knubisoft.base.string.StringTasks;
import com.knubisoft.base.string.StringTasksImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTasksTest {

    ReflectionTasks instance = new ReflectionTasksImpl();

    @Test
    @SneakyThrows
    public void createNewInstanceForClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(InheritedEntryModel.class, instance.createNewInstanceForClass(clazz).getClass());
        assertEquals(InheritedEntryModel.class,
                instance.createNewInstanceForClass(InheritedEntryModel.class).getClass());
    }

    @Test
    @SneakyThrows
    public void createNewInstanceForClassFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(clazz));
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.createNewInstanceForClass(null));
    }

    @Test
    @SneakyThrows
    public void findImplementationForInterfaceSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(clazz));
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(EntryModel.class));
    }

    @Test
    @SneakyThrows
    public void findImplementationForInterfaceFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(clazz));
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.findImplementationForInterface(null));
    }

    @Test
    @SneakyThrows
    public void findAllFieldsForClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(4, instance.findAllFieldsForClass(clazz).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasksImpl.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasksImpl.class).size());
        assertEquals(4, instance.findAllFieldsForClass(InheritedEntryModel.class).size());
    }

    @Test
    @SneakyThrows
    public void findAllFieldsForClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.findAllFieldsForClass(null));
    }

    @Test
    @SneakyThrows
    public void countPrivateMethodsInClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(2, instance.countPrivateMethodsInClass(clazz));
        assertEquals(0, instance.countPrivateMethodsInClass(StringTasks.class));
        assertEquals(2, instance.countPrivateMethodsInClass(EntryModel.class));
    }

    @Test
    @SneakyThrows
    public void countPrivateMethodsInClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.countPrivateMethodsInClass(null));
    }

    @Test
    void isMethodHasAnnotation() throws NoSuchMethodException {
        Method builder = InheritedEntryModel.class.getMethod("builder");
        assertTrue(instance.isMethodHasAnnotation(builder, InheritedEntryModel.class));
    }

    @Test
    void evaluateMethodByNameSuccessful() {
        assertEquals("Hello", instance.evaluateMethodByName(TestModel.class, "returnString"));
    }

    @Test
    void evaluateMethodByNameFail() {
        assertThrows(NoSuchElementException.class, () -> instance
                .evaluateMethodByName(TestModel.class, null));
    }

    @Test
    public void evaluateMethodByNameArgsSuccessful() {
        assertEquals("dlroW ,olleH",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "reverseString", "Hello, World"));
        assertEquals("He, Worldllo",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "insertStringInMiddle",
                        "Hello", ", World"));
        assertEquals("g",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "uniqueCharacters",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"));
    }

    @Test
    public void evaluateMethodByNameArgsFail() {
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(null, "builder", "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(), null, "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(),
                        "insertStringInMiddle", null));
    }

    @Test
    void changePrivateFieldValueSuccessful() {
        TestModel testModel = (TestModel) instance.changePrivateFieldValue(new TestModel(), "returnIt", "world");
        assertEquals(new TestModel("world").returnString(), testModel.returnString());
    }

    @Test
    public void changePrivateFieldValueFail() {
        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(null, "returnIt", "world"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(new TestModel(), null, "world"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(new TestModel(), " ", "world"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(new TestModel(), "returnIt", null));
    }
}