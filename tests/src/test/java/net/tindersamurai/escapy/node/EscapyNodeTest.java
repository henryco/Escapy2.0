package net.tindersamurai.escapy.node;

import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class EscapyNodeTest {

	@Test
	public void nodeCommonTest() {

		IEscapyNode root = new EscapyNode("Some root content", "root");

		IEscapyNode child1 = new EscapyNode("Some child 1", "child1");
		child1.addNode(new EscapyNode("CHILD 3", "child3").addNode(new EscapyNode("sub1", "b").addNode(new EscapyNode("sub3", "d"))));
		child1.addNode(new EscapyNode("CHILD 4", "child4").addNode(new EscapyNode("sub2", "c")));

		root.addNode(child1);
		root.addNode(new EscapyNode("Some child 2", "a"));
		root.addNode(new EscapyNode("WTF", "wtf-w"));

		Assert.assertEquals(child1.getNode("child3:b : d"), root.getNode("child1 : child3 : b : d"));

		Assert.assertEquals(root.removeNode("child1:child4:c").getId(), "c");
		root.removeNode("child1:child4:c");
		Assert.assertNull(root.removeNode("child1:child4:c"));

		Assert.assertEquals(root.getNode("child1 : child3 : b").getId(), "b");
		Assert.assertEquals(child1.getNode("child3 : b:d").getId(), "d");
		root.removeNode("child1");
		Assert.assertNull(root.getNode("child1:child3:b"));
		Assert.assertNull(child1.getNode("child3:b:d"));
		Assert.assertNull(root.getNode("child1:child3"));
	}

	@Test
	public void foreachTest() {

		IEscapyNode root = new EscapyNode("Some root content", "root");

		IEscapyNode child1 = new EscapyNode("Some child 1", "child1");
		child1.addNode(new EscapyNode("CHILD 3", "child3").addNode(new EscapyNode("sub1", "b").addNode(new EscapyNode("sub3", "d"))));
		child1.addNode(new EscapyNode("CHILD 4", "child4").addNode(new EscapyNode("sub2", "c")));

		root.addNode(child1);
		root.addNode(new EscapyNode("Some child 2", "a"));
		root.addNode(new EscapyNode("WTF", "wtf-w"));

		System.out.println(root.treeView("--- ") + "\n");
	}


}