package com.javatraining.moksiakova;

import com.javatraining.moksiakova.components.CustomerComponent;
import com.javatraining.moksiakova.components.OrderComponent;
import com.javatraining.moksiakova.components.ProductComponent;
import com.javatraining.moksiakova.components.SupplierComponent;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class MainImpl {
    public static void main(String[] args) {
        CustomerComponent customerComponent = new CustomerComponent();
        customerComponent.createCustomer("123456","Telegraph");
        customerComponent.createCustomer("98765","Telegraph");
        customerComponent.updateCustomer(2,"98765","updateCustomerName");
        customerComponent.delete(2);
        Optional<Customer> customerRemoved = customerComponent.findById(2);

        SupplierComponent supplierComponent = new SupplierComponent();
        supplierComponent.createSupplier("Roga i Kopyta","8-123-123456678");
        supplierComponent.createSupplier("Neznaika","8-123-123456678");
        supplierComponent.updateSupplier(2, "Neznaika na Lune","1111111");
        Optional<Supplier> supplier = supplierComponent.findById(2);
        supplierComponent.delete(2);
        Optional<Supplier> supplierRemoved = supplierComponent.findById(2);

        OrderComponent orderComponent = new OrderComponent();
        orderComponent.createOrder("1", 1, 12.23);
        orderComponent.createOrder("2", 1, 120.23);
        orderComponent.updateOrder(1, "1/1", 2.22);
        Optional<Order> orderUpdated = orderComponent.findById(1);
        log.info("Updated order: {}", orderUpdated.get());
        orderComponent.deleteOrder(2);

        ProductComponent productComponent = new ProductComponent();
        productComponent.createProduct("Flower", 1, 1.11);
        productComponent.createProduct("Pencil", 1, 2.11);
        productComponent.updateProduct(1, "Flower new", 2.22);
        Optional<Product> productUpdated = productComponent.findById(1);
        log.info("Updated product: {}", productUpdated.get());
        productComponent.deleteProduct(2);
    }
}
