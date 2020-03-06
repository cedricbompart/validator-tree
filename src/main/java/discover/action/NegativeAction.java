package discover.action;

import discover.PaymentRequest;

public class NegativeAction implements Action {

	@Override
	public boolean test(final PaymentRequest request) {
		return false;
	}

}
