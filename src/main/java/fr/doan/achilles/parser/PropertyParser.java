package fr.doan.achilles.parser;

import static fr.doan.achilles.metadata.builder.ListPropertyMetaBuilder.listPropertyMetaBuilder;
import static fr.doan.achilles.metadata.builder.MapPropertyMetaBuilder.mapPropertyMetaBuilder;
import static fr.doan.achilles.metadata.builder.SetPropertyMetaBuilder.setPropertyMetaBuilder;
import static fr.doan.achilles.metadata.builder.SimplePropertyMetaBuilder.simplePropertyMetaBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import fr.doan.achilles.bean.BeanPropertyHelper;
import fr.doan.achilles.metadata.PropertyMeta;
import fr.doan.achilles.validation.Validator;

public class PropertyParser {

    private final BeanPropertyHelper helper = new BeanPropertyHelper();

    public <V extends Serializable> PropertyMeta<V> parse(Class<?> beanClass, Field field, String propertyName) {
        Class<?> fieldType = field.getType();

        PropertyMeta<V> propertyMeta;

        if (List.class.isAssignableFrom(fieldType)) {
            propertyMeta = parseListProperty(beanClass, field, propertyName, fieldType);
        }

        else if (Set.class.isAssignableFrom(fieldType)) {
            propertyMeta = parseSetProperty(beanClass, field, propertyName, fieldType);
        }

        else if (Map.class.isAssignableFrom(fieldType)) {
            propertyMeta = parseMapProperty(beanClass, field, propertyName, fieldType);
        }

        else {
            propertyMeta = parseSimpleProperty(beanClass, field, propertyName);
        }

        return propertyMeta;
    }

    @SuppressWarnings("unchecked")
    private <V extends Serializable> PropertyMeta<V> parseSimpleProperty(Class<?> beanClass, Field field,
            String propertyName) {
        Validator.validateSerializable(field.getType(), field.getName());
        Method[] accessors = helper.findAccessors(beanClass, field);

        return simplePropertyMetaBuilder((Class<V>) field.getType()).propertyName(propertyName).accessors(accessors)
                .build();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <V extends Serializable> PropertyMeta<V> parseListProperty(Class<?> beanClass, Field field,
            String propertyName, Class<?> fieldType) {

        Class valueClass;
        Class<? extends List<V>> listType = (Class<? extends List<V>>) fieldType;
        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                valueClass = (Class) actualTypeArguments[0];
            } else {
                valueClass = Object.class;
            }
        } else {
            valueClass = Object.class;
        }

        Validator.validateSerializable(valueClass, "value type of " + field.getName());
        Method[] accessors = helper.findAccessors(beanClass, field);

        return listPropertyMetaBuilder((Class<V>) valueClass).listClass(listType).propertyName(propertyName)
                .accessors(accessors).build();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <V extends Serializable> PropertyMeta<V> parseSetProperty(Class<?> beanClass, Field field,
            String propertyName, Class<?> fieldType) {

        Class valueClass;
        Class<? extends Set<V>> setType = (Class<? extends Set<V>>) fieldType;
        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                valueClass = (Class) actualTypeArguments[0];
            } else {
                valueClass = Object.class;
            }
        } else {
            valueClass = Object.class;
        }
        Validator.validateSerializable(valueClass, "value type of " + field.getName());
        Method[] accessors = helper.findAccessors(beanClass, field);

        return setPropertyMetaBuilder((Class<V>) valueClass).setClass(setType).propertyName(propertyName)
                .accessors(accessors).build();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <V extends Serializable> PropertyMeta<V> parseMapProperty(Class<?> beanClass, Field field,
            String propertyName, Class<?> fieldType) {

        Class valueClass;
        Class keyType;

        Class<? extends Map> mapType = (Class<? extends Map>) fieldType;
        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            if (actualTypeArguments.length > 1) {
                keyType = (Class) actualTypeArguments[0];
                valueClass = (Class) actualTypeArguments[1];
            } else {
                keyType = Object.class;
                valueClass = Object.class;
            }
        } else {
            keyType = Object.class;
            valueClass = Object.class;
        }
        Validator.validateSerializable(valueClass, "value type of " + field.getName());
        Validator.validateSerializable(keyType, "key type of " + field.getName());
        Method[] accessors = helper.findAccessors(beanClass, field);

        return mapPropertyMetaBuilder(valueClass).keyClass(keyType).mapClass(mapType).propertyName(propertyName)
                .accessors(accessors).build();

    }
}