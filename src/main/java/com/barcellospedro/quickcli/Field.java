package com.barcellospedro.quickcli;

import java.text.MessageFormat;
import java.util.List;

public class Field extends Templates {
    private final String type;
    private final String name;

    public Field(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public static Field fromArgs(String field) {
        var keyValue = field.split(":");
        var type = keyValue[1];
        var name = keyValue[0];
        return new Field(type, name);
    }

    public static List<Field> fromAttributes(List<String> attributes) {
        return attributes.stream().map(Field::fromArgs).toList();
    }

    public static String renderFields(List<Field> fields) {
        final var result = fields.stream().map(Field::renderField).toList();
        return String.join("\n", result);
    }

    public static String renderGetSet(List<Field> fields) {
        final var result = fields.stream().map(Field::renderGetSet).toList();
        return String.join("\n", result);
    }

    private static String renderField(Field field) {
        return MessageFormat.format(FIELD_TEMPLATE, field.type, field.name);
    }

    private static String renderGetSet(Field field) {
        return MessageFormat.format(GETTER_SETTER_TEMPLATE, field.type, field.name);
    }
}
