package com.invoice.service;

import com.invoice.entity.Dealer;

public interface DealerService {

    Dealer saveDealer(Dealer dealer);

    Dealer getDealerById(String id);
}
