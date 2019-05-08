package com.shi.creator.converter;

import org.modelmapper.convention.MatchingStrategies;

import com.shi.creator.domain.ContractType;
import com.shi.creator.entity.Contract;

public class ContractTypeConverter implements TypeConverter<ContractType, Contract>{
	
    public static ContractTypeConverter instance = new ContractTypeConverter();

	@Override
	public ContractType toXml(Contract entity){
		
	    modelMapper.addConverter(new DateConverter());		
		ContractType type = modelMapper.map(entity, ContractType.class);
        return type;
	}

	@Override
	public Contract toEntity(ContractType type){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		Contract reservation = modelMapper.map(type, Contract.class);
		return reservation;
	}
}
