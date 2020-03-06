package discover;

import java.util.Optional;
import java.util.function.Supplier;

import discover.action.Action;

public class Node {
	private final Action action;
	private Optional<Node> leftNode;
	private Optional<Node> rightNode;

	public Node(final Action action) {
		this.action = action;
	}

	public Node left(final Supplier<Node> child) {
		leftNode = Optional.of(child.get());
		return this;
	}

	public Node right(final Supplier<Node> child) {
		rightNode = Optional.of(child.get());
		return this;
	}

	public PaymentResponseType process(final PaymentRequest request) {
		return action.getResponse().orElseGet(() -> action.test(request) ? getChild(leftNode).process(request)
				: getChild(rightNode).process(request));
	}

	private Node getChild(final Optional<Node> child) {
		return child.orElseThrow(() -> new IllegalArgumentException("child not found"));
	}
}
