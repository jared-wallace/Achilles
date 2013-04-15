package info.archinnov.achilles.wrapper.builder;

import static org.fest.assertions.api.Assertions.assertThat;
import info.archinnov.achilles.composite.factory.CompositeFactory;
import info.archinnov.achilles.dao.GenericColumnFamilyDao;
import info.archinnov.achilles.entity.metadata.PropertyMeta;
import info.archinnov.achilles.entity.operations.EntityLoader;
import info.archinnov.achilles.entity.operations.EntityPersister;
import info.archinnov.achilles.entity.operations.EntityProxifier;
import info.archinnov.achilles.helper.CompositeHelper;
import info.archinnov.achilles.iterator.factory.IteratorFactory;
import info.archinnov.achilles.iterator.factory.KeyValueFactory;
import info.archinnov.achilles.proxy.interceptor.AchillesInterceptor;
import info.archinnov.achilles.wrapper.JoinWideMapWrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

/**
 * JoinWideMapWrapperBuilderTest
 * 
 * @author DuyHai DOAN
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class JoinWideMapWrapperBuilderTest
{
	@Mock
	private GenericColumnFamilyDao<Integer, Long> dao;

	@Mock
	private PropertyMeta<Integer, String> propertyMeta;

	@Mock
	private AchillesInterceptor<Integer> interceptor;

	@Mock
	private EntityPersister persister;

	@Mock
	private EntityLoader loader;

	@Mock
	private EntityProxifier proxifier;

	@Mock
	private CompositeHelper compositeHelper;

	@Mock
	private CompositeFactory compositeFactory;

	@Mock
	private KeyValueFactory keyValueFactory;

	@Mock
	private IteratorFactory iteratorFactory;

	@Test
	public void should_build() throws Exception
	{
		JoinWideMapWrapper<Integer, Long, Integer, String> wrapper = JoinWideMapWrapperBuilder
				.builder(1, dao, propertyMeta) //
				.interceptor(interceptor) //
				.proxifier(proxifier) //
				.compositeHelper(compositeHelper) //
				.compositeFactory(compositeFactory) //
				.iteratorFactory(iteratorFactory)//
				.keyValueFactory(keyValueFactory) //
				.loader(loader) //
				.persister(persister) //
				.build();

		assertThat(wrapper).isNotNull();
		assertThat(wrapper.getInterceptor()).isSameAs(interceptor);
		assertThat(Whitebox.getInternalState(wrapper, "dao")).isSameAs(dao);
		assertThat(Whitebox.getInternalState(wrapper, "interceptor")).isSameAs(interceptor);
		assertThat(Whitebox.getInternalState(wrapper, "propertyMeta")).isSameAs(propertyMeta);
		assertThat(Whitebox.getInternalState(wrapper, "compositeHelper")).isSameAs(compositeHelper);
		assertThat(Whitebox.getInternalState(wrapper, "compositeFactory")).isSameAs(
				compositeFactory);
		assertThat(Whitebox.getInternalState(wrapper, "proxifier")).isSameAs(proxifier);
		assertThat(Whitebox.getInternalState(wrapper, "iteratorFactory")).isSameAs(iteratorFactory);
		assertThat(Whitebox.getInternalState(wrapper, "keyValueFactory")).isSameAs(keyValueFactory);
		assertThat(Whitebox.getInternalState(wrapper, "loader")).isSameAs(loader);
		assertThat(Whitebox.getInternalState(wrapper, "persister")).isSameAs(persister);
	}
}