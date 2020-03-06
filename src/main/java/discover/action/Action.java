package discover.action;

import java.util.Optional;
import java.util.function.Predicate;

import discover.PaymentRequest;
import discover.PaymentResponseType;

public interface Action extends Predicate<PaymentRequest> {
	default Optional<PaymentResponseType> getResponse() {
		return Optional.empty();
	}
}
