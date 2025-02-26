package com.barcellospedro.quickcli;

import java.util.List;

public class Commands {
    protected static String getClassDefinition(String className, List<String> attributes) {
        List<Field> fieldsList = Field.fromAttributes(attributes);
        String fields = Field.renderFields(fieldsList);
        String getSet = Field.renderGetSet(fieldsList);
        return ClassDefinition.prepare(className, fields, getSet).render();
    }
}
