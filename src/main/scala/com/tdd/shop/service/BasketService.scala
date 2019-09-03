package com.tdd.shop.service

import com.tdd.shop.domain._

class BasketService {

  def getBasket(products: Seq[Product], offer: Option[Offer] = None): Basket = {
    val basketProduct: Seq[BasketProduct] = buildBasketProduct(products)
    val basket: Seq[BasketProduct] = offer match {
      case Some(offer@BuyOneGetOneFree(_)) => {
        val (firstProducts, otherProducts) = basketProduct.partition(p => offer.eligibleProducts.contains(p.`type`))
         def transformProductQuantity: Seq[BasketProduct] = firstProducts.map { product =>
          product.copy(quantity = product.quantity / 2).calculateTotal
        }

        transformProductQuantity ++ otherProducts
      }
      case Some(offer@ThreeForTwo(_)) => {
        val (firstProducts, otherProducts) = basketProduct.partition(p => offer.eligibleProducts.contains(p.`type`))
        def transformProductQuantity: Seq[BasketProduct] = firstProducts.map { p =>
          val quantity = p.quantity
          p.copy(quantity = (quantity / 3) * 2).calculateTotal
        }

        transformProductQuantity ++ otherProducts

      }
      case None => basketProduct
    }

    Basket(basket).calculateTotal
  }

  private def buildBasketProduct(products: Seq[Product]): Seq[BasketProduct] = {
    products.groupBy(_.name).map { product =>
      val productType = product._1
      val productItemDetails = product._2

      BasketProduct(
        productType,
        productItemDetails.head.price,
        productItemDetails.map(_.price).sum,
        productItemDetails.size
      )
    }.toSeq
  }
}