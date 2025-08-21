package com.invoice.service.impl;

import org.springframework.stereotype.Service;

import com.invoice.entity.Dealer;
import com.invoice.exception.ResourceNotFound;
import com.invoice.repository.DealerRepository;
import com.invoice.service.DealerService;

@Service
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;

    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    @Override
    public Dealer saveDealer(Dealer dealer) {

        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer getDealerById(String id) {
        return dealerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Dealer not found with id: " + id)); 
    }
}
