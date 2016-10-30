package course2.week3.gatesimulation

abstract class Gates extends Simulation{
  
  def InverterDelay: Int
  def AndGateDelay: Int
  def OrGateDelay: Int
  
  
  class Wire {
    
    private var sigVal = false
    private var actions: List[Action] = List()
    
    def getSignal = sigVal
    
    def setSignal(s: Boolean) = {
      if (s != sigVal) {
        sigVal = s
        actions foreach (_())
      }
    }
    
    def addAction(a: Action) {
      actions = a :: actions
      a()
    }
  }
    
    def inverter(input: Wire, output: Wire): Unit = {
      def invertAction(): Unit = {
        val inputSig = input.getSignal
        afterDelay(InverterDelay) {
          output setSignal !inputSig
        }
      }
      input addAction invertAction
    }
    
    def andGate(input1: Wire, input2: Wire, output: Wire): Unit = {
      def andAction(): Unit = {
        val inputSig1 = input1.getSignal
        val inputSig2 = input2.getSignal
        afterDelay(AndGateDelay) {
          output setSignal inputSig1 & inputSig2
        }
      }
      
      input1 addAction andAction
      input2 addAction andAction
    }
    
    def orGate(input1: Wire, input2: Wire, output: Wire): Unit = {
      def orAction(): Unit = {
        val inputSig1 = input1.getSignal
        val inputSig2 = input2.getSignal
        afterDelay(AndGateDelay) {
          output setSignal inputSig1 | inputSig2
        }
      }
      
      input1 addAction orAction
      input2 addAction orAction
    }
    
    def probe(name: String, wire: Wire) {
      def probAction(): Unit = {
        println(s"$name $currentTime new-value $wire.getSignal")
      }
      
      wire addAction probAction
    } 
}