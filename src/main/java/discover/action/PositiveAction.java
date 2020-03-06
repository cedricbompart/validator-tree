package discover.action;

import discover.PaymentRequest;

public class PositiveAction implements Action {

	@Override
	public boolean test(final PaymentRequest request) {
		return true;
	}

}
