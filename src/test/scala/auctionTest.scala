import org.scalatest.FunSuite
import lookout.Bid
import lookout.Auction

class auctionTest extends FunSuite {
  test("BasicBidTest.cube") {
      // 1: a normal looking bid
      val bid = Bid("Yuhang", 50.0, 80.0, 3.0)
      assert(bid.name == "Yuhang")
      assert(bid.lowBid == 50.0)
      assert(bid.hiBid == 80.0)
      assert(bid.increment == 3.0)
      // 2: a bad bid
      assertThrows[IllegalArgumentException]{Bid("",1.0,2.0,3.0)}
      // 3: another bad bid
      assertThrows[IllegalArgumentException]{Bid("Bad 2", 3.0, 2.0, 1.0)}
  }

  test("BasicAuctionTest.cube") {
    val aucEngine = new Auction("Basketball")
    aucEngine.addBid(Bid("Kobe", 50,80,3))
    assert(aucEngine.getRecords() == List(Bid("Kobe",50.0,80.0,3.0)))
    assert(aucEngine.getWinner() == ("Kobe", 50))
  }

  test("AuctionBicycleTest.cube") {
    val aucEngine = new Auction("Bicycle")
    aucEngine.addBid(Bid("Alice", 50.0, 80.0, 3.0))
    assert(aucEngine.getWinner() == ("Alice", 50.0))
    aucEngine.addBid(Bid("Aaron", 60.0, 82.0, 2.0))
    assert(aucEngine.getWinner() == ("Aaron", 81.0))
    aucEngine.addBid(Bid("Amanda", 55.0, 85.0, 5.0))
    assert(aucEngine.getWinner() == ("Amanda", 82.0))
  }

  test("AuctionScooterTest.cube") {
    val aucEngine = new Auction("Scooter")
    aucEngine.addBid(Bid("Alice", 700.0, 725.0, 2.0))
    assert(aucEngine.getWinner() == ("Alice", 700.0))
    aucEngine.addBid(Bid("Aaron", 599.0, 725.0, 15.0))
    assert(aucEngine.getWinner() == ("Aaron", 725.0))
    aucEngine.addBid(Bid("Amanda", 625.0, 725.0, 8.0))
    assert(aucEngine.getWinner() == ("Aaron", 725.0))
  }

  test("AuctionBoatTest.cube") {
    val aucEngine = new Auction("Boat")
    aucEngine.addBid(Bid("Alice", 2500, 3000, 500))
    assert(aucEngine.getWinner() == ("Alice", 2500.0))
    aucEngine.addBid(Bid("Aaron", 2800, 3100, 201))
    assert(aucEngine.getWinner() == ("Aaron", 3001.0))
    aucEngine.addBid(Bid("Amanda", 2501, 3200, 247))
    assert(aucEngine.getWinner() == ("Amanda", 3002.0))
  }
}
