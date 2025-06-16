package com.OracleResults.OracleResults.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "items_oracle")
public class ItemOracle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String job;

    private String status;
    private String type;
    private String jobClass;
    private String assembly;

    @Column(name = "assembly_description", nullable = true)
    private String assemblyDescription;

    private Integer quantity;

    @Column(name = "quantity_completed", nullable = true)
    private Integer quantityCompleted;

    @Column(name = "quantity_scrapped", nullable = true)
    private Integer quantityScrapped;

    @Column(name = "yield", nullable = true)
    private Double yield;

    @Column(name = "quantity_remaining", nullable = true)
    private Integer quantityRemaining;

    @Column(name = "start_date", nullable = true)
    private String startDate;

    @Column(name = "date_completed", nullable = true)
    private String dateCompleted;

    @Column(name = "date_closed", nullable = true)
    private String dateClosed;

    @Column(name = "extra_column", nullable = true)
    private String extraColumn;

    public ItemOracle(Long id, String job, String status, String type, String jobClass, String assembly,
            String assemblyDescription, Integer quantity, Integer quantityCompleted, Double yield,
            Integer quantityScrapped, Integer quantityRemaining, String startDate,
            String dateCompleted, String dateClosed, String extraColumn) {
        this.id = id;
        this.job = job;
        this.status = status;
        this.type = type;
        this.jobClass = jobClass;
        this.assembly = assembly;
        this.assemblyDescription = assemblyDescription;
        this.quantity = quantity;
        this.quantityCompleted = quantityCompleted;
        this.yield = yield;
        this.quantityScrapped = quantityScrapped;
        this.quantityRemaining = quantityRemaining;
        this.startDate = startDate;
        this.dateCompleted = dateCompleted;
        this.dateClosed = dateClosed;
        this.extraColumn = extraColumn;
    }

    public ItemOracle() {
    }

}
