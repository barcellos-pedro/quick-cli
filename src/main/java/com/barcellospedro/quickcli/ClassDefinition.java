package com.barcellospedro.quickcli;

import static com.barcellospedro.quickcli.FilesHelper.ROOT_PACKAGE;
import static com.barcellospedro.quickcli.Templates.CLASS_TEMPLATE;

import java.text.MessageFormat;

public record ClassDefinition(String className, String fields, String getSet) {
        public String render() {
                return MessageFormat.format(CLASS_TEMPLATE, ROOT_PACKAGE, className(), fields(), getSet());
        }
}
