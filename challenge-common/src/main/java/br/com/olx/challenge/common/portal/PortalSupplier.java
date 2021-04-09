package br.com.olx.challenge.common.portal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PortalSupplier {

    private final Map<String, AbstractPortal> portals;

    public AbstractPortal supplyPortal(String portal) {
        return portals.entrySet().stream()
            .filter(authorizer -> authorizer.getValue().getPortal().equals(portal))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Portal of name %s not found", portal)))
            .getValue();
    }

}