package io.github.zikani03.acescrape.data;

import java.util.Optional;
import java.util.Set;

import org.eclipse.jetty.util.ConcurrentHashSet;

public class ContractsCollection {

	private Set<Contract> contracts;
	
	public ContractsCollection() {
		this.contracts = new ConcurrentHashSet<Contract>();
	}
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}

	public Optional<Contract> findByReference(String contractReference) {
		return this.contracts
				.stream()
				.filter(contract -> contract.getContractReference().equalsIgnoreCase(contractReference))
				.findFirst();
	}
}
