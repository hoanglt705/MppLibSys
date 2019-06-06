package service;

import java.util.List;

import domain.CheckoutRecord;

public interface ICheckoutService {

	void save(CheckoutRecord checkoutRecord);

	List<CheckoutRecord> findAll(String memberId);

}
