package algorithm.xml;

import algorithm.ModelRoot;

import java.util.List;

public interface XMLTasks {

    ModelRoot parseXML(String xml);

    List<Integer> getAllIds(String xml);

    List<String> getNameWithIdMoreThan1(String xml);

}