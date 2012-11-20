package fr.doan.achilles.metadata;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;

public class SimplePropertyMetaTest {

    @Test
    public void should_cast_value_to_correct_type() throws Exception {
        SimplePropertyMeta<String> meta = new SimplePropertyMeta<String>();
        meta.setValueClass(String.class);

        Object testString = "test";

        Object casted = meta.get(testString);

        assertThat(casted).isInstanceOf(String.class);
    }
}