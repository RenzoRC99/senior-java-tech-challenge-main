package com.mango.pricing.prices.interfaces;


import com.mango.pricing.prices.application.pricecases.addprice.AddPriceHandler;
import com.mango.pricing.prices.application.pricecases.addprice.AddPriceMapper;
import com.mango.pricing.prices.application.pricecases.addprice.AddPriceRequest;
import com.mango.pricing.prices.application.pricecases.getcurrentprice.CurrentPriceDTO;
import com.mango.pricing.prices.application.pricecases.getcurrentprice.GetCurrentPriceHandler;
import com.mango.pricing.prices.application.pricecases.getcurrentprice.GetCurrentPriceQuery;
import com.mango.pricing.prices.application.pricecases.getproductprices.GetProductPricesHandler;
import com.mango.pricing.prices.application.pricecases.getproductprices.GetProductPricesQuery;
import com.mango.pricing.prices.application.pricecases.getproductprices.ProductPricesDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/products/{productId}/prices")
public class PriceController {
    private final AddPriceHandler addPriceHandler;
    private final AddPriceMapper addPriceMapper;
    private final GetCurrentPriceHandler getCurrentPriceHandler;
    private final GetProductPricesHandler getProductPricesHandler;

    public PriceController(AddPriceHandler addPriceHandler, AddPriceMapper addPriceMapper, GetCurrentPriceHandler getCurrentPriceHandler, GetProductPricesHandler getProductPricesHandler) {
        this.addPriceHandler = addPriceHandler;
        this.addPriceMapper = addPriceMapper;
        this.getCurrentPriceHandler = getCurrentPriceHandler;
        this.getProductPricesHandler = getProductPricesHandler;
    }

    @PostMapping("/{priceId}")
    public ResponseEntity<Void> addPrice(@PathVariable UUID productId,
                                         @PathVariable UUID priceId,
                                         @RequestBody AddPriceRequest request) {

        var command = addPriceMapper.toCommand(productId, priceId, request);
        addPriceHandler.handle(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "date")
    public ResponseEntity<CurrentPriceDTO> getCurrentPrice(
            @PathVariable("productId") UUID productId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        var query = new GetCurrentPriceQuery(productId, date);
        return getCurrentPriceHandler.handle(query)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ProductPricesDTO> getProductPrices(@PathVariable("productId") UUID productId) {
        var query = new GetProductPricesQuery(productId);
        ProductPricesDTO dto = getProductPricesHandler.handle(query);
        return ResponseEntity.ok(dto);
    }
}
