package util

  abstract class GraphBase[T, U] {
    case class Edge(n1: Node, n2: Node, value: U) {
      def toTuple = (n1.value, n2.value, value)
    }
    case class Node(value: T) {
      var adj: List[Edge] = Nil
      // neighbors are all nodes adjacent to this node.
      def neighbors: List[Node] = adj.map(edgeTarget(_, this).get)
    }

    var nodes: Map[T, Node] = Map()
    var edges: List[Edge] = Nil
    
    // If the edge E connects N to another node, returns the other node,
    // otherwise returns None.
    def edgeTarget(e: Edge, n: Node): Option[Node]

    override def equals(o: Any) = o match {
      case g: GraphBase[_,_] => (nodes.keys.toList -- g.nodes.keys.toList == Nil &&
                                 edges.map(_.toTuple) -- g.edges.map(_.toTuple) == Nil)
      case _ => false
    }
    def addNode(value: T) = {
      val n = new Node(value)
      nodes = Map((value -> n)) ++ nodes
      n
    }
//    def isIsomorphicTo[R,S](o: GraphBase[R,S]): Boolean = {
//	    // Build a lazy list so we only have to evaluate as much as necessary.
//	    def listMappings(tNodes: List[Node], oNodes: List[o.Node]) =
//	      tNodes.projection.flatMap(tn => oNodes.projection.map((tn, _)))
//	    // Used on partially-filled isomorphisms to weed out some early.
//	    def isValidMapping(iso: Map[Node,o.Node]): Boolean = 
//	      nodes.values forall {tn =>
//	        (!iso.contains(tn) ||
//	         tn.neighbors.filter(iso.contains).forall(tnn => iso(tn).neighbors.contains(iso(tnn))))
//	      }
//	    def isValidCompleteMapping(iso: Map[Node,o.Node]): Boolean = 
//	      nodes.values forall {tn =>
//	        Set(tn.neighbors.map(iso.apply): _*) == Set(iso(tn).neighbors: _*)
//	      }
//	    def isIsomorphicToR(tNodes: List[Node], oNodes: List[o.Node], iso: Map[Node,o.Node]): Boolean =
//	      if (tNodes == Nil) isValidCompleteMapping(iso)
//	      else listMappings(tNodes, oNodes).filter(p => isValidMapping(iso + p)) exists {p =>
//	        isIsomorphicToR(tNodes - p._1, oNodes - p._2, iso + p)
//	      }
//	    isIsomorphicToR(nodes.values.toList, o.nodes.values.toList, Map())
//  }
  }

  class Graph[T, U] extends GraphBase[T, U] {
    override def equals(o: Any) = o match {
      case g: Graph[_,_] => super.equals(g)
      case _ => false
    }

    def edgeTarget(e: Edge, n: Node): Option[Node] =
      if (e.n1 == n) Some(e.n2)
      else if (e.n2 == n) Some(e.n1)
      else None

    def addEdge(n1: T, n2: T, value: U) = {
      val e = new Edge(nodes(n1), nodes(n2), value)
      edges = e :: edges
      nodes(n1).adj = e :: nodes(n1).adj
      nodes(n2).adj = e :: nodes(n2).adj
    }   
  }
