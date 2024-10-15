package com.devchangetheworld.ewebsite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItem;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void addCartItem(CartItem cartItem){
        this.cartItem.add(cartItem);
        cartItem.setCart(this);
        updateTotalAmount();
    }

    public void removeCartItem(CartItem cartItem){
        this.cartItem.remove(cartItem);
        cartItem.setCart(null);
        updateTotalAmount();

    }

    public void updateTotalAmount(){
        this.totalAmount = cartItem.stream().map(item -> {
            BigDecimal unitPrice = item.getUnitPrice();
            if(unitPrice == null) {
                return unitPrice = BigDecimal.ZERO;
            }
            return unitPrice = item.getUnitPrice().multiply(new BigDecimal(item.getQuality()));

        }).reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
