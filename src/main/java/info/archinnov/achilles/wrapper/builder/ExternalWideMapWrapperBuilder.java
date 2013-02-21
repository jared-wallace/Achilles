package info.archinnov.achilles.wrapper.builder;

import info.archinnov.achilles.dao.GenericCompositeDao;
import info.archinnov.achilles.entity.metadata.PropertyMeta;
import info.archinnov.achilles.proxy.interceptor.AchillesInterceptor;
import info.archinnov.achilles.wrapper.ExternalWideMapWrapper;

/**
 * ExternalWideMapWrapperBuilder
 * 
 * @author DuyHai DOAN
 * 
 */
public class ExternalWideMapWrapperBuilder<ID, K, V>
{
	private ID id;
	private GenericCompositeDao<ID, V> dao;
	private PropertyMeta<K, V> wideMapMeta;
	private AchillesInterceptor interceptor;

	public ExternalWideMapWrapperBuilder(ID id, GenericCompositeDao<ID, V> dao,
			PropertyMeta<K, V> wideMapMeta)
	{
		this.id = id;
		this.dao = dao;
		this.wideMapMeta = wideMapMeta;
	}

	public static <ID, K, V> ExternalWideMapWrapperBuilder<ID, K, V> builder(ID id,
			GenericCompositeDao<ID, V> dao, PropertyMeta<K, V> wideMapMeta)
	{
		return new ExternalWideMapWrapperBuilder<ID, K, V>(id, dao, wideMapMeta);
	}

	public ExternalWideMapWrapperBuilder<ID, K, V> interceptor(AchillesInterceptor interceptor)
	{
		this.interceptor = interceptor;
		return this;
	}

	public ExternalWideMapWrapper<ID, K, V> build()
	{
		ExternalWideMapWrapper<ID, K, V> wrapper = new ExternalWideMapWrapper<ID, K, V>();
		wrapper.setId(id);
		wrapper.setDao(dao);
		wrapper.setWideMapMeta(wideMapMeta);
		wrapper.setInterceptor(interceptor);
		return wrapper;
	}

}