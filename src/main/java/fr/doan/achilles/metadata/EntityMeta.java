package fr.doan.achilles.metadata;

import java.io.Serializable;
import java.util.Map;
import me.prettyprint.hector.api.Serializer;
import fr.doan.achilles.dao.GenericDao;

public class EntityMeta<ID extends Serializable> {

    public static final String COLUMN_FAMILY_PATTERN = "[a-zA-Z0-9_]+";
    private String canonicalClassName;
    private String columnFamilyName;
    private Long serialVersionUID;
    private Serializer<?> idSerializer;
    private Map<String, PropertyMeta<?>> propertyMetas;
    private PropertyMeta<ID> idMeta;
    private GenericDao<ID> dao;

    public String getCanonicalClassName() {
        return canonicalClassName;
    }

    public void setCanonicalClassName(String canonicalClassName) {
        this.canonicalClassName = canonicalClassName;
    }

    public String getColumnFamilyName() {
        return columnFamilyName;
    }

    public void setColumnFamilyName(String columnFamilyName) {
        this.columnFamilyName = columnFamilyName;
    }

    public Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(Long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public Serializer<?> getIdSerializer() {
        return idSerializer;
    }

    public void setIdSerializer(Serializer<?> idSerializer) {
        this.idSerializer = idSerializer;
    }

    public Map<String, PropertyMeta<?>> getPropertyMetas() {
        return propertyMetas;
    }

    public void setPropertyMetas(Map<String, PropertyMeta<?>> propertyMetas) {
        this.propertyMetas = propertyMetas;
    }

    public PropertyMeta<ID> getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(PropertyMeta<ID> idMeta) {
        this.idMeta = idMeta;
    }

    public GenericDao<ID> getDao() {
        return dao;
    }

    public void setDao(GenericDao<ID> dao) {
        this.dao = dao;
    }

}