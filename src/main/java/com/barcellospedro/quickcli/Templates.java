package com.barcellospedro.quickcli;

public class Templates {
    public static final String FIELD_TEMPLATE = "private {0} {1};";

    public static final String CLASS_TEMPLATE = """
            package {0};

            class {1} '{'
                {2}

                {3}
            '}'
            """;

    public static final String GETTER_SETTER_TEMPLATE = """
                public {0} get{1}() '{'
                    return {1};
                '}'

                public void set{1}({0} value) '{'
                    {1} = value;
                '}'
            """;

}
