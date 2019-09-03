package com.tdd.shop.service

import com.tdd.shop.domain.ProductTypeEnum._
import com.tdd.shop.domain._
import org.scalatest.{FlatSpec, Matchers}

class BasketServiceSpec extends FlatSpec with Matchers {

  it should "calculate the sum of product of Apple and Oranges" in {
    val basketService = new BasketService
    val products = Seq(
      Product(Apple, BigDecimal("0.60")),
      Product(Apple, BigDecimal("0.60")),
      Product(Orange, BigDecimal("0.25")),
      Product(Apple, BigDecimal("0.60"))
    )

    basketService.getBasket(products) shouldBe Basket(Seq(
      BasketProduct(Orange, BigDecimal("0.25"), BigDecimal("0.25"), 1),
      BasketProduct(Apple, BigDecimal("0.60"), BigDecimal("1.80"), 3)
    ), BigDecimal("2.05"))
  }

  it should "add buy one get one free offer on Apple" in {
    val basketService = new BasketService
    val products = Seq(
      Product(Apple, BigDecimal("0.60")),
      Product(Apple, BigDecimal("0.60")),
      Product(Apple, BigDecimal("0.60")),
      Product(Apple, BigDecimal("0.60")),
      Product(Orange, BigDecimal("0.25")),
    )

    basketService.getBasket(products, Some(BuyOneGetOneFree())) shouldBe Basket(Seq(
      BasketProduct(Apple, BigDecimal("0.60"), BigDecimal("1.20"), 2),
      BasketProduct(Orange, BigDecimal("0.25"), BigDecimal("0.25"), 1)
    ), BigDecimal("1.45"))
  }

  it should "3 for 2 on oranges" in {
    val basketService = new BasketService
    val products = Seq(
      Product(Apple, BigDecimal("0.60")),
      Product(Apple, BigDecimal("0.60")),
      Product(Orange, BigDecimal("0.25")),
      Product(Orange, BigDecimal("0.25")),
      Product(Orange, BigDecimal("0.25"))
    )

    basketService.getBasket(products, Some(ThreeForTwo())) shouldBe Basket(Seq(
      BasketProduct(Orange, BigDecimal("0.25"), BigDecimal("0.50"), 2),
      BasketProduct(Apple, BigDecimal("0.60"), BigDecimal("1.20"), 2)
    ), BigDecimal("1.70"))
  }
}
