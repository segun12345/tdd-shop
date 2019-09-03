package com.tdd.shop.domain

case class Basket(products: Seq[BasketProduct], total: BigDecimal = BigDecimal(0.0)) {
  def calculateTotal: Basket = {
    this.copy(total = products.map(_.total).sum)
  }
}
