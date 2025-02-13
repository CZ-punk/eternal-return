package demo.eternalreturn.infrastructure.proxy.service.l10n;

import reactor.core.publisher.Mono;

public interface L10NService {
    Mono<?> callL10n(String language);
}
