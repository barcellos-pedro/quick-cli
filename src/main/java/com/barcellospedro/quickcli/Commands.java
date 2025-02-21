package com.barcellospedro.quickcli;

import java.text.MessageFormat;
import java.util.List;

import static com.barcellospedro.quickcli.FilesHelper.rootPackage;

public class Commands {
    protected static final String PACKAGE = rootPackage();

    protected static String getClassDefinition(String className, List<String> attributes) {
        List<Field> fields = Field.getFields(attributes);
        String fieldsString = Field.getFieldsAsString(fields);

        return MessageFormat.format("""
                package {0};
                
                class {1} '{'
                    {2}
                '}'
                """, PACKAGE, className, fieldsString);
    }
}
