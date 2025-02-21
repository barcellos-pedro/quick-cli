package com.barcellospedro.quickcli;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public record Field(String type, String name) {
    /**
     * Template Indexes
     * {0} - Type
     * {1} - Name
     */
    public static final String TEMPLATE = """
                private {0} {1};
            
                public {0} get{1}() '{'
                    return {1};
                '}'
            
                public void set{1}({0} value) '{'
                    {1} = value;
                '}'
            """;

    /**
     * Class field representation
     * field       -   E.g: title:String
     * field[0]    -   field name
     * field[1]    -   field type
     */
    public static Field fromArgs(String field) {
        var keyValue = field.split(":");
        var type = keyValue[1];
        var name = keyValue[0];
        return new Field(type, name);
    }

    public static List<Field> getFields(List<String> attributes) {
        return attributes.stream().map(Field::fromArgs).toList();
    }

    public static String getFieldsAsString(List<Field> fields) {
        return fields.stream()
                .map(Field::declareField)
                .collect(Collectors.joining("\n"));
    }

    private static String declareField(Field field) {
        return MessageFormat.format(TEMPLATE, field.type(), field.name());
    }
}
