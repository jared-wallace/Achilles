package fr.doan.achilles.entity.metadata.factory;

import static fr.doan.achilles.entity.metadata.PropertyType.JOIN_WIDE_MAP;
import static fr.doan.achilles.entity.metadata.PropertyType.LAZY_LIST;
import static fr.doan.achilles.entity.metadata.PropertyType.LAZY_MAP;
import static fr.doan.achilles.entity.metadata.PropertyType.LAZY_SET;
import static fr.doan.achilles.entity.metadata.PropertyType.LAZY_SIMPLE;
import static fr.doan.achilles.entity.metadata.PropertyType.LIST;
import static fr.doan.achilles.entity.metadata.PropertyType.MAP;
import static fr.doan.achilles.entity.metadata.PropertyType.SET;
import static fr.doan.achilles.entity.metadata.PropertyType.SIMPLE;
import static fr.doan.achilles.entity.metadata.PropertyType.WIDE_MAP;
import static fr.doan.achilles.serializer.Utils.INT_SRZ;
import static fr.doan.achilles.serializer.Utils.LONG_SRZ;
import static fr.doan.achilles.serializer.Utils.OBJECT_SRZ;
import static fr.doan.achilles.serializer.Utils.STRING_SRZ;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.util.List;

import mapping.entity.CompleteBean;
import me.prettyprint.hector.api.Serializer;

import org.junit.Before;
import org.junit.Test;

import parser.entity.Bean;
import parser.entity.MyMultiKey;
import fr.doan.achilles.entity.metadata.PropertyMeta;
import fr.doan.achilles.entity.metadata.factory.PropertyMetaFactory;

/**
 * PropertyMetaBuilderTest
 * 
 * @author DuyHai DOAN
 * 
 */
public class PropertyMetaFactoryTest
{
	Method[] accessors = new Method[2];

	@Before
	public void setUp() throws Exception
	{
		accessors[0] = Bean.class.getDeclaredMethod("getId");
		accessors[1] = Bean.class.getDeclaredMethod("setId", Long.class);
	}

	@Test
	public void should_build_simple() throws Exception
	{

		PropertyMeta<Void, String> built = PropertyMetaFactory.factory(String.class).type(SIMPLE)
				.propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(SIMPLE);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isFalse();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_simple_lazy() throws Exception
	{

		PropertyMeta<Void, String> built = PropertyMetaFactory.factory(String.class)
				.type(LAZY_SIMPLE).propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(LAZY_SIMPLE);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isTrue();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_simple_with_object_as_value() throws Exception
	{
		PropertyMeta<Void, Bean> built = PropertyMetaFactory.factory(Bean.class).type(SIMPLE)
				.propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(SIMPLE);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		Bean bean = new Bean();
		assertThat(built.getValue((Object) bean)).isInstanceOf(Bean.class);
		assertThat(built.getValueClass()).isEqualTo(Bean.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				OBJECT_SRZ.getComparatorType());

		assertThat(built.isLazy()).isFalse();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_list() throws Exception
	{

		PropertyMeta<Void, String> built = PropertyMetaFactory.factory(String.class).type(LIST)
				.propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(LIST);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isFalse();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_list_lazy() throws Exception
	{

		PropertyMeta<Void, String> built = PropertyMetaFactory.factory(String.class)
				.type(LAZY_LIST).propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(LAZY_LIST);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isTrue();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_set() throws Exception
	{

		PropertyMeta<Void, String> built = PropertyMetaFactory.factory(String.class).type(SET)
				.propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(SET);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isFalse();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_set_lazy() throws Exception
	{

		PropertyMeta<Void, String> built = PropertyMetaFactory.factory(String.class).type(LAZY_SET)
				.propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(LAZY_SET);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isTrue();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_map() throws Exception
	{

		PropertyMeta<Integer, String> built = PropertyMetaFactory
				.factory(Integer.class, String.class).type(MAP).propertyName("prop")
				.accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(MAP);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getKey((Object) 12)).isInstanceOf(Integer.class);
		assertThat(built.getKeyClass()).isEqualTo(Integer.class);
		assertThat(built.getKeySerializer().getComparatorType()).isEqualTo(
				INT_SRZ.getComparatorType());

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isFalse();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_map_with_object_as_key() throws Exception
	{
		PropertyMeta<Bean, String> built = PropertyMetaFactory.factory(Bean.class, String.class)
				.type(MAP).propertyName("prop").accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(MAP);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		Bean bean = new Bean();
		assertThat(built.getKey((Object) bean)).isInstanceOf(Bean.class);
		assertThat(built.getKeyClass()).isEqualTo(Bean.class);
		assertThat(built.getKeySerializer().getComparatorType()).isEqualTo(
				OBJECT_SRZ.getComparatorType());

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isFalse();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_map_lazy() throws Exception
	{

		PropertyMeta<Integer, String> built = PropertyMetaFactory
				.factory(Integer.class, String.class).type(LAZY_MAP).propertyName("prop")
				.accessors(accessors).build();

		assertThat(built.propertyType()).isEqualTo(LAZY_MAP);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getKey((Object) 12)).isInstanceOf(Integer.class);
		assertThat(built.getKeyClass()).isEqualTo(Integer.class);
		assertThat(built.getKeySerializer().getComparatorType()).isEqualTo(
				INT_SRZ.getComparatorType());

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isTrue();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@Test
	public void should_build_wide_map() throws Exception
	{

		PropertyMeta<Integer, String> built = PropertyMetaFactory
				.factory(Integer.class, String.class).type(WIDE_MAP).propertyName("prop")
				.accessors(accessors).singleKey(true).build();

		assertThat(built.propertyType()).isEqualTo(WIDE_MAP);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		assertThat(built.getKey((Object) 12)).isInstanceOf(Integer.class);
		assertThat(built.getKeyClass()).isEqualTo(Integer.class);
		assertThat(built.getKeySerializer().getComparatorType()).isEqualTo(
				INT_SRZ.getComparatorType());

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isTrue();
		assertThat(built.isSingleKey()).isTrue();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void should_build_multi_key_wide_map() throws Exception
	{

		List<Class<?>> componentClasses = mock(List.class);
		List<Method> componentGetters = mock(List.class);
		List<Method> componentSetters = mock(List.class);
		PropertyMeta<MyMultiKey, String> built = PropertyMetaFactory
				.factory(MyMultiKey.class, String.class) //
				.type(WIDE_MAP).propertyName("prop") //
				.accessors(accessors) //
				.componentClasses(componentClasses) //
				.componentGetters(componentGetters) //
				.componentSetters(componentSetters) //
				.singleKey(false).build();

		assertThat(built.propertyType()).isEqualTo(WIDE_MAP);
		assertThat(built.getPropertyName()).isEqualTo("prop");

		MyMultiKey multiKey = new MyMultiKey();
		assertThat(built.getKey((Object) multiKey)).isInstanceOf(MyMultiKey.class);
		assertThat(built.getKeyClass()).isEqualTo(MyMultiKey.class);
		assertThat(built.getKeySerializer()).isNull();

		assertThat(built.getComponentClasses()).isSameAs(componentClasses);

		assertThat(built.getComponentGetters()).isSameAs(componentGetters);

		assertThat(built.getComponentSetters()).isSameAs(componentSetters);

		assertThat(built.getValue((Object) "val")).isInstanceOf(String.class);
		assertThat(built.getValueClass()).isEqualTo(String.class);
		assertThat(built.getValueSerializer().getComparatorType()).isEqualTo(
				STRING_SRZ.getComparatorType());

		assertThat(built.isLazy()).isTrue();
		assertThat(built.isSingleKey()).isFalse();
		assertThat(built.isJoinColumn()).isFalse();
	}

	@SuppressWarnings(
	{
			"rawtypes",
			"unchecked"
	})
	@Test
	public void should_build_join_wide_map_with_bean() throws Exception
	{
		Method idGetter = CompleteBean.class.getDeclaredMethod("getId");
		PropertyMeta<Integer, CompleteBean> meta = PropertyMetaFactory
				.factory(Integer.class, CompleteBean.class) //
				.type(JOIN_WIDE_MAP).propertyName("name") //
				.accessors(accessors) //
				.singleKey(true) //
				.insertable(false) //
				.entityValue(true) //
				.joinColumnFamily("externalCF") //
				.idGetter(idGetter) //
				.idClass(Long.class) //
				.build();

		assertThat(meta.propertyType()).isEqualTo(JOIN_WIDE_MAP);
		assertThat(meta.getPropertyName()).isEqualTo("name");
		assertThat(meta.isSingleKey()).isTrue();
		assertThat(meta.isLazy()).isTrue();
		assertThat(meta.isJoinColumn()).isTrue();
		assertThat(meta.isInsertable()).isFalse();
		assertThat(meta.isEntityValue()).isTrue();
		assertThat(meta.getJoinColumnFamily()).isEqualTo("externalCF");
		assertThat(meta.getIdGetter()).isEqualTo(idGetter);
		assertThat(meta.getIdClass()).isEqualTo((Class) Long.class);
		assertThat(meta.getIdSerializer()).isEqualTo((Serializer) LONG_SRZ);
	}

	@SuppressWarnings(
	{
			"rawtypes",
			"unchecked"
	})
	@Test
	public void should_build_join_wide_map_with_wide_row() throws Exception
	{
		Method idGetter = CompleteBean.class.getDeclaredMethod("getId");
		PropertyMeta<Integer, String> meta = PropertyMetaFactory
				.factory(Integer.class, String.class) //
				.type(JOIN_WIDE_MAP).propertyName("name") //
				.accessors(accessors) //
				.singleKey(true) //
				.insertable(false) //
				.entityValue(false) //
				.joinColumnFamily("externalCF") //
				.idGetter(idGetter) //
				.idClass(Long.class) //
				.build();

		assertThat(meta.propertyType()).isEqualTo(JOIN_WIDE_MAP);
		assertThat(meta.getPropertyName()).isEqualTo("name");
		assertThat(meta.isSingleKey()).isTrue();
		assertThat(meta.isLazy()).isTrue();
		assertThat(meta.isJoinColumn()).isTrue();
		assertThat(meta.isInsertable()).isFalse();
		assertThat(meta.isEntityValue()).isFalse();
		assertThat(meta.getJoinColumnFamily()).isEqualTo("externalCF");

		assertThat(meta.getIdGetter()).isEqualTo(idGetter);
		assertThat(meta.getIdClass()).isEqualTo((Class) Long.class);
		assertThat(meta.getIdSerializer()).isEqualTo((Serializer) LONG_SRZ);
	}
}