package com.example.tacocloud;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Taco_Order")
@JsonSerialize
public class TacoOrder {
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Date placedAt = new Date(); // Убедитесь, что это поле используется

    @JsonIgnore
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @JsonIgnore
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @JsonIgnore
    @NotBlank(message="City is required")
    private String deliveryCity;

    @JsonIgnore
    @NotBlank(message="State is required")
    private String deliveryState;

    @JsonIgnore
    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @JsonIgnore
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @JsonIgnore
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @JsonIgnore
    @NotBlank(message="CVV is required")
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Column(name = "cc_cvv")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
//    @JsonBackReference
    @JsonIgnore
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User user;

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
