package nl.testchamber.mailordercoffeeshop.data

import nl.testchamber.apiservice.data.Ingredient


data class Order(val customerName: String, val customerEmail: String, val orderName: String, val ingredients: List<Ingredient>)