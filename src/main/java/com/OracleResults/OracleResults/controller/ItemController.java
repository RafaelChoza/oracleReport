package com.OracleResults.OracleResults.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OracleResults.OracleResults.dto.ItemOracle;
import com.OracleResults.OracleResults.service.ItemService;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity <List<ItemOracle>> getAllItems() {
        List<ItemOracle> allItems = itemService.getAllItems();
        return ResponseEntity.ok(allItems);

    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemOracle>> searchItems(
        @RequestParam(required = false) String job,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String assembly,
        @RequestParam(required = false) String assemblyDescription,
        @RequestParam(required = false) String quantityMin,
        @RequestParam(required = false) String quantityMax,
        @RequestParam(required = false) String dateStart,
        @RequestParam(required = false) String dateEnd,
        @RequestParam(required = false) String assemblyStartingWith,
        @RequestParam(required = false) String jobEndingWith

    ) {
        List<ItemOracle> results = itemService.searchItems(job, type, assembly, assemblyDescription, quantityMin, quantityMax, dateStart, dateEnd, assemblyStartingWith, jobEndingWith);
        return ResponseEntity.ok(results);
    }
}
