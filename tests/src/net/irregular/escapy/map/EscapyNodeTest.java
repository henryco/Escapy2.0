package net.irregular.escapy.map;

import net.irregular.escapy.map.node.EscapyNode;
import net.irregular.escapy.map.node.IEscapyNode;
import org.junit.Assert;
import org.junit.Test;

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

		Assert.assertEquals(root.removeNode("child1 : child3 : b").getId(), "b");
		root.removeNode("child1:child3");
		Assert.assertNull(root.getNode("child1:child3:b"));
		Assert.assertNull(child1.getNode("child3:b:d"));
		Assert.assertNull(root.getNode("child1:child3"));
		Assert.assertNull(child1.getNodeDirectly("child3"));
	}


}