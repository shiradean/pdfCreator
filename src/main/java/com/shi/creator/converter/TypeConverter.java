package com.shi.creator.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;

public interface TypeConverter<T, E> {
	
	static ModelMapper modelMapper = new ModelMapper();
	
    default List<T> toXml(Collection<E> entities){
        List<T> types = new LinkedList<>();
        for (E entity : entities)
            types.add(toXml(entity));
        
        return types;
    }
    
    default List<E> toEntity(Collection<T> types){
        List<E> entities = new LinkedList<>();
        for (T type : types)
        	entities.add(toEntity(type));
        
        return entities;
    }
    
     T toXml(E entity);
     E toEntity(T type);
}
