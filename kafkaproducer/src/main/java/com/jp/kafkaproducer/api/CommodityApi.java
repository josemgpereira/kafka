package com.jp.kafkaproducer.api;

import com.jp.kafkaproducer.entity.Commodity;
import com.jp.kafkaproducer.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/commodity")
public class CommodityApi {

    private final CommodityService commodityService;

    @GetMapping()
    public ResponseEntity<List<Commodity>> generateCommodities() {
        List<Commodity> commodities = commodityService.createDummyCommodities();
        if (commodities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commodities);
    }
}