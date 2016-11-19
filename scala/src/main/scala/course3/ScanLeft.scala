package course3

/**
  * Created by vishnu on 11/18/16.
  */
class ScanLeft {

  def scanLeft[A](inp: Array[A], a0: A, f:(A,A) => A, out: Array[A]): Unit = {
    def helper(idx: Int, b: A): Unit = {
      if (idx < inp.length) {
        out(idx + 1) = f(b, inp(idx))
        helper(idx + 1, out(idx + 1))
      }
    }
    helper(0, a0)
  }

  def reduceSeg[A](inp: Array[A], left: Int, right: Int, a0: A, f: (A,A) => A): A = {
    var idx = left
    var b0 = a0
    while(idx < right) {
      b0 = f(inp(idx), b0)
      idx += 1
    }
    b0
  }

  def mapSeg[A, B](inp: Array[A], left: Int, right: Int, fi: (Int, A) => B, out: Array[B]): Unit = {
    for (i <- Range(left, right)) {
      out(i) = fi(i, inp(i))
    }
  }

  def scanLeftUsingMapReduceSeg[A](inp: Array[A], a0: A, f:(A,A) => A, out:Array[A]): Unit = {
    val fi = { (i: Int, v: A) => reduceSeg(inp, 0, i, a0, f)}
    mapSeg(inp, 0, inp.length, fi, out)
    val last = inp.length - 1
    out(last + 1) = f(out(last), inp(last))
  }


  sealed abstract class Tree[A]
  case class Leaf[A](a: A) extends Tree[A]
  case class Node[A](l: Tree[A], r: Tree[A]) extends Tree[A]

  sealed abstract class TreeRes[A] { val res: A}
  case class LeafRes[A](override val res: A) extends TreeRes[A]
  case class NodeRes[A](l: TreeRes[A], override val res: A, r: TreeRes[A]) extends TreeRes[A]

  def reduceRes[A](inp: Tree[A], f:(A, A) => A): TreeRes[A] = {
    inp match {
      case Leaf(a) => LeafRes(a)
      case Node(l: Tree[A], r: Tree[A]) => {
        val tLeft = reduceRes(l, f)
        val tRight = reduceRes(r, f)
        NodeRes(tLeft,f(tLeft.res, tRight.res), tRight)
      }
    }
  }

  def upsweep[A](inp: Tree[A], f:(A, A) => A): TreeRes[A] = {
    inp match {
      case Leaf(a) => LeafRes(a)
      case Node(l: Tree[A], r: Tree[A]) => {
        val(tLeft,tRight) = parallel(reduceRes(l, f), reduceRes(r, f))
        NodeRes(tLeft,f(tLeft.res, tRight.res), tRight)
      }
    }
  }

  def downsweep[A](t: TreeRes[A], a0: A, f:(A, A) => A): Tree[A] = t match {
    case LeafRes(v) => Leaf(f(a0, v))
    case NodeRes(l, _, r) =>{
      val (ltree, rtree) = parallel(downsweep[A](l, a0, f), downsweep(l, f(a0, l.res), f))
      Node(ltree, rtree)
    }
  }

  def scanLeft[A](t: Tree[A], a0: A, f: (A, A) => A): Tree[A] = {
    val tRes = upsweep(t, f)
    val t2 = downsweep(tRes, a0, f)
    prepend(a0, t2)
  }

  def prepend[A](a: A, t: Tree[A]): Tree[A] = {
    t match {
      case Leaf(x) => Node(Leaf(a), Leaf(x))
      case Node(l,r) => Node(prepend(a, l), r)
    }
  }

  sealed abstract class TreeResA[A] { val res: A}
  case class LeafResA[A](from: Int, to: Int, override val res: A) extends TreeResA[A]
  case class NodeResA[A](l: TreeResA[A], override val res: A, r: TreeResA[A]) extends TreeResA[A]

  def upsweep[A](inp: Array[A], from: Int, to: Int, f:(A, A) => A, threshold: Int): TreeResA[A] = {
    if (to - from < threshold)
      LeafResA(from, to, reduceSeg(inp, from, to, inp(from), f))
    else {
      val mid = from + (to - from)/2
      val (tl, tr) = parallel(upsweep(inp, from, mid, f, threshold), upsweep(inp, mid, to, f, threshold))
      NodeResA(tl, f(tl.res, tr.res), tr)
    }
  }

  def downsweep[A](inp: Array[A], a0: A, f:(A, A) => A, t: TreeResA[A], out: Array[A]): Unit = t match {
    case LeafResA(from, to, res) => scanLeftSeg(inp, from, to, res, f, out)
    case NodeResA(l, _, r) =>{
      val (_, _) = parallel(downsweep[A](inp, a0, f, l, out), downsweep(inp, f(a0, l.res), f, r, out))
    }
  }

  def scanLeftSeg[A](inp: Array[A], from: Int, to: Int, res: A, f:(A, A) => A, out: Array[A]): A = {
    def helper(idx: Int, b: A): Unit = {
      if (idx < to) {
        out(idx + 1) = f(b, inp(idx))
        helper(idx + 1, out(idx + 1))
      }
    }
    helper(from, res)
  }

  def scanLeft[A](inp: Array[A], a0: A, f: (A, A) => A, out: Array[A]): Unit = {
    val t = upsweep(inp, 0, inp.length, f, 10)
    downsweep(inp, a0, f, t, out)
    out(0) = a0
  }


}
