package lookout

import scala.collection.mutable.ListBuffer

case class Bid(name:String, lowBid:Double, hiBid:Double, increment:Double){
  require(name.length()>0 && lowBid > 0 && hiBid > 0 && increment > 0 && lowBid < hiBid)
}

class Auction(itemName:String) {
  private var currentWinner : Bid = null
  private var currentWinningAmount : Double = 0
  private var records : ListBuffer[Bid] = new ListBuffer[Bid]()
  private val auctionItemName:String = itemName

  // addBid: accepts a bid object, keeps the record, and determines the possible new winner of the auction
  def addBid(bid: Bid):Unit = {
    // record this bid for further logging/analysis, if needed
    records += bid
    if (currentWinner == null) {
      currentWinner = bid
      currentWinningAmount = bid.lowBid
    } else battle(bid)
  }

  // battle: find out who is going to be the new winner between new bidder and current winner
  def battle(bid: Bid):Unit = {
    // only need to update winner if new bidder maximum bid is possible to exceed current winning amount
    if (currentWinningAmount < bid.hiBid){
      // calculate how far current winner can really go
      var i = 0
      while (currentWinningAmount + i*currentWinner.increment <= currentWinner.hiBid) {i=i+1}
      val actualMaxWinnerAmount = currentWinningAmount + (i-1)*currentWinner.increment
      // if max winner can bid is more than new bidder can go at most, then current winner remains
      // otherwise, winner will be the new bidder
      if (actualMaxWinnerAmount >= bid.hiBid) currentWinningAmount = actualMaxWinnerAmount
      else{
        currentWinner = bid
        currentWinningAmount = actualMaxWinnerAmount+1
      }
    }
  }

  // getRecords: return the recorded bids
  def getRecords():List[Bid]={
    records.toList
  }

  // getWinner: return a tuple of (winning bid, winning amount)
  def getWinner():(String, Double)={
    (currentWinner.name, currentWinningAmount)
  }

  def getItemName():String = {
    auctionItemName
  }
}
