package com.shi.creator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.shi.creator.converter.ContractTypeConverter;
import com.shi.creator.entity.Contract;
import com.shi.creator.entity.Holder;
import com.shi.creator.service.ContractService;
import com.shi.creator.service.HolderService;

public class Main {
	
	private static Holder createDummyHolder() {
		Holder holder = new Holder();
		holder.setEmail("test@user.com");
		holder.setName("User");
		holder.setSurname("Test");
		holder.addContract(createDummyContract());
		return holder;
	}
	
	private static Contract createDummyContract() {
		Contract contract = new Contract();
		contract.setNumber("Contract #1");
		contract.setSignedDate(LocalDate.now());
		contract.setSince(LocalDate.now());
		contract.setTill(LocalDate.now().plusYears(1));
		contract.setSum(new BigDecimal(12000));
		return contract;
	}

	public static void main(String[] args) {
		
		Holder holder = createDummyHolder();
		HolderService holderService = new HolderService();
		holderService.saveHolder(holder);
		
		ContractService contractService = new ContractService();
		Contract contract = contractService.findContract(1);
				
		Creator creator  = new Creator();		

		try {
			OutputStream out = new FileOutputStream("out.pdf");
			out.write(creator.createPdf(ContractTypeConverter.instance.toXml(contract)));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
