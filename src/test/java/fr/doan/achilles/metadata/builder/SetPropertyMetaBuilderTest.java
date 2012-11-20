package fr.doan.achilles.metadata.builder;

import static fr.doan.achilles.metadata.builder.SetPropertyMetaBuilder.setPropertyMetaBuilder;
import static org.fest.assertions.api.Assertions.assertThat;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import fr.doan.achilles.exception.ValidationException;
import fr.doan.achilles.metadata.SetPropertyMeta;
import fr.doan.achilles.serializer.Utils;

public class SetPropertyMetaBuilderTest {

    @Test
    public void should_build_set_property_meta() throws Exception {

        Method[] accessors = new Method[2];
        accessors[0] = Bean.class.getDeclaredMethod("getFollowers", (Class<?>[]) null);
        accessors[1] = Bean.class.getDeclaredMethod("setFollowers", Set.class);

        SetPropertyMeta<String> meta = (SetPropertyMeta<String>) setPropertyMetaBuilder(String.class)
                .setClass(HashSet.class).propertyName("followers").accessors(accessors).build();

        assertThat(meta.getPropertyName()).isEqualTo("followers");
        assertThat(meta.getValueClass()).isEqualTo(String.class);
        assertThat(meta.getValueSerializer().getComparatorType()).isEqualTo(Utils.STRING_SRZ.getComparatorType());
        assertThat(meta.getGetter()).isEqualTo(accessors[0]);
        assertThat(meta.getSetter()).isEqualTo(accessors[1]);
        assertThat(meta.newSetInstance()).isInstanceOf(HashSet.class);

    }

    @Test(expected = ValidationException.class)
    public void should_exception_when_missing_data() throws Exception {

        setPropertyMetaBuilder(String.class).propertyName("name").build();

    }

    class Bean {
        private Set<String> followers;

        public Set<String> getFollowers() {
            return followers;
        }

        public void setFollowers(Set<String> followers) {
            this.followers = followers;
        }
    }
}