package com.tdd.shop.domain

import com.tdd.shop.domain.ProductTypeEnum.{Apple, Orange, ProductType}

trait Offer {
  def eligibleProducts: Seq[ProductType]
}

case class BuyOneGetOneFree(override val eligibleProducts: Seq[ProductType] = Seq(Apple)) extends Offer

case class ThreeForTwo(eligibleProducts: Seq[ProductType] = Seq(Orange)) extends Offer