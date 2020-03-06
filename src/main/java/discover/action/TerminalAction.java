package discover.action;

import java.util.Optional;

import discover.PaymentRequest;
import discover.PaymentResponseType;

public class TerminalAction implements Action {

	private final PaymentResponseType response;

	public TerminalAction(final PaymentResponseType response) {
		this.response = response;
	}

	@Override
	public Optional<PaymentResponseType> getResponse() {
		return Optional.of(response);
	}

	@Override
	public boolean test(final PaymentRequest request) {
		throw new IllegalStateException();
	}

}
