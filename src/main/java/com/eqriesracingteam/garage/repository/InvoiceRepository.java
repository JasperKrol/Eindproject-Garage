package com.eqriesracingteam.garage.repository;

import com.eqriesracingteam.garage.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository <Invoice, Long> {


}
