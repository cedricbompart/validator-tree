package discover;

import org.junit.Assert;
import org.junit.Test;

import discover.action.NegativeAction;
import discover.action.PositiveAction;
import discover.action.TerminalAction;

public class TreeTest {

	@Test
	public void testFirstLeft() {
		final Node root = new Node(new PositiveAction())
				.left(() -> new Node(new TerminalAction(PaymentResponseType.SUCCESS)))
				.right(() -> new Node(new NegativeAction())
						.left(() -> new Node(new TerminalAction(PaymentResponseType.UNKNOWN)))
						.right(() -> new Node(new TerminalAction(PaymentResponseType.FAIL))));

		final PaymentResponseType response = root.process(new PaymentRequest());
		Assert.assertEquals(PaymentResponseType.SUCCESS, response);
	}

	@Test
	public void testSecondRight() {
		final Node root = new Node(new NegativeAction())
				.left(() -> new Node(new TerminalAction(PaymentResponseType.SUCCESS)))
				.right(() -> new Node(new NegativeAction())
						.left(() -> new Node(new TerminalAction(PaymentResponseType.UNKNOWN)))
						.right(() -> new Node(new TerminalAction(PaymentResponseType.FAIL))));

		final PaymentResponseType response = root.process(new PaymentRequest());
		Assert.assertEquals(PaymentResponseType.FAIL, response);
	}

	@Test
	public void testThirdLeft() {
		final Node root = new Node(new NegativeAction())
				.left(() -> new Node(new TerminalAction(PaymentResponseType.SUCCESS)))
				.right(() -> new Node(new PositiveAction())
						.left(() -> new Node(new PositiveAction())
								.left(() -> new Node(new TerminalAction(PaymentResponseType.WARNING))))
						.right(() -> new Node(new TerminalAction(PaymentResponseType.FAIL))));

		final PaymentResponseType response = root.process(new PaymentRequest());
		Assert.assertEquals(PaymentResponseType.WARNING, response);
	}

	@Test
	public void testTerminalRoot() {
		final Node root = new Node(new TerminalAction(PaymentResponseType.WHATEVER));

		final PaymentResponseType response = root.process(new PaymentRequest());
		Assert.assertEquals(PaymentResponseType.WHATEVER, response);
	}
}
