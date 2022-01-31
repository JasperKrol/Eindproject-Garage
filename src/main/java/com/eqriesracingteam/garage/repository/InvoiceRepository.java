package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


}
